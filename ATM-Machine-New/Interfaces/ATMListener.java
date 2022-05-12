package Interfaces;

import Exceptions.InvalidModeException;

public interface ATMListener {

  public static enum ATM_Mode {
    LOGIN, MENU, BALANCE, WITHDRAWAL, DEPOSIT, ADMIN
  };

  public void atmEnterAction(String input);

  public void atmSwitchModeAction(ATM_Mode mode);

  public default ATM_Mode getModeFromString(String input) throws InvalidModeException {
    switch (input) {
      case "1":
        return ATM_Mode.BALANCE;
      case "2":
        return ATM_Mode.WITHDRAWAL;
      case "3":
        return ATM_Mode.DEPOSIT;
      case "4":
        return ATM_Mode.LOGIN;
      default:
        throw new InvalidModeException("Invalid menu input: " + input);
    }

  }
}
