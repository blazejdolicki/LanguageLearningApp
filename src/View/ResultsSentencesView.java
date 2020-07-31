package View;

import Model.Exercise;
import Model.FileReader;
import Model.Probability;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.HashMap;

public class ResultsSentencesView extends ScrollPane {
    public ResultsSentencesView(String toLanguageString, HashMap<String,String> chosenSentences, HashMap<String,TextField> wordInputs){
        FileReader fileReader = new FileReader("Phrasebook");
        ArrayList<String> toLanguage = fileReader.getLanguagesList().get(toLanguageString);
        VBox vbox = new VBox(8);
        System.out.println("Size "+wordInputs.size());
        int correctAnswers = 0;
        int wrongAnswers = 0;
//         TODO put the following "for" somehow in a method in Exercise
        double[] probabilityArray = Probability.readProbabilityArray(toLanguage.size());
        int x = 0;

        for(String sentence:chosenSentences.keySet()){
            System.out.println("Word "+sentence);
            int globalIndex = Exercise.getRandomIndices().get(x);
            TextFlow textFlow = new TextFlow();
            String answerWord = chosenSentences.get(sentence);
            String wordInput = wordInputs.get(sentence).getText();
            System.out.println("Word "+wordInput);
            String[] splitSentence = sentence.split(answerWord);

            Text answerText = new Text(answerWord);
            answerText.setFill(Color.GREEN);
            answerText.setFont(Font.font("Verdana", FontWeight.BOLD,15));
            Text sentenceText1 = new Text(splitSentence[0]);
            Text sentenceText2 = new Text(splitSentence[1]);
            textFlow.setMaxWidth(560);
            textFlow.getChildren().addAll(sentenceText1,answerText,sentenceText2);
            Text answer = new Text();

            if(wordInput.equals(answerWord)){
                answer.setText("Correct answer!");
                answer.setFill(Color.GREEN);
                if(probabilityArray[globalIndex]==0.0){
                    probabilityArray[globalIndex]=1.0;
                }
                else{
                    double value = probabilityArray[globalIndex];
                    probabilityArray[globalIndex]=value+1;
                }
                correctAnswers++;
            }
            else{
                answer.setText("Wrong answer - Your answer was: "+wordInput);
                answer.setFill(Color.RED);
                wrongAnswers++;
            }
            vbox.getChildren().addAll(textFlow,answer);

        }
        Text score = new Text("You've filled in "+correctAnswers+" out of "+(correctAnswers+wrongAnswers)+" sentences correctly.");

        Button submit = new Button("Repeat");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SentencesView sentencesView = new SentencesView(5,"English","Polish",4);
                Main.getStage().setScene(new Scene(sentencesView));
            }
        });
        Button backToMenu = new Button("Back to menu");
        backToMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MenuView menuView = new MenuView();
                Main.getStage().setScene(new Scene(menuView));
            }
        });
        submit.setMinSize(150, 50);
        backToMenu.setMinSize(150,50);
        vbox.getChildren().addAll(score,submit,backToMenu);
//        getChildren().add(vbox);
        setPadding(new Insets(50,40,50,40));
        //setCenter(vbox);
        setContent(vbox);
        Main.getStage().setWidth(700);
        Main.getStage().setHeight(700);
        // for each of the words indicate how many sentences you want
        // handle exception when there is not enough sentences for a word
        // get randomly (for now) sentences and display them
        // add the gaps
        // add a results view
    }
}
