package cn.xyh.tree.domain;

public class Card {
    private int cardId;
    private int userId;
    private String cardType;
    private String cardContent;
    private String cardName;
    private String cardSpecies;

    public String getCardSpecies() {
        return cardSpecies;
    }

    public void setCardSpecies(String cardSpecies) {
        this.cardSpecies = cardSpecies;
    }
    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardContent() {
        return cardContent;
    }

    public void setCardContent(String cardContent) {
        this.cardContent = cardContent;
    }


}
