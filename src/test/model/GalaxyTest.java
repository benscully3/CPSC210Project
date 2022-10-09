package model;

import exceptions.NameAlreadyUsed;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class GalaxyTest {
    private NeutronStar neutronStar;
    private BlackHole blackHole;
    private GiantStar giantStar;

    private SolarSystem solarSystem1;
    private SolarSystem solarSystem2;
    private SolarSystem solarSystem3;
    HashMap<String, SolarSystem> solarSystems;

    private Galaxy galaxy;

    @BeforeEach
    public void runBefore() {
        neutronStar = new NeutronStar("NS", 1.6);
        blackHole = new BlackHole("BH", 15);
        giantStar = new GiantStar("GS", 10);

        solarSystem1 = new SolarSystem("SS1", blackHole);
        solarSystem2 = new SolarSystem("SS2", neutronStar);
        solarSystem3 = new SolarSystem("SS3", giantStar);
        solarSystems = new HashMap<>();
    }

    @Test
    public void constructorTest() {
        galaxy = new Galaxy("Galaxy");
        assertEquals("Galaxy", galaxy.getName());
        assertEquals(solarSystems, galaxy.getSolarSystems());
        assertEquals(0, galaxy.getSolarSystemCount());
    }

    @Test
    public void addSolarSystemTest() throws NameAlreadyUsed {
        galaxy = new Galaxy("Galaxy");
        galaxy.addSolarSystem(solarSystem1);
        solarSystems.put("SS1", solarSystem1);

        assertEquals(1, galaxy.getSolarSystemCount());
        assertEquals(solarSystem1, galaxy.getSolarSystem("SS1"));
        assertEquals(solarSystems, galaxy.getSolarSystems());
    }

    @Test
    public void addSolarSystemTestMultiple() throws NameAlreadyUsed {
        galaxy = new Galaxy("Galaxy");
        galaxy.addSolarSystem(solarSystem1);
        solarSystems.put("SS1", solarSystem1);

        assertEquals(1, galaxy.getSolarSystemCount());
        assertEquals(solarSystem1, galaxy.getSolarSystem("SS1"));
        assertEquals(solarSystems, galaxy.getSolarSystems());

        galaxy.addSolarSystem(solarSystem2);
        solarSystems.put("SS2", solarSystem2);

        assertEquals(2, galaxy.getSolarSystemCount());
        assertEquals(solarSystem1, galaxy.getSolarSystem("SS1"));
        assertEquals(solarSystems, galaxy.getSolarSystems());
    }

    @Test
    public void removeSolarSystemTest() throws NameAlreadyUsed {
        galaxy = new Galaxy("Galaxy");
        galaxy.addSolarSystem(solarSystem1);
        galaxy.addSolarSystem(solarSystem2);
        assertEquals(2, galaxy.getSolarSystemCount());
        solarSystems.put("SS1", solarSystem1);
        solarSystems.put("SS2", solarSystem2);

        galaxy.removeSolarSystem("SS1");
        solarSystems.remove("SS1");
        assertEquals(1, galaxy.getSolarSystemCount());
        assertEquals(solarSystems, galaxy.getSolarSystems());

    }

    @Test
    public void removeSolarSystemTestMultiple() throws NameAlreadyUsed {
        galaxy = new Galaxy("Galaxy");
        galaxy.addSolarSystem(solarSystem1);
        galaxy.addSolarSystem(solarSystem2);
        galaxy.addSolarSystem(solarSystem3);
        assertEquals(3, galaxy.getSolarSystemCount());
        solarSystems.put("SS1", solarSystem1);
        solarSystems.put("SS2", solarSystem2);
        solarSystems.put("SS3", solarSystem3);

        galaxy.removeSolarSystem("SS1");
        solarSystems.remove("SS1");

        assertEquals(2, galaxy.getSolarSystemCount());
        assertEquals(solarSystems, galaxy.getSolarSystems());

        galaxy.removeSolarSystem("SS3");
        solarSystems.remove("SS3");

        assertEquals(1, galaxy.getSolarSystemCount());
        assertEquals(solarSystems, galaxy.getSolarSystems());

    }

    @Test
    public void changeNameTest() {
        galaxy = new Galaxy("Galaxy");
        assertEquals("Galaxy", galaxy.getName());
        galaxy.changeName("New name");
        assertEquals("New name", galaxy.getName());
    }


}
