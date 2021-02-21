import javax.swing.*;

public class MyImage {

    private String path;


    public MyImage(){}

    public String getPath(Card card) {
        path=card.getCardType()+".png";
        return path;
    }
}
