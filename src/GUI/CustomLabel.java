package GUI;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CustomLabel extends Label {
    private final int fontSize = 15;
    private int index;

    public CustomLabel(String text){
        setFont(Font.font("Verdana", FontWeight.NORMAL, fontSize));
        setText(text);
    }

    public CustomLabel(){
        setFont(Font.font("Verdana", FontWeight.NORMAL, fontSize));
    }

    public CustomLabel(int index){
        this.index = index;
        setFont(Font.font("Verdana", FontWeight.NORMAL, fontSize));
    }

    public int getFontSize(){
        return fontSize;
    }

    public int getIndex(){
        return index;
    }


}
