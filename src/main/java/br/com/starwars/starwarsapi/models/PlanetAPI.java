package br.com.starwars.starwarsapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import org.springframework.data.annotation.Id;

/**
 *
 * @author RODRIGO BENTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetAPI implements Serializable {
    
    private static final long serialVersionUID = 1l;
    
    @Id
    private String id;
    private String climate;
    private LocalDate created;
    private String diameter;
    private LocalDate edited;
    private String[] films;
    private String gravity;
    private String name;
    private String orbital_period;
    private String population;
    private String surface_water;
    private String terrain;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public String getDiameter() {
        return diameter;
    }

    public void setDiameter(String diameter) {
        this.diameter = diameter;
    }

    public LocalDate getEdited() {
        return edited;
    }

    public void setEdited(LocalDate edited) {
        this.edited = edited;
    }

    public String[] getFilms() {
        return films;
    }

    public void setFilms(String[] films) {
        this.films = films;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrbital_period() {
        return orbital_period;
    }

    public void setOrbital_period(String orbital_period) {
        this.orbital_period = orbital_period;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getSurface_water() {
        return surface_water;
    }

    public void setSurface_water(String surface_water) {
        this.surface_water = surface_water;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.climate);
        hash = 79 * hash + Objects.hashCode(this.created);
        hash = 79 * hash + Objects.hashCode(this.diameter);
        hash = 79 * hash + Objects.hashCode(this.edited);
        hash = 79 * hash + Arrays.deepHashCode(this.films);
        hash = 79 * hash + Objects.hashCode(this.gravity);
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + Objects.hashCode(this.orbital_period);
        hash = 79 * hash + Objects.hashCode(this.population);
        hash = 79 * hash + Objects.hashCode(this.surface_water);
        hash = 79 * hash + Objects.hashCode(this.terrain);
        hash = 79 * hash + Objects.hashCode(this.url);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlanetAPI other = (PlanetAPI) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.climate, other.climate)) {
            return false;
        }
        if (!Objects.equals(this.diameter, other.diameter)) {
            return false;
        }
        if (!Objects.equals(this.gravity, other.gravity)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.orbital_period, other.orbital_period)) {
            return false;
        }
        if (!Objects.equals(this.population, other.population)) {
            return false;
        }
        if (!Objects.equals(this.surface_water, other.surface_water)) {
            return false;
        }
        if (!Objects.equals(this.terrain, other.terrain)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.created, other.created)) {
            return false;
        }
        if (!Objects.equals(this.edited, other.edited)) {
            return false;
        }
        if (!Arrays.deepEquals(this.films, other.films)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PlanetDTO{" + "id=" + id + ", climate=" 
                + climate + ", created=" + created + ", diameter=" 
                + diameter + ", edited=" + edited + ", films=" 
                + films + ", gravity=" + gravity + ", name=" 
                + name + ", orbital_period=" + orbital_period + ", population=" 
                + population + ", surface_water=" + surface_water + ", terrain=" 
                + terrain + ", url=" + url + '}';
    }
    
}
