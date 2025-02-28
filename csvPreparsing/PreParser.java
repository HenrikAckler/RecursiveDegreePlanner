import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Rough and dirty pre-parsing that will clean up the CSV and prepare it for further processing.
 * TODO: Improve documentation here
 * @author Henrik Ackler
 */
public class PreParser 
{
   private final String INPUT_PATH = "null";
   private String[][] outputArray = new String[5365][12];
   public static void main(String[] args) {
      PreParser myParser = new PreParser();
   }

   public PreParser()
   {
      File inputFile = new File("csvPreparsing\\test.csv");
      if(!inputFile.exists()){
         System.out.println("BAD");
      }
      try {
         Scanner fileScanner = new Scanner(inputFile);

         int index = 0;
         while(fileScanner.hasNextLine()){
            String row = fileScanner.nextLine();
            outputArray[index] = row.split(",");
            index++;
         }
         fileScanner.close();
         
      } catch (FileNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}
