package Backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Scanner;

public class Probability {
    // test after merge
    public static double[] readProbabilityArray(int dictionarySize) throws FileNotFoundException {
        double[] probabilityArray = new double[dictionarySize];
        File file = new File("Probability.txt");
        Scanner input = new Scanner(file).useLocale(Locale.US);
        int x=0;

        while(input.hasNextDouble()){
            probabilityArray[x] = input.nextDouble();
            x++;
        }
        input.close();
        return probabilityArray;
    }

    public static void saveProbabilityArray(double[] array) throws FileNotFoundException{
        PrintWriter writer = new PrintWriter("Probability.txt");
        for(int x=0;x<array.length;x++){
            double probability=array[x];
            if(probability>=0){
                probability=array[x]*0.8;
            }
            writer.print(probability+" ");
        }
        writer.close();
    }
}
