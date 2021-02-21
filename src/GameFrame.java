import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.net.URL;

public class GameFrame extends  JFrame{


    protected JButton betButton;
    protected JButton standButton;
    protected JButton hitButton;
    protected JPanel rootPanel;
    protected JPanel playerImageContainer;
    protected JPanel dealerImageContainer;
    protected JLabel playerBalance;
    protected JLabel playerSum;
    protected JLabel dealerSum;
    protected JSpinner betAmountSpinner;


    public GameFrame()
    {

            setScreenSize();
            setTitle("Blackjack");
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            add(rootPanel);
            setJSpinnerToNumericOnly();


    }

    private void setScreenSize()
    {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(screenSize.width-50,screenSize.height-50);
    }

    private  void setJSpinnerToNumericOnly()
    {
        JFormattedTextField txt = ((JSpinner.NumberEditor) betAmountSpinner.getEditor()).getTextField();
        ((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
    }





}
