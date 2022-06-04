import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

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
    keypad = new Keypad(this, 150, 160);

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
      sidePanel.setTextField("");
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

  public void showCardPrompt() {
    tfTop.setText("Bitte Karte einführen");
    sidePanel.setLabelHTML("<br><br><br>Bite Bankkarte einführen<br><br>");
    sidePanel.setBackButtonVisible(false);
    sidePanel.setOkButtonVisible(true);
    sidePanel.setTextFieldVisible(false);
  }

  public void showLogin() {
    sidePanel.setTextField("");
    tfTop.setText("Bitte geben Sie ihre PIN ein: ");
    sidePanel.setLabelHTML("");
    sidePanel.setBackButtonVisible(false);
    sidePanel.setOkButtonVisible(false);
    sidePanel.setTextFieldVisible(true);
  }

  public void showMenu() {
    tfTop.setText("Willkommen am ATM, " + atm.getCurrentAccount().getUsername() + "!");
    sidePanel.setLabelHTML("Bitte wähle eine Option: <br>"
        + "1 - Guthaben anzeigen<br>"
        + "2 - Geld abheben<br>"
        + "3 - Geld einzahlen<br>"
        + "4 - Abbrechen");
    sidePanel.setBackButtonVisible(false);
    sidePanel.setOkButtonVisible(false);
    sidePanel.setTextFieldVisible(true);
  }

  public void showBalance() {
    tfTop.setText("Guthaben von " + atm.getCurrentAccount().getUsername());
    sidePanel.setLabelHTML("<br>"
        + "Verfügbares Guthaben: <br>"
        + atm.getCurrentAccount().getAvailableBalance() + " €<br><br>"
        + "Gesamtes Guthaben: <br>"
        + atm.getCurrentAccount().getTotalBalance() + " €");
    sidePanel.setBackButtonVisible(true);
    sidePanel.setOkButtonVisible(false);
    sidePanel.setTextFieldVisible(false);
  }

  public void showWithdrawal() {
    tfTop.setText("Choose an amount to withdraw.");
    sidePanel.setLabelHTML("<br>"
        + "Verfügbares Guthaben: <br>"
        + atm.getCurrentAccount().getAvailableBalance() + " €<br><br>");
    sidePanel.setBackButtonVisible(true);
    sidePanel.setOkButtonVisible(false);
    sidePanel.setTextFieldVisible(false);
  }

  public void showDeposit() {
    tfTop.setText("Choose an amount to deposit.");
    sidePanel.setLabelHTML("Verfügbares Guthaben: <br>"
        + atm.getCurrentAccount().getAvailableBalance() + " €<br><br>"
        + "Gesamtes Guthaben: <br>"
        + atm.getCurrentAccount().getTotalBalance() + " €");
    sidePanel.setBackButtonVisible(true);
    sidePanel.setOkButtonVisible(false);
    sidePanel.setTextFieldVisible(false);
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
}
