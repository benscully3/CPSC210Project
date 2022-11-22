package model;

import exceptions.NameAlreadyUsedException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.HashMap;

// Class to represent a galaxy with SolarSystems and a name
public class Galaxy implements Writable {
    private String name;
    private HashMap<String, SolarSystem> solarSystems;
    private int solarSystemCount;

    // EFFECT: construct a galaxy with no solar systems and a given name
    public Galaxy(String name) {
        this.name = name;
        solarSystemCount = 0;
        solarSystems = new HashMap<>();

        EventLog.getInstance().logEvent(new Event("Created Galaxy: " + name));
    }

    // MODIFIES: this
    // EFFECT: adds a solar system to the galaxy
    //         check's if name has already been used and throws an error if it has
    public void addSolarSystem(SolarSystem solarSystem) throws NameAlreadyUsedException {
        String solarSystemName = solarSystem.getName();
        
        if (solarSystems.get(solarSystemName) == null) {
            solarSystems.put(solarSystemName, solarSystem);
            solarSystemCount = solarSystemCount + 1;

            EventLog.getInstance().logEvent(new Event("Added solar system to galaxy"));
        } else {
            throw new NameAlreadyUsedException();
        }
    }

    // REQUIRES: solar system must be in galaxy
    // MODIFIES: this
    // EFFECT: remove a solar system from the galaxy based on name
    public void removeSolarSystem(String solarSystemName) {
        solarSystems.remove(solarSystemName);
        solarSystemCount = solarSystemCount - 1;
        EventLog.getInstance().logEvent(new Event("Removed solar system from galaxy"));
    }

    // MODIFIES: this
    // EFFECT: change the galaxies name
    public void changeName(String newName) {
        this.name = newName;
        EventLog.getInstance().logEvent(new Event("Changed galaxy's name to " + name));
    }

    // EFFECT: return a solar system based on it's name
    public SolarSystem getSolarSystem(String solarSystemName) {
        return solarSystems.get(solarSystemName);
    }

    // EFFECT: write galaxy data to a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("solarSystemCount", solarSystemCount);
        json.put("solarSystems", solarSystemsToJson());

        EventLog.getInstance().logEvent(new Event("Wrote galaxy to JSon"));

        return json;
    }

    // EFFECT
    private JSONArray solarSystemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (SolarSystem s : solarSystems.values()) {
            jsonArray.put(s.toJson());
        }

        EventLog.getInstance().logEvent(new Event("Wrote solar systems to JSon"));

        return jsonArray;
    }

    // getters
    public HashMap<String, SolarSystem> getSolarSystems() {
        return solarSystems;
    }

    public int getSolarSystemCount() {
        return solarSystemCount;
    }

    public String getName() {
        return name;
    }


}
