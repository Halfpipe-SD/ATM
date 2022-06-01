package Interfaces;

import Exceptions.InvalidModeException;

public interface ATMListener {

  enum ATM_Mode {
    LOGIN, MENU, BALANCE, WITHDRAWAL, DEPOSIT, ADMIN
  };

  void atmEnterAction(String input);

  void atmSwitchModeAction(ATM_Mode mode);

  default ATM_Mode getModeFromString(String input) throws InvalidModeException {
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
        throw new InvalidModeException("Ungültiger Menu-Modus: " + input);
    }

  }
}
