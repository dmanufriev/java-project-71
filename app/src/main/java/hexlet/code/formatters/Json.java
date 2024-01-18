package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.nodes.DiffNode;
import java.util.List;

public class Json {

    public static String toString(List<DiffNode> nodes) throws JsonProcessingException {

        if (nodes == null) {
            return "";
        }

        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(nodes)
                        .replace("[{", "[\n  {")
                        .replace("},", "},\n  ")
                        .replace("}]", "}\n]");
    }
}
