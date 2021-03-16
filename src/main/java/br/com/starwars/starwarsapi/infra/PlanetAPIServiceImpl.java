package br.com.starwars.starwarsapi.infra;

import br.com.starwars.starwarsapi.models.PlanetAPI;
import br.com.starwars.starwarsapi.models.ResultAPI;
import br.com.starwars.starwarsapi.repositories.PlanetAPIRepository;
import br.com.starwars.starwarsapi.services.PlanetAPIService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author RODRIGO BENTO
 */
@Service
public class PlanetAPIServiceImpl implements PlanetAPIService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PlanetAPIRepository planetAPIRepository;

    @Override
    public int numberOfFilmsByPlanet(String name) {
        int numberOfFilms = 0;
        Optional<PlanetAPI> result = planetAPIRepository.findByName(name);
        if (result.isPresent()){
            PlanetAPI planet = result.get();
            numberOfFilms = planet.getFilms() != null ? planet.getFilms().length : 0;
        }
        return numberOfFilms;
    }

    @Override
    public void insert(List<PlanetAPI> planets) {
        planetAPIRepository.insert(planets);
    }

    @Override
    public List<PlanetAPI> listAll() {
        return planetAPIRepository.findAll();
    }

    @Override
    public List<PlanetAPI> receiveData() {
        List<PlanetAPI> planets = new ArrayList<PlanetAPI>();
        final String url = "https://swapi.dev/api/planets/";
        ResponseEntity<ResultAPI> responseEntity = restTemplate.getForEntity(url, ResultAPI.class);
        while (responseEntity.getStatusCode() == HttpStatus.OK) {
            ResultAPI result = responseEntity.getBody();
            String urlNext = null;
            if (result != null) {
                urlNext = result.getNext();
                planets.addAll(result.getPlanets());
            }

            if (urlNext != null) {
                urlNext = urlNext.replace("http", "https");
                responseEntity = restTemplate.getForEntity(urlNext, ResultAPI.class);
            } else {
                break;
            }
        }
        return planets;
    }

}
