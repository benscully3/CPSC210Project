package Persistence;

import exceptions.NameAlreadyUsed;
import model.*;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() throws FileNotFoundException {
        Galaxy galaxy = new Galaxy("Galaxy");
        JsonWriter writer = new JsonWriter("./PersonalProject/data/my\0illegal:fileName.json");
        assertThrows(FileNotFoundException.class, () -> {
                    writer.open();
        }, "Throw expected");

    }

    @Test
    void testWriterEmptyGalaxy() {
        try {
            Galaxy galaxy = new Galaxy("Galaxy");
            JsonWriter writer = new JsonWriter("./PersonalProject/data/testWriterEmptyGalaxy.json");
            writer.open();
            writer.write(galaxy);
            writer.close();

            JsonReader reader = new JsonReader("./PersonalProject/data/testWriterEmptyGalaxy.json");
            galaxy = reader.read();
            assertEquals("Galaxy", galaxy.getName());
            assertEquals(0, galaxy.getSolarSystemCount());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralGalaxy() {
        try {
            ArrayList<SolarSystem> solarSystems = new ArrayList<SolarSystem>();
            ArrayList<SolarSystem> solarSystemsRead = new ArrayList<SolarSystem>();
            Galaxy galaxy = new Galaxy("galaxy");
            buildGalaxy(galaxy);
            for (SolarSystem s : galaxy.getSolarSystems().values()) {
                solarSystems.add(s);
            }

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGalaxy.json");
            writer.open();
            writer.write(galaxy);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGalaxy.json");
            Galaxy galaxyRead = reader.read();

            assertEquals("galaxy", galaxyRead.getName());
            for (SolarSystem s : galaxyRead.getSolarSystems().values()) {
                solarSystemsRead.add(s);
            }
            assertEquals(4, galaxyRead.getSolarSystemCount());
            for (int i=0; i<solarSystemsRead.size(); i++) {
                String name = solarSystems.get(i).getName();
                CentralBody centralBody = solarSystems.get(i).getCentralBody();
                HashMap<String, Planet> planets = solarSystems.get(i).getPlanets();
                checkSolarSystem(name, centralBody, planets, solarSystemsRead.get(i));
            }

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    // helper function to build a galaxy to test json
    private void buildGalaxy(Galaxy galaxy) {
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
