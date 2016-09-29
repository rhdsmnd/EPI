
public class Main {
	public static void main(String[] args) {
		boolean verbose = false;
		if (args.length > 1 &&
					(args[1].equals("--verbose") || args[1].equals("-v"))) {
				
		System.out.println("Running tests for Elements of Programming Interviews,"
							+ " problem 10.3: Least Common Ancestor.");

		Tester.runTests("./tests.txt", verbose, (inp, expOutput, verbose) -> {

		}

	}	
}
