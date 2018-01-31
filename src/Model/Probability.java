package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Błażej
 * @since 31/01/2018
 */

public class Probability {
    // test after merge
    public static double[] readProbabilityArray(int dictionarySize) {
        double[] probabilityArray = new double[dictionarySize];
        try{
            File file = new File("Probability.txt");
            Scanner input = new Scanner(file).useLocale(Locale.US);
            int x=0;

            while(input.hasNextDouble()){
                probabilityArray[x] = input.nextDouble();
                x++;
            }
            input.close();
        }
        catch(FileNotFoundException e){

        }

        return probabilityArray;
    }

    public static void saveProbabilityArray(double[] array){
        try{
            PrintWriter writer = new PrintWriter("Probability.txt");
            for(int x=0;x<array.length;x++){
                double probability=array[x];
                if(probability>=0){
                    probability=array[x]*0.8;
                    probability = Math.round(probability*100)/100.0;
                }
                writer.print(probability+" ");
            }
            writer.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }


    }
}
