package tests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import klassen.*;



public class AccountTests {

  private ATM atm;
  private BankDatabase bankDatabase;

  @Before
  public void setUp() throws FileNotFoundException, IOException {
    atm = new ATM("\\bin\\Assets\\accounts.json");
    atm.start();
    bankDatabase = atm.getBankDatabase();
  }

  @Test
  public void checkAccounts() {
    ArrayList<Account> accounts = bankDatabase.getAccounts();

    assertTrue(accounts.size() > 1);
  }

  @Test
  public void checkComponents() {
    assertTrue(atm.getScreen() != null);
    assertTrue(atm.getBankDatabase() != null);
  }

}
