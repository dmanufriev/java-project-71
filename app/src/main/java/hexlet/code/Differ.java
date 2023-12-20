package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {

        Map<String, Object> map1 = Parser.toMap(filePath1);
        Map<String, Object> map2 = Parser.toMap(filePath2);
        Set<String> keys = new TreeSet<>(map1.keySet());
        keys.addAll(map2.keySet());

        String lineTemplate = "    %s %s: %s\n";
        String diffResult = "{\n";
        diffResult += keys.stream()
                .map(key -> {
                    String resultStr;
                    if (map1.containsKey(key) && (map2.containsKey(key))) {
                        if (map1.get(key).equals(map2.get(key))) {
                            resultStr = String.format(lineTemplate, " ", key, map1.get(key));
                        } else {
                            resultStr = String.format(lineTemplate, "-", key, map1.get(key))
                                    + String.format(lineTemplate, "+", key, map2.get(key));
                        }
                    } else if (map1.containsKey(key)) {
                        resultStr = String.format(lineTemplate, "-", key, map1.get(key));
                    } else {
                        resultStr = String.format(lineTemplate, "+", key, map2.get(key));
                    }
                    return resultStr;
                })
                .collect(Collectors.joining(""));
        diffResult += "}";

        return diffResult;
    }
}
