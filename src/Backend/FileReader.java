package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public class FileReader {

    public static ArrayList<String> chooseTranslatedLanguage(ArrayList<ArrayList<String>> languagesList){
        ArrayList<String> dictionary1;
        Scanner in = new Scanner(System.in);
        System.out.println("Write language that you want to translate: ");
        String output = in.next();

        if(output.equals("polish")){
            dictionary1=languagesList.get(1);
            return dictionary1;

        }
        else if(output.equals("english")){
            dictionary1=languagesList.get(0);
            return dictionary1;
        }
        else{
            invalidLanguage();
            dictionary1=chooseTranslatedLanguage(languagesList);
        }

        return dictionary1;
    }

    public static ArrayList<String> chooseInputLanguage(ArrayList<ArrayList<String>> languagesList){
        Scanner in = new Scanner(System.in);
        ArrayList<String> dictionary2;

        System.out.println("Write your input language: ");
        String input = in.next();
        if(input.equals("english")){
            dictionary2=languagesList.get(0);
            return dictionary2;
        }
        else if(input.equals("polish")){
            dictionary2=languagesList.get(1);
            return dictionary2;
        }
        else{
            invalidLanguage();
            dictionary2=chooseInputLanguage(languagesList);
        }
        return dictionary2;
    }



    public static void invalidLanguage(){
        System.out.println("This language is not available. Choose different language.");
    }


    public static ArrayList<ArrayList<String>> inputDictionary() throws FileNotFoundException {
        ArrayList<ArrayList<String>> languagesList = new ArrayList<>();
        File file = new File("Slownik.txt");
        Scanner input = new Scanner(file);
        ArrayList<String> englishList = new ArrayList<>();
        ArrayList<String> polishList = new ArrayList<>();
        HashSet<String> languages = new HashSet<>();
        System.out.println(input.next());
        languages.add(input.next());
        languages.add(input.next());
        int x=0;
        while(input.hasNextLine()){
            String line = input.nextLine();
            String[] parts = line.split("\t");

            String english = parts[parts.length-2];
            String polish = parts[parts.length-1];

            polishList.add(polish);
            englishList.add(english);
            x++;
        }
        input.close();
        languagesList.add(englishList);
        languagesList.add(polishList);
        return languagesList;
    }
}
