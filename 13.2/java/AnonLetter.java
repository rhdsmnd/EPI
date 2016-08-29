import java.lang.String;
import java.util.HashMap;

public class AnonLetter {
    public static boolean canMake(String letter, String mag) {
        //Consider using arrayof 256 chars for ascii
        HashMap<Character, Integer> letterChars
                            = new HashMap<Character, Integer>();
        Integer numChars;
        for (int i = 0; i < letter.length(); i += 1) {
            numChars = letterChars.get(letter.charAt(i));
            if (numChars == null) {
                letterChars.put(letter.charAt(i), 1);
            } else {
                letterChars.put(letter.charAt(i), numChars + 1);
            }
        }

        for (int i = 0; i < mag.length(); i += 1) {
            numChars = letterChars.get(mag.charAt(i));
            if (numChars != null) {
                if (numChars > 1) {
                    letterChars.put(mag.charAt(i), numChars - 1);
                } else if (numChars == 1) {
                    letterChars.remove(mag.charAt(i));
                    if (letterChars.isEmpty()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
