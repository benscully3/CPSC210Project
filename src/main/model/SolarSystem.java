package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.HashMap;

// class to represent a solar system with Planets, a central body and a name
public class SolarSystem extends Body implements Writable {
    private CentralBody centralBody;
    private HashMap<String, Planet> planets;  // planet list, key is planet name
    private int planetCount;
    private String planetName;

    // MODIFIES: this
    // EFFECTS: construct a solar system with a given central body, name and no planets
    public SolarSystem(String name, CentralBody centralBody) {
        this.centralBody = centralBody;
        planets = new HashMap<>();
        planetCount = 0;
        this.name = name;

        EventLog.getInstance().logEvent(new Event("Created solar system: " + name));
    }

    // MODIFIES: this
    // EFFECTS: adds a planet to solar system, adds 1 to count
    //          if new planet collides with pre-existing planet
    //          add radii and masses, keep old planet name and
    //          make the planet now have a moon.
    //          Return true if there was a collision, false if not.
    public boolean addPlanet(Planet planet) {
        for (Planet p : planets.values()) {
            if (checkCollision(planet, p)) {
                Planet newPlanet = new Planet(p.getName(),
                        p.radius + planet.radius, p.getOrbitSize(), true);
                planetName = newPlanet.getName();
                planets.remove(p.getName());
                planets.put(planetName, newPlanet);

                EventLog.getInstance().logEvent(new Event("Added planet to solar system"));

                return true;
            }
        }
        planetName = planet.getName();
        planets.put(planetName, planet);
        planetCount++;

        EventLog.getInstance().logEvent(new Event("Added planet to solar system"));

        return false;
    }

    // REQUIRES: planet must be in solar system
    // MODIFIES: this
    // EFFECTS: removes a planet in the solar system based on its key (name)
    //          removes 1 from count
    public void removePlanet(String planetName) {
        planets.remove(planetName);
        planetCount--;

        EventLog.getInstance().logEvent(new Event("Removed planet from solar system"));
    }

    // EFFECT: returns true if two planets would collide (have the same radius)
    public boolean checkCollision(Planet planet1, Planet planet2) {
        double orbit1 = planet1.getOrbitSize();
        double orbit2 = planet2.getOrbitSize();

        return orbit1 == orbit2;

    }

    // EFFECT: clear all planets from a SolarSystem
    public void clearPlanets() {
        planets.clear();
        planetCount = 0;

        EventLog.getInstance().logEvent(new Event("Cleared planets from solar system"));
    }

    // REQUIRES: planet must be in solar system
    // EFFECTS: get planet based on its key (name)
    public Planet getPlanet(String planetName) {
        return planets.get(planetName);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("centralBody", centralBodyToJson());
        json.put("planets", planetsToJson());

        return json;
    }

    private JSONArray planetsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Planet p : planets.values()) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }

    private JSONObject centralBodyToJson() {
        JSONObject json = new JSONObject();
        json.put("name", centralBody.getName());
        json.put("centralBodyType", centralBody.getCentralBodyType());
        json.put("mass", centralBody.getMass());
        json.put("radius", centralBody.getRadius());
        if (centralBody.getCentralBodyType().equals("Giant Star")) {
            GiantStar giantStar = (GiantStar) centralBody;
            json.put("luminosity", giantStar.getLuminosity());
        }


        return json;
    }

    // getters
    public HashMap<String, Planet> getPlanets() {
        return planets;
    }

    public CentralBody getCentralBody() {
        return centralBody;
    }

    public int getPlanetCount() {
        return planetCount;
    }

}
