import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;

public class SidePanel extends JPanel {

  private String textareaString;
  private final Color backgroundColor = Color.lightGray;

  public SidePanel(int width, int height) {
    setPreferredSize(new Dimension(width, height));
    setBackground(backgroundColor);
    setLayout(new FlowLayout());
  }

  public void setTextarea(String text) {
    textareaString = text;
    revalidate();
  }

  public String getTextarea() {
    return textareaString;
  }

}
