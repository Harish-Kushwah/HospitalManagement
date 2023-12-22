package myutil;


import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

class CustomRenderer extends DefaultTableCellRenderer
{
    Color text_color=new Color(0x535354);
    Font text_font=new Font("Mangal",Font.ROMAN_BASELINE,15);

    Color first_row_color = new Color(0xE0E0F5);
    Color second_row_color = Color.white;
 
    public  CustomRenderer(){}
    public  CustomRenderer(Color text_color , Font text_font)
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
        setHorizontalAlignment(JLabel.CENTER);
        c.setBackground(row % 2 == 0 ? this.first_row_color : this.second_row_color);
//        setBorder(new LineBorder(new Color(0x5A81EF),1,true));
        return c;
    }
}
public class LibraryTable extends JTable{

    DefaultTableModel defaultTableModel ;

    Color TABLE_BACKGROUND_COLOR = new Color(0xE9E9EE);
    Color TABLE_HEADER_BACKGROUND_COLOR = new Color(0x6b676b);
    Color TABLE_HEADER_FORGROUND_COLOR = new Color(0xFDFDFD);

    int row_height = 35;

    CustomRenderer customRenderer = new CustomRenderer();
    public  LibraryTable(String data[][], String column[])
    {
        defaultTableModel = new DefaultTableModel(data,column){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        this.setModel(defaultTableModel);

        this.setRowHeight(row_height);
        this.setBackground(TABLE_BACKGROUND_COLOR);
        this.setIntercellSpacing(new Dimension(0,5));
        this.setShowGrid(false);
        this.setRowMargin(5);
        this.getTableHeader().setPreferredSize(new Dimension(100,30));
        this.getTableHeader().setBackground(TABLE_HEADER_BACKGROUND_COLOR);
        this.getTableHeader().setForeground(TABLE_HEADER_FORGROUND_COLOR);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        int total_col = defaultTableModel.getColumnCount();
        for(int i=0;i<total_col;i++){
            TableColumn col =  this.getColumnModel().getColumn(i);
            col.setCellRenderer(customRenderer);
        }

    }

    public  void setRowColor(Color first_row_color , Color second_row_color)
    {
        customRenderer.setRowColor(first_row_color,second_row_color);
    }
    public  void setTextColor(Color textColor)
    {
        customRenderer.setTextColor(textColor);
    }
    public  void setTextFont(Color textFont)
    {
        customRenderer.setTextColor(textFont);
    }


}
