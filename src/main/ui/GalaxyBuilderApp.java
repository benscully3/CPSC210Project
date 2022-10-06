package ui;

import model.*;

// Galaxy builder application
public class GalaxyBuilderApp {
    private Galaxy galaxy;

    public void GalaxyBuilderApp(){}

    // REQUIRES: central body must be binary
    // MODIFIES: solarSystem
    // EFFECT: The two central bodies collapse into one, adding their mass
    //              if two white dwarfs -> supernova (destroys solar system)
    //              else if one white dwarf one 'normal star' -> supernova
    //              else if two main sequence or giants -> one giant
    //              else if one black hole -> one black hole
    //              else if one neutron star -> one neutron star
    public void coalesce(SolarSystem solarSystem){}
}
