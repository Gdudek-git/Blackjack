import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyImage  {

    private String path;


    public MyImage(){}

    public String getPath(Card card) {
        path=card.getCardType()+".png";
        return path;
    }


}
