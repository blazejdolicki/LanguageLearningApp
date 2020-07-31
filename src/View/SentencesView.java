package View;

import Model.Exercise;
import Model.FileReader;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.io.FileNotFoundException;
import java.util.*;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class SentencesView extends ScrollPane {
    LinkedHashMap<String, String> words;
    ArrayList<String> transformedWords;
    public static String fileName;
    public LinkedHashMap<String,ArrayList<String>> sentences;
    private static ArrayList<Integer> randomIndices;

    public SentencesView(int numberOfWords, String fromLanguageString, String toLanguageString, int sentencesPerWord){
        try{
            words = Exercise.printExercise(numberOfWords, fromLanguageString, toLanguageString);
            transformedWords = new ArrayList<>();
            for(String word:words.keySet()){
                transformedWords.add(word);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        String sentencesLanguage;
        if(fromLanguageString=="French"||toLanguageString=="French"){
            fileName = "PhrasebookFR";
            sentencesLanguage = "French";
        }
        else{
            fileName = "Phrasebook";
            sentencesLanguage = "English";
        }

        FileReader fileReader = new FileReader(fileName);
        sentences = fileReader.getAllSentencesList();
        randomIndices = Exercise.getRandomIndices();
        System.out.println(randomIndices.size());
        HashMap<String,String> chosenSentences = new HashMap();


        for(int i = 0;i<numberOfWords-1;i++){
            for(int j = 0;j<sentencesPerWord-1;j++){
                String randomWord = transformedWords.get(i);
                int randomSentenceIndex = (int)(Math.random()*sentences.get(randomWord).size());
                String randomSentence = sentences.get(randomWord).get(randomSentenceIndex);
                chosenSentences.put(randomSentence, randomWord);
                System.out.println(randomWord+"\n"+randomSentence);
            }
        }

        VBox vbox = new VBox(8);
        HashMap<String,TextField> wordInputs = new HashMap<>();
        String wordsUsed = " - ";
        for(String sentence:chosenSentences.keySet()){
            String word =chosenSentences.get(sentence);
            if(!wordsUsed.contains(word)){
                wordsUsed += word+" - ";
            }
        }
        Text title = new Text("Words used");
        title.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        title.setWrappingWidth(560);
        Text usedWords = new Text(wordsUsed+"\n\n");
        Text sentencesTitle = new Text("Sentences:");
        sentencesTitle.setFont(Font.font("Verdana", FontWeight.BOLD,15));
        vbox.getChildren().addAll(title,usedWords,sentencesTitle);
        for(String sentence:chosenSentences.keySet()){
            System.out.println("Print sentence "+sentence);
            String replacement="";
            String answerWord = chosenSentences.get(sentence);
            for(int j=0;j<answerWord.length();j++){
                Character space = (char) 32;
                if(chosenSentences.get(sentence).charAt(j) == space){
                    replacement+="  ";
                }
                else{
                    replacement+="_ ";
                }
            }

            String sentenceWithGap = sentence.replaceFirst(answerWord,replacement);
            Text sentenceText = new Text(sentenceWithGap);
            sentenceText.setWrappingWidth(560);
            TextField sentence2 = new TextField();
            sentence2.setMaxWidth(12*answerWord.length());
            wordInputs.put(sentence,sentence2);
            vbox.getChildren().addAll(sentenceText,sentence2);
        }
        Button submit = new Button("Submit");
        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                for(String sentence:wordInputs.keySet()) {
                    System.out.println("HERE " + wordInputs.get(sentence).getText());
                }
                ResultsSentencesView resultsSentencesView = new ResultsSentencesView(toLanguageString, chosenSentences,wordInputs);
                Main.getStage().setScene(new Scene(resultsSentencesView));
            }
        });
        submit.setMinSize(150, 50);
        vbox.getChildren().add(submit);
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
