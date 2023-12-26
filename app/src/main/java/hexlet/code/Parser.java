package hexlet.code;

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

// TODO       Map<String, Object> employee = objectMapper.readValue(file, new TypeReference<>(){});
        return mapper.readValue(Files.readString(path), Map.class);
    }
}
