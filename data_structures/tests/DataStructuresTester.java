import ucb.junit.textui;

/* You MAY add public @Test methods to this class.  You may also add
 * additional public classes containing "Testing" in their name. These
 * may not be part of your graph package per se (that is, it must be
 * possible to remove them and still have your package work). */

/** Unit tests for the graph package. */
public class DataStructuresTester {

	static final String[] allTests = {"graphs", "queues", "trees", "stacks"};

    /** Run all JUnit tests in the graph package. */
    public static void main(String[] args) {

		if (args.length == 0) {
			args = allTests;
		}

		int testRet;
		for (String arg: args) {
			if (arg.length() < 2) {
				//invalid string
				continue;
			}

			String modified = arg.substring(0, 1).toUpperCase()
								+ arg.substring(1, arg.length() - 1);

			String className = "com.rhdes." + arg + "."
									+ modified + "Tests";
			try {
				System.out.println("Running Test Class for: " + arg);
				System.out.println("- - - - -");
				Class<?> testClass = ClassLoader.getSystemClassLoader().loadClass(className);

				testRet = textui.runClasses(testClass);
				System.out.println("- - - - -");
				System.out.println("Exit code for test runner: " + testRet);
				System.out.println("-----------------------------");
			} catch(ClassNotFoundException e) {
				System.out.println("Could not find test class for data structure "
									+ arg);
				System.out.println(e);
			}
		}
    }
}
