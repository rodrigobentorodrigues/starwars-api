package br.com.starwars.starwarsapi.services;

import br.com.starwars.starwarsapi.models.PlanetAPI;
import java.util.List;

/**
 *
 * @author RODRIGO BENTO
 */
public interface PlanetAPIService {
    
    public int numberOfFilmsByPlanet(String planet);
    public void insert(List<PlanetAPI> planets);
    public List<PlanetAPI> listAll();
    public List<PlanetAPI> receiveData();
    
}
