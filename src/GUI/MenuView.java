package GUI;

import Backend.Exercise;
import Backend.FileReader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuView extends GridPane{
    public MenuView(){

        Button exercise = new Button("Exercise");
        exercise.setMinSize(150, 50);
        exercise.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event){
                 try{
                     PrepareExerciseView prepareView = new PrepareExerciseView();
                     Main.getStage().setScene(new Scene(prepareView));
                 }
                 catch(IOException e){
                     System.out.println("File not found.");
                 }
             }
         });
         add(exercise, 0, 0);
        setHalignment(exercise, HPos.CENTER);

        Button translateWord = new Button("Translate word");
        translateWord.setMinSize(150,50);
        translateWord.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PrepareTranslatorView translatorView = new PrepareTranslatorView();
                Main.getStage().setScene(new Scene(translatorView));

            }
        });
        add(translateWord, 0,1);
        setHalignment(translateWord, HPos.CENTER);

        setAlignment(Pos.CENTER);
        setVgap(10);

    }
}
