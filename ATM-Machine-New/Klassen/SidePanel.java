package klassen;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interfaces.ATMListener.ATM_Mode;

public class SidePanel extends JPanel {

  private final Color backgroundColor = Color.lightGray;
  private final Font tfPinFont = new Font("", Font.BOLD, 20);
  private final Font tfTransactionFont = new Font("", Font.PLAIN, 12);

  private JTextField tfPin = new JTextField(3);
  private JTextField tfTransaction = new JTextField(10);

  private JLabel tl = new JLabel();
  private JButton backButton = new JButton("Abbrechen");
  private JButton okButton = new JButton("Ok");

  public SidePanel(ATM atm, int width, int height) {

    setPreferredSize(new Dimension(width, height));
    setBackground(backgroundColor);
    setLayout(new FlowLayout());

    tfPin.setEditable(false);
    tfPin.setFont(tfPinFont);
    tfPin.setHorizontalAlignment(JTextField.CENTER);

    tfTransaction.setEditable(true);
    tfTransaction.setFont(tfTransactionFont);
    tfTransaction.setHorizontalAlignment(JTextField.RIGHT);

    backButton.setVisible(false);
    backButton.setFocusable(false);
    backButton.addActionListener(e -> atm.atmSwitchModeAction(ATM_Mode.MENU));

    okButton.setVisible(false);
    okButton.setFocusable(false);
    okButton.addActionListener(e -> atm.atmEnterAction(tfTransaction.getText()));

    add(tfPin);
    add(tl);
    add(tfTransaction);
    add(backButton);
    add(okButton);
  }

  public void setModeCardPrompt() {
    backButton.setVisible(false);
    okButton.setVisible(true);
    tfPin.setVisible(false);
    tfTransaction.setVisible(false);
  }

  public void setModeLogin() {
    tfPin.setText("");
    backButton.setVisible(false);
    okButton.setVisible(false);
    tfPin.setVisible(true);
    tfTransaction.setVisible(false);
  }

  public void setModeMenu() {
    backButton.setVisible(false);
    okButton.setVisible(false);
    tfPin.setVisible(true);
    tfTransaction.setVisible(false);
  }

  public void setModeBalance() {
    backButton.setVisible(true);
    okButton.setVisible(false);
    tfPin.setVisible(false);
    tfTransaction.setVisible(false);
  }

  public void setModeWithdrawal() {
    tfTransaction.setText("50.00");
    backButton.setVisible(true);
    okButton.setVisible(true);
    tfPin.setVisible(false);
    tfTransaction.setVisible(true);
  }

  public void setModeDeposit() {
    backButton.setVisible(true);
    okButton.setVisible(true);
    tfPin.setVisible(false);
    tfTransaction.setVisible(true);
  }

  public void addTextFieldChar(String s) {
    tfPin.setText(tfPin.getText() + s);
  }

  public void setTfPin(String text) {
    tfPin.setText(text);
  }

  public void setTfTransaction(String text) {
    tfTransaction.setText(text);
  }

  public void setLabelHTML(String html) {
    tl.setText("<html>" + html + "</html>");
  }

  public String getTextFieldText() {
    return tfPin.getText();
  }

  public String getLabel() {
    return tl.getText();
  }

}
