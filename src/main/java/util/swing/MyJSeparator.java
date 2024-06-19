
package util.swing;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javaswingdev.system.SystemColor;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 *
 * @author haris
 */
public class MyJSeparator extends JSeparator{
 
    public MyJSeparator(JPanel panel)
    {
       panel.add(createJSeparator(), "wrap,pushx,growx");
    }
    
    public MyJSeparator(JPanel panel,String label)
    {
        Font f = new Font("Sans-Serif", Font.TRUETYPE_FONT, 15);
        Font f1 = new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 15);

        JLabel subTitle = new JLabel(label);
        subTitle.setFont(f1);
        subTitle.setForeground(SystemColor.MAIN_COLOR_2);

        panel.add(subTitle, "al left");
        panel.add(createJSeparator(), "wrap,pushx,growx");
    }
    
    private JSeparator createJSeparator() {
        JSeparator sp = new JSeparator();
        sp.setBackground(Color.gray);
        sp.setPreferredSize(new Dimension(1, 5));
        return sp;
    }
    
}
