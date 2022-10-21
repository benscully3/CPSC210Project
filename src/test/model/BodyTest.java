package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BodyTest {
    private BlackHole blackHole;

    @Test
    public void changeNameTest() {
        blackHole = new BlackHole("BH", 100);
        assertEquals("BH", blackHole.getName());
        blackHole.changeName("New name");
        assertEquals("New name", blackHole.getName());
    }

}
