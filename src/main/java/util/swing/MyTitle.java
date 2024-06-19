/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.swing;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author haris
 */
public class MyTitle extends JLabel {

    public MyTitle(String title) {
        super(title, JLabel.CENTER);
        Font f = new Font("Sans-Serif", Font.BOLD, 18);
        setFont(f);
        super.setPreferredSize(new Dimension(1, 27));
    }
}
