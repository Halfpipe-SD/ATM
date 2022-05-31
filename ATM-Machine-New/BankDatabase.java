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

import Exceptions.LoginFailedException;

public class BankDatabase {

  private Gson gson = new GsonBuilder().setPrettyPrinting().create();

  private ArrayList<Account> accounts = new ArrayList<Account>();
  private String accountsFilePath;

  public BankDatabase(String pathToAccountsJSON) throws FileNotFoundException, IOException {

    // Set accounts file path
    accountsFilePath = new File("").getAbsolutePath() + pathToAccountsJSON;

    // read accounts from file
    accounts = readAccountsFromFile();
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
      throw new LoginFailedException("PIN should be 4 characters long");

    Account found = null;
    for (Account acc : accounts) {
      if (pin.equals(acc.getPin()))
        found = acc;
    }
    if (found == null)
      throw new LoginFailedException("Error Validating PIN: " + pin);

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
