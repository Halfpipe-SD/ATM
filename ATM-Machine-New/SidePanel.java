
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Interfaces.ATMListener.ATM_Mode;

public class SidePanel extends JPanel {

  private final Color backgroundColor = Color.lightGray;
  private final Font tfFont = new Font("", Font.BOLD, 20);

  private JTextField tf = new JTextField(3);
  private JLabel tl = new JLabel();
  private JButton backButton = new JButton("Back");

  public SidePanel(ATM atm, int width, int height) {

    setPreferredSize(new Dimension(width, height));
    setBackground(backgroundColor);
    setLayout(new FlowLayout());

    tf.setEditable(false);
    tf.setFont(tfFont);

    backButton.setVisible(false);
    backButton.setFocusable(false);
    backButton.addActionListener(e -> atm.atmSwitchModeAction(ATM_Mode.MENU));

    add(tf);
    add(tl);
    add(backButton);
  }

  public void hideTextField() {
    tf.setVisible(false);
  }

  public void showTextField() {
    tf.setVisible(true);
  }

  public void hideBackButton() {
    backButton.setVisible(false);
  }

  public void showBackButton() {
    backButton.setVisible(true);
  }

  public void clearTextField() {
    tf.setText("");
  }

  public void addTextFieldChar(String s) {
    tf.setText(tf.getText() + s);
  }

  public void setTextField(String text) {
    tf.setText(text);
  }

  public void setLabelHTML(String html) {
    tl.setText("<html>" + html + "</html>");
  }

  public String getTextField() {
    return tf.getText();
  }

  public String getLabel() {
    return tl.getText();
  }

}
