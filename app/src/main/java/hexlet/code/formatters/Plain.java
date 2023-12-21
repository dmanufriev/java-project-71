package hexlet.code.formatters;

import hexlet.code.DiffType;
import hexlet.code.NodeDiff;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Plain {

    public static String toString(List<NodeDiff> nodes) {
        if (nodes == null) {
            return "";
        }
        return nodes.stream()
                    .filter(node -> node.getType() != DiffType.NO_CHANGES)
                    .map(node -> {
                        String resultString = "Property '" + node.getKey() + "' ";
                        switch (node.getType()) {
                            case ADD:
                                resultString += "was added with value: " + objectToString(node.getObjectTo());
                                break;
                            case DELETE:
                                resultString += "was removed";
                                break;
                            case UPDATE:
                                resultString += "was updated. From " + objectToString(node.getObjectFrom())
                                                + " to " + objectToString(node.getObjectTo());
                                break;
                            default:
                                resultString = "";
                                break;
                        }
                        return resultString;
                    })
                .collect(Collectors.joining("\n"));
    }

    private static String objectToString(Object object) {
        if (object == null) {
            return "null";
        }
        if (object instanceof String) {
            return "'" + object + "'";
        }
        if (object instanceof Collection<?> || object instanceof Map<?, ?>) {
            return "[complex value]";
        }
        return object.toString();
    }
}
