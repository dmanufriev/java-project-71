package hexlet.code.nodes;

import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Objects;

public final class DiffNodeGenerator {
    Map<String, Object> mapFrom;
    Map<String, Object> mapTo;

    public DiffNodeGenerator(Map<String, Object> mapFrom, Map<String, Object> mapTo) {
        this.mapFrom = mapFrom;
        this.mapTo = mapTo;
    }

    public List<DiffNode> generate() {

        Set<String> keys = new TreeSet<>(mapFrom.keySet());
        keys.addAll(mapTo.keySet());

        List<DiffNode> nodes = new ArrayList<>();
        for (String key : keys) {
            nodes.add(createNode(key));
        }
        return nodes;
    }

    private DiffNode createNode(String key) {
        DiffNodeType diffNodeType;
        if (!mapTo.containsKey(key)) {
            diffNodeType = DiffNodeType.DELETE;
        } else if (!mapFrom.containsKey(key)) {
            diffNodeType = DiffNodeType.ADD;
        } else if (!Objects.equals(mapFrom.get(key), mapTo.get(key))) {
            diffNodeType = DiffNodeType.UPDATE;
        } else {
            diffNodeType = DiffNodeType.NO_CHANGES;
        }

        return new DiffNode(diffNodeType, key, mapFrom.get(key), mapTo.get(key));
    }
}
