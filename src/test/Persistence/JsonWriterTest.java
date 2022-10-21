package Persistence;

import model.CentralBody;
import model.Galaxy;
import model.Planet;
import model.SolarSystem;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() throws FileNotFoundException {
        Galaxy galaxy = new Galaxy("Galaxy");
        JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
        assertThrows(FileNotFoundException.class, () -> {
            writer.open();
        }, "Throw expected");

    }

    @Test
    void testWriterEmptyGalaxy() {
        try {
            Galaxy galaxy = new Galaxy("galaxy");
            JsonWriter writer = new JsonWriter("./PersonalProject/data/testWriterEmptyGalaxy.json");
            writer.open();
            writer.write(galaxy);
            writer.close();

            JsonReader reader = new JsonReader("./PersonalProject/data/testWriterEmptyGalaxy.json");
            galaxy = reader.read();
            assertEquals("galaxy", galaxy.getName());
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

            JsonWriter writer = new JsonWriter("./PersonalProject/data/testWriterGeneralGalaxy.json");
            writer.open();
            writer.write(galaxy);
            writer.close();

            JsonReader reader = new JsonReader("./PersonalProject/data/testWriterGeneralGalaxy.json");
            Galaxy galaxyRead = reader.read();

            assertEquals("galaxy", galaxyRead.getName());
            for (SolarSystem s : galaxyRead.getSolarSystems().values()) {
                solarSystemsRead.add(s);
            }
            assertEquals(4, galaxyRead.getSolarSystemCount());
            for (int i = 0; i < solarSystemsRead.size(); i++) {
                String name = solarSystems.get(i).getName();
                CentralBody centralBody = solarSystems.get(i).getCentralBody();
                HashMap<String, Planet> planets = solarSystems.get(i).getPlanets();
                checkSolarSystem(name, centralBody, planets, solarSystemsRead.get(i));
            }

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
