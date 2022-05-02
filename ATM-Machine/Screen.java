import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.BorderLayout;

import Listeners.KeypadListener;
import Listeners.ATMListener;

public class Screen extends JFrame implements KeypadListener {

	private Keypad keypad = new Keypad(this, 180, 160);
	private SidePanel sidePanel = new SidePanel(160, 160);

	private JTextField tfTop = new JTextField(20);
	// private JTextField tfBottom = new JTextField(20);

	private Font tfFontTop = new Font("", Font.PLAIN, 12);
	// private Font tfFontBottom = new Font("", Font.BOLD, 12);

	private ATM atm;

	public Screen(ATM atm, String title) {
		super(title);
		this.atm = atm;

		// initialize main screen
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// setup textfields
		tfTop.setHorizontalAlignment(JLabel.CENTER);
		tfTop.setFont(tfFontTop);
		tfTop.setEditable(false);
		// tfBottom.setHorizontalAlignment(JLabel.CENTER);
		// tfBottom.setFont(tfFontBottom);
		// tfBottom.setEditable(false);

		// add components to main screen
		add(tfTop, BorderLayout.NORTH);
		add(keypad, BorderLayout.CENTER);
		add(sidePanel, BorderLayout.LINE_END);
		// add(tfBottom, BorderLayout.SOUTH);
		pack();
		setVisible(true);
	}

	@Override
	public void buttonPressed(String value) {
		if (value == "Clear")
			sidePanel.clearTextField();
		else if (value == "Enter")
			atm.atmEnterAction(sidePanel.getTextField());
		else
			sidePanel.addTextFieldChar(value);
	}

	public void setAdditionalTitle(String title) {
		setTitle("ATM Machine - " + title);
	}

	public void showLogin() {
		tfTop.setText("Insert your credit/debit card, then enter your PIN number: ");
		sidePanel.setLabelHTML("");
	}

	public void showMenu() {
		tfTop.setText("Welcome to the ATM, " + atm.getCurrentAccount().getUsername() + "!");
		sidePanel.setLabelHTML("Please select an option: <br>"
				+ "1 - Balance<br>"
				+ "2 - Withdrawal<br>"
				+ "3 - Deposit<br>"
				+ "4 - Logout");
	}

	public void showBalance() {
		tfTop.setText("Balance Information of " + atm.getCurrentAccount().getUsername());
		sidePanel.setLabelHTML("Available Balance is: <br>"
				+ atm.getCurrentAccount().getAvailableBalance() + " €<br><br>"
				+ "Total Balance: <br>"
				+ atm.getCurrentAccount().getTotalBalance() + " €");
	}

	public SidePanel getSidePanel() {
		return sidePanel;
	}

	public String getText() {
		return tfTop.getText();
	}
}
