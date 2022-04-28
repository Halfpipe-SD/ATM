// Transaction.java
// Abstract superclass Transaction represents an ATM transaction

public abstract class Transaction
{
   private int accountNumber; // indicates account involved
   protected Screen screen; // ATM's screen
   private BankDatabase bankDatabase; // account info database

   // Transaction constructor invoked by subclasses using super()
   public Transaction(int userAccountNumber, Screen atmScreen, 
      BankDatabase atmBankDatabase)
   {
      accountNumber = userAccountNumber;
      screen = atmScreen;
      bankDatabase = atmBankDatabase;
   } // end Transaction constructor

   // return account number 
   public int getAccountNumber()
   {
      return accountNumber; 
   } // end method getAccountNumber

   // return reference to screen
   public Screen getScreen()
   {
      return screen;
   } // end method getScreen

   // return reference to bank database
   public BankDatabase getBankDatabase()
   {
      return bankDatabase;
   } // end method getBankDatabase

   // perform the transaction (overridden by each subclass)
   abstract public void execute();
} // end class Transaction



/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/