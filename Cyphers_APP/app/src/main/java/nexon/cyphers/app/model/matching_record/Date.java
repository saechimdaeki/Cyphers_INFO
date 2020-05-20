package nexon.cyphers.app.model.matching_record;

import java.util.HashMap;
import java.util.Map;

public class Date {
    private String start;
    private String end;
    private Map<String, Object> additional = new HashMap<String, Object>();

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additional;
    }
}
