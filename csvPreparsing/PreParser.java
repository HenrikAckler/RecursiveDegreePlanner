import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Rough and dirty pre-parsing that will clean up the CSV and prepare it for
 * further processing.
 * TODO: Improve documentation here
 * 
 * @author Henrik Ackler
 */
public class PreParser {
   private final String INPUT_PATH = "csvPreparsing\\TrimmedData.csv";//csvPreparsing\\TrimmedData.csv
   private String[][] outputArray = new String[5212][12];

   public static void main(String[] args) {
      PreParser myParser = new PreParser();
      myParser.commaReplace();
      System.out.println(myParser.arrayToString());
      myParser.arrayToFile(myParser.arrayToString());
   }

   /**
    * Loads input file, scanning through and adding each cell to the
    * outputArray[][]
    * TODO: Fix error handling
    */
   public PreParser() {
      File inputFile = new File(INPUT_PATH);
      if (!inputFile.exists()) {
         System.out.println("BAD");
      }
      
      try {
         Scanner fileScanner = new Scanner(inputFile);

         int index = 0;
         while (fileScanner.hasNextLine() && index < outputArray.length) {
            String row = fileScanner.nextLine();
            outputArray[index] = row.split(",");
            index++;
         }
         fileScanner.close();

      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {
      
      }
   }

   private void commaReplace() {
      for (int row = 0; row < outputArray.length; row++) {
         for (int col = 0; col < outputArray[row].length; col++) {
            if (outputArray[row][col] != null) {
               outputArray[row][col] = outputArray[row][col].replaceAll("%COMMA%", ",");
            }
         }
      }
   }

   private String arrayToString() {
      StringBuilder outString = new StringBuilder();
      for (int row = 0; row < outputArray.length; row++) {
         for (int col = 0; col < outputArray[row].length; col++) {
            outString.append(" " + outputArray[row][col] + " |");
         }
         outString.append("\n");
      }
      return outString.toString();
   }

   private void arrayToFile(String input){
      try {
         FileWriter myWriter = new FileWriter("arrayOutput.txt");
         myWriter.write(input);
         myWriter.close();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}
