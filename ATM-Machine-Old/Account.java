
public class Account {
   private int accountNumber;
   private int pin;
   private double availableBalance;
   private double totalBalance;
   private int admin;
   private String username;

   public Account(String Username, int theAccountNumber, int thePIN,
         double theAvailableBalance, double theTotalBalance, int isadmin) {
      setUsername(Username);
      setAccountNumber(theAccountNumber);
      setPin(thePIN);
      setAvailableBalance(theAvailableBalance);
      setTotalBalance(theTotalBalance);
      setAdmin(isadmin);
   }

   public boolean validatePIN(int userPIN) {
      if (userPIN == getPin())
         return true;
      else
         return false;
   }

   public double getAvailableBalance() {
      return availableBalance;
   }

   public double getTotalBalance() {
      return totalBalance;
   }

   public void credit(double amount) {
      setTotalBalance(getTotalBalance() + amount);
   }

   public void debit(double amount) {
      setAvailableBalance(getAvailableBalance() - amount);
      setTotalBalance(getTotalBalance() - amount);
   }

   public int getAccountNumber() {
      return accountNumber;
   }

   public int getISadmin() {
      return getAdmin();
   }

   public int GetPin() {
      return getPin();
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public void setAccountNumber(int accountNumber) {
      this.accountNumber = accountNumber;
   }

   public int getPin() {
      return pin;
   }

   public void setPin(int pin) {
      this.pin = pin;
   }

   public void setAvailableBalance(double availableBalance) {
      this.availableBalance = availableBalance;
   }

   public void setTotalBalance(double totalBalance) {
      this.totalBalance = totalBalance;
   }

   public int getAdmin() {
      return admin;
   }

   public void setAdmin(int admin) {
      this.admin = admin;
   }

}

/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and *
 * Pearson Education, Inc. All Rights Reserved. *
 * *
 * DISCLAIMER: The authors and publisher of this book have used their *
 * best efforts in preparing the book. These efforts include the *
 * development, research, and testing of the theories and programs *
 * to determine their effectiveness. The authors and publisher make *
 * no warranty of any kind, expressed or implied, with regard to these *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or *
 * consequential damages in connection with, or arising out of, the *
 * furnishing, performance, or use of these programs. *
 *************************************************************************/