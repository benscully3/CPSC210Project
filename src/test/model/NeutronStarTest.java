package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NeutronStarTest {
    private NeutronStar neutronStar;
    private double radius;

    @BeforeEach
    public void runBefore() {
        neutronStar = new NeutronStar("neutron star", 1.8);
    }

    @Test
    public void neutronStarTest() {
        radius = 3.19 * Math.pow(1.8, -0.33);
        assertEquals(1.8, neutronStar.getMass());
        assertEquals(radius, neutronStar.getRadius());
        assertEquals("Neutron Star", neutronStar.getCentralBodyType());
        assertEquals("neutron star", neutronStar.getName());
    }

    @Test
    public void neutronStarTestAlternative() {
        NeutronStar neutronStarAlt = new NeutronStar("neutron star", "Neutron Star",
                2.1, 10000);
        assertEquals(2.1, neutronStarAlt.getMass());
        assertEquals(10000, neutronStarAlt.getRadius());
        assertEquals("Neutron Star", neutronStarAlt.getCentralBodyType());
        assertEquals("neutron star", neutronStarAlt.getName());
    }

    @Test
    public void canSupernovaTest() {
        assertFalse(neutronStar.canSupernova());
    }

}
