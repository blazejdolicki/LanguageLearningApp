package View;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;

/**
 *
 * @author Błażej
 * @since 31/01/2018
 */

public class MenuView extends GridPane{
    public MenuView(){

        Label mainMenu = new Label("Main menu");
        mainMenu.setFont(Font.font("Verdana", FontWeight.NORMAL, 38));
        add(mainMenu,0,0);
        setHalignment(mainMenu, HPos.CENTER);

        Button exercise = new Button("Translate words");
        exercise.setFont(Font.font("Verdana",FontWeight.NORMAL,23));
        exercise.setMinSize(250, 80);
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
         add(exercise, 0, 1);
        setHalignment(exercise, HPos.CENTER);


        Button sentences = new Button("Fill gaps in sentences");
        sentences.setFont(Font.font("Verdana",FontWeight.NORMAL,23));
        sentences.setMinSize(250,80);
        sentences.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // test parameters, should be made generic later on
                SentencesView sentencesView = new SentencesView(5,"English","Polish",3);
                Main.getStage().setScene(new Scene(sentencesView));
            }

        });

        add(sentences, 0,2);
        setHalignment(sentences, HPos.CENTER);


        Button translateWord = new Button("Translate word");
        translateWord.setFont(Font.font("Verdana",FontWeight.NORMAL,23));
        translateWord.setMinSize(250,80);
        translateWord.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PrepareTranslatorView translatorView = new PrepareTranslatorView();
                Main.getStage().setScene(new Scene(translatorView));
            }

        });

//        add(translateWord, 0,3);
        setHalignment(translateWord, HPos.CENTER);

        setAlignment(Pos.CENTER);
        setVgap(10);

    }
}
