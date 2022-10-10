package model;

// class representing a neutron star
public class NeutronStar extends Star {

    // REQUIRES: 1.4 < mass < 2.1
    // EFFECT: construct a neutron star based off of mass with a given name
    public NeutronStar(String name, double mass) {
        this.mass = mass;
        this.radius = 3.19 * Math.pow(this.mass, -0.33);
        this.centralBodyType = "Neutron Star";
        this.name = name;
    }

    // EFFECT: returns false because neutron stars cannot go supernova
    public boolean canSupernova() {
        return false;
    }

}
