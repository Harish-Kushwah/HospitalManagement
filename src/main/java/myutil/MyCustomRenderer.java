package myutil;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

public class MyCustomRenderer extends DefaultTableCellRenderer
{
    Color text_color=new Color(0x535354);
    Font text_font=new Font("Mangal",Font.CENTER_BASELINE,13);

    Color first_row_color = new Color(0xf2f2f7);
    Color second_row_color = Color.white;
 
    public  MyCustomRenderer(){}
    public  MyCustomRenderer(Color text_color , Font text_font)
    {
        this.text_color = text_color;
        this.text_font  = text_font;

    }
    public  void setRowColor(Color first_row_color,Color second_row_color)
    {
        this.first_row_color = first_row_color;
        this.second_row_color= second_row_color;
    }
    public  void setTextColor(Color text_color)
    {
        this.text_color = text_color;
    }
    public  void setTextFont(Font text_font)
    {
        this.text_font = text_font;
    }


    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setForeground(this.text_color);
        setFont(this.text_font);
      
//        setHorizontalAlignment(JLabel.WEST);
        c.setBackground(row % 2 == 0 ? this.first_row_color : this.second_row_color);
//        setBorder(new LineBorder(new Color(0x5A81EF),1,true));
        return c;
    }
}