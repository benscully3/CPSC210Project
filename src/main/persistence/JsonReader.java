package persistence;

import exceptions.NameAlreadyUsedException;
import model.*;
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
        JSONArray jsonArray = jsonObject.getJSONArray("solarSystems");
        for (Object json : jsonArray) {
            JSONObject nextSolarSystem = (JSONObject) json;
            addSolarSystem(galaxy, nextSolarSystem);
        }
    }

    // MODIFIES: galaxy
    // EFFECTS: parses solar system from JSON object and adds it to galaxy
    private void addSolarSystem(Galaxy galaxy, JSONObject jsonSolarSystem) {
        String name = jsonSolarSystem.getString("name");
        CentralBody centralBody = readCentralBody(jsonSolarSystem.getJSONObject("centralBody"));
        SolarSystem solarSystem = new SolarSystem(name, centralBody);
        addPlanets(solarSystem, jsonSolarSystem);
        try {
            galaxy.addSolarSystem(solarSystem);
        } catch (NameAlreadyUsedException e) {
            throw new RuntimeException(e);
        }
    }

    // EFFECT: reads and rebuilds central body
    private CentralBody readCentralBody(JSONObject jsonCentralBody) {
        String name = jsonCentralBody.getString("name");
        String centralBodyType = jsonCentralBody.getString("centralBodyType");
        double radius = jsonCentralBody.getDouble("radius");
        double mass = jsonCentralBody.getDouble("mass");

        if (centralBodyType.equals("Black Hole")) {
            BlackHole blackHole = new BlackHole(name, centralBodyType, mass, radius);
            return blackHole;
        } else if (centralBodyType.equals("Neutron Star")) {
            NeutronStar neutronStar = new NeutronStar(name, centralBodyType, mass, radius);
            return neutronStar;
        } else if (centralBodyType.equals("Giant Star")) {
            double luminosity = jsonCentralBody.getDouble("luminosity");
            GiantStar giantStar = new GiantStar(name, centralBodyType, mass, radius, luminosity);
            return giantStar;
        } else {
            WhiteDwarf whiteDwarf = new WhiteDwarf(name, centralBodyType, mass, radius);
            return whiteDwarf;
        }
    }

    // MODIFIES: solarSystem
    // EFFECTS: parses planets from JSON object and adds it to solar systems
    private void addPlanets(SolarSystem solarSystem, JSONObject jsonSolarSystem) {
        JSONArray jsonPlanets = jsonSolarSystem.getJSONArray("planets");
        for (Object json : jsonPlanets) {
            JSONObject nextPlanet = (JSONObject) json;
            addPlanet(solarSystem, nextPlanet);
        }

    }

    // MODIFIES: solarSystem
    // EFFECTS: parses planet from JSON object and adds it to solar system
    private void addPlanet(SolarSystem solarSystem, JSONObject jsonPlanet) {
        String name = jsonPlanet.getString("name");
        double mass = jsonPlanet.getDouble("mass");
        double radius = jsonPlanet.getDouble("radius");
        double orbitSize = jsonPlanet.getDouble("orbitSize");
        boolean moon = jsonPlanet.getBoolean("moon");
        boolean rocky = jsonPlanet.getBoolean("rocky");

        Planet planet = new Planet(name, radius, mass, orbitSize, moon, rocky);
        solarSystem.addPlanet(planet);
    }
}
