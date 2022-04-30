
import java.util.Scanner;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.security.Key;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class Keypad extends JPanel {

   // private HashMap<String, JButton> buttons = new HashMap<String, JButton>();

   private final String[] buttonValues = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "Clear", "Enter" };
   private final Color backgroundColor = Color.gray;
   private String userinput = "";

   public Keypad(int width, int height) {

      setPreferredSize(new Dimension(width, height));
      setBackground(backgroundColor);
      setLayout(new FlowLayout());

      // create buttons for each label
      for (String value : buttonValues) {
         JButton button = new JButton(value);

         // add event listeners to buttons
         button.addActionListener(e -> buttonPressed(value));

         // // put buttons in hashmap and add to panel
         // buttons.put(value, button);
         add(button);
      }
   }

   private void buttonPressed(String value) {
      if (value.equals("Clear")) {
         userinput = "";
         System.out.println("clear pressed");

      } else if (value.equals("Enter")) {

         System.out.println("enter pressed");
      } else {
         System.out.println(value + " pressed");
         userinput += value;
      }

   }

   // public int getInput() throws InputMismatchException, NoSuchElementException,
   // IllegalStateException {
   // Scanner s = new Scanner(System.in);
   // int input = s.nextInt();
   // s.close();
   // return input;
   // }
}
