package util.swing;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author haris
 */
public class MyJScrollPane extends JScrollPane {
    public MyJScrollPane(JPanel panel)
    {
        super(panel);
        setBorder(null);
        setVerticalScrollBar(new javaswingdev.swing.scroll.ScrollBar());
        getVerticalScrollBar().setBackground(Color.WHITE);
        getViewport().setBackground(Color.WHITE);
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
    }
}
