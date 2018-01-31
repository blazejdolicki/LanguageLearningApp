package View;

import javafx.scene.control.Button;

/**
 *
 * @author Błażej
 * @since 31/01/2018
 */

public class CounterButton extends Button{
    private int counter;
    public CounterButton(int counter){
        this.counter = counter;
    }

    public int getCounter(){
        return counter;
    }
}
