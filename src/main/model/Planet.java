package model;

// abstract class to represent a planet with orbit size and moon
public abstract class Planet extends Body{
    protected int orbitSize;
    protected boolean moon; // does the planet have one moon

    // REQUIRES:
    // MODIFIES: this
    // EFFECT: collides this planet with another making one planet
    //         with the combined masses
    public void collide(){
        // stub
    }

}
