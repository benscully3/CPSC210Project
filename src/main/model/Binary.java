package model;

public class Binary extends CentralBody{
    private CentralBody centralBody1;  // Cannot be another binary
    private CentralBody centralBody2;  // Cannot be another binary

    // REQUIRES: Central body parameters cannot be binaries
    // EFFECT:
    public void Binary(CentralBody centralBody1, CentralBody centralBody2){
        // stub
    }

    // MODIFIES: this
    // EFFECT: The two central bodies collapse into one, adding their mass
    //              if two white dwarfs -> supernova (destroys solar system)
    //              else if two main sequence or giants -> one giant
    //              else if one black hole -> one black hole
    //              else if one neutron star -> one neutron star
    public void coalesce(){}

    public CentralBody getCentralBody1(){
        return centralBody1;
    }

    public CentralBody getCentralBody2(){
        return centralBody2;
    }
}
