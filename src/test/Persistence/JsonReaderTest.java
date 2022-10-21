package Persistence;

import model.CentralBody;
import model.Galaxy;
import model.Planet;
import model.SolarSystem;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Galaxy galaxy = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testReaderEmptyGalaxy() {
        JsonReader reader = new JsonReader("./PersonalProject/data/testReaderEmptyGalaxy.json");
        try {
            Galaxy galaxy = reader.read();
            assertEquals("galaxy", galaxy.getName());
            assertEquals(0, galaxy.getSolarSystemCount());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    public void testReaderGeneralGalaxy() {
        ArrayList<SolarSystem> solarSystems = new ArrayList<SolarSystem>();
        ArrayList<SolarSystem> solarSystemsRead = new ArrayList<SolarSystem>();
        Galaxy galaxy = new Galaxy("galaxy");
        buildGalaxy(galaxy);
        for (SolarSystem s : galaxy.getSolarSystems().values()) {
            solarSystems.add(s);
        }
        JsonReader reader = new JsonReader("./PersonalProject/data/testReaderGeneralGalaxy.json");
        try {
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
            fail("Couldn't read from file");
        }
    }

}
