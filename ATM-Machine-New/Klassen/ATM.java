package klassen;

import java.io.FileNotFoundException;
import java.io.IOException;

import exceptions.InvalidModeException;
import exceptions.LoginFailedException;
import interfaces.ATMListener;

public class ATM implements ATMListener {

  private static ATM uniqueinstance;
  private boolean debugMode = false;

  private final String pathToAccounts = "\\ATM-Machine-New\\assets\\accounts.json";
  private final String title = "ATM Machine";

  // Komponenten der Hauptklasse
  private Screen screen = null;
  private BankDatabase bankDatabase = null;
  private Account currentAccount = null;
  private ATM_Mode currentMode = null;

  private ATM(boolean debug) throws FileNotFoundException, IOException {
    debugMode = debug;
    screen = new Screen(this, title);
    bankDatabase = new BankDatabase(pathToAccounts);
  }

  public ATM(String pathToJSON) throws FileNotFoundException, IOException {
    debugMode = true;
    screen = new Screen(this, title);
    bankDatabase = new BankDatabase(pathToJSON);
  }

  public static ATM getInstance(boolean debugMode) throws FileNotFoundException, IOException {
    if (uniqueinstance == null)
      uniqueinstance = new ATM(debugMode);
    return uniqueinstance;
  }

  // starte im CARD_REQ Modus
  public void start() {
    this.atmSwitchModeAction(ATM_Mode.CARD_REQ);
  }

  @Override
  public void atmEnterAction(String input) {
    if (debugMode)
      System.out.println("Enter action in mode: " + currentMode + " with input: " + input);

    // Lösche Eingabe im Textfeld und lösche Fehlermeldung
    screen.getSidePanel().setTextField("");
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
          screen.setErrorMessage("Die Admin-Ansicht muss zuerst geschlossen werden!");
          break;
        case CARD_REQ: // do nothing
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

    if (withdrawAmount > 1000) {
      screen.setErrorMessage("Sie können nicht mehr als 1000€ auf einmal abheben.");
    } else if (withdrawAmount > getCurrentAccount().getAvailableBalance()) {
      screen.setErrorMessage("Ihr aktueller Kontostand ist zu niedrig um " + withdrawAmount + "€ abzuheben.");
    } else {
      getCurrentAccount().debit(withdrawAmount);
      screen.getSidePanel().setLabelHTML("<br>"
          + "Verfügbares Guthaben: <br>"
          + getCurrentAccount().getAvailableBalance() + " €<br><br>");
      screen.setText("Sie haben " + withdrawAmount + "€ abgehoben.");
    }
  }

  public void depositMoney(String input) {
    int depositAmount = Integer.parseInt(input);

    if (depositAmount > 5000) {
      screen.setErrorMessage("Sie können nicht mehr als 5000€ auf einmal einzahlen.");
    } else if (depositAmount > 0) {
      getCurrentAccount().credit(depositAmount);
      screen.getSidePanel().setLabelHTML("Verfügbares Guthaben: <br>"
          + getCurrentAccount().getAvailableBalance() + " €<br><br>"
          + "Gesamtes Guthaben: <br>"
          + getCurrentAccount().getTotalBalance() + " €");
      screen.setText("Sie haben " + depositAmount + "€ eingezahlt.");
    }
  }

  @Override
  public void atmSwitchModeAction(ATM_Mode newMode) {
    if (debugMode) {
      System.out.println("Switched mode from: " + currentMode + " to: " + newMode);
      screen.setAdditionalTitle(newMode.toString());
    }

    currentMode = newMode;

    switch (newMode) {
      case CARD_REQ:
        screen.showCardPrompt();
        break;
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
        new AdminView(this, "Admin-Ansicht");
        break;
    }
  }

  public Account getCurrentAccount() {
    return currentAccount;
  }

  public BankDatabase getBankDatabase() {
    return bankDatabase;
  }

  public Screen getScreen() {
    return screen;
  }

  public ATM_Mode getCurrentMode() {
    return currentMode;
  }
}
