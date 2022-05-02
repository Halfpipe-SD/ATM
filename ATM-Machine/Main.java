import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

   public static void main(String[] args) {

      // create new atm instance and start it
      try {
         ATM atm = new ATM(true);
         atm.start();

      } catch (FileNotFoundException fnfe) {
         System.out.println("File was not found!");
         System.exit(1);

      } catch (IOException ioe) {
         System.out.println("IOException when reading accounts json!");
         System.exit(1);

      } catch (Exception e) {
         System.out.println("Exception when starting ATM!");
         e.printStackTrace();
      }
   }
}
