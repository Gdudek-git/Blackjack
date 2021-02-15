import javax.swing.*;
import java.awt.*;

public class GameFrame  extends JFrame {

    private static int WIDTH;
    private static int HEIGHT;


    public static void main(String[] args)
    {
         GameFrame frame = new GameFrame();
        frame.setTitle("Blackjack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public GameFrame()
    {
        getScreenSize();

    }



    private void getScreenSize()
    {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setFrameSize(screenSize);
    }

    private void setFrameSize(Dimension screenSize)
    {
        WIDTH = screenSize.width;
        HEIGHT = screenSize.height;
        setSize(WIDTH,HEIGHT);
    }
}
