import java.io.FileNotFoundException;
import java.io.IOException;

import Exceptions.LoginFailedException;
import Interfaces.ATMListener;
import Exceptions.InvalidMenuException;

public class ATM implements ATMListener {

  private final String pathToAccounts = "/accounts.json";

  private Screen screen = null;
  private BankDatabase bankDatabase = null;
  private Account currentAccount = null;
  private ATM_Mode currentMode = null;

  private boolean debugMode = false;

  public ATM(boolean debug) throws FileNotFoundException, IOException {

    // set debug mode
    debugMode = debug;

    // initialize Screen and BankDatabase
    screen = new Screen(this, "ATM-Machine");
    bankDatabase = new BankDatabase(pathToAccounts);
  }

  public ATM() throws FileNotFoundException, IOException {
    this(false);
  }

  public void start() {
    // start in login mode
    this.atmSwitchModeAction(ATM_Mode.LOGIN);
  }

  @Override
  public void atmEnterAction(String input) {
    if (debugMode)
      System.out.println("Enter action in mode: " + currentMode + " with input: " + input);

    // clear sidepanel text field and error message
    screen.getSidePanel().clearTextField();
    screen.clearErrorMessage();

    try {
      switch (currentMode) {
        case LOGIN:
          currentAccount = bankDatabase.validateAccount(input);
          this.atmSwitchModeAction(ATM_Mode.MENU);
          break;
        case MENU:
          this.atmSwitchModeAction(this.getModeFromString(input));
          break;
        case BALANCE:
          break;
        case WITHDRAWAL:
          break;
        case DEPOSIT:
          break;
        case ADMIN:
          break;
      }
    } catch (LoginFailedException e) {
      screen.setErrorMessage(e.getMessage());

    } catch (InvalidMenuException e) {
      screen.setErrorMessage(e.getMessage());
    }
  }

  @Override
  public void atmSwitchModeAction(ATM_Mode newMode) {
    if (debugMode)
      System.out.println("Switched mode from: " + currentMode + " to: " + newMode);

    currentMode = newMode;
    screen.setAdditionalTitle(currentMode.toString());

    switch (newMode) {
      case LOGIN:
        screen.showLogin();
        break;
      case MENU:
        screen.showMenu();
        break;
      case BALANCE:
        screen.showBalance();
        break;
      case WITHDRAWAL:
        break;
      case DEPOSIT:
        break;
      case ADMIN:
        break;
    }
  }

  public Account getCurrentAccount() {
    return currentAccount;
  }
}
