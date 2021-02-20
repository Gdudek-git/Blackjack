import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class GameManager extends GameFrame implements ActionListener {

    private Deck deck;
    private GameFrame gameFrame;
    private int balance=1000;
    private int dealerSumOfPoints;
    private int playerSumOfPoints;
    private int betAmount;

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
                calculatePlayerPoints();
                calculateDealerPoints();
                setUI();
                setButtonsAfterBet();
                checkBlackjack();
            }


        }
        if(actionEvent.getActionCommand()=="Stand")
        {
            hitButton.setEnabled(false);
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

    private void threadSleep()
    {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
            deleteCardFromDeck();
        }
    }

    private void fillDealerHand(int cardsAmount)
    {
        for(int i =0;i<cardsAmount;i++)
        {
            dealerHand.add(deck.getCard());
            deleteCardFromDeck();
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
        setUI();
    }

    private int checkDeckSize()
    {
        return deck.getDeckSize();
    }









}
