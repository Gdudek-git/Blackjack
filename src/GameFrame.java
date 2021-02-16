import javax.swing.*;
import java.awt.*;

public class GameFrame extends  JFrame{


    private JButton betButton;
    private JButton standButton;
    private JButton hitButton;
    private JPanel rootPanel;


    public GameFrame()
    {

            setScreenSize();
            setTitle("Blackjack");
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            add(rootPanel);
    }

    private void setScreenSize()
    {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(screenSize.width-50,screenSize.height-50);
    }



}
