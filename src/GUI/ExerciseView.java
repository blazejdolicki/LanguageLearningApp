package GUI;

import Backend.Exercise;
import Backend.FileReader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import sun.text.resources.iw.FormatData_iw_IL;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class ExerciseView extends GridPane{
    private int numberOfWords;
    private String translatedLanguage;
    private String inputLanguage;
    LinkedHashMap<String, String> words;

    public ExerciseView(int numberOfWords, String translatedLanguage, String inputLanguage){
        this.numberOfWords =  numberOfWords;
        this.translatedLanguage = translatedLanguage;
        this.inputLanguage = inputLanguage;

        if(numberOfWords>5){
            Main.getStage().setWidth(700);
            Main.getStage().setHeight(700);
        }

        Label description = new Label("Translate to "+ inputLanguage);
        description.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        add(description,0,0);

        try{
            words = Exercise.printExercise(numberOfWords, translatedLanguage, inputLanguage);
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        TextField[] textFields = new TextField[numberOfWords];
        for(int i=1;i<=numberOfWords;i++){
            String outputText = (String)words.keySet().toArray()[i-1];
            Label wordOutput = new Label(outputText); // test text
            add(wordOutput,0,i);

            TextField wordInput = new TextField();
            textFields[i-1]=wordInput;
            add(wordInput,2,i);

        }





        Button submit = new Button("Submit");
        submit.setMinSize(150, 50);
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){
                ResultsView resultsView = new ResultsView(translatedLanguage, inputLanguage, words, textFields);
                Main.getStage().setScene(new Scene(resultsView));
            }
        });
        add(submit, 1, numberOfWords+1);




        setAlignment(Pos.CENTER);
        setHalignment(submit, HPos.CENTER);
        setVgap(10);

    }
}
