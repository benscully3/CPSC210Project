package Persistence;

import exceptions.NameAlreadyUsed;
import model.*;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkSolarSystem(String name, CentralBody centralBody, HashMap<String, Planet> planets, SolarSystem solarSystem) {
        ArrayList<Planet> expectedPlanetsValues = new ArrayList<Planet>();
        ArrayList<Planet> planetsValues = new ArrayList<Planet>();
        assertEquals(name, solarSystem.getName());
        assertEquals(planets.size(), solarSystem.getPlanetCount());
        checkCentralBody(centralBody, solarSystem.getCentralBody());
        for (Planet p : planets.values()) {
            expectedPlanetsValues.add(p);
        }
        for (Planet p : solarSystem.getPlanets().values()) {
            planetsValues.add(p);
        }
        checkPlanets(expectedPlanetsValues, planetsValues);
    }

    private void checkPlanets(ArrayList<Planet> expectedPlanets, ArrayList<Planet> planets) {
        for (int i=0; i<planets.size(); i++) {
            checkPlanet(expectedPlanets.get(i), planets.get(i));
        }
    }

    private void checkPlanet(Planet expectedPlanet, Planet planet) {
        assertEquals(expectedPlanet.getName(), planet.getName());
        assertEquals(expectedPlanet.getOrbitSize(), planet.getOrbitSize());
        assertEquals(expectedPlanet.getMass(), planet.getMass());
        assertEquals(expectedPlanet.isMoon(), planet.isMoon());
        assertEquals(expectedPlanet.isRocky(), planet.isRocky());
    }

    private void checkCentralBody(CentralBody expectedCentralBody, CentralBody centralBody) {
        assertEquals(expectedCentralBody.getCentralBodyType(), centralBody.getCentralBodyType());
        assertEquals(expectedCentralBody.getMass(), centralBody.getMass());
        assertEquals(expectedCentralBody.getName(), centralBody.getName());
        assertEquals(expectedCentralBody.getRadius(), centralBody.getRadius());
    }

    // helper function to build a galaxy to test json
    protected void buildGalaxy(Galaxy galaxy) {
        BlackHole blackHole = new BlackHole("black hole", "Black Hole", 10, 30);
        NeutronStar neutronStar = new NeutronStar("neutron star", "Neutron Star", 2, 15000);
        WhiteDwarf whiteDwarf = new WhiteDwarf("white dwarf", "White Dwarf", 1.1, 10);
        GiantStar giantStar = new GiantStar("giant star", "Giant Star", 10, 45, 25);
        try {
            galaxy.addSolarSystem(new SolarSystem("solar System1", blackHole));
            galaxy.addSolarSystem(new SolarSystem("solar System2", neutronStar));
            galaxy.addSolarSystem(new SolarSystem("solar System3", whiteDwarf));
            galaxy.addSolarSystem(new SolarSystem("solar System4", giantStar));
        } catch (NameAlreadyUsed e) {
            throw new RuntimeException(e);
        }
        SolarSystem solarSystem = galaxy.getSolarSystem("solar System1");
        solarSystem.addPlanet(new Planet("planet1", 10, 13,  4, true, false));
        solarSystem.addPlanet(new Planet("planet2", 4, 7, 3, false, true));
    }
}
