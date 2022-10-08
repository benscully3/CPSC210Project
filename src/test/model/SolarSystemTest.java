package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class SolarSystemTest {
    private SolarSystem solarSystem;


    private BlackHole blackHole;
    private Planet planet1;
    private Planet planet2;
    private Planet planet3;
    private Planet planet4;
    private Planet newPlanet;

    private HashMap<String, Planet> planets = new HashMap<>();

    @BeforeEach
    public void runBefore() {
        blackHole = new BlackHole("BH", 10);
        planet1 = new Planet("planet1", 10, 3, false);
        planet2 = new Planet("planet2", 3, 5, true);
        planet3 = new Planet("planet3", 1, 3.1, false);
        planet4 = new Planet("planet4", 1, 3, false);

        solarSystem = new SolarSystem("Solar System", blackHole);
    }

    @Test
    public void constructorTest() {
        assertEquals(blackHole, solarSystem.getCentralBody());
        assertEquals(planets, solarSystem.getPlanets());
        assertEquals(0, solarSystem.getPlanetCount());
        assertEquals("Solar System", solarSystem.getName());
    }

    @Test
    public void addPlanetTest() {
        assertFalse(solarSystem.addPlanet(planet1));
        planets.put("planet1", planet1);
        assertEquals(1, solarSystem.getPlanetCount());
        assertEquals(planet1, solarSystem.getPlanet("planet1"));
        assertEquals(planets, solarSystem.getPlanets());
    }

    @Test
    public void addPlanetTestMultipleNoCollide() {
        assertFalse(solarSystem.addPlanet(planet1));
        planets.put("planet1", planet1);
        assertEquals(1, solarSystem.getPlanetCount());
        assertEquals(planet1, solarSystem.getPlanet("planet1"));
        assertEquals(planets, solarSystem.getPlanets());

        assertFalse(solarSystem.addPlanet(planet2));
        planets.put("planet2", planet2);
        assertEquals(2, solarSystem.getPlanetCount());
        assertEquals(planet2, solarSystem.getPlanet("planet2"));
        assertEquals(planets, solarSystem.getPlanets());

    }

    @Test
    public void addPlanetTestMultipleCollide() {
        assertFalse(solarSystem.addPlanet(planet1));
        planets.put("planet1", planet1);
        assertEquals(1, solarSystem.getPlanetCount());
        assertEquals(planet1, solarSystem.getPlanet("planet1"));
        assertEquals(planets, solarSystem.getPlanets());

        assertTrue(solarSystem.addPlanet(planet4));
        newPlanet = new Planet("planet1", planet1.getRadius() + planet4.getRadius(),
                planet1.getOrbitSize(), true);
        planets.remove("planet1");
        planets.put("planet1", newPlanet);
        assertEquals(1, solarSystem.getPlanetCount());
        Planet comparePlanet = solarSystem.getPlanet("planet1");
        assertEquals(newPlanet.getName(), comparePlanet.getName());
        assertEquals(newPlanet.getRadius(), comparePlanet.getRadius());
        assertEquals(newPlanet.getMass(), comparePlanet.getMass());
        assertEquals(newPlanet.getOrbitSize(), comparePlanet.getOrbitSize());
    }

    @Test
    public void addPlanetTestMultipleJustNoCollide() {
        assertFalse(solarSystem.addPlanet(planet1));
        planets.put("planet1", planet1);
        assertEquals(1, solarSystem.getPlanetCount());
        assertEquals(planet1, solarSystem.getPlanet("planet1"));
        assertEquals(planets, solarSystem.getPlanets());

        assertFalse(solarSystem.addPlanet(planet3));
        planets.put("planet3", planet3);
        assertEquals(2, solarSystem.getPlanetCount());
        assertEquals(planet3, solarSystem.getPlanet("planet3"));
        assertEquals(planets, solarSystem.getPlanets());
    }


    @Test
    public void removePlanetTest() {
        solarSystem.addPlanet(planet1);
        assertEquals(1, solarSystem.getPlanetCount());

        solarSystem.removePlanet("planet1");
        assertEquals(0, solarSystem.getPlanetCount());
        assertEquals(planets, solarSystem.getPlanets());

    }

    @Test
    public void removePlanetTestMultiple() {
        solarSystem.addPlanet(planet1);
        solarSystem.addPlanet(planet2);
        solarSystem.addPlanet(planet3);
        planets.put("planet1", planet1);
        planets.put("planet2", planet2);
        planets.put("planet3", planet3);
        assertEquals(3, solarSystem.getPlanetCount());


        solarSystem.removePlanet("planet1");
        planets.remove("planet1");
        assertEquals(2, solarSystem.getPlanetCount());
        assertEquals(planets, solarSystem.getPlanets());

        solarSystem.removePlanet("planet3");
        planets.remove("planet3");
        assertEquals(1, solarSystem.getPlanetCount());
        assertEquals(planets, solarSystem.getPlanets());


    }

    @Test
    public void checkCollisionTestTrue() {
        assertTrue(solarSystem.checkCollision(planet1, planet4));
    }

    @Test
    public void checkCollisionTestFalse() {
        assertFalse(solarSystem.checkCollision(planet1, planet2));
    }

    @Test
    public void checkCollisionTestJustFalse() {
        assertFalse(solarSystem.checkCollision(planet1, planet3));
    }

    @Test
    public void clearPlanetsTest() {
        assertFalse(solarSystem.addPlanet(planet1));
        assertFalse(solarSystem.addPlanet(planet2));
        assertFalse(solarSystem.addPlanet(planet3));
        assertEquals(3, solarSystem.getPlanetCount());

        solarSystem.clearPlanets();
        assertEquals(0, solarSystem.getPlanetCount());
        assertEquals(planets, solarSystem.getPlanets());
    }

}
