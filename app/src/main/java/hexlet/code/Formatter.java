package hexlet.code;

import java.util.List;
import java.util.stream.Collectors;

public class Formatter {

    public static String toString(List<NodeDiff> nodes, String format) {
        if (!format.toLowerCase().equals("stylish")) {
            return "Format " + format + " isn't supported";
        }
        String lineTemplate = "    %s %s: %s\n";
        String diffResult = "{\n";
        diffResult += nodes.stream()
                .map(node -> {
                    String resultStr;
                    switch (node.getType()) {
                        case NO_CHANGES:
                            resultStr = String.format(lineTemplate, " ", node.getKey(), node.getObjectFrom());
                            break;
                        case ADD:
                            resultStr = String.format(lineTemplate, "+", node.getKey(), node.getObjectTo());
                            break;
                        case DELETE:
                            resultStr = String.format(lineTemplate, "-", node.getKey(), node.getObjectFrom());
                            break;
                        case UPDATE:
                            resultStr = String.format(lineTemplate, "-", node.getKey(), node.getObjectFrom())
                                        + String.format(lineTemplate, "+", node.getKey(), node.getObjectTo());
                            break;
                        default:
                            resultStr = "";
                            break;
                    }
                    return resultStr;
                })
                .collect(Collectors.joining(""));
        diffResult += "}";
        return diffResult;
    }
}