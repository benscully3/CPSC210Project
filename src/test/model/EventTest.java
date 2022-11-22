package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event e;
    private Date d;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("Created galaxy");   // (1)
        d = Calendar.getInstance().getTime();   // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Created galaxy", e.getDescription());
        double t1 = d.getTime();
        double t2 = e.getDate().getTime();
        assertTrue(100 > abs(t1 - t2)); // asserts that the times are within 100 ms of each other
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Created galaxy", e.toString());
    }
}