package hexlet.code;
import hexlet.code.formatters.Formatter;
import hexlet.code.nodes.DiffNodeType;
import hexlet.code.nodes.DiffNode;
import hexlet.code.parsers.DataSupplier;
import hexlet.code.parsers.ParserFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, Formatter.STYLISH_FORMAT);
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {

        ParserFactory parserFactory = new ParserFactory();
        Map<String, Object> map1 = parserFactory.getParser(DataSupplier.getFileExtension(filePath1))
                                                .parse(DataSupplier.getData(filePath1));
        Map<String, Object> map2 = parserFactory.getParser(DataSupplier.getFileExtension(filePath2))
                                                .parse(DataSupplier.getData(filePath2));

        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        List<DiffNode> nodes = new ArrayList<>();
        for (String key : keys) {
            DiffNodeType diffNodeType;
            if (!map1.containsKey(key)) {
                diffNodeType = DiffNodeType.ADD;
            } else if (!map2.containsKey(key)) {
                diffNodeType = DiffNodeType.DELETE;
            } else {
                Object value1 = map1.get(key);
                Object value2 = map2.get(key);
                if ((value1 == null) && (value2 == null) || (value1 != null && value1.equals(value2))) {
                    diffNodeType = DiffNodeType.NO_CHANGES;
                } else {
                    diffNodeType = DiffNodeType.UPDATE;
                }
            }
            nodes.add(new DiffNode(diffNodeType, key, map1.get(key), map2.get(key)));
        }
        return Formatter.toString(nodes, format);
    }
}
