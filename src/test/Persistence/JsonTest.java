package Persistence;

import model.CentralBody;
import model.Planet;
import model.SolarSystem;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkSolarSystem(String name, CentralBody centralBody, HashMap<String, Planet> planets, SolarSystem solarSystem) {
        assertEquals(name, solarSystem.getName());
        checkCentralBody(centralBody, solarSystem.getCentralBody());
        checkPlanets((ArrayList<Planet>) planets.values(), (ArrayList<Planet>) solarSystem.getPlanets().values());
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
}
