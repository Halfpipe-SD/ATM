package Listeners;

public interface ATMListener {

  public enum ATM_Mode {
    LOGIN, MENU, BALANCE, WITHDRAWAL, DEPOSIT, ADMIN
  }

  public boolean isDebugMode();

  public void atmEnterAction(String input);

  public void atmSwitchModeAction(ATM_Mode mode);
}
