package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Scanner;

import View.CustomLabel;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 *
 * @author Błażej
 * @since 31/01/2018
 */
public class Exercise{
    private static int numberOfClues=0;
    private static Random random = new Random();
    private static ArrayList<Integer> randomIndices;
    private static LinkedHashMap<String, String> words;
    private static double correctCounter=0;
    private static double percent;
    private static ArrayList<String> correctAnswersList = new ArrayList<>();
    private static ArrayList<Integer> indicesWhereCluesUsed = new ArrayList<>();
    private static int numberOfAllClues=0;
    public static String fileName;
    public static LinkedHashMap<String,String> printExercise(int numberOfWords, String fromLanguageString, String toLanguageString) throws FileNotFoundException {
        randomIndices = new ArrayList<>();
        words = new LinkedHashMap<>();

        if(fromLanguageString=="French"||toLanguageString=="French"){
            fileName = "PhrasebookFR";
        }
        else{
            fileName = "Phrasebook";
        }

        FileReader fileReader = new FileReader(fileName);


        ArrayList<String> fromLanguage = fileReader.getLanguagesList().get(fromLanguageString);
        ArrayList<String> toLanguage = fileReader.getLanguagesList().get(toLanguageString);

        File file = new File("Probability.txt");
        double[] probabilityArray = new double[fromLanguage.size()];
        if(file.exists()){
            probabilityArray = Probability.readProbabilityArray(probabilityArray.length);
        }


        Scanner input2 = new Scanner(System.in);

        for (int i = 0; i<numberOfWords; i++){
            double p = Math.random();
            int randomIndex = random.nextInt(fromLanguage.size()-1);
            double probabilityCoefficient=0.0;
            if(probabilityArray[randomIndex]!=0.0){
                probabilityCoefficient = probabilityArray[randomIndex];
            }
            if(randomIndices.contains(randomIndex)){
                i--;
            }
            else if(p<probabilityCoefficient){
                i--;
            }
            else{
                String wordOutput = fromLanguage.get(randomIndex);
                words.put(wordOutput,"");
                randomIndices.add(randomIndex);
            }
        }

        input2.close();
        return words;
    }

    public static CustomLabel[][] results(String toLanguageString, LinkedHashMap<String, String> words, TextField[] textFields) {
        FileReader fileReader = new FileReader(fileName);
        ArrayList<String> toLanguage = fileReader.getLanguagesList().get(toLanguageString);
        int numberOfWords = words.size();

        int counter =0;
        for(String key:words.keySet()){
                words.put(key, textFields[counter].getText());
                counter++;
        }

        CustomLabel[] userAnswers = new CustomLabel[words.size()];
        CustomLabel[] correctAnswers = new CustomLabel[words.size()];
        for(int y = 0; y<userAnswers.length;y++){
            userAnswers[y] = new CustomLabel();
        }

        int x =0;
        double[] probabilityArray = Probability.readProbabilityArray(toLanguage.size());
        numberOfAllClues = indicesWhereCluesUsed.size();
        for (String key : words.keySet() ) {
            String userInput = words.get(key);
            int globalIndex = randomIndices.get(x);

            String correctAnswer = toLanguage.get(globalIndex);
            correctAnswersList.add(correctAnswer);
            correctAnswers[x] = new CustomLabel(correctAnswer);
            correctAnswers[x].setTextFill(Color.GREEN);
            if(userInput.equals(correctAnswer)){

                if(indicesWhereCluesUsed.size()!=0 && x==indicesWhereCluesUsed.get(0)){
                    numberOfClues++;
                    indicesWhereCluesUsed.remove(0);
                }
                correctCounter++;
                if(probabilityArray[globalIndex]==0.0){
                    probabilityArray[globalIndex]=1.0;
                }
                else{
                    double value = probabilityArray[globalIndex];
                    probabilityArray[globalIndex]=value+1;


                }
                userAnswers[x]= new CustomLabel("Your answers was correct!");

            }
            else if(correctAnswer.length()==userInput.length()){

                int countTypo =0;
                for(int y=0;y<correctAnswer.length();y++){
                    if(!(correctAnswer.charAt(y)==userInput.charAt(y))){
                        countTypo++;
                    }
                }
                if(countTypo<=2){
                    if(indicesWhereCluesUsed.size()!=0 && x==indicesWhereCluesUsed.get(0)){
                        numberOfClues++;
                        indicesWhereCluesUsed.remove(0);
                    }
                    userAnswers[x] = new CustomLabel(userInput+" (typo)");
                    userAnswers[x].setTextFill(Color.RED);
                    correctCounter+=0.5;
                }
                else{
                    userAnswers[x] = new CustomLabel(userInput);
                    userAnswers[x].setTextFill(Color.RED);
                }
            }
            else{
                if(userInput.equals("")){
                    userAnswers[x] = new CustomLabel("No answer");
                }
                else{
                    userAnswers[x] = new CustomLabel(userInput);
                    userAnswers[x].setTextFill(Color.RED);
                }

            }
            x++;
        }
        indicesWhereCluesUsed = new ArrayList<>();
        correctCounter=correctCounter-numberOfClues*0.5;
        percent = (correctCounter/numberOfWords)*100;
        percent = Math.round(percent * 100);
        percent = percent/100;

        Probability.saveProbabilityArray(probabilityArray);
        CustomLabel[][] resultsLabels = new CustomLabel[2][correctAnswers.length];
        resultsLabels[0] = userAnswers;
        resultsLabels[1] = correctAnswers;
        return resultsLabels;
    }

    public static CustomLabel[] correctAnswers(String toLanguageString, LinkedHashMap<String, String> words) {
        FileReader fileReader = new FileReader(fileName);
        ArrayList<String> toLanguage = fileReader.getLanguagesList().get(toLanguageString);
        int numberOfWords = words.size();

        int counter =0;

        CustomLabel[] correctAnswers = new CustomLabel[words.size()];

        int x =0;

        for (String key : words.keySet() ) {
            int globalIndex = randomIndices.get(x);
            String correctAnswer = toLanguage.get(globalIndex);
            correctAnswers[x] = new CustomLabel(correctAnswer);
            x++;
        }
        return correctAnswers;
    }


    public static String printClue(int index, String correctAnswer, String toLanguageString){
        indicesWhereCluesUsed.add(index);
        CustomLabel[] correctAnswers = Exercise.correctAnswers(toLanguageString, words);
        String correctAnswerClue = correctAnswers[index].getText();
        String clue ="\t";
        clue +=correctAnswerClue.charAt(0)+" ";
        int numberOfShownLetter = correctAnswerClue.length()/3;
        int[] randomPlaces = new int[numberOfShownLetter];
        for(int d = 0;d<randomPlaces.length;d++){
            int randomPlace = random.nextInt(correctAnswer.length()-1);
            if(!contains(randomPlace, randomPlaces)){
                randomPlaces[d] = randomPlace;
            }
            else{
                d--;
            }

        }


        for(int c=1;c<correctAnswerClue.length();c++){
            Character space = correctAnswerClue.charAt(c);
            if(space ==(char) 32){
                clue += " ";
            }
            else if(contains(c,randomPlaces)){
                clue += correctAnswerClue.charAt(c)+" ";
            }
            else{
                clue += "_ ";
            }
        }

        return clue;
    }

    public static boolean contains(int randomPlace, int[] randomPlaces){
        for(int i=0;i<randomPlaces.length;i++){
            if(randomPlaces[i]==randomPlace){
                return true;
            }

        }
        return false;
    }

    public static int getNumberOfClues(){
        return numberOfClues;
    }

    public static int getNumberOfAllClues(){
        return  numberOfAllClues;
    }

    public static void setNumberOfClues(int newValue){
        numberOfClues = newValue;
    }

    public static void setNumberOfAllClues(int newValue){
        numberOfAllClues = newValue;
    }

    public static double getCorrectCounter(){
        return correctCounter;
    }

    public static void setCorrectCounter(double newValue){
        correctCounter=newValue;
    }

    public static double getPercent(){
        return percent;
    }
}
