package br.com.starwars.starwarsapi.repositories;

import br.com.starwars.starwarsapi.models.PlanetAPI;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author RODRIGO BENTO
 */
@Repository
public interface PlanetAPIRepository extends MongoRepository<PlanetAPI, String>{
    
    Optional<PlanetAPI> findByName(String name);
    
}
