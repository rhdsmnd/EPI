

public class Main {
    public static void main(String[] args) {
		Tester.runMain(args, 13, 2, "Anonymous Letter", (input, verbose) -> {
			int firstQuoteInd = input.indexOf("\"");
			if (firstQuoteInd == -1) {
				return "";
			}
			int secondQuoteInd = input.indexOf("\"", firstQuoteInd + 1);
			if (secondQuoteInd == -1) {
				return "";
			}
			int thirdQuoteInd = input.indexOf("\"", secondQuoteInd + 1);
			if (thirdQuoteInd == -1) {
				return "";
			}
			int fourthQuoteInd = input.indexOf("\"", thirdQuoteInd + 1);
			if (fourthQuoteInd == -1) {
				return "";
			}
			
			String letter = input.substring(firstQuoteInd + 1,
											secondQuoteInd);
			String dict = input.substring(thirdQuoteInd + 1,
											fourthQuoteInd);
			return AnonLetter.canMake(letter, dict).toString();
		});
    }
}
