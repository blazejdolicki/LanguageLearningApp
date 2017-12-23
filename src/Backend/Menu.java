package Backend;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void setMenu(Scanner in, ArrayList<String> dictionary1, ArrayList<String> dictionary2) throws FileNotFoundException{

        System.out.println("Do you want to do an exercise?");
        String exercise = in.next();
        if(exercise.equals("yes")){
            // Exercise.printExercise(dictionary1, dictionary2);
        }
        else{
            System.out.println("Do you want to see the dictionary?");
            String dictionary = in.next();
            if(dictionary.equals("yes")){
                OtherModes.printDictionary(dictionary1,dictionary2);
            }
            else{
                System.out.println("Do you want to translate some word?");
                String translate = in.next();
                if(translate.equals("yes")){
                    OtherModes.printWord(dictionary1,dictionary2);
                }
            }
        }
    }
}
