package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Scanner;

public class Exercise{
    private static int numberOfClues=0;
    private static Random random = new Random();
    private static ArrayList<Integer> randomIndices = new ArrayList<>();
    private static LinkedHashMap<String, String> words = new LinkedHashMap<>();

    public static void printExercise(ArrayList<String> dictionary1, ArrayList<String> dictionary2) throws FileNotFoundException {
        //test
        int numberOfWords = 7;
        File file = new File("Probability.txt");
        double[] probabilityArray = new double[dictionary1.size()];
        if(file.exists()){
            probabilityArray = Probability.readProbabilityArray(probabilityArray.length);
        }


        System.out.println("");
        Scanner input2 = new Scanner(System.in,"Cp852");
        System.out.println("Translate the given phrases. After each phrase write \"s\" to submit you answer. If you want a clue, write \"clue\".");

        for (int i = 0; i<numberOfWords; i++){
            double p = Math.random();
            int randomIndex = random.nextInt(dictionary1.size()-1);
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
                String wordOutput = dictionary1.get(randomIndex);
                System.out.println(i+1+".");
                System.out.println(wordOutput);
                randomIndices.add(randomIndex);

                String wordInput = input2.next();
                String correctAnswer = dictionary2.get(randomIndices.get(i));
                if(wordInput.equals("clue")){
                    wordInput = printClue(correctAnswer, wordInput,input2);
                }
                int stringSize = correctAnswer.split(" ").length;
                for(int j=0;j<stringSize-1;j++){
                    String newInput=input2.next();
                    wordInput = wordInput+" "+newInput;
                }

                words.put(wordOutput, wordInput);
                System.out.println("");
            }
        }
        input2.close();
        results(dictionary2,probabilityArray, numberOfWords);

    }

    public static void results(ArrayList<String> dictionary2, double[] probabilityArray, int numberOfWords) throws FileNotFoundException{
        System.out.println("");
        System.out.println("RESULTS:");
        System.out.println("");
        int x =0;
        double correctCounter=0;
        for (String key : words.keySet() ) {
            System.out.println(x+1+	". "+key+" ");
            System.out.print("Your answer: "+words.get(key)+" ");
            String userInput = words.get(key);
            int globalIndex = randomIndices.get(x);
            String correctAnswer = dictionary2.get(globalIndex);
            if(userInput.equals(correctAnswer)){
                System.out.println("Correct!");
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
                if(countTypo==2){
                    System.out.println("Correct, but there is a typo!");
                    System.out.println("Correct answer: " + dictionary2.get(randomIndices.get(x)));
                    correctCounter+=0.5;
                }
                else{
                    System.out.println("Incorrect :( ");
                    System.out.println("Correct answer: " + dictionary2.get(randomIndices.get(x)));
                }
            }
            else{
                System.out.println("Incorrect :( ");
                System.out.println("Correct answer: " + dictionary2.get(randomIndices.get(x)));
            }
            x++;
            System.out.println("");
        }
        correctCounter=correctCounter-numberOfClues*0.5;
        double percent = (correctCounter/numberOfWords)*100;
        percent = Math.round(percent * 100);
        percent = percent/100;
        System.out.println("You used "+numberOfClues+" clues.");
        if(correctCounter%1.0==0){
            int finalCorrectCounter = (int) correctCounter;
            System.out.println("Correct answers: "+finalCorrectCounter+"/"+numberOfWords+" - " +percent+"%");
        }
        else{
            System.out.println("Correct answers: "+correctCounter+"/"+numberOfWords+" - " +percent+"%");
        }
        Probability.saveProbabilityArray(probabilityArray);
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
}
