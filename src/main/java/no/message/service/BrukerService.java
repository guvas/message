package no.message.service;

import no.message.model.Bruker;
import no.message.repository.BrukerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrukerService {
    private BrukerRepository brukerRepository;

    @Autowired
    public void setBrukerRepository(BrukerRepository brukerRepository) {
        this.brukerRepository = brukerRepository;
    }

    public Bruker findByName(String name){
        return brukerRepository.findByName(name);
    }

    public Bruker  createBruker(Bruker bruker){
        return brukerRepository.save(bruker);
    }

    public Bruker hentBruker(String brukernavn){
        Bruker bruker = null;

        try{
            bruker = findByName(brukernavn);
        }catch (NullPointerException e){
            System.out.println(brukernavn + " finnes ikke i db");
        }

        return bruker;
    }
}
