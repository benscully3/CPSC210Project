package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Galaxy {
    private HashMap<String,SolarSystem> solarSystems;

    public Galaxy(){
        // stub
    }

    // MODIFIES: this
    // EFFECT: adds a solar system to the galaxy
    public void addSolarSystem(CentralBody centralbody, ArrayList<Planet> planets){
        // stub
    }

    public SolarSystem getSolarSystem(String solarSystemName){
        //  stub
        CentralBody centralBody = new BlackHole(5);
        ArrayList<Planet> planets = new ArrayList<>();
        SolarSystem solarSystem = new SolarSystem(centralBody,planets);
        return solarSystem;
    }


    public HashMap<String, SolarSystem> getSolarSystems(){
        return solarSystems;
    }
}
