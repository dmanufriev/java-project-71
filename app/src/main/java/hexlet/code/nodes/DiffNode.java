package hexlet.code.nodes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiffNode {
    private DiffNodeType type;
    private String key;
    private Object objectFrom;
    private Object objectTo;
}
