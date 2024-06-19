package util.swing;

/**
 *
 * @author haris
 */
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
public class MyJLabel extends JLabel{
    
    public MyJLabel()
    {
          JLabel title = new JLabel("Add New Application ", JLabel.CENTER);
//        title.setForeground(Color.red);
        Font f = new Font("Sans-Serif", Font.BOLD, 18);
        title.setFont(f);
        title.setPreferredSize(new Dimension(1, 27));
    }
}
