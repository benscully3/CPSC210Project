package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WhiteDwarfTest {
    private WhiteDwarf whiteDwarf;
    private double radius;


    @Test
    public void constructorTest() {
        whiteDwarf = new WhiteDwarf("white dwarf", 0.7);
        radius = 5916 * Math.pow(0.7, -0.33);
        assertEquals(0.7, whiteDwarf.getMass());
        assertEquals(radius, whiteDwarf.getRadius());
        assertEquals("white dwarf", whiteDwarf.getName());
        assertEquals("White Dwarf", whiteDwarf.getCentralBodyType());
    }

    @Test
    public void canSupernovaTestYes() {
        whiteDwarf = new WhiteDwarf("white dwarf", 1.6);
        assertTrue(whiteDwarf.canSupernova());
    }

    // Edge case
    @Test
    public void canSupernovaTestJustYes() {
        whiteDwarf = new WhiteDwarf("white dwarf", 1.41);
        assertTrue(whiteDwarf.canSupernova());
    }

    @Test
    public void canSupernovaTestNo() {
        whiteDwarf = new WhiteDwarf("white dwarf", 0.8);
        assertFalse(whiteDwarf.canSupernova());
    }

    // Edge Case
    @Test
    public void canSupernovaTestJustNo() {
        whiteDwarf = new WhiteDwarf("white dwarf", 1.4);
        assertFalse(whiteDwarf.canSupernova());
    }
}
