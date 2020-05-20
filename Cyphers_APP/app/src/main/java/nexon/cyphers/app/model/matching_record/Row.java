package nexon.cyphers.app.model.matching_record;

import java.util.HashMap;
import java.util.Map;

public class Row {
    private String date;
    private String matchId;
    private PlayInfo playInfo;
    private map map;
    private Position position;
    private Map<String, Object> additional = new HashMap<String, Object>();

    public String getDate() {
        return date;
    }

    public String getMatchId() {
        return matchId;
    }

    public PlayInfo getPlayInfo() {
        return playInfo;
    }

    public map getMap() {
        return map;
    }

    public Position getPosition() {
        return position;
    }

    public Map<String, Object> getAdditional() {
        return additional;
    }

}
