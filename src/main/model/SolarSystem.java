package model;

import java.util.ArrayList;
import java.util.HashMap;

public class SolarSystem {
    private CentralBody centralBody;
    private HashMap<String, Planet> planets;  // planet list, key is planet name

    // MODIFIES: this
    // EFFECTS: construct a solar system with a given central body and planets
    public SolarSystem(CentralBody centralBody, ArrayList<Planet> planetList){
        //stub
    }

    // MODIFIES: this
    // EFFECTS: adds a planet to solar system
    public void addPlanet(Planet planet){
        //stub
    }

    // REQUIRES: planet must be in solar system
    // MODIFIES: this
    // EFFECTS: removes a planet in the solar system based on its key (name)
    public void removePlanet(Planet planet){
        //stub
    }

    // REQUIRES: planet must be in solar system
    // EFFECTS: get planet based on its key (name)
    public Planet getPlanet(String planetName){
        Planet planet = new Planet(30);
        return planet;
    }

    // EFFECTS: gets all planets in solar system
    public HashMap<String, Planet> getPlanets(){
        return planets;
    }

    // EFFECTS: get central body
    public CentralBody getCentralBody(){
        return centralBody;
    }
}
