package br.com.starwars.starwarsapi.repositories;

import br.com.starwars.starwarsapi.models.Planet;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author RODRIGO BENTO
 */
@Repository
public interface PlanetRepository extends MongoRepository<Planet, String> {
    
    Optional<Planet> findByName(String name);
    
}
