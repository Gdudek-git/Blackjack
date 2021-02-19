import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameManager extends GameFrame implements ActionListener {

    private Deck deck;



    public GameManager()
    {
            new GameFrame();
            deck = new Deck();
            betButton.addActionListener(this);
            standButton.addActionListener(this);
            hitButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getActionCommand()=="Bet")
        {
            System.out.println("dzialam");
        }
        if(actionEvent.getActionCommand()=="Stand")
        {
            System.out.println("dzialdsadasam");
        }

        if(actionEvent.getActionCommand()=="Hit")
        {
            System.out.println("dhhhhlam");
        }
    }
}
