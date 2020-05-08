package no.message.repository;

import no.message.model.Bruker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrukerRepository extends JpaRepository<Bruker, Long> {

    Bruker findByName(String name);

}
