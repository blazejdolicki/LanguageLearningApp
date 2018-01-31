package View;

import javafx.scene.control.Button;

public class CounterButton extends Button{
    private int counter;
    public CounterButton(int counter){
        this.counter = counter;
    }

    public int getCounter(){
        return counter;
    }
}
