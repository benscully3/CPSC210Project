package Persistence;

import model.Galaxy;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

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
}
