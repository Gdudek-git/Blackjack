public class Card {

    private CardSuit cardSuit;
    private CardValue cardValue;


    public Card(CardValue cardValue, CardSuit cardSuit)
    {
        this.cardValue =  cardValue;
        this.cardSuit = cardSuit;
    }

    public int getCardValue()
    {
        int value=0;
        switch (this.cardValue.toString())
        {
            case "TWO": value=2;break;
            case "THREE": value=3;break;
            case "FOUR": value=4;break;
            case "FIVE": value=5;break;
            case "SIX": value=6;break;
            case "SEVEN": value=7;break;
            case "EIGHT": value=8;break;
            case "NINE": value=9;break;
            case "TEN": value=10;break;
            case "JACK": value=10;break;
            case "QUEEN": value=10;break;
            case "KING": value=10;break;
        }
        return value;
    }

    public String getCardType()
    {
        return this.cardValue.toString() + this.cardSuit.toString();
    }

    public String checkIfAce()
    {
        return this.cardValue.toString();
    }






}
