package model;

import java.util.HashMap;

public class SolarSystem extends Body{
    private CentralBody centralBody;
    private HashMap<String, Planet> planets;  // planet list, key is planet name
    private int planetCount;
    private String planetName;

    // MODIFIES: this
    // EFFECTS: construct a solar system with a given central body and no planets
    public SolarSystem(String name, CentralBody centralBody){
        this.centralBody = centralBody;
        planets = new HashMap<>();
        planetCount = 0;
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: adds a planet to solar system, adds 1 to count
    public void addPlanet(Planet planet){
        planetName = planet.getName();
        planets.put(planetName, planet);
        planetCount++;
    }

    // REQUIRES: planet must be in solar system
    // MODIFIES: this
    // EFFECTS: removes a planet in the solar system based on its key (name)
    //          removes 1 from count
    public void removePlanet(String planetName){
        planets.remove(planetName);
        planetCount--;
    }

    // REQUIRES: planet must be in solar system
    // EFFECTS: get planet based on its key (name)
    public Planet getPlanet(String planetName){
        return planets.get(planetName);
    }

    // EFFECTS: gets all planets in solar system
    public HashMap<String, Planet> getPlanets(){
        return planets;
    }

    // EFFECTS: get central body
    public CentralBody getCentralBody(){
        return centralBody;
    }

    public int getPlanetCount(){ return planetCount; }
}
