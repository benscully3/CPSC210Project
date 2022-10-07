package model;

// abstract class to represent a planet with orbit size and moon
public class Planet extends Body{
    private double orbitSize;
    private boolean moon; // does the planet have one moon
    private boolean rocky; // if false: gas giant
    private double rhoGas = 0.04; // gas planet density
    private double rhoRocky = 0.18; // rocky planet density



    // EFFECT: create a default rocky planet with no moon
    //         with 1 earth radius and one earth orbit
    public Planet(String name){
        this.radius = 1;
        this.moon = false;
        this.name = name;
        this.orbitSize = 1;
        this.rocky = true;
        this.mass = calculateMass(this.rocky, 1);
    }

    // EFFECT: create a gas or rocky planet based on radius with or without a moon
    public Planet(String name, double radius, double orbitSize, boolean isMoon){
        this.name = name;
        this.radius = radius;
        this.orbitSize = orbitSize;
        this.moon = isMoon;
        if (this.radius <= 5){
            this.rocky = true;
            this.mass = calculateMass(true, this.radius);
        } else{
            this.rocky = false;
            this.mass = calculateMass(false, this.radius);
        }

    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECT: collides this planet with another making one planet
    //         with the combined masses
    //         new planet has a moon
    public void collide(){
        // stub
    }

    public double getOrbitSize(){
        return orbitSize;
    }

    public boolean isMoon(){
        return moon;
    }

    public boolean isRocky(){
        return rocky;
    }

    private double calculateMass(boolean isRocky, double radius){
        double mass;
        double base;

        if (isRocky){
            mass = 4 * 3.14 * rhoRocky * Math.pow(radius, 3);
        }else{
            mass = 4 * 3.14 * rhoGas * Math.pow(radius, 3);
        }
        return mass;
    }

}
