package no.message;


import no.message.model.Bruker;
import no.message.model.Meldinger;
import no.message.repository.BrukerRepository;
import no.message.repository.MeldingRepository;
import nz.net.ultraq.thymeleaf.LayoutDialect;
import nz.net.ultraq.thymeleaf.decorators.strategies.AppendingStrategy;
import nz.net.ultraq.thymeleaf.decorators.strategies.GroupingStrategy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring5.SpringTemplateEngine;

@SpringBootApplication(scanBasePackages = {"no.message"})
public class Application  extends WebMvcConfigurerAdapter {



    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.addDialect(new LayoutDialect());
        engine.addDialect(new LayoutDialect(new GroupingStrategy()));
        engine.addDialect(new LayoutDialect(new AppendingStrategy()));
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/*");
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