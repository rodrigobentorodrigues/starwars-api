package br.com.starwars.starwarsapi.controllers;

import br.com.starwars.starwarsapi.models.Planet;
import br.com.starwars.starwarsapi.services.PlanetAPIService;
import br.com.starwars.starwarsapi.services.PlanetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get a list of planets")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "List of planets",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Planet.class))})})
    public ResponseEntity<List<Planet>> get() {
        return new ResponseEntity<List<Planet>>(planetService.listAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a planet by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the planet",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Planet.class))}),
        @ApiResponse(responseCode = "404", description = "Planet not found", content = @Content)})
    public ResponseEntity<Planet> getByID(@PathVariable("id") String id) {
        Planet planetByID = planetService.getByID(id);
        if (planetByID != null) {
            return new ResponseEntity(planetByID, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/name/{name}")
    @Operation(summary = "Get a planet by its name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the planet",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Planet.class))}),
        @ApiResponse(responseCode = "404", description = "Planet not found", content = @Content)})
    public ResponseEntity<Planet> getByName(@PathVariable("name") String name) {
        Planet planetByID = planetService.getByName(name);
        if (planetByID != null) {
            return new ResponseEntity(planetByID, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @Operation(summary = "Insert a planet")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Insert the planet",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Planet.class))}),
        @ApiResponse(responseCode = "400", description = "Invalid properties supplied", content = @Content)})
    public ResponseEntity<Planet> post(@Valid @RequestBody Planet planet) {
        int numberOfOcurrencesInFilms = planetAPIService.numberOfFilmsByPlanet(planet.getName());
        planet.setNumberOfFilmsAppearances(numberOfOcurrencesInFilms);
        planetService.add(planet);
        return new ResponseEntity<Planet>(planet, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a planet by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Delete successuful",
                content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Planet.class))}),
        @ApiResponse(responseCode = "404", description = "Planet not found", content = @Content)})
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
