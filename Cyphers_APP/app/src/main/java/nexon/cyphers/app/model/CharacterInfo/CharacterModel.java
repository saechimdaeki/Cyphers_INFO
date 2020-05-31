package nexon.cyphers.app.model.CharacterInfo;


/* retrofit2가아닌 Jsoup 캐릭터 정보를 위한 클래스. */
public class CharacterModel {
    String characterId;
    String characterName;

    public String getCharacterId() {
        return characterId;
    }

    public void setCharacterId(String characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }
}
