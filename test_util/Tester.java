import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class Tester {

	private Tester() { }

	// consider implementing Runnable interface
	static class TestCase {
		public TestCase(String input, String expOutput, TestLogic func) {
			this(input, expOutput, func, false);
		}

		public TestCase(String input, String expOutput, TestLogic func, boolean verbose) {
			this.input = input;
			this.expOutput = expOutput;
			output = "";
			this.func = func;
			passed = false;
			ran = false;
			this.verbose = verbose;
		}

		private String input;
		private String expOutput;
		private String output;
		private TestLogic func;
		private boolean passed;
		private boolean ran;
		private boolean verbose;

		public boolean runTestCase() {
			if (ran) {
				System.out.println("Already ran test case.");
				return passed;
			} else {
				output = func.runTestCase(input, expOutput, verbose);
				passed = output.equals(expOutput);

				if (passed) {
					// consider adding a title or description to TestCase instead of using input
					System.out.println("Test passed: " + input);
				} else if (verbose) {
					System.out.println("Test failed: " + input);
					System.out.println("----- Expected -----");
					System.out.println(expOutput);
					System.out.println("----- Actual -----");
					System.out.println(output);
				} else {
					System.out.println("Test failed: " + input);
				}

				ran = true;
				return passed;
			}
		}
	}

	interface TestLogic {
		String runTestCase(String inp, String expOutput, boolean verbose);
	}

	public static void runTests(String testFileName, boolean verbose, TestLogic func) {
		Integer testCasesRun = 0;
		Integer successfulRun = 0;
		Scanner inpScanner = null;
		try {
			inpScanner = new Scanner(new File(testFileName));
			String line;

			StringBuffer input;
			String expOutput;
			boolean startedParsing = false;

			/** Parse case by case. */
			while ((line = getNextLine(inpScanner)) != null) {
				/** If loop exits before parsing an entire case, we know an error occurred by checking STARTEDPARSING. */
				startedParsing = true;

				/** Parse input arguments line by line until we hit "-- Expected --" line. */
				input = new StringBuffer();
				while (!line.equals("-- Expected --")) {
					input.append(line + "\n");
					line = getNextLine(inpScanner);
					if (line == null) {
						System.out.println("Error parsing test case: no \"-- Expected --\" line");
					}
				}

				line = getNextLine(inpScanner);
				if (line == null) {
					System.out.println("Error parsing test case: no solution");
				}
				expOutput = line;

				// set verbose later
				Tester.TestCase cur = new Tester.TestCase(input.toString(), expOutput, func, verbose);

				boolean passed = cur.runTestCase();

				testCasesRun += 1;
				if (passed) {
					successfulRun += 1;
				}

				if ((line = getNextLine(inpScanner)) != null && !line.equals("")) {
					System.out.println("No blank line after test case.");
				}
				startedParsing = false;

			}
			System.out.println("Test summary: " + successfulRun.toString() + " / " + testCasesRun.toString() + " passed");
		} catch(IOException e) {
			System.out.println("IOException in runTests: " + e.toString());
		} finally {
			if (inpScanner != null) inpScanner.close();
		}
	}

	private static String getNextLine(Scanner testFile) {
		if (testFile.hasNextLine()) {
			return testFile.nextLine();
		} else {
			return null;
		}
	}
}
