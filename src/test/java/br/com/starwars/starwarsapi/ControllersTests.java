package br.com.starwars.starwarsapi;

import br.com.starwars.starwarsapi.models.Planet;
import br.com.starwars.starwarsapi.services.PlanetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 *
 * @author RODRIGO BENTO
 */
@SpringBootTest
@AutoConfigureMockMvc
class ControllersTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private PlanetService planetService;
    private final String BASE_URL = "https://localhost:8080/api/planets";

    @Test
    void contextLoads() {
    }

    @Test
    void getAllPlanets() throws Exception {
        List<Planet> planets = new ArrayList<Planet>();
        when(planetService.listAll()).thenReturn(planets);
        mvc.perform(get(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getPlanetWhenIDNotExists() throws Exception {
        when(planetService.getByID("123")).thenReturn(null);
        mvc.perform(get(BASE_URL + "/{id}", "123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getPlanetWhenNameNotExists() throws Exception {
        when(planetService.getByID("nameTest")).thenReturn(null);
        mvc.perform(get(BASE_URL + "/name/{name}", "nameTest")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void postPlanetWhenModelIsInCompliance() throws Exception {
        Planet planet = new Planet("Tatooine", "Summer", "Stony");
        mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(planet))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void postPlanetWhenNameIsNotPresent() throws Exception {
        Planet planet = new Planet();
        planet.setClimate("Winter");
        planet.setTerrain("Flat");
        mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(planet))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Name is mandatory")));
    }

    @Test
    void postPlanetWhenClimateIsNotPresent() throws Exception {
        Planet planet = new Planet();
        planet.setName("Tatooine");
        planet.setTerrain("Flat");
        mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(planet))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Climate is mandatory")));
    }

    @Test
    void postPlanetWhenTerrainIsNotPresent() throws Exception {
        Planet planet = new Planet();
        planet.setName("Tatooine");
        planet.setClimate("Winter");
        mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(planet))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Terrain is mandatory")));
    }

    @Test
    void postPlanetWhenNameHasMinLength() throws Exception {
        Planet planet = new Planet();
        planet.setName("T");
        planet.setClimate("Winter");
        planet.setTerrain("Flat");
        mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(planet))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Name must contain between 2 to 50 characters")));
    }
    
    @Test
    void postPlanetWhenNameHasMaxLength() throws Exception {
        Planet planet = new Planet();
        planet.setName("TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
        planet.setClimate("Winter");
        planet.setTerrain("Flat");
        mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(planet))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Name must contain between 2 to 50 characters")));
                
    }
    
    @Test
    void postPlanetWhenClimateHasMinLength() throws Exception {
        Planet planet = new Planet();
        planet.setName("Tatooine");
        planet.setClimate("W");
        planet.setTerrain("Flat");
        mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(planet))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Climate must contain between 2 to 100 characters")));
    }
    
    @Test
    void postPlanetWhenClimateHasMaxLength() throws Exception {
        Planet planet = new Planet();
        planet.setName("Tatooine");
        planet.setClimate("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
        planet.setTerrain("Flat");
        mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(planet))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Climate must contain between 2 to 100 characters")));
    }
    
    @Test
    void postPlanetWhenTerrainHasMinLength() throws Exception {
        Planet planet = new Planet();
        planet.setName("Tatooine");
        planet.setClimate("Winter");
        planet.setTerrain("F");
        mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(planet))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Terrain must contain between 2 to 100 characters")));
    }
    
    @Test
    void postPlanetWhenTerrainHasMaxLength() throws Exception {
        Planet planet = new Planet();
        planet.setName("Tatooine");
        planet.setClimate("Winter");
        planet.setTerrain("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
        mvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(planet))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Terrain must contain between 2 to 100 characters")));
    }
    
    @Test
    void deletePlanetWhenIDNotExists() throws Exception {
        mvc.perform(delete(BASE_URL + "/{id}", "123")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}
