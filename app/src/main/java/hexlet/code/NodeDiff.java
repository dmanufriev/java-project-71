package hexlet.code;

public class NodeDiff {
    private DiffType type;
    private String key;
    private Object objectFrom;
    private Object objectTo;

    public NodeDiff(DiffType type, String key, Object objectFrom, Object objectTo) {
        this.type = type;
        this.key = key;
        this.objectFrom = objectFrom;
        this.objectTo = objectTo;
    }

    public DiffType getType() {
        return type;
    }

    public void setType(DiffType type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getObjectFrom() {
        return objectFrom;
    }

    public void setObjectFrom(Object objectFrom) {
        this.objectFrom = objectFrom;
    }

    public Object getObjectTo() {
        return objectTo;
    }

    public void setObjectTo(Object objectTo) {
        this.objectTo = objectTo;
    }
}

enum DiffType {
    NO_CHANGES,
    ADD,
    DELETE,
    UPDATE
}
