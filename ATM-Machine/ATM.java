import java.io.IOException;
import java.nio.file.InvalidPathException;

import Exceptions.LoginFailedException;
import Exceptions.InvalidMenuException;
import Listeners.ATMListener;

public class ATM implements ATMListener {

	private final String pathToAccounts = "/accounts.json";

	private Screen screen = null;
	private BankDatabase bankDatabase = null;
	private Account currentAccount = null;
	private ATM_Mode currentMode = null;

	private boolean debugMode = false;

	public ATM(boolean debug) throws InvalidPathException, IOException {

		// set debug mode
		debugMode = debug;

		// initialize Screen and BankDatabase
		screen = new Screen(this, "ATM Machine");
		bankDatabase = new BankDatabase(pathToAccounts);
	}

	public ATM() throws InvalidPathException, IOException {
		this(false);
	}

	public void start() {
		// start in login mode
		this.atmSwitchModeAction(ATM_Mode.LOGIN);
	}

	@Override
	public void atmEnterAction(String input) {
		if (debugMode)
			System.out.println("Mode: " + currentMode + " | Input: " + input);

		screen.getSidePanel().clearTextField();
		try {
			switch (currentMode) {
				case LOGIN:
					currentAccount = bankDatabase.validateAccount(input);
					this.atmSwitchModeAction(ATM_Mode.MENU);
					break;
				case MENU:
					this.atmSwitchModeAction(getModeFromMenuInput(input));
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
			// TODO set error message
			System.out.println(e);
		} catch (InvalidMenuException e) {
			// TODO set error message
			System.out.println(e);
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

	private ATM_Mode getModeFromMenuInput(String input) throws InvalidMenuException {
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
				throw new InvalidMenuException("Invalid menu input: " + input);
		}
	}

	public Account getCurrentAccount() {
		return currentAccount;
	}
}
