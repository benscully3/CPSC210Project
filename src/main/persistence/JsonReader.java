package persistence;

import exceptions.NameAlreadyUsed;
import model.CentralBody;
import model.Galaxy;
import model.Planet;
import model.SolarSystem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads galaxy from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads galaxy from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Galaxy read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGalaxy(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses galaxy from JSON object and returns it
    private Galaxy parseGalaxy(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Galaxy galaxy = new Galaxy(name);
        addSolarSystems(galaxy, jsonObject);
        return galaxy;
    }

    // MODIFIES: galaxy
    // EFFECTS: parses solar systems from JSON object and adds them to galaxy
    private void addSolarSystems(Galaxy galaxy, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("thingies");
        for (Object json : jsonArray) {
            JSONObject nextSolarSystem = (JSONObject) json;
            addSolarSystem(galaxy, nextSolarSystem);
        }
    }

    // MODIFIES: galaxy
    // EFFECTS: parses solar system from JSON object and adds it to galaxy
    private void addSolarSystem(Galaxy galaxy, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        CentralBody centralBody = galaxy.getSolarSystem(jsonObject.getString("category")).getCentralBody();
        SolarSystem solarSystem = new SolarSystem(name, centralBody);
        addPlanets(solarSystem, jsonObject);
        // TODO: add planets to solar system
        try {
            galaxy.addSolarSystem(solarSystem);
        } catch (NameAlreadyUsed e) {
            throw new RuntimeException(e);
        }
    }

    // MODIFIES: solarSystem
    // EFFECTS: parses planets from JSON object and adds it to solar systems
    private void addPlanets(SolarSystem solarSystem, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("planets");
        for (Object json : jsonArray) {
            JSONObject nextPlanet = (JSONObject) json;
            addPlanet(solarSystem, nextPlanet);
        }

    }

    // MODIFIES: solarSystem
    // EFFECTS: parses planet from JSON object and adds it to solar system
    private void addPlanet(SolarSystem solarSystem, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double mass = jsonObject.getDouble("mass");
        double radius = jsonObject.getDouble("radius");
        double orbitSize = jsonObject.getDouble("orbitSize");
        boolean moon = jsonObject.getBoolean("moon");
        boolean rocky = jsonObject.getBoolean("rocky");

        Planet planet = new Planet(name, radius, mass, orbitSize, moon, rocky);
        solarSystem.addPlanet(planet);
    }
}
