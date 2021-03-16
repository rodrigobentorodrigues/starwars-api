package br.com.starwars.starwarsapi.infra;

import br.com.starwars.starwarsapi.models.Planet;
import br.com.starwars.starwarsapi.repositories.PlanetRepository;
import br.com.starwars.starwarsapi.services.PlanetService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author RODRIGO BENTO
 */
@Service
public class PlanetServiceImpl implements PlanetService {

    @Autowired
    private PlanetRepository planetRepository;
    
    @Override
    public void add(Planet planet) {
        planetRepository.insert(planet);
    }

    @Override
    public void delete(Planet planet) {
        planetRepository.delete(planet);
    }

    @Override
    public List<Planet> listAll() {
        return planetRepository.findAll();
    }

    @Override
    public Planet getByName(String name) {
        Optional<Planet> planet = planetRepository.findByName(name);
        return planet.orElse(null);
    }

    @Override
    public Planet getByID(String id) {
        return planetRepository.findById(id).orElse(null);
    }
    
}
