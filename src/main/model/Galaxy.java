package model;

import java.util.HashMap;

public class Galaxy {
    private String name;
    private HashMap<String,SolarSystem> solarSystems;
    private int solarSystemCount;
    private String solarSystemName;

    // EFFECT: construct a galaxy with no solar systems
    public Galaxy(String name){
        this.name = name;
        solarSystemCount = 0;
        solarSystems = new HashMap<>();
    }

    // MODIFIES: this
    // EFFECT: adds a solar system to the galaxy
    public void addSolarSystem(SolarSystem solarSystem){
        solarSystemName = solarSystem.getName();
        solarSystems.put(solarSystemName, solarSystem);
        solarSystemCount = solarSystemCount + 1;
    }

    // REQUIRES: solar system must be in galaxy
    // MODIFIES: this
    // EFFECT: remove a solar system from the galaxy based on name
    public void removeSolarSystem(String solarSystemName){
        solarSystems.remove(solarSystemName);
        solarSystemCount  = solarSystemCount - 1;

    }

    // MODIFIES: this
    // EFFECT: change the galaxies name
    public void changeName(String newName){
        this.name = newName;
    }

    public SolarSystem getSolarSystem(String solarSystemName){
        return solarSystems.get(solarSystemName);
    }


    public HashMap<String, SolarSystem> getSolarSystems(){
        return solarSystems;
    }

    public int getSolarSystemCount(){ return solarSystemCount; }

    public String getName(){ return name; }


}
