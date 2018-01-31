package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 *
 * @author Błażej
 * @since 31/01/2018
 */
public class FileReader {
    private LinkedHashMap<String, ArrayList<String>> languagesList;
    public FileReader() {
        languagesList = new LinkedHashMap<>();
        File file = new File("Slownik.txt");

        try{
            Scanner input = new Scanner(file,"Cp1250");
            ArrayList<String> firstLanguageList = new ArrayList<>();
            ArrayList<String> secondLanguageList = new ArrayList<>();
            String firstLanguage = input.next();
            String secondLanguage = input.next();
            int x=0;
            while(input.hasNextLine()){
                String line = input.nextLine();
                String[] parts = line.split("\t");

                String english = parts[parts.length-2];
                String polish = parts[parts.length-1];
                secondLanguageList.add(polish);
                firstLanguageList.add(english);
                x++;
            }
            input.close();
            languagesList.put(firstLanguage, firstLanguageList);
            languagesList.put(secondLanguage, secondLanguageList);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

    public LinkedHashMap<String, ArrayList<String>> getLanguagesList() {
        return languagesList;
    }

}
