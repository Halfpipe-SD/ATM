import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

public class BankDatabase {
   private static final String pathToAccountsJSON = "../accounts.json";
   private static final ArrayList<Account> accounts = initializeAccounts();

   public BankDatabase() {
      System.out.println(this.accounts);
   }

   private static ArrayList<Account> initializeAccounts() {
      try {
         // Read the accounts.json file
         Reader r = Files.newBufferedReader(Paths.get(pathToAccountsJSON));

         // Define the type of the object to be deserialized
         Type t = new TypeToken<ArrayList<Account>>() {
         }.getType();

         // convert JSON string to account arrayList object
         return new Gson().fromJson(r, t);

      } catch (InvalidPathException ipe) {
         System.out.println("Error reading the path of accounts.json");

      } catch (IOException ioe) {
         System.out.println("Error reading accounts.json file!");

      } catch (Exception e) {
         e.printStackTrace();
      }
      // terminate program
      System.exit(1);
      return null;
   }

// public Account getAccount(int accountnumber) {

// for (Account currentAccount : accounts) {

// if (currentAccount.getAccountNumber() == accountnumber)
// return currentAccount;
// }

// return null;
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

// public static void Adduser() {
// String name = Screen.Inputfield1.getText();
// int accountnumber = Integer.parseInt(Screen.Inputfield2.getText());
// int pin = Integer.parseInt(Screen.Inputfield4.getText());
// int balance = Integer.parseInt(Screen.Inputfield3.getText());

// Account newaccount = new Account(name, accountnumber, pin, balance, balance,
// 0);
// accounts.add(newaccount);

// Screen.Inputfield1.setText("");
// Screen.Inputfield2.setText("");
// Screen.Inputfield3.setText("");
// Screen.Inputfield4.setText("");
// }

// public static void Deleteuser(int position) {
// accounts.remove(position);

// }

// }
