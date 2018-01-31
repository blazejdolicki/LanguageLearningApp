package Model;

import java.util.ArrayList;
import java.util.Scanner;

public class OtherModes {
    public static void printWord(ArrayList<String> dictionary1, ArrayList<String> dictionary2){
        System.out.println("Write the word that you want to translate.");
        Scanner input = new Scanner(System.in,"Cp852");
        String inputWord = input.next();
        int index = 0;
        for(int x=0;x<dictionary1.size();x++){
            if(inputWord.equals(dictionary1.get(x))){
                System.out.println(dictionary2.get(x));
            }
        }
    }

    public static void printDictionary(ArrayList<String> dictionary1, ArrayList<String> dictionary2){
        System.out.println("");
        for(int i=0;i<dictionary1.size();i++){
            System.out.print(dictionary1.get(i));
            for(int j=0;j<15-dictionary1.get(i).length();j++){
                System.out.print(" ");
            }
            System.out.print(" - " +dictionary2.get(i));
            System.out.println("");
        }
    }
}
