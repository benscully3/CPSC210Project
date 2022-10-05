package model;

import java.util.HashMap;

public abstract class CentralBody extends Body{
    protected HashMap<String, Planet> planets;

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
}
