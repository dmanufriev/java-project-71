package hexlet.code.nodes;

public class DiffNode {
    private DiffNodeType type;
    private String key;
    private Object objectFrom;
    private Object objectTo;

    public DiffNode(DiffNodeType type, String key, Object objectFrom, Object objectTo) {
        this.type = type;
        this.key = key;
        this.objectFrom = objectFrom;
        this.objectTo = objectTo;
    }

    public DiffNodeType getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public Object getObjectFrom() {
        return objectFrom;
    }

    public Object getObjectTo() {
        return objectTo;
    }
}
