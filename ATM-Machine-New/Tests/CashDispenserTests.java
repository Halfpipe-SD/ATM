package tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import exceptions.InvalidTransactionException;
import klassen.CashDispenser;

public class CashDispenserTests {

  private CashDispenser cashDispenser;

  @Before
  public void setUp() {
    cashDispenser = new CashDispenser(true);
  }

  @Test
  public void checkMoneyInventory() {
    HashMap<String, Integer> inventory = cashDispenser.getMoneyInventory();

    assertTrue(inventory.get("5€") == 10);
    assertTrue(inventory.get("10€") == 10);
    assertTrue(inventory.get("20€") == 10);
    assertTrue(inventory.get("50€") == 10);
    assertTrue(inventory.get("100€") == 10);
  }

  @Test
  public void checkDepositAmount() throws InvalidTransactionException {
    cashDispenser.depositAmount(875);

    HashMap<String, Integer> inventory = cashDispenser.getMoneyInventory();

    assertTrue(inventory.get("100€") == 18);
    assertTrue(inventory.get("50€") == 11);
    assertTrue(inventory.get("20€") == 11);
    assertTrue(inventory.get("10€") == 10);
    assertTrue(inventory.get("5€") == 11);
  }

  @Test
  public void checkWithdrawAmount() throws InvalidTransactionException {
    cashDispenser.withdrawAmount(875);

    HashMap<String, Integer> inventory = cashDispenser.getMoneyInventory();

    assertTrue(inventory.get("100€") == 2);
    assertTrue(inventory.get("50€") == 9);
    assertTrue(inventory.get("20€") == 9);
    assertTrue(inventory.get("10€") == 10);
    assertTrue(inventory.get("5€") == 9);
  }

  @Test
  public void checkInvalidWithdraw() throws InvalidTransactionException {

    assertThrows(InvalidTransactionException.class, () -> {
      cashDispenser.withdrawAmount(-5);
    });
    assertThrows(InvalidTransactionException.class, () -> {
      cashDispenser.withdrawAmount(7);
    });
    assertThrows(InvalidTransactionException.class, () -> {
      cashDispenser.withdrawAmount(1100);
    });
  }

  @Test
  public void checkInvalidDeposit() throws InvalidTransactionException {

    assertThrows(InvalidTransactionException.class, () -> {
      cashDispenser.depositAmount(-4);
    });
    assertThrows(InvalidTransactionException.class, () -> {
      cashDispenser.withdrawAmount(7);
    });
    assertThrows(InvalidTransactionException.class, () -> {
      cashDispenser.withdrawAmount(1100);
    });
  }

}
