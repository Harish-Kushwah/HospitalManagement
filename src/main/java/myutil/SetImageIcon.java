package myutil;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class SetImageIcon extends JPanel {

    private ImageIcon image;
    private  int width,height;


    public SetImageIcon(ImageIcon icon , int width, int height) {
        image = icon;
        this.width = width;
        this.height = height;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension( this.width,this.height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        int imageWidth = image.getIconWidth();
        int imageHeight = image.getIconHeight();

        if (imageWidth == 0 || imageHeight == 0) {
            return;
        }
        double widthScale = (double)getWidth() / (double)imageWidth;
        double heightScale = (double)getHeight() / (double)imageHeight;
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(image.getImage(), AffineTransform.getScaleInstance(widthScale, heightScale), this);
        g2d.dispose();
    }

}