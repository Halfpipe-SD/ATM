package Listeners;

public interface ATMListener {

  enum ATM_Mode {
    LOGIN, MENU, BALANCE, WITHDRAWAL, DEPOSIT, ADMIN
  }

  boolean isDebugMode();

  void atmEnterAction(String input);

  void atmSwitchModeAction(ATM_Mode mode);
}
