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

public class BankDatabase {
  private boolean debugMode;

  private Gson gson = new GsonBuilder().setPrettyPrinting().create();

  private ArrayList<Account> accounts = new ArrayList<Account>();
  private String accountsFilePath;
  private CashDispenser cashDispenser;

  public BankDatabase(boolean debugMode, String pathToAccountsJSON) throws FileNotFoundException, IOException {
    this.debugMode = debugMode;

    cashDispenser = new CashDispenser(debugMode);
    accountsFilePath = new File("").getAbsolutePath() + pathToAccountsJSON;
    accounts = readAccountsFromFile();
  }

  public void saveAccount(Account a) throws IOException {
    int index = accounts.indexOf(a);

    if (index == -1)
      accounts.add(a);
    else
      accounts.set(index, a);

    saveAccountsToFile(accounts);
  }

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

  private ArrayList<Account> readAccountsFromFile() throws FileNotFoundException {
    FileReader fr = new FileReader(accountsFilePath);
    // Define the type of the object to be read from the json file
    Type t = new TypeToken<ArrayList<Account>>() {
    }.getType();

    return gson.fromJson(fr, t);
  }

  public void saveAccountsToFile(ArrayList<Account> accs) throws IOException {
    FileWriter fw = new FileWriter(accountsFilePath);
    gson.toJson(accs, fw);
    fw.close();
  }

  public void deleteUser(String accNumber) throws IndexOutOfBoundsException {
    accounts.removeIf(acc -> acc.getAccountNumber() == accNumber);
  }

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

  public Account getAccountByAccountNumber(String accNumber) {
    for (Account acc : accounts) {
      if (acc.getAccountNumber() == accNumber)
        return acc;
    }
    return null;
  }

  public ArrayList<Account> getAccounts() {
    return accounts;
  }
}
