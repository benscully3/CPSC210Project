package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryTest {
    private CentralBody blackHole;
    private CentralBody neutronStar;
    private CentralBody whiteDwarf1;
    private CentralBody whiteDwarf2;

    private Binary testBinary;

    @BeforeEach
    public void runBefore() {
        blackHole = new BlackHole("black hole", 5);
        neutronStar = new NeutronStar("neutron star", 1.7);
        whiteDwarf1 = new WhiteDwarf("white dwarf1", 0.7);
        whiteDwarf2 = new WhiteDwarf("white dwarf2", 1);

    }

    @Test
    public void ConstructorTest() {
        testBinary = new Binary("binary", blackHole, neutronStar);
        assertEquals(blackHole, testBinary.getCentralBody1());
        assertEquals(neutronStar, testBinary.getCentralBody2());
        assertEquals(blackHole.getMass() + neutronStar.getMass(),
                testBinary.getMass());
        assertEquals(blackHole.getRadius() + neutronStar.getRadius(),
                testBinary.getRadius());
        assertEquals("binary", testBinary.getName());
        assertEquals("Binary", testBinary.getCentralBodyType());
    }

    @Test
    public void ConstructorTestSameType() {
        testBinary = new Binary("binary", whiteDwarf1, whiteDwarf2);
        assertEquals(whiteDwarf1, testBinary.getCentralBody1());
        assertEquals(whiteDwarf2, testBinary.getCentralBody2());
        assertEquals(whiteDwarf1.getMass() + whiteDwarf2.getMass(),
                testBinary.getMass());
        assertEquals(whiteDwarf1.getRadius() + whiteDwarf2.getRadius(),
                testBinary.getRadius());
    }

    @Test
    public void canSupernovaTest() {
        testBinary = new Binary("binary", whiteDwarf1, whiteDwarf2);
        assertFalse(testBinary.canSupernova());
    }
}
