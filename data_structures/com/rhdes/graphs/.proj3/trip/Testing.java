package trip;

/* You MAY add public @Test methods to this class.  You may also add
 * additional public classes containing "Testing" in their name. These
 * may not be part of your trip package per se (that is, it must be
 * possible to remove them and still have your package work). */

import org.junit.Test;
import ucb.junit.textui;
import static org.junit.Assert.*;

/** Unit tests for the trip package. */
public class Testing {

    /** Run all JUnit tests in the graph package. */
    public static void main(String[] ignored) {
        System.exit(textui.runClasses(trip.Testing.class));
    }

    @Test
    public void simpleTest() {
        Location l1 = new Location("A", 5.0, 1.0);
        Location l2 = new Location("B", 1.0, 1.0);
        Location l3 = new Location("C", 1.0, 5.0);
        Location l4 = new Location("D", 5.0, 5.0);
        Road r1 = new Road("AB", 4.0, "W", l1, l2);
        Road r2 = new Road("BC", 4.0, "N", l1, l2);
        Road r3 = new Road("CD", 4.0, "E", l1, l2);
        Road r4 = new Road("DA", 4.0, "S", l1, l2);
        assertEquals((int) r1.weight(), 4);
        assertEquals(r2.getDir(), "N");
        assertEquals(r3.getTo(), l2);
        assertEquals(r4.toString(), "DA");
    }
}
