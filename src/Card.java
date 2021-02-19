public class Card {

    private CardSuit cardSuit;
    private CardValue cardValue;


    public Card(CardValue cardValue, CardSuit cardSuit)
    {
        this.cardValue =  cardValue;
        this.cardSuit = cardSuit;
    }

    public CardValue getCardValue()
    {
        return this.cardValue;
    }

    public String getCardType()
    {
        return this.cardValue.toString() + " " + this.cardSuit.toString();
    }






}
