package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import exceptions.InvalidTransactionException;
import klassen.*;

public class AccountTests {

  private ATM atm;
  private BankDatabase bankDatabase;
  private String pathToJSON = "\\bin\\Assets\\accounts.json";

  private String username = "test1";
  private String accountNumber = "4321";
  private String pin = "1234";
  private double availableBalance = 10.00;
  private double totalBalance = 10.00;
  private boolean isAdmin = false;

  private Account a1;

  @Before
  public void setUp() throws FileNotFoundException, IOException {

    atm = new ATM(true, pathToJSON);
    bankDatabase = atm.getBankDatabase();

    a1 = new Account(username, accountNumber, pin, availableBalance, totalBalance, isAdmin);
    bankDatabase.saveAccount(a1);

    atm.start();
  }

  @Test
  public void validatePIN() {
    assertTrue(a1.validatePIN(pin));
  }

  @Test
  public void checkCredit() throws InvalidTransactionException, IOException {
    bankDatabase.creditAccount(a1, 5);
    assertTrue(a1.getTotalBalance() == totalBalance + 5);
  }

  @Test
  public void checkDebit() throws InvalidTransactionException, IOException {
    bankDatabase.debitAccount(a1, 5);
    assertTrue(a1.getAvailableBalance() == availableBalance - 5);
  }
}