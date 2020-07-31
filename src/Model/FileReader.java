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
    LinkedHashMap<String,ArrayList<String>> allSentencesList;

    public LinkedHashMap<String,ArrayList<String>> getAllSentencesList() {
        return allSentencesList;
    }

    public FileReader(String fileName) {
        languagesList = new LinkedHashMap<>();
        File file = new File(fileName+".txt");
        allSentencesList = new LinkedHashMap<>();
        try{
            Scanner input = new Scanner(file,"Cp1250");
            ArrayList<String> firstLanguageList = new ArrayList<>();
            ArrayList<String> secondLanguageList = new ArrayList<>();
            String[] firstLine = input.nextLine().split("\t");
            String firstLanguage = firstLine[0];
            String secondLanguage = firstLine[1];
            firstLanguageList.add(firstLine[2]);
            secondLanguageList.add(firstLine[3]);

            while(input.hasNextLine()){
                String line = input.nextLine();
                String[] parts = line.split("\t");

                String firstLangWord = parts[2];
                String secondLangWord = parts[3];
                ArrayList<String> wordSentencesList = new ArrayList<>();
                for(int i=4;i<parts.length;i++){
                    wordSentencesList.add(parts[i]);
                }
                System.out.println("Number of sentences" + wordSentencesList.size());
                String correspondingWord;
                if(firstLanguage.equals("French")){
                    correspondingWord = firstLangWord;
                }
                else if(secondLanguage.equals("French")){
                    correspondingWord = secondLangWord;
                }
                else if(firstLanguage.equals("English")){
                    correspondingWord = firstLangWord;
                }
                else{
                    correspondingWord = secondLangWord;
                }
                allSentencesList.put(correspondingWord,wordSentencesList);
                firstLanguageList.add(firstLangWord);
                secondLanguageList.add(secondLangWord);
            }

            input.close();
            languagesList.put(firstLanguage, firstLanguageList);
            languagesList.put(secondLanguage, secondLanguageList);
            System.out.println(languagesList.get(firstLanguage).size());
            System.out.println(languagesList.get(secondLanguage).size());
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }

    public LinkedHashMap<String, ArrayList<String>> getLanguagesList() {
        return languagesList;
    }

}
