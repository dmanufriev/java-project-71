package hexlet.code;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class NodeDiffSerializer extends StdSerializer<NodeDiff> {

    public NodeDiffSerializer() {
        this(null);
    }

    public NodeDiffSerializer(Class<NodeDiff> t) {
        super(t);
    }

    @Override
    public void serialize(NodeDiff node, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("key", node.getKey());
        DiffType diffType = node.getType();
        gen.writeStringField("action", diffType.toString());
        if (diffType != DiffType.ADD) {
            gen.writeObjectField("objectFrom", node.getObjectFrom());
        }
        if (diffType != DiffType.DELETE) {
            gen.writeObjectField("objectTo", node.getObjectTo());
        }
        gen.writeEndObject();
    }
}
