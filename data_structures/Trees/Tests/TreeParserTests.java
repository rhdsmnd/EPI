import static org.junit.Assert.*;
import org.junit.Test;

public class TreeParserTests {

	@Test
	public void intTreeTest {
		String verbBinTreeInt = "(1 () ())";
		String simpBinTreeInt = "(1)";

		String handleWhitespace = "(   1 ( \n) (\t	)		)";
		String multilevelTree = "(1 (2) (3))";

		String incompleteTree = "(1 () (2 () ()))";
		String unbalancedTree = "(1 (2 (3 () ()) ()) ())";

		String largerMultilevelTree = "(1 (2 (6) (7)) (3 (8) (9)) ";

		String threeChildren = "(1 (2 (5) (6) (7)) (3 (8) (9) (10)) (4 (11) (12) (13)))";

	}
