package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(String filePath1, String filePath2) throws Exception {

        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();

        if (!Files.exists(path1)) {
            throw new Exception("File '" + path1 + "' does not exist");
        }
        if (!Files.exists(path2)) {
            throw new Exception("File '" + path2 + "' does not exist");
        }

        ObjectMapper mapper = new ObjectMapper();
        TreeMap<String, Object> mapFile1 = mapper.readValue(Files.readString(path1), TreeMap.class);
        TreeMap<String, Object> mapFile2 = mapper.readValue(Files.readString(path2), TreeMap.class);

        Set<String> keys = new TreeSet<>(mapFile1.keySet());
        keys.addAll(mapFile2.keySet());

        String lineTemplate = "    %s %s: %s\n";
        String diffResult = "{\n";
        diffResult += keys.stream()
                            .map(key -> {
                                if (mapFile1.containsKey(key) && (mapFile2.containsKey(key))) {
                                    if (mapFile1.get(key).equals(mapFile2.get(key))) {
                                        return String.format(lineTemplate, " ", key, mapFile1.get(key));
                                    } else {
                                        return String.format(lineTemplate, "-", key, mapFile1.get(key))
                                                + String.format(lineTemplate, "+", key, mapFile2.get(key));
                                    }
                                } else if (mapFile1.containsKey(key)) {
                                    return String.format(lineTemplate, "-", key, mapFile1.get(key));
                                } else {
                                    return String.format(lineTemplate, "+", key, mapFile2.get(key));
                                }
                            })
                            .collect(Collectors.joining(""));
        diffResult += "}";

        return diffResult;
    }

}
