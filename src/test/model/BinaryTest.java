package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryTest {
    private CentralBody blackHole;
    private CentralBody neutronStar;
    private CentralBody whiteDwarf1;
    private CentralBody whiteDwarf2;
    private CentralBody mainSequence;

    private Binary testBinary;

    @BeforeEach
    public void runBefore(){
        blackHole = new BlackHole(5);
        neutronStar = new NeutronStar(1.7);
        whiteDwarf1 = new WhiteDwarf(0.7);
        whiteDwarf2 = new WhiteDwarf(1);

    }

    @Test
    public void ConstructorTest(){
        testBinary = new Binary(blackHole, neutronStar);
        assertEquals(blackHole, testBinary.getCentralBody1());
        assertEquals(neutronStar, testBinary.getCentralBody2());
    }

    // Test coalesce with black hole involved
    @Test
    public void coalesceTestBlackHole(){}

    // Test coalesce with black hole and neutron star involved
    @Test
    public void coalesceTestBlackHoleNeutronStar(){}

    // Test coalesce with neutron star involved
    @Test
    public void coalesceTestNeutronStar(){}

    // Test coalesce with one white dwarf
    // not enough mass for supernova
    @Test
    public void coalesceTestWhiteDwarf(){}

    // Test coalesce with one white dwarf
    // enough mass for supernova
    @Test
    public void coalesceTestWhiteDwarfSupernovae(){}

    // Test coalesce with two white dwarf
    // not enough mass for supernova
    @Test
    public void coalesceTestWhiteDwarfs(){}

    // Test coalesce with two white dwarf
    // enough mass for supernova
    @Test
    public void coalesceTestWhiteDwarfsSupernovae(){}

    // Test coalesce with two non-degenerate stars
    // one giant star - becomes red giant
    @Test
    public void coalesceTestGiant(){}

    // Test coalesce with two non-degenerate stars
    // two giant stars - becomes red giant
    @Test
    public void coalesceTestGiants(){}

    // Test coalesce with two non-degenerate stars
    // two main sequence - becomes red giant
    @Test
    public void coalesceTestMainSequences(){}

}
