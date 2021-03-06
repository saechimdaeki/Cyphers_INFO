package nexon.cyphers.app.model.matching_Detail;

import java.util.HashMap;
import java.util.Map;

public class PlayDetailInfo {
    private Boolean random;

    private Integer partyUserCount;

    private String characterId;

    private String characterName;

    private Integer level;

    private Integer killCount;

    private Integer deathCount;

    private Integer assistCount;

    private Integer attackPoint;

    private Integer damagePoint;

    private Integer battlePoint;

    private Integer sightPoint;

    private Integer playTime;

    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Boolean getRandom() {
        return random;
    }

    public Integer getPartyUserCount() {
        return partyUserCount;
    }

    public String getCharacterId() {
        return characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getKillCount() {
        return killCount;
    }

    public Integer getDeathCount() {
        return deathCount;
    }

    public Integer getAssistCount() {
        return assistCount;
    }

    public Integer getAttackPoint() {
        return attackPoint;
    }

    public Integer getDamagePoint() {
        return damagePoint;
    }

    public Integer getBattlePoint() {
        return battlePoint;
    }

    public Integer getSightPoint() {
        return sightPoint;
    }

    public Integer getPlayTime() {
        return playTime;
    }
}
