package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Scanner;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class Exercise{
    private static int numberOfClues=0;
    private static Random random = new Random();
    private static ArrayList<Integer> randomIndices = new ArrayList<>();
    private static LinkedHashMap<String, String> words;
    private static double correctCounter=0;
    private static double percent;


    public static LinkedHashMap<String,String> printExercise(int numberOfWords, String translatedLanguageString, String inputLanguageString) throws FileNotFoundException {
        words = new LinkedHashMap<>();
        FileReader fileReader = new FileReader();
        ArrayList<String> translatedLanguage = fileReader.getLanguagesList().get(translatedLanguageString);
        ArrayList<String> inputLanguage = fileReader.getLanguagesList().get(inputLanguageString);

        File file = new File("Probability.txt");
        double[] probabilityArray = new double[translatedLanguage.size()];
        if(file.exists()){
            probabilityArray = Probability.readProbabilityArray(probabilityArray.length);
        }


        Scanner input2 = new Scanner(System.in,"Cp852");

        for (int i = 0; i<numberOfWords; i++){
            double p = Math.random();
            int randomIndex = random.nextInt(translatedLanguage.size()-1);
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
                String wordOutput = translatedLanguage.get(randomIndex);
                words.put(wordOutput,"");
                randomIndices.add(randomIndex);

//                String wordInput = input2.next();
//                String correctAnswer = inputLanguage.get(randomIndices.get(i));
//                if(wordInput.equals("clue")){
//                    wordInput = printClue(correctAnswer, wordInput,input2);
//                }
//                int stringSize = correctAnswer.split(" ").length;
//                for(int j=0;j<stringSize-1;j++){
//                    String newInput=input2.next();
//                    wordInput = wordInput+" "+newInput;
//                }
//
//                words.put(wordOutput, wordInput);
//                System.out.println("");
            }
        }

        input2.close();
        return words;
        // results(inputLanguage,probabilityArray, numberOfWords);
        // words should first be used to get an arraylist of outputwords
        //then to each outputword an inputword should be assigned
    }

    // 23.12. results() has to much variables from Exercise class, so the method should return an array of 2 arrays
    // first array has text for "your answer" labels and the second one for "correct answer" labels
    public static Label[][] results(String inputLanguageString, LinkedHashMap<String, String> words, TextField[] textFields) throws FileNotFoundException{
        FileReader fileReader = new FileReader();
        ArrayList<String> inputLanguage = fileReader.getLanguagesList().get(inputLanguageString);
        int numberOfWords = words.size();

        int counter =0;
        for(String key:words.keySet()){
            words.put(key, textFields[counter].getText());
            counter++;
        }

        Label[] userAnswers = new Label[words.size()];
        Label[] correctAnswers = new Label[words.size()];
        for(int y = 0; y<userAnswers.length;y++){
            userAnswers[y] = new Label();
        }

        int x =0;
        double[] probabilityArray = Probability.readProbabilityArray(inputLanguage.size());

        for (String key : words.keySet() ) {
            String userInput = words.get(key);
            int globalIndex = randomIndices.get(x);
            String correctAnswer = inputLanguage.get(globalIndex);
            correctAnswers[x] = new Label(correctAnswer);
            correctAnswers[x].setTextFill(Color.GREEN);
            if(userInput.equals(correctAnswer)){
                correctCounter++;
                if(probabilityArray[globalIndex]==0.0){
                    probabilityArray[globalIndex]=1.0;
                }
                else{
                    double value = probabilityArray[globalIndex];
                    probabilityArray[globalIndex]=value+1;


                }

            }
            else if(correctAnswer.length()==userInput.length()){
                int countTypo =0;
                for(int y=0;y<correctAnswer.length();y++){
                    if(!(correctAnswer.charAt(y)==userInput.charAt(y))){
                        countTypo++;
                    }
                }
                if(countTypo<=2){
                    userAnswers[x] = new Label(correctAnswer+" (typo)");
                    userAnswers[x].setTextFill(Color.RED);
                    correctCounter+=0.5;
                }
                else{
                    userAnswers[x] = new Label(userInput);
                    userAnswers[x].setTextFill(Color.RED);
                }
            }
            else{
                userAnswers[x] = new Label(userInput);
                userAnswers[x].setTextFill(Color.RED);
            }
            x++;
        }
        correctCounter=correctCounter-numberOfClues*0.5;
        percent = (correctCounter/numberOfWords)*100;
        percent = Math.round(percent * 100);
        percent = percent/100;

        Probability.saveProbabilityArray(probabilityArray);
        Label[][] resultsLabels = new Label[2][correctAnswers.length];
        resultsLabels[0] = userAnswers;
        resultsLabels[1] = correctAnswers;
        return resultsLabels;
    }

    public static String printClue(String correctAnswer,String wordInput, Scanner input2){
        numberOfClues++;

        int randomPlace = random.nextInt(correctAnswer.length()-1);
        System.out.print(correctAnswer.charAt(0)+" ");
        for(int c=1;c<correctAnswer.length()-1;c++){
            if(c==randomPlace){
                System.out.print(correctAnswer.charAt(randomPlace)+" ");
            }
            System.out.print("_ ");
        }
        wordInput=input2.next();
        return wordInput;
    }

    public static int getNumberOfClues(){
        return numberOfClues;
    }

    public static double getCorrectCounter(){
        return correctCounter;

    }

    public static double getPercent(){
        return percent;
    }
}
