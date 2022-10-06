package model;

// abstract class to represent a planet with orbit size and moon
public class Planet extends Body{
    private double orbitSize;
    private boolean moon; // does the planet have one moon
    private boolean rocky; // if false: gas giant


    public Planet(double radius){}

    // REQUIRES:
    // MODIFIES: this
    // EFFECT: collides this planet with another making one planet
    //         with the combined masses
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

}
