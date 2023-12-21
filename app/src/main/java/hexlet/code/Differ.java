package hexlet.code;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        Map<String, Object> map1 = Parser.toMap(filePath1);
        Map<String, Object> map2 = Parser.toMap(filePath2);
        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        List<NodeDiff> nodes = new ArrayList<>();
        for (var key : keys) {
            DiffType diffNodeType;
            var value1 = map1.get(key);
            var value2 = map2.get(key);
            if (!map1.containsKey(key)) {
                diffNodeType = DiffType.ADD;
            } else if (!map2.containsKey(key)) {
                diffNodeType = DiffType.DELETE;
            } else {
                if ((value1 == null) && (value2 == null) || (value1 != null && value1.equals(value2))) {
                    diffNodeType = DiffType.NO_CHANGES;
                } else {
                    diffNodeType = DiffType.UPDATE;
                }
            }
            nodes.add(new NodeDiff(diffNodeType, key, value1, value2));
        }
        return Formatter.toString(nodes, format);
    }
}
