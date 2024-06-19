
package util.swing;

/**
 *
 * @author haris
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
public class MyJTextField extends JTextField {
    public MyJTextField(String placeholder)
    {
      
        super(placeholder);
        setPreferredSize(new Dimension(1, 27));

        setForeground(Color.GRAY);

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(placeholder)) {
                    setText("");
                    setForeground(Color.black);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setForeground(Color.GRAY);
                    setText(placeholder);
                }
            }
        });
    }
    public void setMyText(String text)
    {
        setText(text);
        setForeground(Color.black);
    }
    public void setPlaceholder(String text)
    {
        setText(text);
        setForeground(Color.GRAY);
    }
}
