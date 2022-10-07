package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WhiteDwarfTest {
    private WhiteDwarf whiteDwarf;
    private double radius;


    @Test
    public void constructorTest(){
        whiteDwarf = new WhiteDwarf("white dwarf",0.7);
        radius = 0.0085 * Math.pow(0.7, -1/3);
        assertEquals(0.7, whiteDwarf.getMass());
        assertEquals(radius, whiteDwarf.getRadius());
    }

    @Test
    public void canSupernovaTestYes(){
        whiteDwarf = new WhiteDwarf("white dwarf",1.6);
        assertTrue(whiteDwarf.canSupernova());
    }

    @Test
    public void canSupernovaTestJustYes(){
        whiteDwarf = new WhiteDwarf("white dwarf",1.4);
        assertTrue(whiteDwarf.canSupernova());
    }

    @Test
    public void canSupernovaTestNo(){
        whiteDwarf = new WhiteDwarf("white dwarf",0.8);
        assertFalse(whiteDwarf.canSupernova());
    }

    @Test
    public void canSupernovaTestJustNo(){
        whiteDwarf = new WhiteDwarf("white dwarf",1.39);
        assertFalse(whiteDwarf.canSupernova());
    }
}
