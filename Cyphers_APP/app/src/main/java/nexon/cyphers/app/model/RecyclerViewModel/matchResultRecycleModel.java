package nexon.cyphers.app.model.RecyclerViewModel;


/* 텍스트뷰와 이미지뷰에 넣을것이기때문에 전부 String 으로 모델링처리  */
public class matchResultRecycleModel {
    String matchingType;// 공식/ 파티유무/ 맵이름/ 날짜 /플레이시간 다들어갈예정
    String matchingCharacterImage;
    String matchingCharacterPosition;
    String matchingCharacterPositionAttribute1;
    String matchingCharacterPositionAttribute2;
    String matchingCharacterPositionAttribute3;
    String CharacterNameLevel;
    String dealingPoint;
    String damagedPoint;
    String KDA;
    String KDAPOINT;
    String BattlePoint;
    String SightPoint;
    String playtime;
    String matchingResult;
    String matchId;

    public String getMatchId() {
        return matchId;
    }

    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getMatchingResult() {
        return matchingResult;
    }

    public void setMatchingResult(String matchingResult) {
        this.matchingResult = matchingResult;
    }

    public String getPlaytime() {
        return playtime;
    }

    public void setPlaytime(String playtime) {
        this.playtime = playtime;
    }

    public String getKDAPOINT() {
        return KDAPOINT;
    }

    public void setKDAPOINT(String KDAPOINT) {
        this.KDAPOINT = KDAPOINT;
    }

    public String getCharacterNameLevel() {
        return CharacterNameLevel;
    }

    public void setCharacterNameLevel(String characterNameLevel) {
        CharacterNameLevel = characterNameLevel;
    }

    public String getMatchingType() {
        return matchingType;
    }

    public void setMatchingType(String matchingType) {
        this.matchingType = matchingType;
    }

    public String getMatchingCharacterImage() {
        return matchingCharacterImage;
    }

    public void setMatchingCharacterImage(String matchingCharacterImage) {
        this.matchingCharacterImage = matchingCharacterImage;
    }

    public String getMatchingCharacterPosition() {
        return matchingCharacterPosition;
    }

    public void setMatchingCharacterPosition(String matchingCharacterPosition) {
        this.matchingCharacterPosition = matchingCharacterPosition;
    }

    public String getMatchingCharacterPositionAttribute1() {
        return matchingCharacterPositionAttribute1;
    }

    public void setMatchingCharacterPositionAttribute1(String matchingCharacterPositionAttribute1) {
        this.matchingCharacterPositionAttribute1 = matchingCharacterPositionAttribute1;
    }

    public String getMatchingCharacterPositionAttribute2() {
        return matchingCharacterPositionAttribute2;
    }

    public void setMatchingCharacterPositionAttribute2(String matchingCharacterPositionAttribute2) {
        this.matchingCharacterPositionAttribute2 = matchingCharacterPositionAttribute2;
    }

    public String getMatchingCharacterPositionAttribute3() {
        return matchingCharacterPositionAttribute3;
    }

    public void setMatchingCharacterPositionAttribute3(String matchingCharacterPositionAttribute3) {
        this.matchingCharacterPositionAttribute3 = matchingCharacterPositionAttribute3;
    }

    public String getDealingPoint() {
        return dealingPoint;
    }

    public void setDealingPoint(String dealingPoint) {
        this.dealingPoint = dealingPoint;
    }

    public String getDamagedPoint() {
        return damagedPoint;
    }

    public void setDamagedPoint(String damagedPoint) {
        this.damagedPoint = damagedPoint;
    }

    public String getKDA() {
        return KDA;
    }

    public void setKDA(String KDA) {
        this.KDA = KDA;
    }

    public String getBattlePoint() {
        return BattlePoint;
    }

    public void setBattlePoint(String battlePoint) {
        BattlePoint = battlePoint;
    }

    public String getSightPoint() {
        return SightPoint;
    }

    public void setSightPoint(String sightPoint) {
        SightPoint = sightPoint;
    }
}

