package model;

// class represent a white dwarf star
public class WhiteDwarf extends Star {

    // EFFECT: construct a white dwarf based off of mass
    public WhiteDwarf(String name, double mass) {
        this.mass = mass;
        this.radius = 0.0085 * Math.pow(mass, -1 / 3);
        this.name = name;
    }

    // REQUIRES: must be in a binary
    // MODIFIES: this
    // EFFECT: star explodes destroying solar system
    public boolean canSupernova() {
        if (this.mass >= 1.4) {
            return true;
        } else {
            return false;
        }
    }
}
