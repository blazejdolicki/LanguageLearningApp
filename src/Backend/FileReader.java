package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public class FileReader {
    private ArrayList<ArrayList<String>> languagesList;
    private ArrayList<String> languages;
    public FileReader() throws FileNotFoundException {
        languagesList = new ArrayList<>();
        File file = new File("Slownik.txt");
        Scanner input = new Scanner(file);
        ArrayList<String> englishList = new ArrayList<>();
        ArrayList<String> polishList = new ArrayList<>();
        languages = new ArrayList<>();
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
    }

    public ArrayList<ArrayList<String>> getLanguagesList() {
        return languagesList;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }
}
