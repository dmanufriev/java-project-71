package hexlet.code.nodes;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public final class DiffNodeSerializer extends StdSerializer<DiffNode> {

    public DiffNodeSerializer() {
        this(null);
    }

    public DiffNodeSerializer(Class<DiffNode> t) {
        super(t);
    }

    @Override
    public void serialize(DiffNode node, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("key", node.getKey());
        DiffNodeType diffNodeType = node.getType();
        gen.writeStringField("action", diffNodeType.toString());
        if (diffNodeType != DiffNodeType.ADD) {
            gen.writeObjectField("objectFrom", node.getObjectFrom());
        }
        if (diffNodeType != DiffNodeType.DELETE) {
            gen.writeObjectField("objectTo", node.getObjectTo());
        }
        gen.writeEndObject();
    }
}
