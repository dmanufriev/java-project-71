package hexlet.code.formatters;

import hexlet.code.nodes.DiffNode;
import java.util.List;
import java.util.stream.Collectors;

public class Stylish {

    public static String toString(List<DiffNode> nodes) {

        if (nodes == null) {
            return "";
        }

        String lineTemplate = "  %s %s: %s\n";
        StringBuilder resBuilder = new StringBuilder("{\n");
        resBuilder.append(nodes.stream()
                .map(node -> {
                    StringBuilder builder = new StringBuilder();
                    switch (node.getType()) {
                        case NO_CHANGES:
                            builder.append(String.format(lineTemplate, " ", node.getKey(), node.getObjectFrom()));
                            break;
                        case ADD:
                            builder.append(String.format(lineTemplate, "+", node.getKey(), node.getObjectTo()));
                            break;
                        case DELETE:
                            builder.append(String.format(lineTemplate, "-", node.getKey(), node.getObjectFrom()));
                            break;
                        case UPDATE:
                            builder.append(String.format(lineTemplate, "-", node.getKey(), node.getObjectFrom()));
                            builder.append(String.format(lineTemplate, "+", node.getKey(), node.getObjectTo()));
                            break;
                        default:
                            throw new RuntimeException("Unknown node type: " + node.getType());
                    }
                    return builder.toString();
                })
                .collect(Collectors.joining(""))
        );
        resBuilder.append("}");
        return resBuilder.toString();
    }

}
