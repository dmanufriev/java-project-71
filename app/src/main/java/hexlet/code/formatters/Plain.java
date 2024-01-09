package hexlet.code.formatters;

import hexlet.code.nodes.DiffNodeType;
import hexlet.code.nodes.DiffNode;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Plain {

    public static String toString(List<DiffNode> nodes) {

        if (nodes == null) {
            return "";
        }

        return nodes.stream()
                    .filter(node -> node.getType() != DiffNodeType.NO_CHANGES)
                    .map(node -> {
                        StringBuilder builder = new StringBuilder("Property '");
                        builder.append(node.getKey());
                        builder.append("' ");
                        switch (node.getType()) {
                            case ADD:
                                builder.append("was added with value: ");
                                builder.append(objectToString(node.getObjectTo()));
                                break;
                            case DELETE:
                                builder.append("was removed");
                                break;
                            case UPDATE:
                                builder.append("was updated. From ");
                                builder.append(objectToString(node.getObjectFrom()));
                                builder.append(" to ");
                                builder.append(objectToString(node.getObjectTo()));
                                break;
                            default:
                                break;
                        }
                        return builder.toString();
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
