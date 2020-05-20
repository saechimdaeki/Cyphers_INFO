package nexon.cyphers.app.model.matching_record;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Position {
    private String name;
    private String explain;
    private List<Attribute> attribute;
    private Map<String, Object> additional = new HashMap<String, Object>();

    public String getName() {
        return name;
    }

    public String getExplain() {
        return explain;
    }

    public List<Attribute> getAttribute() {
        return attribute;
    }

    public Map<String, Object> getAdditional() {
        return additional;
    }
}
