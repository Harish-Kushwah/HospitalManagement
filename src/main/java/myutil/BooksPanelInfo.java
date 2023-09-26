package myutil;

import javax.swing.*;
import java.awt.*;

public class BooksPanelInfo
{
    String title_name;
    String total;

    ImageIcon icon;
    Color color;

   public  void setInfo(String title_name,String total,ImageIcon icon)
    {
        this.title_name=title_name;
        this.total = total;
        this.icon = icon;

    }
    public  void setInfo(String title_name,String total,ImageIcon icon,Color color)
    {
        this.title_name=title_name;
        this.total = total;
        this.icon = icon;
        this.color  =color;
    }
    public  void setColor(Color color)
    {
        this.color = color;
    }
    public  String getTitle()
    {
        return this.title_name;
    }
    public  String getTotal()
    {
        return  this.total;
    }
    public  ImageIcon getIcon()
    {
        return  icon;
    }
    public  Color getColor()
    {
        return  this.color;
    }
}