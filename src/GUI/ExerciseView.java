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

import javax.swing.*;
import java.awt.event.ActionListener;
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
    private int i;


    public ExerciseView(int numberOfWords, String translatedLanguage, String inputLanguage){
        this.numberOfWords =  numberOfWords;
        this.translatedLanguage = translatedLanguage;
        this.inputLanguage = inputLanguage;


        if(numberOfWords>5){
            Main.getStage().setWidth(700);
            Main.getStage().setHeight(700);
        }

        Main.getStage().setWidth(800);

        CustomLabel description = new CustomLabel("Translate to "+ inputLanguage);
        description.setFont(Font.font("Verdana", FontWeight.BOLD, description.getFontSize()));
        add(description,0,0);

        try{
            words = Exercise.printExercise(numberOfWords, translatedLanguage, inputLanguage);
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        TextField[] textFields = new TextField[numberOfWords];
        i=1;
        while(i<=numberOfWords){
            String outputText = (String)words.keySet().toArray()[i-1];
            CustomLabel wordOutput = new CustomLabel(outputText); // test text
            add(wordOutput,0,i);

            TextField wordInput = new TextField();
            textFields[i-1]=wordInput;
            add(wordInput,2,i);

            CounterButton clue = new CounterButton(i);
            clue.setText("Clue");

            CustomLabel clueLabel = new CustomLabel(i);
            clue.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println(outputText);
                    clueLabel.setText(Exercise.printClue(clueLabel.getIndex()-1, outputText, inputLanguage));
                    int counterParameter = clue.getCounter();
                    getChildren().remove(clue);
                    add(clueLabel, 3, counterParameter);
                }
            });
            add(clue, 3,i);
            i++;
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
