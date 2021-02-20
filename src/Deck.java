import java.util.ArrayList;
import java.util.Collections;

public class Deck  {

    private ArrayList<Card> cards = null;
    private ArrayList<Card> usedCards = null;


    public Deck()
    {
        this.cards = new ArrayList<Card>();
        this.usedCards = new ArrayList<Card>();
        fillDeck();
        shuffle();
    }


    private void fillDeck()
    {
        for(CardValue value: CardValue.values())
        {
            for(CardSuit suit:CardSuit.values())
            {
                cards.add(new Card(value,suit));
            }
        }
    }

    private void shuffle()
    {
        Collections.shuffle(cards);
    }

    public void removeCard()
    {
        usedCards.add(cards.get(0));
        cards.remove(0);
    }

    public void addCard(Card cardToAdd)
    {
        cards.add(cardToAdd);
    }

    public void shuffleUsedCards()
    {
        Collections.shuffle(usedCards);
    }

    public Card getCard()
    {
        return cards.get(0);
    }

    public int getDeckSize()
    {
        return cards.size();
    }

    public int getUsedCardsDeckSize()
    {
        return usedCards.size();
    }
    public Card getUsedCard(int index)
    {
        return usedCards.get(index);
    }

    public void clearUsedCards()
    {
        usedCards.clear();
    }




}
