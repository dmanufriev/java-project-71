package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import hexlet.code.NodeDiff;

import java.util.List;

import hexlet.code.NodeDiffSerializer;

public class Json {

    public static String toString(List<NodeDiff> nodes) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("NodeDiffSerializer",
                        new Version(1, 0, 0, null, null, null));
        module.addSerializer(NodeDiff.class, new NodeDiffSerializer());
        mapper.registerModule(module);
        return mapper.writeValueAsString(nodes)
                        .replace("[{", "[\n  {")
                        .replace("},", "},\n  ")
                        .replace("}]", "}\n]");
    }
}
