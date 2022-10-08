package model;

public class BlackHole extends CentralBody {
    // REQUIRES: mass > 2.1
    // EFFECT: constructs black hole based on mass
    public BlackHole(String name, double mass) {
        this.mass = mass;
        this.radius = 4.2e-6 * mass;
        this.name = name;
        this.centralBodyType = "Black Hole";
    }

    public boolean canSupernova() {
        return false;
    }
}
