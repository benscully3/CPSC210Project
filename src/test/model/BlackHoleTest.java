package model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BlackHoleTest {
    private BlackHole blackHole;
    private double radius;

    @Test
    public void constructorTest() {
        blackHole = new BlackHole("black hole", 10);
        radius = 2.95 * 10;
        assertEquals(10, blackHole.getMass());
        assertEquals(radius, blackHole.getRadius());
        assertEquals("black hole", blackHole.getName());
        assertEquals("Black Hole", blackHole.getCentralBodyType());
    }

    @Test
    public void canSupernovaTest() {
        blackHole = new BlackHole("black hole", 100);
        assertFalse(blackHole.canSupernova());

        ArrayList<String> list = new ArrayList<String>();
        list.isEmpty();
    }

}
