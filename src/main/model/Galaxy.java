package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Galaxy {
    private HashMap<String,SolarSystem> solarSystems;

    public void Galaxy(){
        // stub
    }

    // MODIFIES: this
    // EFFECT: adds a solar system to the galaxy
    public void addSolarSystem(CentralBody centralbody, ArrayList<Planet> planets){
        // stub
    }

    public SolarSystem getSolarSystem(String solarSystemName){
        //  stub
        SolarSystem solarSystem = new SolarSystem();
        return solarSystem;
    }


    public HashMap<String, SolarSystem> getSolarSystems(){
        return solarSystems;
    }
}
