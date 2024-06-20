package util.swing.panel;

import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel {

    private  int WIDE = 640;
    private  int HIGH = 240;
    private static final float HUE_MIN = 0;
    private Color color1 = Color.white;
    //    private Color color2 = new Color(0xBFBFF6);
    private Color color2 =Color.white;

    public  void setDirectionLeft()
    {
        Color t  = color1;
        color1 = color2;
        color2 = t;
    }
    public  GradientPanel() {}
    public  GradientPanel(Color color)
    {
        color2 = color;
    }
    public  GradientPanel(Color color1,Color color2)
    {
        this.color1 = color1;
        this.color2 = color2;
    }
    public  GradientPanel(Color color , int wide,int high)
    {
        color2 = color;
        this.WIDE = wide;
        this.HIGH = high;

    } public  GradientPanel(Color color1 , Color color2 , int wide,int high)
    {
        this.color1 = color1;
        this.color2 = color2;
        this.WIDE = wide;
        this.HIGH = high;

    }
    public  void setStartColor(Color color1)
    {
        this.color1=color1;
    }
    public  void setEndColor(Color color2)
    {
        this.color2=color2;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint p = new GradientPaint(0, 0, color1, getWidth(), 0, color2);
        g2d.setPaint(p);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        
      
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDE, HIGH);
    }
}