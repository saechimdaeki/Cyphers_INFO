package nexon.cyphers.app.model.matching_record;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class matchingRecordModel {
    private Matches matches;
    private Map<String, Object> additional = new HashMap<String, Object>();

    public Matches getMatches() {
        return matches;
    }

    public Map<String, Object> getAdditional() {
        return additional;
    }
}
