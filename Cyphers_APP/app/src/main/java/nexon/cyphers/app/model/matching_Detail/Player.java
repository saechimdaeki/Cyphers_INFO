package nexon.cyphers.app.model.matching_Detail;

import java.util.List;

import nexon.cyphers.app.model.matching_record.PlayInfo;
import nexon.cyphers.app.model.matching_record.Position;
import nexon.cyphers.app.model.matching_record.map;


public class Player {
    private String playerId;

    private String nickname;

    private map map;

    private PlayInfo playInfo;

    private Position position;

    private List<Item> items = null;

    public String getPlayerId() {
        return playerId;
    }

    public String getNickname() {
        return nickname;
    }

    public nexon.cyphers.app.model.matching_record.map getMap() {
        return map;
    }

    public PlayInfo getPlayInfo() {
        return playInfo;
    }

    public Position getPosition() {
        return position;
    }

    public List<Item> getItems() {
        return items;
    }
}
