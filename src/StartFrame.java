

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class StartFrame extends JFrame {

    private static int WIDTH;
    private static int HEIGHT;
    private JLabel title = null;
    private JLabel author = null;
    private JButton startButton = null;
    private JPanel panelForButtonAndIcon = null;
    private ImageIcon buttonIcon = null;

    public StartFrame()
    {
        getScreenSize();
        setTitle("Blackjack");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3,0));
        setLabels();
        add(title);
        setIcon();
        setButton();
        add(panelForButtonAndIcon);
        add(author);


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

    private void setLabels()
    {
        title = new JLabel("Blackjack");
        title.setFont(new Font("Dialog", Font.PLAIN, 50));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setOpaque(true);
        title.setBackground(Color.lightGray);

        author = new JLabel("Grzegorz Dudek  ");
        author.setFont(new Font("Serif", Font.PLAIN, 25));
        author.setHorizontalAlignment(JLabel.RIGHT);
        author.setVerticalAlignment(JLabel.BOTTOM);
        author.setOpaque(true);
        author.setBackground(Color.lightGray);

    }


    private void setButton()
    {

        startButton = new JButton("START");
        startButton.setFont(new Font("Serif", Font.PLAIN, 25));
        startButton.setFocusable(false);
        startButton.setPreferredSize(new Dimension(300,50));
        startButton.setBackground(Color.gray);

        panelForButtonAndIcon.add(startButton);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
                GameManager gameFrame = new GameManager();
                gameFrame.setVisible(true);
            }
        });



    }

    private void setIcon()
    {
        panelForButtonAndIcon = new JPanel();
        URL iconUrl = this.getClass().getResource("/icon.png");
        buttonIcon = new ImageIcon(new ImageIcon(iconUrl).getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        JLabel label = new JLabel(buttonIcon);
        panelForButtonAndIcon.add(label);
        panelForButtonAndIcon.setBackground(Color.lightGray);

    }







}
