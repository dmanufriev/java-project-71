package hexlet.code.parsers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static Map<String, Object> toMap(String filePath) throws Exception {

        Path path = Paths.get(filePath).toAbsolutePath().normalize();
        if (!Files.exists(path)) {
            throw new Exception("File '" + path + "' does not exist");
        }

        ObjectMapper mapper;
        if (filePath.endsWith(".json")) {
            mapper = new ObjectMapper();
        } else if (filePath.endsWith(".yml")) {
            mapper = new YAMLMapper();
        } else {
            throw new Exception("File '" + filePath + "' has wrong extension");
        }

        return mapper.readValue(Files.readString(path), new TypeReference<>() { });
    }
}
