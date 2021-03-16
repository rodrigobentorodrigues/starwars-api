package br.com.starwars.starwarsapi.services;

import br.com.starwars.starwarsapi.models.Planet;
import java.util.List;

/**
 *
 * @author RODRIGO BENTO
 */
public interface PlanetService {
    
    public void add(Planet planet);
    public void delete(Planet planet);
    public List<Planet> listAll();
    public Planet getByName(String name);
    public Planet getByID(String id);
    
}
