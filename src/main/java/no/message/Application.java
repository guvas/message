package no.message;


import no.message.model.Bruker;
import no.message.model.Meldinger;
import no.message.repository.BrukerRepository;
import no.message.repository.MeldingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = {"no.message"})
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public CommandLineRunner brukerInit(BrukerRepository repository) {
        return (args) -> {
            repository.save(new Bruker(1L, "Guvanch"));
            repository.save(new Bruker(2L, "Test"));
        };
    }

    @Bean
    public CommandLineRunner meldingInit(MeldingRepository repository) {
        return (args) -> {
            repository.save(new Meldinger(1L, "En tekst fra Guvanch", new Bruker(1L, "Guvanch"), new Bruker(2L, "test")));
            repository.save(new Meldinger(2L, "En tekst fra Test", new Bruker(2L, "test"), new Bruker(1L, "Guvanch")));
        };
    }
}