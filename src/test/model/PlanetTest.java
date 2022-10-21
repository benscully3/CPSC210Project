package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlanetTest {
    private Planet gasPlanet;
    private Planet rockyPlanet;
    private Planet planetWithMoon;
    private static final double RHO_GAS = 0.04; // gas planet density
    private static final double RHO_ROCKY = 0.18; // rocky planet density


    @BeforeEach
    public void runBefore() {
        rockyPlanet = new Planet("Rocky Planet");
        gasPlanet = new Planet("Gas Planet", 10, 4, false);
        planetWithMoon = new Planet("Planet with moon", 2, 2, true);
    }

    // Tests different types of planets
    @Test
    public void constructorTest() {
        assertEquals(10, gasPlanet.getRadius());
        assertEquals(calculateMass(false, 10), gasPlanet.getMass());
        assertEquals("Gas Planet", gasPlanet.getName());
        assertEquals(4, gasPlanet.getOrbitSize());
        assertFalse(gasPlanet.isMoon());
        assertFalse(gasPlanet.isRocky());

        assertEquals(1, rockyPlanet.getRadius());
        assertEquals(calculateMass(true, 1), rockyPlanet.getMass());
        assertEquals("Rocky Planet", rockyPlanet.getName());
        assertEquals(1, rockyPlanet.getOrbitSize());
        assertFalse(rockyPlanet.isMoon());
        assertTrue(rockyPlanet.isRocky());

        assertEquals(2, planetWithMoon.getRadius());
        assertEquals(calculateMass(true, 2), planetWithMoon.getMass());
        assertEquals("Planet with moon", planetWithMoon.getName());
        assertEquals(2, planetWithMoon.getOrbitSize());
        assertTrue(planetWithMoon.isMoon());
        assertTrue(planetWithMoon.isRocky());

    }

    @Test
    public void constructorTestSavedPlanet() {
        Planet planetSaved = new Planet("planet", 4, 3, 5, true, true);
        assertEquals(4, planetSaved.getRadius());
        assertEquals(3, planetSaved.getMass());
        assertEquals("planet", planetSaved.getName());
        assertEquals(5, planetSaved.getOrbitSize());
        assertTrue(planetSaved.isMoon());
        assertTrue(planetSaved.isRocky());
    }

    @Test
    public void toJsonTest () {
        JSONObject jsonPlanet = rockyPlanet.toJson();
        assertEquals(rockyPlanet.getName(), jsonPlanet.getString("name"));
        assertEquals(rockyPlanet.getMass(), jsonPlanet.getDouble("mass"));
        assertEquals(rockyPlanet.getRadius(), jsonPlanet.getDouble("radius"));
        assertEquals(rockyPlanet.getOrbitSize(), jsonPlanet.getDouble("orbitSize"));
        assertEquals(rockyPlanet.isRocky(), jsonPlanet.getBoolean("rocky"));
        assertEquals(rockyPlanet.isMoon(), jsonPlanet.getBoolean("moon"));
    }

    // helper function for constructor tests
    private double calculateMass(boolean isRocky, double radius) {
        double mass;

        if (isRocky) {
            mass = 1.33 * 3.14 * RHO_ROCKY * Math.pow(radius, 3);
        } else {
            mass = 1.33 * 3.14 * RHO_GAS * Math.pow(radius, 3);
        }
        return mass;
    }
}
