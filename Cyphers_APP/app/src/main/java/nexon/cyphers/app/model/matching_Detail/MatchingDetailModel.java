package nexon.cyphers.app.model.matching_Detail;

import java.util.List;

import nexon.cyphers.app.model.matching_Detail.Player;


public class MatchingDetailModel {
    private String date;
    private String gameTypeId;
    private List<Team> teams;
    private List<Player> players;

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGameTypeId() {
        return gameTypeId;
    }

    public void setGameTypeId(String gameTypeId) {
        this.gameTypeId = gameTypeId;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
