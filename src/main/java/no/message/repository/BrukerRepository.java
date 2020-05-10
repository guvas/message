package no.message.repository;

import no.message.model.Bruker;
import org.springframework.data.repository.CrudRepository;

public interface BrukerRepository extends CrudRepository<Bruker, Long> {

    Bruker findByName(String name);

}
