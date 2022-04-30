
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.JButton;

public class Screen extends JFrame {
	private Keypad mainKeypad = new Keypad(180, 160);
	private SidePanel sidePanel = new SidePanel(200, 160);
	private JTextField textField = new JTextField(20);

	public Screen(String title) {
		super(title);

		// initialize main screen
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// add components to main screen
		add(textField, BorderLayout.NORTH);
		add(mainKeypad, BorderLayout.CENTER);
		add(sidePanel, BorderLayout.LINE_END);
		pack();
		setVisible(true);

		showLogin();
	}

	public JTextField getTextField() {
		return textField;
	}

	private void resetScreen() {
		getContentPane().removeAll();
		setLayout(new FlowLayout());
		add(textField);
	}

	public void showLogin() {
		textField.setEditable(false);

		String message = "Insert your credit/debit card then\n"
				+ "Insert your credit/debit card then\n"
				+ "Enter your PIN number: ";

		sidePanel.setTextarea(message);
		repaint();
	}

	public void showMenu() {
		textField.setVisible(false);
		String message = "Welcome to the ATM\n"
				+ "Please select an option: \n"
				+ "1 - Balance\n"
				+ "2 - Withdrawal\n"
				+ "3 - Deposit\n"
				+ "4 - Exit";
		sidePanel.setTextarea(message);
		repaint();
	}

	public void showBalanceUI() {
		// getContentPane().removeAll();
		// setLayout(new FlowLayout());

		// add(new JLabel("Balance Information:"));
		// add(new JLabel("Avaliable Balance:"));
		// add(new JLabel("Total Balance:"));
		// Exit = new JButton("Back");
		// add(messageJLabel);
		// add(messageJLabel2);
		// add(messageJLabel3);
		// add(Exit);
		// repaint();
	}

	public void createWithdrawUI() {
		// getContentPane().removeAll();
		// revalidate();
		// messageJLabel = new JLabel("Withdraw Menu: ");
		// messageJLabel2 = new JLabel("1 - $20 ");
		// messageJLabel3 = new JLabel("2 - $40 ");
		// messageJLabel4 = new JLabel("3 - $60 ");
		// messageJLabel5 = new JLabel("4 - $100 ");
		// messageJLabel6 = new JLabel("5 - $200 ");
		// messageJLabel7 = new JLabel(" Choose an amount to withdraw");
		// Exit = new JButton("Cancel");
		// setLayout(new FlowLayout());
		// add(messageJLabel);
		// add(messageJLabel2);
		// add(messageJLabel3);
		// add(messageJLabel4);
		// add(messageJLabel5);
		// add(messageJLabel6);
		// add(Exit);
		// add(messageJLabel7);
		// repaint();

	}

	public void CreateDepositUI() {
		// getContentPane().removeAll();
		// messageJLabel2 = new JLabel("Please enter a deposit amount in CENTS");
		// messageJLabel3 = new JLabel("");
		// Inputfield2 = new JTextField(10);
		// Inputfield2.setEditable(false);
		// button1 = new JButton("Deposit");
		// Exit = new JButton("Cancel");
		// add(messageJLabel2);
		// add(messageJLabel3);
		// add(Inputfield2);
		// add(Exit);
		// repaint();
	}

	public void createAdminpage() {
		// messageJLabel = new JLabel("View Users:");
		// messageJLabel2 = new JLabel("Account number:");
		// messageJLabel3 = new JLabel("Avaliable Balance:");
		// messageJLabel4 = new JLabel("Total Balance:");
		// messageJLabel5 = new
		// JLabel("________________________________________________");
		// button1 = new JButton("Next");
		// button4 = new JButton("Previous");
		// Exit = new JButton("Back");
		// Inputfield1 = new JTextField(10);
		// Inputfield2 = new JTextField(10);
		// Inputfield3 = new JTextField(10);
		// Inputfield4 = new JTextField(10);
		// setLayout(new FlowLayout());
		// messageJLabel6 = new JLabel("Add Account: ");
		// messageJLabel7 = new JLabel("User name: ");
		// add(messageJLabel);
		// messageJLabel8 = new JLabel(" Account number: ");
		// add(messageJLabel2);
		// messageJLabel10 = new JLabel(" PIN: ");

		// messageJLabel9 = new JLabel(" Balance number: ");
		// button2 = new JButton("Add");
		// button3 = new JButton("Delete");

		// add(messageJLabel3);
		// add(messageJLabel4);
		// add(button4);
		// add(button1);
		// add(button3);
		// add(messageJLabel5);
		// add(messageJLabel6);
		// add(messageJLabel7);
		// add(Inputfield1);
		// add(messageJLabel8);
		// add(Inputfield2);
		// add(messageJLabel10);
		// add(Inputfield4);
		// add(messageJLabel9);
		// add(Inputfield3);

		// add(button2);

		// add(Exit);
		// repaint();
	}

	public void displayMessage(String message) {
		System.out.print(message);
	}

	public void displayMessageLine(String message) {
		System.out.println(message);
	}

	public void displayDollarAmount(double amount) {
		System.out.printf("$%,.2f", amount);
	}

}
