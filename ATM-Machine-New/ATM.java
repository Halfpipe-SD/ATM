import java.io.FileNotFoundException;
import java.io.IOException;

import Exceptions.InvalidModeException;
import Exceptions.LoginFailedException;
import Interfaces.ATMListener;

public class ATM implements ATMListener {

  private static ATM uniqueinstance;
  private boolean debugMode = false;

  private final String pathToAccounts = "/accounts.json";
  private final String title = "ATM Machine";

  // Komponenten der Hauptklasse
  private Screen screen = null;
  private BankDatabase bankDatabase = null;
  private Account currentAccount = null;
  private ATM_Mode currentMode = null;

  private ATM(boolean debug) throws FileNotFoundException, IOException {
    // setze debug modus
    debugMode = debug;

    // initialisiere Bildschirm und Bank-Datenbank
    screen = new Screen(this, title);
    bankDatabase = new BankDatabase(pathToAccounts);
  }

  public static ATM getInstance(boolean debugMode) throws FileNotFoundException, IOException {
    if (uniqueinstance == null)
      uniqueinstance = new ATM(debugMode);
    return uniqueinstance;
  }

  // starte im Login Modus
  public void start() {
    this.atmSwitchModeAction(ATM_Mode.LOGIN);
  }

  @Override
  public void atmEnterAction(String input) {
    if (debugMode)
      System.out.println("Enter action in mode: " + currentMode + " with input: " + input);

    // Lösche Eingabe im Textfeld und lösche Fehlermeldung
    screen.getSidePanel().clearTextField();
    screen.clearErrorMessage();

    try {
      switch (currentMode) {
        case LOGIN:
          currentAccount = bankDatabase.validateAccount(input);
          // wechsle in den admin modus, wenn der account ein Administrator ist
          if (currentAccount.getAdmin())
            this.atmSwitchModeAction(ATM_Mode.ADMIN);
          else
            this.atmSwitchModeAction(ATM_Mode.MENU);
          break;
        case MENU:
          this.atmSwitchModeAction(this.getModeFromString(input));
          break;
        case BALANCE:
          break;
        case WITHDRAWAL:
          this.withdrawMoney(input);
          break;
        case DEPOSIT:
          this.depositMoney(input);
          break;
        case ADMIN:
          screen.setErrorMessage("You have to close the Admin View first!");
          break;
      }
    } catch (LoginFailedException e) {
      screen.setErrorMessage(e.getMessage());

    } catch (InvalidModeException e) {
      screen.setErrorMessage(e.getMessage());
    }
  }

  // Prototypen für withdraw und deposit Funktion. Bis jetzt kann man jeden Betrag
  // auswählen (nicht nur Scheine)
  public void withdrawMoney(String input) {
    int withdrawAmount = Integer.parseInt(input);

    if (withdrawAmount > getCurrentAccount().getAvailableBalance()) {
      screen.setErrorMessage("You don't have sufficient funds to withdraw " + input + "€.");
    } else if (withdrawAmount > 1000) {
      screen.setErrorMessage("You can't withdraw more than 1000€ at once.");
    } else {
      getCurrentAccount().debit(withdrawAmount);
      screen.getSidePanel().setLabelHTML("<br>"
          + "Available Balance is: <br>"
          + getCurrentAccount().getAvailableBalance() + " €<br><br>");
      screen.setText("You withdrew " + withdrawAmount + "€.");
    }
  }

  public void depositMoney(String input) {
    int depositAmount = Integer.parseInt(input);

    if (depositAmount > 5000) {
      screen.setErrorMessage("You can't deposit more than 5000€ at once.");
    } else if (depositAmount > 0) {
      getCurrentAccount().credit(depositAmount);
      screen.getSidePanel().setLabelHTML("Available Balance is: <br>"
          + getCurrentAccount().getAvailableBalance() + " €<br><br>"
          + "Total Balance: <br>"
          + getCurrentAccount().getTotalBalance() + " €");
      screen.setText("You deposited " + depositAmount + "€.");
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
        screen.showWithdrawal();
        break;
      case DEPOSIT:
        screen.showDeposit();
        break;
      case ADMIN:
        new AdminView(this, title + " - Admin View");
        break;
    }
  }

  public Account getCurrentAccount() {
    return currentAccount;
  }
}
