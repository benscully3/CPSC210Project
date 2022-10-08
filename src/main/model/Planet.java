package model;

// abstract class to represent a planet with orbit size and moon
public class Planet extends Body {
    private double orbitSize;
    private boolean moon; // does the planet have one moon
    private boolean rocky; // if false: gas giant
    private final double RHO_GAS = 0.04; // gas planet density
    private final double RHO_ROCKY = 0.18; // rocky planet density


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
    }

    // EFFECT: create a gas or rocky planet based on radius with or without a moon
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

    }

    public double getOrbitSize() {
        return orbitSize;
    }

    public boolean isMoon() {
        return moon;
    }

    public boolean isRocky() {
        return rocky;
    }

    private double calculateMass(boolean isRocky, double radius) {
        double mass;

        if (isRocky) {
            mass = 1.33 * 3.14 * RHO_ROCKY * Math.pow(radius, 3);
        } else {
            mass = 1.33 * 3.14 * RHO_GAS * Math.pow(radius, 3);
        }
        return mass;
    }

}
