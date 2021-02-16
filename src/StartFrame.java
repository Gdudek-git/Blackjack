

import javax.swing.*;
import java.awt.*;

public class StartFrame extends JFrame {

    private static int WIDTH;
    private static int HEIGHT;




    public StartFrame()
    {
        getScreenSize();
        setTitle("Blackjack");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

    }



    private void getScreenSize()
    {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setFrameSize(screenSize);
    }

    private void setFrameSize(Dimension screenSize)
    {
        WIDTH = screenSize.width/2;
        HEIGHT = screenSize.height/2;
        setSize(WIDTH,HEIGHT);
    }



}
