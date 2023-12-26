package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.util.List;

public class Formatter {

    public static final String PLAIN_FORMAT = "plain";
    public static final String STYLISH_FORMAT = "stylish";
    public static final String JSON_FORMAT = "json";


    public static String toString(List<NodeDiff> nodes, String format) {

        if (format.toLowerCase().equals(PLAIN_FORMAT)) {
            return Plain.toString(nodes);
        }

        if (format.toLowerCase().equals(STYLISH_FORMAT)) {
            return Stylish.toString(nodes);
        }

        if (format.toLowerCase().equals(JSON_FORMAT)) {
            try {
                return Json.toString(nodes);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }

        return "Format " + format + " isn't supported";
    }
}
