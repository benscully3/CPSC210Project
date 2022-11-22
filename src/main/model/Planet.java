package model;

import org.json.JSONObject;
import persistence.Writable;

//  class to represent a planet with orbit size, moon, and type (rocky, or gas)
public class Planet extends Body implements Writable {
    private double orbitSize;
    private boolean moon; // does the planet have one moon
    private boolean rocky; // if false: gas giant
    private static final double RHO_GAS = 0.04; // gas planet density
    private static final double RHO_ROCKY = 0.18; // rocky planet density


    // EFFECT: create a default rocky planet with no moon
    //         with 1 earth radius and one earth orbit
    //         this constructor is mainly for test classes
    public Planet(String name) {
        this.radius = 1;
        this.moon = false;
        this.name = name;
        this.orbitSize = 1;
        this.rocky = true;
        this.mass = calculateMass(this.rocky, 1);

        EventLog.getInstance().logEvent(new Event("Created planet: " + name));
    }

    // REQUIRES: radius and orbitSize must be positive
    // EFFECT: create a gas or rocky planet based on radius with or without a moon
    //         planets with radius < 5 are rocky, otherwise they are gas giants
    public Planet(String name, double radius, double orbitSize, boolean isMoon) {
        this.name = name;
        this.radius = radius;
        this.orbitSize = orbitSize;
        this.moon = isMoon;
        if (this.radius <= 5) {
            this.rocky = true;
            this.mass = calculateMass(true, this.radius);
        } else {
            this.rocky = false;
            this.mass = calculateMass(false, this.radius);
        }

        EventLog.getInstance().logEvent(new Event("Created planet: " + name));
    }

    // REQUIRES: radius and orbitSize must be positive
    // EFFECT: create a gas or rocky planet with all given data, only used when creating planets
    //         from a saved file.
    public Planet(String name, double radius, double mass, double orbitSize, boolean isMoon, boolean isRocky) {
        this.name = name;
        this.radius = radius;
        this.mass = mass;
        this.orbitSize = orbitSize;
        this.moon = isMoon;
        this.rocky = isRocky;

        EventLog.getInstance().logEvent(new Event("Created planet: " + name));
    }


    // REQUIRES: radius must be positive
    // EFFECT: calculates planet mass based off of radius and therefore based
    //         on whether it is rocky or gaseous
    private double calculateMass(boolean isRocky, double radius) {
        double mass;

        if (isRocky) {
            mass = 1.33 * 3.14 * RHO_ROCKY * Math.pow(radius, 3);
        } else {
            mass = 1.33 * 3.14 * RHO_GAS * Math.pow(radius, 3);
        }
        return mass;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("mass", mass);
        json.put("radius", radius);
        json.put("orbitSize", orbitSize);
        json.put("rocky", rocky);
        json.put("moon", moon);


        return json;
    }

    // getters
    public double getOrbitSize() {
        return orbitSize;
    }

    public boolean isMoon() {
        return moon;
    }

    public boolean isRocky() {
        return rocky;
    }


}
