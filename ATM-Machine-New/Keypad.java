import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import Interfaces.KeypadListener;

public class Keypad extends JPanel {

  private final String[] buttonValues = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "Clear", "Enter" };
  private final Color backgroundColor = Color.gray;

  public Keypad(KeypadListener bl, int width, int height) {

    setPreferredSize(new Dimension(width, height));
    setBackground(backgroundColor);
    setLayout(new FlowLayout());

    // create buttons and add them to the panel
    for (String value : buttonValues) {
      JButton button = new JButton(value);
      button.addActionListener(e -> bl.buttonPressed(button.getText()));
      add(button);
    }
  }
}
