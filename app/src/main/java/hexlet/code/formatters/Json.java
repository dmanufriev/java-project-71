package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import hexlet.code.nodes.DiffNode;

import java.util.List;

import hexlet.code.nodes.DiffNodeSerializer;

public class Json {

    public static String toString(List<DiffNode> nodes) throws JsonProcessingException {

        if (nodes == null) {
            return "";
        }

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module =
                new SimpleModule("NodeDiffSerializer",
                        new Version(1, 0, 0, null, null, null));
        module.addSerializer(DiffNode.class, new DiffNodeSerializer());
        mapper.registerModule(module);
        return mapper.writeValueAsString(nodes)
                        .replace("[{", "[\n  {")
                        .replace("},", "},\n  ")
                        .replace("}]", "}\n]");
    }
}
