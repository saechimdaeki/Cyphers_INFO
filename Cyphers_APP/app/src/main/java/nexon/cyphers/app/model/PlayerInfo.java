package nexon.cyphers.app.model;

import java.util.List;

public class PlayerInfo {
     String clanName;
     int ratingPoint;
     int maxRatingPoint;
     String tierName;
     List<GameTypeModel> records;

    public String getClanName() {
        return clanName;
    }

    public void setClanName(String clanName) {
        this.clanName = clanName;
    }

    public int getRatingPoint() {
        return ratingPoint;
    }

    public void setRatingPoint(int ratingPoint) {
        this.ratingPoint = ratingPoint;
    }

    public int getMaxRatingPoint() {
        return maxRatingPoint;
    }

    public void setMaxRatingPoint(int maxRatingPoint) {
        this.maxRatingPoint = maxRatingPoint;
    }

    public String getTierName() {
        return tierName;
    }

    public void setTierName(String tierName) {
        this.tierName = tierName;
    }

    public List<GameTypeModel> getRecords() {
        return records;
    }

    public void setRecords(List<GameTypeModel> records) {
        this.records = records;
    }
}
