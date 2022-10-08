package model;

import java.util.HashMap;

public class SolarSystem extends Body {
    private CentralBody centralBody;
    private HashMap<String, Planet> planets;  // planet list, key is planet name
    private int planetCount;
    private String planetName;

    // MODIFIES: this
    // EFFECTS: construct a solar system with a given central body and no planets
    public SolarSystem(String name, CentralBody centralBody) {
        this.centralBody = centralBody;
        planets = new HashMap<>();
        planetCount = 0;
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: adds a planet to solar system, adds 1 to count
    //          if new planet collides with pre-existing planet
    //          add radii and masses, keep old planet name and
    //          make the planet have a moon
    public boolean addPlanet(Planet planet) {
        for (Planet p : planets.values()) {
            if (checkCollision(planet, p)) {
                Planet newPlanet = new Planet(p.getName(),
                        p.radius + planet.radius, p.getOrbitSize(), true);
                planetName = newPlanet.getName();
                planets.remove(p.getName());
                planets.put(planetName, newPlanet);
                return true;
            }
        }
        planetName = planet.getName();
        planets.put(planetName, planet);
        planetCount++;
        return false;
    }

    // REQUIRES: planet must be in solar system
    // MODIFIES: this
    // EFFECTS: removes a planet in the solar system based on its key (name)
    //          removes 1 from count
    public void removePlanet(String planetName) {
        planets.remove(planetName);
        planetCount--;
    }

    public boolean checkCollision(Planet planet1, Planet planet2) {
        double orbit1 = planet1.getOrbitSize();
        double orbit2 = planet2.getOrbitSize();

        if (orbit1 == orbit2) {
            return true;
        } else {
            return false;
        }

    }

    public void clearPlanets(){
        planets.clear();
        planetCount = 0;
    }

    // REQUIRES: planet must be in solar system
    // EFFECTS: get planet based on its key (name)
    public Planet getPlanet(String planetName) {
        return planets.get(planetName);
    }

    // EFFECTS: gets all planets in solar system
    public HashMap<String, Planet> getPlanets() {
        return planets;
    }

    // EFFECTS: get central body
    public CentralBody getCentralBody() {
        return centralBody;
    }

    public int getPlanetCount() {
        return planetCount;
    }


}
