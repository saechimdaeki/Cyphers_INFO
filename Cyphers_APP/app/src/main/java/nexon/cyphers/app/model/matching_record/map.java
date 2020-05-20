package nexon.cyphers.app.model.matching_record;

import java.util.HashMap;
import java.util.Map;

public class map {
    private Integer mapId;
    private String name;
    private Map<String, Object> additional = new HashMap<String, Object>();

    public Integer getMapId() {
        return mapId;
    }

    public String getName() {
        return name;
    }

    public java.util.Map<String, Object> getAdditional() {
        return additional;
    }
}
