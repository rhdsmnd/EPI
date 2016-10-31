import static org.junit.Assert.*;
import org.junit.Test;

import java.io.PrintStream;
import java.lang.StringBuffer;

public class TesterTests {

	@Test
	public void testOutput() {
		PrintStream stdOut = System.out;	

		class CaptureOutput extends PrintStream {
			CaptureOutput(PrintStream capture) {
				super(capture);
				readBuf = new StringBuffer();
			}

			public void write(byte buf[], int off, int len) {
				super.write(buf, off, len);
				readBuf.append(new String(buf, off, len));
			}

			public String getOutput() {
				return readBuf.toString();
			}

			private StringBuffer readBuf;

		}

		CaptureOutput consoleOutput = new CaptureOutput(System.out);
		System.setOut(consoleOutput);
		Tester.runTests("simple_test.txt", true, (inp, verbose) -> {
					return inp.trim();
		});

		assertTrue(consoleOutput.getOutput().contains("Test summary: 4 / 4 passed"));

	}

}
