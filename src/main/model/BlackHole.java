package model;

// Class to represent a black hole
public class BlackHole extends CentralBody {

    // REQUIRES: mass > 2.1
    // EFFECT: constructs black hole based on mass with a given name
    public BlackHole(String name, double mass) {
        this.mass = mass;
        this.radius = 2.95 * mass;
        this.name = name;
        this.centralBodyType = "Black Hole";
    }

    // EFFECT: returns false because black holes cannot go supernova
    public boolean canSupernova() {
        return false;
    }
}
