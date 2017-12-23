package GUI;

import Backend.FileReader;
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
import javafx.scene.paint.Color;

import javax.xml.soap.Text;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PrepareExerciseView extends GridPane{

    public PrepareExerciseView() throws FileNotFoundException{
        FileReader fileReader = new FileReader();

        Label translateFrom = new Label("Translate from: ");
        add(translateFrom,0,0);

        ComboBox fromComboBox = new ComboBox();
        for(String language:fileReader.getLanguages()){
            fromComboBox.getItems().add(language);
        }

        fromComboBox.setPromptText("Choose language");
        add(fromComboBox,1,0);
        Label translateTo = new Label("Translate to: ");
        add(translateTo,0,1);

        ComboBox toComboBox = new ComboBox();
        for(String language:fileReader.getLanguages()){
            toComboBox.getItems().add(language);
        }
        toComboBox.setPromptText("Choose language");
        add(toComboBox,1,1);

        Label numberOfWords = new Label("Number of words in exercise (0 - 10)");
        add(numberOfWords,0,2);

        TextField inputNumber = new TextField();
        inputNumber.setMaxWidth(100);
        add(inputNumber,1,2);

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        add(errorLabel,0,3);

        Button submit = new Button("Submit");
        submit.setMinSize(150, 50);
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                checkAndRun(inputNumber, errorLabel, fromComboBox, toComboBox);
            }
        });
        add(submit, 1, 3);

        setAlignment(Pos.CENTER);
        setVgap(20);
        setHgap(5);
    }

    public static void checkAndRun(TextField inputNumber, Label errorLabel, ComboBox fromComboBox, ComboBox toComboBox){
        System.out.println(inputNumber.getText());
        if(!inputNumber.getText().equals("")){
            int n= Integer.parseInt(inputNumber.getText());

            if(n>10||n<1){
                errorLabel.setText("You must input number between 1 and 10.");
            }
            else if(toComboBox.getValue()==null||fromComboBox.getValue()==null){
                errorLabel.setText("You must input languages.");
            }
            else if(toComboBox.getValue().equals(fromComboBox.getValue())){
                errorLabel.setText("Languages can't be the same.");
            }
            else{
                String translatedLanguage = (String) fromComboBox.getValue();
                String inputLanguage = (String) toComboBox.getValue();
                ExerciseView exerciseView = new ExerciseView(n, translatedLanguage, inputLanguage);
                Main.getStage().setScene(new Scene(exerciseView));
            }
        }
        else{
            errorLabel.setText("You must input a number of words.");
        }
    }
}
