package no.message.service;

import no.message.model.Bruker;
import no.message.repository.BrukerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrukerService {
    private static Logger logger = LoggerFactory.getLogger(BrukerService.class);
    private BrukerRepository brukerRepository;

    @Autowired
    public void setBrukerRepository(BrukerRepository brukerRepository) {
        this.brukerRepository = brukerRepository;
    }

    public Bruker findByName(String name){
        return brukerRepository.findByName(name);
    }

    public Bruker createBruker(Bruker bruker){
        return brukerRepository.save(bruker);
    }

    public Bruker hentBruker(String brukernavn){
        Bruker bruker = null;
        try{
            bruker = findByName(brukernavn);
        }catch (NullPointerException e){
            logger.info("{} finnes ikke i db" + brukernavn);
        }

        return bruker;
    }
}
