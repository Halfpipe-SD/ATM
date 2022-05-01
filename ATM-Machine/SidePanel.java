
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;

public class SidePanel extends JPanel {

  private final Color backgroundColor = Color.lightGray;

  private JTextField tf = new JTextField(10);
  private JLabel tl = new JLabel();

  public SidePanel(int width, int height) {
    setPreferredSize(new Dimension(width, height));
    setBackground(backgroundColor);
    setLayout(new FlowLayout());
    tf.setEditable(false);
    add(tf);
    add(tl);
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

  public void setLabel(String text) {
    tl.setText(text);
  }

  public String getTextField() {
    return tf.getText();
  }

  public String getLabel() {
    return tl.getText();
  }

}
