package nexon.cyphers.app.model.matching_record;

import java.util.HashMap;
import java.util.Map;

public class Attribute {
    private Integer level;
    private String id;
    private String name;
    private Map<String, Object> additional = new HashMap<String, Object>();

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Map<String, Object> getAdditional() {
        return this.additional;
    }

    public void setAdditional(String name, Object value) {
        this.additional.put(name, value);
    }
}
