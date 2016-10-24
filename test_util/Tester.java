import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class Tester {

	private Tester() { }

	interface TestLogic {
		boolean runTestCase(StringBuffer inp, String expOutput, boolean verbose);
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
			while ((line = inpScanner.nextLine()) != null) {
				/** If loop exits before parsing an entire case, we know an error occurred by checking STARTEDPARSING. */
				startedParsing = true;

				/** Parse input arguments line by line until we hit "-- Expected --" line. */
				input = new StringBuffer();
				while (!line.equals("-- Expected --")) {
					input.append(line + "\n");
					line = inpScanner.nextLine();
					if (line == null) {
						System.out.println("Error parsing test case: no \"-- Expected --\" line");
					}
				}

				line = inpScanner.nextLine();
				if (line == null) {
					System.out.println("Error parsing test case: no solution");
				}
				expOutput = line;

				if ((line = inpScanner.nextLine()) == null || !line.equals("")) {
					System.out.println("No blank line after test case.");
				}
				startedParsing = false;

				boolean testResult = func.runTestCase(input, expOutput, verbose);

				testCasesRun += 1;
				if (testResult) {
					successfulRun += 1;
				}
			}
			System.out.println("Test summary: " + successfulRun.toString() + " / " + testCasesRun.toString() + " passed");
		} catch(IOException e) {
			System.out.println("IOException in runTests: " + e.toString());
		} finally {
			if (inpScanner != null) inpScanner.close();
		}
	}
}
