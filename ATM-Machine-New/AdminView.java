import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import Interfaces.ATMListener.ATM_Mode;
import Utilities.SpringUtilities;

public class AdminView extends JFrame {

  private ATM atm;

  private JTextField tfTop = new JTextField(20);
  private final Font tfFont = new Font("", Font.PLAIN, 12);

  private final int width = 400;
  private final int height = 200;

  private JLabel lAccountNumber = new JLabel("Account-Nummer", JLabel.TRAILING);
  private JLabel lUsername = new JLabel("Benutzername", JLabel.TRAILING);
  private JLabel lAvailableBalance = new JLabel("Verfügbares Guthaben", JLabel.TRAILING);
  private JLabel lTotalBalance = new JLabel("Gesamtes Guthaben", JLabel.TRAILING);

  private JTextField tfAccountNumber = new JTextField(30);
  private JTextField tfUsername = new JTextField(30);
  private JTextField tfAvailableBalance = new JTextField(30);
  private JTextField tfTotalBalance = new JTextField(30);

  private JButton btnSave = new JButton("Speichern");

  private JPanel rightPanel = new JPanel();

  private JList<String> list;
  private ArrayList<Account> accounts;

  public AdminView(ATM atm, String title) {
    super(title);
    this.atm = atm;
    this.accounts = atm.getBankDatabase().getAccounts();

    // Initialisierung des Top-Textfeldes und des Bottom-Textfeldes
    tfTop.setHorizontalAlignment(JTextField.CENTER);
    tfTop.setFont(tfFont);
    tfTop.setEditable(false);
    tfTop.setText("Willkommen, " + atm.getCurrentAccount().getUsername() + "!");

    // Initialisierung der Liste mit Hilfe eines DefaultListModels
    DefaultListModel<String> model = new DefaultListModel<>();
    for (int i = 0; i < accounts.size(); i++) {
      model.add(i, accounts.get(i).getUsername());
    }
    list = new JList<String>(model);
    list.addListSelectionListener(e -> updateRightPanelWithAccount(list.getSelectedIndex()));
    list.setSelectedIndex(0);

    // Initialisierung des rechten Panels
    rightPanel.setPreferredSize(new Dimension(width - 100, height));
    rightPanel.setLayout(new SpringLayout());
    lAccountNumber.setLabelFor(tfAccountNumber);
    lUsername.setLabelFor(tfUsername);
    lAvailableBalance.setLabelFor(tfAvailableBalance);
    lTotalBalance.setLabelFor(tfTotalBalance);
    rightPanel.add(lAccountNumber);
    rightPanel.add(tfAccountNumber);
    rightPanel.add(lUsername);
    rightPanel.add(tfUsername);
    rightPanel.add(lAvailableBalance);
    rightPanel.add(tfAvailableBalance);
    rightPanel.add(lTotalBalance);
    rightPanel.add(tfTotalBalance);

    btnSave.setFocusable(false);
    btnSave.setHorizontalAlignment(JTextField.CENTER);
    btnSave.addActionListener(e -> btnSave());

    SpringUtilities.makeCompactGrid(rightPanel, 4, 2, 6, 6, 6, 6);

    // Initialisierung der Textfelder des rechten Menus
    updateRightPanelWithAccount(list.getSelectedIndex());

    add(tfTop, BorderLayout.NORTH);
    add(list, BorderLayout.WEST);
    add(rightPanel, BorderLayout.EAST);
    add(btnSave, BorderLayout.SOUTH);

    handleWindowEvents();

    setLocation(atm.getScreen().getLocation());
    setPreferredSize(new Dimension(width, height));
    pack();
    setVisible(true);
  }

  private void btnSave() {
    Account selected = accounts.get(list.getSelectedIndex());
    selected.setUsername(tfUsername.getText());
    selected.setAvailableBalance(Double.parseDouble(tfAvailableBalance.getText()));
    selected.setTotalBalance(Double.parseDouble(tfTotalBalance.getText()));

    accounts.set(list.getSelectedIndex(), selected);
    try {
      atm.getBankDatabase().saveAccountsToFile(accounts);
      System.out.println("Accounts saved!");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void updateRightPanelWithAccount(int i) {
    Account a = accounts.get(i);
    tfAccountNumber.setText(a.getAccountNumber());
    tfUsername.setText(a.getUsername());
    tfAvailableBalance.setText(String.valueOf(a.getAvailableBalance()));
    tfTotalBalance.setText(String.valueOf(a.getTotalBalance()));
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
