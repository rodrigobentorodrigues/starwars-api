package br.com.starwars.starwarsapi.controllers;

import br.com.starwars.starwarsapi.models.Planet;
import br.com.starwars.starwarsapi.services.PlanetAPIService;
import br.com.starwars.starwarsapi.services.PlanetService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author RODRIGO BENTO
 */
@RestController
@RequestMapping("api/planets")
public class PlanetResource {

    @Autowired
    private PlanetService planetService;

    @Autowired
    private PlanetAPIService planetAPIService;

    @GetMapping
    public ResponseEntity<List<Planet>> get() {
        return new ResponseEntity<List<Planet>>(planetService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planet> getByID(@PathVariable("id") String id) {
        Planet planetByID = planetService.getByID(id);
        if (planetByID != null) {
            return new ResponseEntity(planetByID, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Planet> getByName(@PathVariable("name") String name) {
        Planet planetByID = planetService.getByName(name);
        if (planetByID != null) {
            return new ResponseEntity(planetByID, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Planet> post(@Valid @RequestBody Planet planet) {
        int numberOfOcurrencesInFilms = planetAPIService.numberOfFilmsByPlanet(planet.getName());
        planet.setNumberOfFilmsAppearances(numberOfOcurrencesInFilms);
        planetService.add(planet);
        return new ResponseEntity<Planet>(planet, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        Planet planet = planetService.getByID(id);
        if (planet != null) {
            planetService.delete(planet);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
