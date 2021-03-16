package br.com.starwars.starwarsapi.infra;

import br.com.starwars.starwarsapi.models.PlanetAPI;
import br.com.starwars.starwarsapi.services.PlanetAPIService;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author RODRIGO BENTO
 */
@Component
public class DatabaseSeeder implements CommandLineRunner {
    
    @Autowired
    private PlanetAPIService planetAPIService;
    
    @Autowired
    private Logger logger;
    
    @Override
    public void run(String... args) throws Exception {
        try {
            List<PlanetAPI> planets = planetAPIService.listAll();
            if (planets.isEmpty()){
                planets = planetAPIService.receiveData();
                planetAPIService.insert(planets);
                // logger.info("Conclude insert values");
                System.out.println("Conclude insert values");
            }
        } catch (Exception ex){
            System.out.println("Problem to syncronous values to database");
        }
    }
    
}
