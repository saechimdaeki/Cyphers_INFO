package nexon.cyphers.app.model.matching_record;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Matches {
    private Date date;
    private String gameTypeId;
    private String next;
    private List<Row> rows;
    private Map<String, Object> additional = new HashMap<String, Object>();

    public Date getDate() {
        return date;
    }

    public String getGameTypeId() {
        return gameTypeId;
    }

    public String getNext() {
        return next;
    }

    public List<Row> getRows() {
        return rows;
    }

    public Map<String, Object> getAdditional() {
        return additional;
    }
}
