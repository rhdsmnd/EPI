
import com.rhdes.graphs.GraphTests;
import com.rhdes.trees.TreeTests;
import com.rhdes.stacks.StackTests;
import com.rhdes.queues.QueueTests;

import org.junit.Test;
import ucb.junit.textui;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Comparator;

/* You MAY add public @Test methods to this class.  You may also add
 * additional public classes containing "Testing" in their name. These
 * may not be part of your graph package per se (that is, it must be
 * possible to remove them and still have your package work). */

/** Unit tests for the graph package. */
public class DataStructuresTester {

    /** Run all JUnit tests in the graph package. */
    public static void main(String[] args) {
        if (args.length > 0) {
    	    if (args[0].equals("graphs")) {
    		    System.exit(textui.runClasses(com.rhdes.graphs.GraphTests.class));
    	    } else if (args[0].equals("trees")) {
    		    System.exit(textui.runClasses(com.rhdes.trees.TreeTests.class));
    	    } else if (args[0].equals("stacks")) {
    		    System.exit(textui.runClasses(com.rhdes.stacks.StackTests.class));
    	    } else if (args[0].equals("queues")) {
    		    System.exit(textui.runClasses(com.rhdes.queues.QueueTests.class));
    	    } else if (!args[0].equals("all")) {
    		    System.err.println("Unrecognized test class: " + args[0]);
    		    System.exit(1);
    	    }
        }
        System.exit(textui.runClasses(GraphTests.class,
										TreeTests.class,
										StackTests.class));
    }

}
