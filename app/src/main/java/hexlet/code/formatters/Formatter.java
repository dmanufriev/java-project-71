package hexlet.code.formatters;

import hexlet.code.nodes.DiffNode;
import java.util.List;

public class Formatter {

    public static final String PLAIN_FORMAT = "plain";
    public static final String STYLISH_FORMAT = "stylish";
    public static final String JSON_FORMAT = "json";


    public static String toString(List<DiffNode> nodes, String format) throws Exception {

        switch (format.toLowerCase()) {
            case PLAIN_FORMAT:
                return Plain.toString(nodes);

            case STYLISH_FORMAT:
                return Stylish.toString(nodes);

            case JSON_FORMAT:
                return Json.toString(nodes);

            default:
                throw new Exception("Format " + format + " isn't supported");
        }
    }
}
