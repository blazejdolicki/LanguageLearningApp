package GUI;

import Backend.Exercise;
import Backend.FileReader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ExerciseView extends GridPane{
    private int numberOfWords;
    private String translatedLanguage;
    private String inputLanguage;

    public ExerciseView(int numberOfWords, String translatedLanguage, String inputLanguage){
        this.numberOfWords =  numberOfWords;
        this.translatedLanguage = translatedLanguage;
        this.inputLanguage = inputLanguage;

        Label description = new Label("Translate to "+ inputLanguage);
        description.setFont(Font.font("Verdana", FontWeight.BOLD, 13));
        add(description,0,0);
        for(int i=1;i<=numberOfWords;i++){

            Label wordOutput = new Label("wordOutput"+i); // test text
            add(wordOutput,0,i);

            TextField wordInput = new TextField();
            add(wordInput,2,i);

        }


        Button submit = new Button("Submit");
        submit.setMinSize(150, 50);
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event){


            }
        });
        add(submit, 1, numberOfWords+1);




        setAlignment(Pos.CENTER);
        setHalignment(submit, HPos.CENTER);
        setVgap(10);

    }
}
