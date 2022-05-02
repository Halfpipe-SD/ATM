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

	// public void authenticateuser(int pin) {
	// userAuthenticated = bankDatabase.authenticateUser(pin);

	// if (userAuthenticated) {
	// int accountNumber = bankDatabase.getaccpin(pin);
	// AdminCheck = bankDatabase.getadmin(pin);
	// if (AdminCheck == 0) {
	// currentAccountNumber = accountNumber;
	// screen.Mainframe.getContentPane().removeAll();
	// screen.Mainframe.revalidate();
	// createmenu();
	// screen.Mainframe.add(keypad.addkeypad(), BorderLayout.CENTER);
	// screen.Mainframe.revalidate();
	// }

	// else

	// createAdminGUI();
	// Iterator UserIterator = BankDatabase.createIterator();
	// Addcheck check = new Addcheck();
	// Deletecheck check2 = new Deletecheck();
	// screen.button2.addActionListener(check);
	// screen.button3.addActionListener(check2);

	// } else
	// screen.messageJLabel3.setText(
	// "Invalid account number or PIN. Please try again.");
	// }

	// private class authenticate implements ActionListener {
	// public void actionPerformed(ActionEvent e) {

	// int PIN = Integer.parseInt(screen.Inputfield2.getText());

	// authenticateuser(PIN);
	// }
	// }

	// private class Addcheck implements ActionListener {
	// public void actionPerformed(ActionEvent e) {

	// BankDatabase.addUser();

	// }
	// }

	// private class Deletecheck implements ActionListener {
	// public void actionPerformed(ActionEvent e) {

	// BankDatabase.deleteUser(position);
	// position = position - 1;

	// }
	// }

	// public void createmenu() {
	// screen.setSize(300, 150);
	// balancecheck check1 = new balancecheck();
	// Depositcheck check2 = new Depositcheck();
	// Withdrawcheck check3 = new Withdrawcheck();
	// Exitcheck check4 = new Exitcheck();
	// screen.Mainframe.getContentPane().removeAll();
	// screen.Mainframe.revalidate();

	// screen.Mainframe.add(keypad.addkeypad(), BorderLayout.CENTER);
	// screen.createmenu();
	// Account Account1 = bankDatabase.getAccount(currentAccountNumber);
	// screen.messageJLabel.setText("Welcome " + Account1.getUsername()
	// + " ");

	// keypad.B1.addActionListener(check1);
	// keypad.B2.addActionListener(check3);
	// keypad.B3.addActionListener(check2);
	// keypad.B4.addActionListener(check4);
	// screen.Mainframe.revalidate();
	// }

	// private class balancecheck implements ActionListener {
	// public void actionPerformed(ActionEvent e) {
	// userinput = "";
	// performTransactions(1);
	// }
	// }

	// private class Depositcheck implements ActionListener {
	// public void actionPerformed(ActionEvent e) {
	// userinput = "";
	// performTransactions(3);
	// }
	// }

	// private class Withdrawcheck implements ActionListener {
	// public void actionPerformed(ActionEvent e) {
	// userinput = "";
	// performTransactions(2);
	// }
	// }

	// private class Exitcheck implements ActionListener {
	// public void actionPerformed(ActionEvent e) {
	// startlogin();
	// }
	// }

	// private void performTransactions(int a) {

	// Transaction currentTransaction = null;

	// currentTransaction = createTransaction(a);
	// keypad.setbuttons();
	// addkeypadlisteners();

	// userinput = "";
	// screen.Inputfield2.setText(userinput);
	// currentTransaction.execute();

	// Backcheck Back = new Backcheck();
	// screen.Exit.addActionListener(Back);
	// screen.Mainframe.revalidate();

	// }

	// public class Backcheck implements ActionListener {
	// public void actionPerformed(ActionEvent e) {

	// createmenu();
	// screen.Mainframe.add(keypad.addkeypad(), BorderLayout.CENTER);
	// screen.Mainframe.revalidate();
	// userinput = "";
	// screen.Inputfield2.setText(userinput);
	// }
	// }

	// private Transaction createTransaction(int type) {
	// Transaction temp = null;
	// screen.getContentPane().removeAll();
	// screen.revalidate();

	// if (type == 1)
	// temp = new BalanceInquiry(
	// currentAccountNumber, screen, bankDatabase);
	// else if (type == 2)
	// temp = new Withdrawal(currentAccountNumber, screen,
	// bankDatabase, keypad, cashDispenser);
	// else if (type == 3) {
	// screen.setSize(400, 250);
	// temp = new Deposit(currentAccountNumber, screen,
	// bankDatabase, keypad, depositSlot);
	// }

	// return temp;
	// }

	// public void createAdminGUI() {

	// screen.Mainframe.getContentPane().removeAll();
	// Nextcheck Ncheck = new Nextcheck();
	// Prevcheck Pcheck = new Prevcheck();
	// Exitcheck check4 = new Exitcheck();
	// screen.Mainframe.revalidate();
	// screen.createAdminpage();
	// screen.button1.addActionListener(Ncheck);
	// screen.button4.addActionListener(Pcheck);
	// screen.Exit.addActionListener(check4);
	// screen..revalidate();

	// }

	// public class BCheck implements ActionListener {
	// public void actionPerformed(ActionEvent e) {
	// JButton b = (JButton) e.getSource();
	// String label = b.getLabel();
	// userinput = userinput + label;

	// screen.Inputfield2.setText(userinput);

	// }
	// }

	// public class BClear implements ActionListener {
	// public void actionPerformed(ActionEvent e) {

	// userinput = "";
	// screen.Inputfield2.setText(userinput);
	// }
	// }

	// public class Nextcheck implements ActionListener {
	// public void actionPerformed(ActionEvent e) {

	// IterateUser(BankDatabase.createIterator());
	// }
	// }

	// public class Prevcheck implements ActionListener {
	// public void actionPerformed(ActionEvent e) {

	// prevIterateUser(BankDatabase.createIterator());
	// }
	// }

	// public void IterateUser(Iterator Iterator) {
	// if (Iterator.hasNext(position) == true) {
	// position = position + 1;

	// Account AccountItem = (Account) Iterator.next(position);
	// screen.messageJLabel2.setText("Username: " + AccountItem.getUsername());
	// screen.messageJLabel3.setText("Avaliable Balance: " +
	// AccountItem.getAvailableBalance());
	// screen.messageJLabel4.setText("Avaliable Balance: " +
	// AccountItem.getTotalBalance());
	// }

	// }

	// public void prevIterateUser(Iterator Iterator) {
	// if (Iterator.hasPrev(position) == true) {
	// position = position - 1;
	// Account AccountItem = (Account) Iterator.next(position);
	// screen.messageJLabel2.setText("Username: " + AccountItem.getUsername());
	// screen.messageJLabel3.setText("Avaliable Balance: " +
	// AccountItem.getAvailableBalance());
	// screen.messageJLabel4.setText("Avaliable Balance: " +
	// AccountItem.getTotalBalance());

	// }

	// }

}
