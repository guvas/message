package no.message.repository;

import no.message.model.Bruker;
import no.message.model.Meldinger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeldingRepository extends JpaRepository<Meldinger, Long> {
    List<Meldinger> findAllByTilbruker(Bruker bruker);
}
