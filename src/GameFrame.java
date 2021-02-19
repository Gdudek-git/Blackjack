import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class GameFrame extends  JFrame{


    protected JButton betButton;
    protected JButton standButton;
    protected JButton hitButton;
    protected JPanel rootPanel;
    protected JPanel playerImageContainer;
    protected JPanel aiImageContainer;
    protected JLabel playerBalance;
    protected JLabel playerSum;
    protected JLabel aiSum;
    protected JSpinner betAmout;


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
