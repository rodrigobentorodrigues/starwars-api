package br.com.starwars.starwarsapi.models;

import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author RODRIGO BENTO
 */
@Document
public class Planet {

    @Id
    private String id;
    private String name;
    private String climate;
    private String terrain;
    private int numberOfFilmsAppearances;

    public Planet() {
    }

    public Planet(String name, String climate, String terrain) {
        this.name = name;
        this.climate = climate;
        this.terrain = terrain;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public int getNumberOfFilmsAppearances() {
        return numberOfFilmsAppearances;
    }

    public void setNumberOfFilmsAppearances(int numberOfFilmsAppearances) {
        this.numberOfFilmsAppearances = numberOfFilmsAppearances;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.name);
        hash = 73 * hash + Objects.hashCode(this.climate);
        hash = 73 * hash + Objects.hashCode(this.terrain);
        hash = 73 * hash + this.numberOfFilmsAppearances;
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
        final Planet other = (Planet) obj;
        if (this.numberOfFilmsAppearances != other.numberOfFilmsAppearances) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.climate, other.climate)) {
            return false;
        }
        if (!Objects.equals(this.terrain, other.terrain)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Planet{" + "id=" + id + ", name=" 
                + name + ", climate=" + climate + ", terrain=" 
                + terrain + ", numberOfFilmsAppearances=" 
                + numberOfFilmsAppearances + '}';
    }

}
