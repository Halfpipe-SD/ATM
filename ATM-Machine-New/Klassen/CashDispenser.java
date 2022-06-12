package klassen;

import java.util.HashMap;

import exceptions.InvalidTransactionException;

public class CashDispenser {

  private HashMap<String, Integer> moneyInventory = new HashMap<>();
  private boolean debugMode;

  public CashDispenser() {
    this(false);
  }

  public CashDispenser(boolean debugMode) {
    this.debugMode = debugMode;

    moneyInventory.put("5€", 10);
    moneyInventory.put("10€", 10);
    moneyInventory.put("20€", 10);
    moneyInventory.put("50€", 10);
    moneyInventory.put("100€", 10);

    if (debugMode)
      printInfo();
  }

  public void withdrawAmount(double amount) throws InvalidTransactionException {
    int[] amounts = { 0, 0, 0, 0, 0 };

    if (amount <= 0)
      throw new InvalidTransactionException("Betrag kann nicht negativ oder null sein!");

    while (amount > 0) {
      if (amount >= 100) {
        amounts[4]++;
        amount -= 100;
      } else if (amount >= 50) {
        amounts[3]++;
        amount -= 50;
      } else if (amount >= 20) {
        amounts[2]++;
        amount -= 20;
      } else if (amount >= 10) {
        amounts[1]++;
        amount -= 10;
      } else if (amount >= 5) {
        amounts[0]++;
        amount -= 5;
      } else
        throw new InvalidTransactionException("Ungültiger Betrag!");
    }

    if (moneyInventory.get("5€") - amounts[0] < 0)
      throw new InvalidTransactionException("Es sind nicht genügend 5€-Scheine vorhanden!");

    if (moneyInventory.get("10€") - amounts[1] < 0)
      throw new InvalidTransactionException("Es sind nicht genügend 10€-Scheine vorhanden!");

    if (moneyInventory.get("20€") - amounts[2] < 0)
      throw new InvalidTransactionException("Es sind nicht genügend 20€-Scheine vorhanden!");

    if (moneyInventory.get("50€") - amounts[3] < 0)
      throw new InvalidTransactionException("Es sind nicht genügend 50€-Scheine vorhanden!");

    if (moneyInventory.get("100€") - amounts[4] < 0)
      throw new InvalidTransactionException("Es sind nicht genügend 100€-Scheine vorhanden!");

    moneyInventory.put("5€", moneyInventory.get("5€") - amounts[0]);
    moneyInventory.put("10€", moneyInventory.get("10€") - amounts[1]);
    moneyInventory.put("20€", moneyInventory.get("20€") - amounts[2]);
    moneyInventory.put("50€", moneyInventory.get("50€") - amounts[3]);
    moneyInventory.put("100€", moneyInventory.get("100€") - amounts[4]);

    if (debugMode)
      printInfo();
  }

  public void depositAmount(double amount) throws InvalidTransactionException {
    int[] amounts = { 0, 0, 0, 0, 0 };

    if (amount <= 0)
      throw new InvalidTransactionException("Betrag kann nicht negativ oder null sein!");

    while (amount > 0) {
      if (amount >= 100) {
        amounts[4]++;
        amount -= 100;
      } else if (amount >= 50) {
        amounts[3]++;
        amount -= 50;
      } else if (amount >= 20) {
        amounts[2]++;
        amount -= 20;
      } else if (amount >= 10) {
        amounts[1]++;
        amount -= 10;
      } else if (amount >= 5) {
        amounts[0]++;
        amount -= 5;
      } else
        throw new InvalidTransactionException("Ungültiger Betrag!");
    }

    moneyInventory.put("5€", moneyInventory.get("5€") + amounts[0]);
    moneyInventory.put("10€", moneyInventory.get("10€") + amounts[1]);
    moneyInventory.put("20€", moneyInventory.get("20€") + amounts[2]);
    moneyInventory.put("50€", moneyInventory.get("50€") + amounts[3]);
    moneyInventory.put("100€", moneyInventory.get("100€") + amounts[4]);

    if (debugMode)
      printInfo();
  }

  public void printInfo() {
    System.out.println("CashDispenser Inhalt: " + moneyInventory.toString());
  }

  public HashMap<String, Integer> getMoneyInventory() {
    return moneyInventory;
  }
}
