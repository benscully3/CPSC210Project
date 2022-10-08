package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GiantStarTest {
    private GiantStar giantStar;
    private double stephanBoltzmann = 2e-16;
    private double radius;
    private double mass;

    @BeforeEach
    public void runBefore() {
        giantStar = new GiantStar("Giant Star", 10);
    }

    @Test
    public void constructorTest() {
        radius = Math.sqrt(giantStar.getLuminosity() /
                (4.0 * 3.14 * stephanBoltzmann *
                        Math.pow(giantStar.getTemperature(), 4)));
        mass = 1.4 * Math.pow(giantStar.getLuminosity(), 0.286);

        assertEquals(radius, giantStar.getRadius());
        assertEquals(10, giantStar.getLuminosity());
        assertEquals(mass, giantStar.getMass());
        assertEquals(6000, giantStar.getTemperature());
        assertEquals("Giant Star", giantStar.getCentralBodyType());
    }

    @Test
    public void canSupernovaTest() {
        assertTrue(giantStar.canSupernova());
    }
}
