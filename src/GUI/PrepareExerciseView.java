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

        CustomLabel translateFrom = new CustomLabel("Translate from: ");
        add(translateFrom,0,0);

        ComboBox fromComboBox = new ComboBox();
        for(String language:fileReader.getLanguagesList().keySet()){
            fromComboBox.getItems().add(language);
        }

        fromComboBox.setPromptText("English");
        add(fromComboBox,1,0);
        CustomLabel translateTo = new CustomLabel("Translate to: ");
        add(translateTo,0,1);

        ComboBox toComboBox = new ComboBox();
        for(String language:fileReader.getLanguagesList().keySet()){
            toComboBox.getItems().add(language);
        }
        toComboBox.setPromptText("Polish");
        add(toComboBox,1,1);

        CustomLabel numberOfWords = new CustomLabel("Number of words in exercise (0 - 10)");
        add(numberOfWords,0,2);

        TextField inputNumber = new TextField("6");
        inputNumber.setMaxWidth(100);
        add(inputNumber,1,2);

        CustomLabel errorLabel = new CustomLabel();
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

    public static void checkAndRun(TextField inputNumber, CustomLabel errorLabel, ComboBox fromComboBox, ComboBox toComboBox){
        System.out.println(inputNumber.getText());
        if(!inputNumber.getText().equals("")){
            int n= Integer.parseInt(inputNumber.getText());
            String fromValue, toValue;
            if(fromComboBox.getValue()==null){
                fromValue=fromComboBox.getPromptText();
            }
            else{
                fromValue=(String) fromComboBox.getValue();
            }
            if(toComboBox.getValue()==null){
                toValue = toComboBox.getPromptText();
            }
            else{
                toValue = (String) toComboBox.getValue();
            }
            if(n>10||n<1){
                errorLabel.setText("You must input number between 1 and 10.");
            }
            else if(toValue.equals(fromValue)){
                errorLabel.setText("Languages can't be the same.");
            }
            //tutaj musisz pozmieniac troche bo nie moze byc null.equals(costam)
            else{
                String inputLanguage;
                String translatedLanguage;
                if(toComboBox.getValue()==null){
                    inputLanguage = toComboBox.getPromptText();
                }
                else{
                    inputLanguage = (String) toComboBox.getValue();
                }
                if(fromComboBox.getValue()==null){
                    translatedLanguage = fromComboBox.getPromptText();
                }
                else{
                    translatedLanguage = (String) fromComboBox.getValue();
                }

                ExerciseView exerciseView = new ExerciseView(n, translatedLanguage, inputLanguage);
                Main.getStage().setScene(new Scene(exerciseView));
            }
        }
        else{
            errorLabel.setText("You must input a number of words.");
        }
    }
}
