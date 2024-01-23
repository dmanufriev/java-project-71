package hexlet.code;
import hexlet.code.formatters.Formatter;
import hexlet.code.nodes.DiffNodeGenerator;
import hexlet.code.nodes.DiffNode;
import hexlet.code.parsers.DataSupplier;
import hexlet.code.parsers.ParserFactory;

import java.util.Map;
import java.util.List;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, Formatter.STYLISH_FORMAT);
    }

    public static String generate(String filePath1, String filePath2, String format) throws Exception {

        Map<String, Object> map1 = getData(filePath1);
        Map<String, Object> map2 = getData(filePath2);

        DiffNodeGenerator diffNodeGenerator = new DiffNodeGenerator(map1, map2);
        List<DiffNode> nodes = diffNodeGenerator.generate();

        return Formatter.toString(nodes, format);
    }

    private static Map<String, Object> getData(String filePath) throws Exception {
        String content = DataSupplier.getData(filePath);
        String dataFormat = DataSupplier.getFileExtension(filePath);
        ParserFactory parserFactory = new ParserFactory();
        return parserFactory.getParser(dataFormat).parse(content);
    }
}
