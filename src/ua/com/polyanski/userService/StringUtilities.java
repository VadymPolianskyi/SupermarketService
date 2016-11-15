package ua.com.polyanski.userService;

/**
 * Created by vadym on 15.11.2016.
 */
public class StringUtilities {

    public static String pad(int fieldWidth, char padChar, String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length(); i < fieldWidth; i++) {
            sb.append(padChar);
        }
        sb.append(s);

        return sb.toString();
    }
}