import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class GameManager extends GameFrame implements ActionListener {

    private Deck deck;
    private GameFrame gameFrame;
    private int balance=1000;
    private int dealerSumOfPoints;
    private int playerSumOfPoints;
    private int betAmount;
    private Card hiddenDealerCard;
    private MyImage myImage=new MyImage();
    private ImageIcon cardIcon;

    private ArrayList<Card> playerHand =  new ArrayList<Card>();
    private ArrayList<Card> dealerHand = new ArrayList<Card>();


    public GameManager()
    {
            setNewGameFrame();
            setDeck();
            setActionListeners();
            setUI();

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getActionCommand()=="Bet")
        {
            if(getBetAmount()) {

              int tmp= checkDeckSize();

              if(tmp<=10)
              {
                  deck.shuffleUsedCards();
                  for(int i =0;i<deck.getUsedCardsDeckSize();i++)
                  {
                      deck.addCard(deck.getUsedCard(i));
                  }
                  deck.clearUsedCards();
              }
                balance -= betAmount;
                setBetAmount(betAmount);
                setUI();
                fillPlayerHand(2);
                fillDealerHand(2);
                hideSecondDealerCard();
                calculatePlayerPoints();
                calculateDealerPoints();
                setUI();
                setButtonsAfterBet();
                checkBlackjack();
            }

        }
        if(actionEvent.getActionCommand()=="Stand")
        {
            dealerHand.add(hiddenDealerCard);
            dealerSumOfPoints = 0;
            calculateDealerPoints();
            hitButton.setEnabled(false);
            unhideDealerCardImage();
            while(dealerSumOfPoints<16) {
                fillDealerHand(1);
                dealerSumOfPoints = 0;
                calculateDealerPoints();
            }
            setUI();
            checkDealerPoints();


        }

        if(actionEvent.getActionCommand()=="Hit")
        {

            fillPlayerHand(1);
            playerSumOfPoints=0;
            calculatePlayerPoints();
            setUI();
            checkPlayerPoints();


        }
    }


    private void setDeck()
    {
        deck = new Deck();
    }

    private void setActionListeners()
    {
        betButton.addActionListener(this);
        standButton.addActionListener(this);
        hitButton.addActionListener(this);
    }

    private void setUI()
    {
        playerBalance.setText("Your balance: "+ balance);
        dealerSum.setText("Dealer points: "+ dealerSumOfPoints);
        playerSum.setText("Your points: "+playerSumOfPoints);
    }

    private void setNewGameFrame()
    {
        gameFrame = new GameFrame();
    }

    private void setBetAmount(int amount)
    {
        betAmount=amount;
    }

    private boolean getBetAmount()
    {

        try {
            betAmountSpinner.commitEdit();
        } catch ( java.text.ParseException e )
        {
        }
        betAmount=(Integer)betAmountSpinner.getValue();

        if(betAmount>balance)
        {
            JOptionPane.showMessageDialog(gameFrame, "You don't have enough balance");
            return false;
        }
       else if(betAmount==0)
        {
            JOptionPane.showMessageDialog(gameFrame, "You can't bet 0 credits");
            return false;
        }
       else
        {
            return true;
        }

    }

    private void setButtonsAfterBet()
    {
        betButton.setEnabled(false);
        hitButton.setEnabled(true);
        standButton.setEnabled(true);
    }

    private void fillPlayerHand(int cardsAmount)
    {
        for(int i =0;i<cardsAmount;i++)
        {
            playerHand.add(deck.getCard());
            addCardImage(deck.getCard(),true);
            deleteCardFromDeck();
        }
    }

    private void fillDealerHand(int cardsAmount)
    {
        for(int i =0;i<cardsAmount;i++)
        {
            addCardImage(deck.getCard(),false);
            dealerHand.add(deck.getCard());
            deleteCardFromDeck();

        }

        if(cardsAmount==2)
        {
            hideDealerCardImage();
        }
    }


    private void deleteCardFromDeck()
    {
        deck.removeCard();
    }

    private void calculatePlayerPoints() {
        for (Card card : playerHand) {
            if (card.checkIfAce() == "ACE") {
                if (playerSumOfPoints >= 11) {
                    playerSumOfPoints += 2;
                } else {
                    playerSumOfPoints += 11;
                }
            } else {
                playerSumOfPoints += card.getCardValue();
            }
        }
    }

    private void calculateDealerPoints()
    {
        for(Card card: dealerHand)
        {
            if(card.checkIfAce()=="ACE") {
                if (dealerSumOfPoints >= 11) {
                    dealerSumOfPoints += 2;
                } else {
                    dealerSumOfPoints += 11;
                }
            }
            else
            {
                dealerSumOfPoints+=card.getCardValue();
            }
        }
    }

    private void checkPlayerPoints()
    {

        if(playerSumOfPoints>21)
        {
            JOptionPane.showMessageDialog(gameFrame, "You lost");
            resetGame();
        }
        if(playerSumOfPoints==21)
        {
            JOptionPane.showMessageDialog(gameFrame, "You won: "+ betAmount*2);
            balance+=betAmount*2;
            resetGame();
        }
    }

    private void checkBlackjack()
    {
        if(playerSumOfPoints==21)
        {
            JOptionPane.showMessageDialog(gameFrame, "You won: "+ betAmount*2);
            balance+=betAmount*2;
            resetGame();
        }
        if(dealerSumOfPoints==21)
        {
            JOptionPane.showMessageDialog(gameFrame, "You lost");
            resetGame();
        }
    }

    private void checkDealerPoints()
    {

        if(dealerSumOfPoints>21)
        {
            JOptionPane.showMessageDialog(gameFrame, "You won: "+ betAmount*2);
            balance+=betAmount*2;
            resetGame();
        }
        else if(dealerSumOfPoints>playerSumOfPoints)
        {
            JOptionPane.showMessageDialog(gameFrame, "You lost");
            resetGame();
        }
        else if(dealerSumOfPoints==playerSumOfPoints)
        {
            JOptionPane.showMessageDialog(gameFrame, "Tie ");
            balance+=betAmount;
            resetGame();
        }
        else
        {
            JOptionPane.showMessageDialog(gameFrame, "You won: "+ betAmount*2);
            balance+=betAmount*2;
            resetGame();
        }
    }


    private void resetGame()
    {
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
        betButton.setEnabled(true);
        playerHand.clear();
        dealerHand.clear();
        playerSumOfPoints=0;
        dealerSumOfPoints=0;
        playerImageContainer.removeAll();
        dealerImageContainer.removeAll();
        setUI();
    }

    private int checkDeckSize()
    {
        return deck.getDeckSize();
    }

private void hideSecondDealerCard()
{
    hiddenDealerCard = dealerHand.get(1);
    dealerHand.remove(1);
}


private void addCardImage(Card card,boolean playerDeck)
{
    URL imageUrl = this.getClass().getResource("/cards/" +  myImage.getPath(card));
    cardIcon = new ImageIcon(new ImageIcon(imageUrl).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
    if(playerDeck)
    {
       playerImageContainer.add(new JLabel(cardIcon));
    }
    else
    {
        dealerImageContainer.add(new JLabel(cardIcon));
    }




}

private void hideDealerCardImage()
{
    dealerImageContainer.remove(1);
    URL imageUrl = this.getClass().getResource("/cards/REDBACK.png");
    ImageIcon hiddenCardIcon=new ImageIcon(new ImageIcon(imageUrl).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
    dealerImageContainer.add(new JLabel(hiddenCardIcon));

}

private void unhideDealerCardImage()
{
    dealerImageContainer.remove(1);
    addCardImage(hiddenDealerCard,false);

}









}
