package no.message.service;

import no.message.model.Bruker;
import no.message.model.Meldinger;
import no.message.repository.MeldingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MeldingService {

    private MeldingRepository meldingRepository;

    @Autowired
    public void setMeldingRepository(MeldingRepository meldingRepository) {
        this.meldingRepository = meldingRepository;
    }

    public List<Meldinger> getAlleMineMeldinger(Bruker bruker){
        return meldingRepository.findAllByTilbruker(bruker);
    }

    public Meldinger sendMelding(Meldinger meldinger){
        return meldingRepository.save(meldinger);
    }
}
