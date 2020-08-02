package View;

import Model.FileReader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

/**
 *
 * @author Błażej
 * @since 31/01/2018
 */

public class PrepareTranslatorView extends GridPane{
        private static String fromValue, toValue, inputWord;

        public PrepareTranslatorView() {

            CustomLabel translateFrom = new CustomLabel("Translate from: ");
            add(translateFrom,0,0);

            ArrayList<String> languages = new ArrayList<>();
            languages.add("English");
            languages.add("French");
            languages.add("Polish");

            ComboBox fromComboBox = new ComboBox();
            // before languages: fileReader.getLanguagesList().keySet()
            for(String language:languages){
                fromComboBox.getItems().add(language);
            }

            fromComboBox.setPromptText("English");
            add(fromComboBox,1,0);
            CustomLabel translateTo = new CustomLabel("Translate to: ");
            add(translateTo,0,1);

            ComboBox toComboBox = new ComboBox();
            for(String language:languages){
                toComboBox.getItems().add(language);
            }
            toComboBox.setPromptText("Polish");
            add(toComboBox,1,1);

            setValues(fromComboBox,toComboBox);

            Button swap = new Button("Swap languages");
            swap.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    swapValues(fromComboBox, toComboBox);
                }
            });
            add(swap, 1, 2);

            CustomLabel inputLabel = new CustomLabel("Translate word: ");
//            add(inputLabel,0,3);

            TextField inputText = new TextField();
            inputText.setMinWidth(100);
            add(inputText,1,3);

            CustomLabel errorLabel = new CustomLabel();
            errorLabel.setTextFill(Color.RED);
            add(errorLabel,0,4);


            CustomLabel resultTextLabel = new CustomLabel();
            add(resultTextLabel,0,5);
            resultTextLabel.setText("Result: ");

            CustomLabel resultLabel = new CustomLabel();
            add(resultLabel,0,6);
            resultLabel.setFont(Font.font("Verdana", FontWeight.NORMAL, 18));

            Button submit = new Button("Submit");
            submit.setMinSize(150, 50);
            submit.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    checkAndRun(inputText, errorLabel, fromComboBox, toComboBox, resultLabel);
                }
            });
            add(submit, 1, 4);

            Button backToMenu = new Button("Back to menu");
            backToMenu.setMinSize(150, 50);
            EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    MenuView menu = new MenuView();
                    Main.getStage().setScene(new Scene(menu));
                }
            };
            backToMenu.setOnAction(handler);

            add(backToMenu, 2, 4);

            setAlignment(Pos.CENTER);
            setVgap(20);
            setHgap(5);
        }

        public static void swapValues(ComboBox fromComboBox, ComboBox toComboBox){
            toComboBox.setPromptText(fromValue);
            fromComboBox.setPromptText(toValue);
            String temp = fromValue;
            fromValue = toValue;
            toValue = temp;
        }

        public static void setValues(ComboBox fromComboBox, ComboBox toComboBox){
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
        }

    public static void checkAndRun(TextField inputText, CustomLabel errorLabel, ComboBox fromComboBox, ComboBox toComboBox, CustomLabel resultLabel){
        errorLabel.setText("");
        if(!inputText.getText().equals("")){
            if(toValue.equals(fromValue)){
                errorLabel.setText("Languages can't be the same.");
            }
            else if(inputText.getText().equals(inputWord)){

            }
            else{
                String toLanguageString;
                String fromLanguageString;
                if(toComboBox.getValue()==null){
                    toLanguageString = toComboBox.getPromptText();
                }
                else{
                    toLanguageString = (String) toComboBox.getValue();
                }
                if(fromComboBox.getValue()==null){
                    fromLanguageString = fromComboBox.getPromptText();
                }
                else{
                    fromLanguageString = (String) fromComboBox.getValue();
                }
                //tu napisz metode ktora translatuje
                String fileName;
                if(fromLanguageString=="French"||toLanguageString=="French"){
                    fileName = "PhrasebookFR";
                }
                else{
                    fileName = "Phrasebook";
                }

                FileReader fileReader = new FileReader(fileName);
                ArrayList<String> fromLanguage = fileReader.getLanguagesList().get(fromLanguageString);
                ArrayList<String> toLanguage = fileReader.getLanguagesList().get(toLanguageString);
                inputWord = inputText.getText();
                for(int x=0;x<fromLanguage.size();x++){
                    if(inputWord.equals(fromLanguage.get(x))){
                        String inputAnswer = toLanguage.get(x);
                        resultLabel.setText(inputAnswer);
                    }
                }
                

            }
        }
        else{
            errorLabel.setText("You must input a number of words.");
        }
    }
}
