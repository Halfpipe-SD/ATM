import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Interfaces.ATMListener.ATM_Mode;

public class AdminView extends JFrame {

  private JTextField tfTop = new JTextField(20);
  private JTextField tfBottom = new JTextField(20);

  private final Font tfFont = new Font("", Font.PLAIN, 12);

  private ATM atm;

  public AdminView(ATM atm, String title) {
    super(title);
    this.atm = atm;

    initializeUIComponents();
    handleWindowEvents();

    setResizable(false);
    pack();
    setVisible(true);
  }

  private void initializeUIComponents() {
    tfTop.setHorizontalAlignment(JTextField.CENTER);
    tfTop.setFont(tfFont);
    tfTop.setEditable(false);
    tfTop.setText("Welcome, " + atm.getCurrentAccount().getUsername() + "!");

    tfBottom.setHorizontalAlignment(JTextField.CENTER);
    tfBottom.setFont(tfFont);
    tfBottom.setEditable(false);
    tfBottom.setForeground(Color.RED);

    add(tfTop, BorderLayout.NORTH);
    add(tfBottom, BorderLayout.SOUTH);
  }

  private void handleWindowEvents() {
    addWindowListener(new WindowListener() {

      @Override
      public void windowClosing(WindowEvent e) {
        atm.atmSwitchModeAction(ATM_Mode.LOGIN);
      }

      @Override
      public void windowOpened(WindowEvent e) {
      }

      @Override
      public void windowClosed(WindowEvent e) {
      }

      @Override
      public void windowIconified(WindowEvent e) {
      }

      @Override
      public void windowDeiconified(WindowEvent e) {
      }

      @Override
      public void windowActivated(WindowEvent e) {
      }

      @Override
      public void windowDeactivated(WindowEvent e) {
      }
    });
  }

}
