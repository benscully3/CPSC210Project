package model;

// class representing a neutron star
public class NeutronStar extends Star{

    // REQUIRES: 1.4 < mass < 2.1
    // EFFECT: construct a neutron star based off of mass
    public NeutronStar(String name, double mass){
        this.mass = mass;
        this.radius = 4.6e-6 * Math.pow(this.mass, -1/3);
    }

    public boolean canSupernova(){
        return false;
    }

}
