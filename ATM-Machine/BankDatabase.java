import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

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
      // Define the type of the object to be read with gson
      Type t = new TypeToken<ArrayList<Account>>() {
      }.getType();

      return gson.fromJson(fr, t);
   }

   private void saveAccountsToFile(ArrayList<Account> accs) throws IOException {
      FileWriter fw = new FileWriter(accountsFilePath);
      gson.toJson(accs, fw);
      fw.close();
   }

   public void deleteUser(String accNumber) throws IndexOutOfBoundsException {
      accounts.removeIf(acc -> acc.getAccountNumber() == accNumber);
   }

   public Account getAccount(String accNumber) {
      for (Account currentAccount : accounts) {
         if (currentAccount.getAccountNumber() == accNumber)
            return currentAccount;
      }
      return null;
   }

   // public void addUser() {
   // // read input from screen and parse it
   // String name = Screen.Inputfield1.getText();
   // String accountnumber = Screen.Inputfield2.getText();
   // String pin = Screen.Inputfield4.getText();
   // double balance = Double.parseDouble(Screen.Inputfield3.getText());

   // // create new account and add to accounts list
   // accounts.add(new Account(name, accountnumber, pin, balance, balance, false));

   // // reset input fields
   // Screen.Inputfield1.setText("");
   // Screen.Inputfield2.setText("");
   // Screen.Inputfield3.setText("");
   // Screen.Inputfield4.setText("");
   // }

   // private Account getAccountpin(int PIN) {

   // for (Account currentAccount : accounts) {

   // if (currentAccount.GetPin() == PIN)
   // return currentAccount;
   // }

   // return null;
   // }

   // public boolean authenticateUser(int userPIN) {

   // Account userAccount = getAccountpin(userPIN);

   // if (userAccount != null)
   // return userAccount.validatePIN(userPIN);
   // else
   // return false;
   // }

   // public double getAvailableBalance(int userAccountNumber) {
   // return getAccount(userAccountNumber).getAvailableBalance();
   // }

   // public double getTotalBalance(int userAccountNumber) {
   // return getAccount(userAccountNumber).getTotalBalance();
   // }

   // public void credit(int userAccountNumber, double amount) {
   // getAccount(userAccountNumber).credit(amount);
   // }

   // public void debit(int userAccountNumber, double amount) {
   // getAccount(userAccountNumber).debit(amount);
   // }

   // public int getadmin(int userAccountNumber) {
   // return getAccountpin(userAccountNumber).getISadmin();
   // }

   // public static Iterator createIterator() {
   // return new AccountIterator(accounts);
   // }

   // public int getaccpin(int PIN) {
   // for (Account currentAccount : accounts) {

   // if (currentAccount.GetPin() == PIN)
   // return currentAccount.getAccountNumber();
   // else
   // return 1;
   // }
   // return PIN;
   // }

}
