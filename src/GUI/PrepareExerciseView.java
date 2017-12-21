package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class PrepareExerciseView extends GridPane{
    public PrepareExerciseView(){
        //test
        ArrayList<String> languageList = new ArrayList<>();
        languageList.add("English");
        languageList.add("Polish");

        Label translateFrom = new Label("Translate from: ");
        add(translateFrom,0,0);

        ComboBox fromComboBox = new ComboBox();
        for(String language:languageList){
            fromComboBox.getItems().add(language);
        }

        fromComboBox.setPromptText("Choose language");
        add(fromComboBox,1,0);
        Label translateTo = new Label("Translate to: ");
        add(translateTo,0,1);

        ComboBox toComboBox = new ComboBox();
        for(String language:languageList){
            toComboBox.getItems().add(language);
        }
        toComboBox.setPromptText("Choose language");
        add(toComboBox,1,1);

        Label numberOfWords = new Label("How many word do you want to translate?");
        add(numberOfWords,0,2);

        TextField inputNumber = new TextField();
        inputNumber.setMaxWidth(100);
        add(inputNumber,1,2);

        Button submit = new Button("Submit");
        submit.setMinSize(150, 50);
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                int n = Integer.parseInt(inputNumber.getText());
                String translatedLanguage = (String) toComboBox.getValue();
                String inputLanguage = (String) fromComboBox.getValue();
                ExerciseView exerciseView = new ExerciseView(n, translatedLanguage, inputLanguage);
                Main.getStage().setScene(new Scene(exerciseView));
            }
        });
        add(submit, 1, 3);

        setAlignment(Pos.CENTER);
        setVgap(20);
        setHgap(5);
    }
}
