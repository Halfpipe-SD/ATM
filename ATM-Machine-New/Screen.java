import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Interfaces.KeypadListener;

public class Screen extends JFrame implements KeypadListener {

  private Keypad keypad;
  private SidePanel sidePanel;

  private JTextField tfTop = new JTextField(20);
  private JTextField tfBottom = new JTextField(20);

  private final Font tfFont = new Font("", Font.PLAIN, 12);

  private ATM atm;
  private String baseTitle;

  public Screen(ATM atm, String baseTitle) {
    super(baseTitle);
    this.baseTitle = baseTitle;
    this.atm = atm;

    sidePanel = new SidePanel(atm, 180, 160);
    keypad = new Keypad(this, 180, 160);

    // Initialisiere Hauptbildschirm
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // Einstellen der Textfelder
    tfTop.setHorizontalAlignment(JLabel.CENTER);
    tfTop.setFont(tfFont);
    tfTop.setEditable(false);
    tfBottom.setHorizontalAlignment(JLabel.CENTER);
    tfBottom.setFont(tfFont);
    tfBottom.setEditable(false);
    tfBottom.setForeground(Color.RED);

    // Hinzufügen der UI-Komponenten
    add(tfTop, BorderLayout.NORTH);
    add(keypad, BorderLayout.CENTER);
    add(sidePanel, BorderLayout.LINE_END);
    add(tfBottom, BorderLayout.SOUTH);
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
    setTitle(baseTitle + " - " + title);
  }

  public void setErrorMessage(String message) {
    tfBottom.setText(message);
  }

  public void clearErrorMessage() {
    tfBottom.setText("");
  }

  public void showLogin() {
    tfTop.setText("Insert your credit/debit card, then enter your PIN number: ");
    sidePanel.setLabelHTML("");
    sidePanel.hideBackButton();
    sidePanel.showTextField();
  }

  public void showMenu() {
    tfTop.setText("Welcome to the ATM, " + atm.getCurrentAccount().getUsername() + "!");
    sidePanel.setLabelHTML("Please select an option: <br>"
        + "1 - Balance<br>"
        + "2 - Withdrawal<br>"
        + "3 - Deposit<br>"
        + "4 - Logout");
    sidePanel.hideBackButton();
    sidePanel.showTextField();
  }

  public void showBalance() {
    tfTop.setText("Balance Information of " + atm.getCurrentAccount().getUsername());
    sidePanel.setLabelHTML("<br>"
        + "Available Balance is: <br>"
        + atm.getCurrentAccount().getAvailableBalance() + " €<br><br>"
        + "Total Balance: <br>"
        + atm.getCurrentAccount().getTotalBalance() + " €");
    sidePanel.showBackButton();
    sidePanel.hideTextField();
  }

  public void showWithdrawal() {
    tfTop.setText("Choose an amount to withdraw.");
    sidePanel.setLabelHTML("<br>"
        + "Available Balance is: <br>"
        + atm.getCurrentAccount().getAvailableBalance() + " €<br><br>");
    sidePanel.showBackButton();
  }

  public void showDeposit() {
    tfTop.setText("Choose an amount to deposit.");
    sidePanel.setLabelHTML("Available Balance is: <br>"
        + atm.getCurrentAccount().getAvailableBalance() + " €<br><br>"
        + "Total Balance: <br>"
        + atm.getCurrentAccount().getTotalBalance() + " €");
    sidePanel.showBackButton();
  }

  public SidePanel getSidePanel() {
    return sidePanel;
  }

  public String getText() {
    return tfTop.getText();
  }

  public void setText(String text) {
    tfTop.setText(text);
  }

  public Point getScreenLocation() {
    Point screenLocation = getLocation();
    return screenLocation;
  }
}
