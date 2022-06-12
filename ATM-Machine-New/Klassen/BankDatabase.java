package klassen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import exceptions.InvalidTransactionException;
import exceptions.LoginFailedException;

/**
 * Die BankDatabase speichert alle Accounts und ermöglicht Ein- und Auszahlen,
 * Lesen und Speichern von Accounts in eine JSON-Datei.
 * 
 * @author Die Panzerknacker
 */
public class BankDatabase {
  private boolean debugMode;

  private Gson gson = new GsonBuilder().setPrettyPrinting().create();

  private ArrayList<Account> accounts = new ArrayList<Account>();
  private String accountsFilePath;
  private CashDispenser cashDispenser;

  /**
   * Konstruktor der BankDatabase. Hier wird der Debugmodus und der Pfad zur
   * JSON-Datei gesetzt.
   * 
   * @param debugMode          Debugmodus
   * @param pathToAccountsJSON Pfad zur JSON-Datei
   * @throws FileNotFoundException
   * @throws IOException
   */
  public BankDatabase(boolean debugMode, String pathToAccountsJSON) throws FileNotFoundException, IOException {
    this.debugMode = debugMode;

    cashDispenser = new CashDispenser(debugMode);
    accountsFilePath = new File("").getAbsolutePath() + pathToAccountsJSON;
    accounts = readAccountsFromFile();
  }

  /**
   * Funktion um einen Account zu speichern.
   * 
   * @param a Der zu speichernende Account
   * @throws IOException
   */
  public void saveAccount(Account a) throws IOException {
    int index = accounts.indexOf(a);

    if (index == -1)
      accounts.add(a);
    else
      accounts.set(index, a);

    saveAccountsToFile(accounts);
  }

  /**
   * Funktion um einem bestimmten Account einen Betrag zu überweisen.
   * 
   * @param a      Der Account, zu dem der Betrag überwiesen werden soll
   * @param amount Der Betrag, der überwiesen werden soll
   * @throws InvalidTransactionException
   * @throws IOException
   */
  public void creditAccount(Account a, double amount) throws InvalidTransactionException, IOException {
    if (amount < 5)
      throw new InvalidTransactionException("Sie müssen einen minimalen Betrag von 5€ einzahlen!");

    if (amount % 5 != 0)
      throw new InvalidTransactionException("Sie müssen einen Betrag in 5€-Schritten einzahlen!");

    if (amount > 5000)
      throw new InvalidTransactionException("Sie können maximal 5000€ auf einmal einzahlen!");

    // TODO CHECK IF ENVELOPE RECEIVED AND ONLY ADD TO AVAILABLE BALANCE

    cashDispenser.depositAmount(amount);

    a.setAvailableBalance(a.getAvailableBalance() + amount);
    a.setTotalBalance(a.getTotalBalance() + amount);

    saveAccount(a);

    if (debugMode)
      System.out.println("Einzahlung von " + amount + "€ für Account " + a.getUsername());
  }

  /**
   * Funktion um einen bestimmten Account einen Betrag abzuziehen.
   * 
   * @param a      Der Account, von dem der Betrag abgezogen werden soll
   * @param amount Der Betrag, der abgezogen werden soll
   * @throws InvalidTransactionException
   * @throws IOException
   */
  public void debitAccount(Account a, double amount) throws InvalidTransactionException, IOException {
    if (amount < 5)
      throw new InvalidTransactionException("Sie müssen einen minimalen Betrag von 5€ abheben!");

    if (amount % 5 != 0)
      throw new InvalidTransactionException("Sie müssen einen Betrag in 5€-Schritten abheben!");

    if (amount > 1000)
      throw new InvalidTransactionException("Sie können maximal 1000€ auf einmal abheben!");

    if (amount > a.getAvailableBalance())
      throw new InvalidTransactionException("Der Betrag überschreitet Ihr verfügbares Guthaben!");

    cashDispenser.withdrawAmount(amount);

    a.setAvailableBalance(a.getAvailableBalance() - amount);
    a.setTotalBalance(a.getTotalBalance() - amount);

    saveAccount(a);

    if (debugMode)
      System.out.println("Abhebung von " + amount + "€ für Account " + a.getUsername());
  }

  /**
   * Funktion um alle Accounts von einer JSON-Datei zu lesen.
   * 
   * @return ArrayList<Account> Die Accounts
   * @throws FileNotFoundException
   */
  private ArrayList<Account> readAccountsFromFile() throws FileNotFoundException {
    FileReader fr = new FileReader(accountsFilePath);
    // Define the type of the object to be read from the json file
    Type t = new TypeToken<ArrayList<Account>>() {
    }.getType();

    return gson.fromJson(fr, t);
  }

  /**
   * Funktion um alle Accounts in eine JSON-Datei zu speichern.
   * 
   * @param accs ArrayList<Account> Die Accounts
   * @throws IOException
   */
  public void saveAccountsToFile(ArrayList<Account> accs) throws IOException {
    FileWriter fw = new FileWriter(accountsFilePath);
    gson.toJson(accs, fw);
    fw.close();
  }

  public void deleteUser(String accNumber) throws IndexOutOfBoundsException {
    accounts.removeIf(acc -> acc.getAccountNumber() == accNumber);
  }

  /**
   * Funktion um einen Account anhand seiner PIN zu authentifizieren.
   * 
   * @param pin Benutzereingabe
   * @return Gefundener Account
   * @throws LoginFailedException
   */
  public Account validateAccount(String pin) throws LoginFailedException {
    if (pin.length() != 4)
      throw new LoginFailedException("Die PIN muss 4 Zeichen lang sein!");

    Account found = null;
    for (Account acc : accounts) {
      if (pin.equals(acc.getPin()))
        found = acc;
    }
    if (found == null)
      throw new LoginFailedException("Fehlerhafte PIN!");

    return found;
  }

  public ArrayList<Account> getAccounts() {
    return accounts;
  }
}
