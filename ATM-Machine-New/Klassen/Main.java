package klassen;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

  public static void main(String[] args) {

    // create new atm instance and start it
    try {
      ATM atm = ATM.getInstance(true, null);
      atm.start();

    } catch (FileNotFoundException fnfe) {
      System.out.println("Datei wurde nicht gefunden!");
      System.exit(1);

    } catch (IOException ioe) {
      System.out.println("Fehler beim Lesen einer Datei!");
      System.exit(1);

    } catch (Exception e) {
      System.out.println("Fehler: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
