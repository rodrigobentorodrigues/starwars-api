package br.com.starwars.starwarsapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author RODRIGO BENTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultAPI implements Serializable {
    
    private static final long serialVersionUID = 1l;
    
    private int count;
    private String next;
    private String previous;
    @JsonProperty("results")
    private List<PlanetAPI> planets;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<PlanetAPI> getPlanets() {
        return planets;
    }

    public void setPlanets(List<PlanetAPI> planets) {
        this.planets = planets;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.count;
        hash = 67 * hash + Objects.hashCode(this.next);
        hash = 67 * hash + Objects.hashCode(this.previous);
        hash = 67 * hash + Objects.hashCode(this.planets);
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
        final ResultAPI other = (ResultAPI) obj;
        if (this.count != other.count) {
            return false;
        }
        if (!Objects.equals(this.next, other.next)) {
            return false;
        }
        if (!Objects.equals(this.previous, other.previous)) {
            return false;
        }
        if (!Objects.equals(this.planets, other.planets)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResultDTO{" + "count=" + count + ", next=" 
                + next + ", previous=" + previous + ", planets=" 
                + planets + '}';
    }
    
}
