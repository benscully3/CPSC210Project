package model;

// class represent a white dwarf star
public class WhiteDwarf extends Star {

    // REQUIRES: mass must be positive
    // EFFECT: construct a white dwarf based off of mass with a given name
    public WhiteDwarf(String name, double mass) {
        this.mass = mass;
        this.radius = 5916 * Math.pow(mass, -0.33);
        this.name = name;
        this.centralBodyType = "White Dwarf";
    }

    // REQUIRES: must be in a binary
    // EFFECT: star explodes destroying solar system
    //         implementation for conditions when a white dwarf could
    //         go supernova was too complicated. With extra time in the
    //         future, this would be used. For now, it always returns
    //         false when called in GalaxyBuilderApp
    public boolean canSupernova() {
        return this.mass > 1.4;
    }
}
