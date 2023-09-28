/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myutil;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 *
 * @author haris
 */


public class MedicineRowPanel extends JPanel implements MouseListener {
  private static String DEFAULT = "0";
  
  public final JLabel medicine_name = new JLabel("none");
  public final JCheckBox morning_chk = new  JCheckBox("M");
  public final JCheckBox afternoon_chk = new  JCheckBox("A");
  public final JCheckBox evening_chk = new  JCheckBox("E");
  
  public final ButtonGroup btg = new ButtonGroup();
  public final JRadioButton before = new JRadioButton("Before");
  public final JRadioButton after = new JRadioButton("after");
 // public final JButton save = new JButton("Save");
  
  
  public final JTextField total_tablet = new JTextField(5);
  public final JComboBox<String> comboBox = new JComboBox<>(
     new String[] {"1 TAB", "2 TAB"}
  );
  public GradientPanel name,p;

  
  public void setDefaultValues()
  {
      morning_chk.setSelected(true);
      evening_chk.setSelected(true);
      before.setSelected(true);
  }
  public MedicineRowPanel() {
    super(new BorderLayout(5, 10));
    
    total_tablet.setText("4");
    this.setBackground(new Color(0xC5C5EF));
    this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    btg.add(before);
    btg.add(after);
    setDefaultValues();

    comboBox.addItemListener(new ItemListener() {
      @Override public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {

            if("1 TAB".equals(e.getItem()))
            {
                  total_tablet.setText("1");
            }
            else if("2 TAB".equals(e.getItem()))
            {
                total_tablet.setText("2");
            }
        }
      }
    });

    name = new GradientPanel(new Color(0xC5C5EF),new Color(0xFFFFFF),170,35);
//    name.setBackground(new Color(0x9C9CE7));
    p = new GradientPanel(new Color(0xFFFFFF),new Color(0xC5C5EF),400,35);
    //JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER));
    p.setLayout(new FlowLayout(FlowLayout.CENTER));
//    p.setOpaque(true);

    morning_chk.setBackground(new Color(0xFFFFFF));
    morning_chk.addMouseListener(this);
    afternoon_chk.setBackground(new Color(0xFFFFFF));
    afternoon_chk.addMouseListener(this);
    evening_chk.setBackground(new Color(0xFFFFFF));
    evening_chk.addMouseListener(this);
    before.setBackground(new Color(0xE7EAF3));
    before.addMouseListener(this);
    after.setBackground(new Color(0xE7EAF3));
    after.addMouseListener(this);
    comboBox.setBackground(new Color(0xE7EAF3));
    comboBox.addMouseListener(this);
    
   
    name.add(medicine_name);
    p.add(morning_chk);
    p.add(afternoon_chk);
    p.add(evening_chk);
    p.add(before);
    p.add(after);
    p.add(total_tablet);
    p.add(comboBox);
    //p.add(save);
    add(name,BorderLayout.WEST);
    add(p, BorderLayout.CENTER);
 
  }
  public M_BandType getDetials()
  {
       System.out.println("Medicine name :" + this.medicine_name.getText());
       System.out.println("Morning Status  :" + this.morning_chk.isSelected());
       
       return new M_BandType(medicine_name.getText() ,
                            morning_chk.isSelected(),
                            afternoon_chk.isSelected(),
                            evening_chk.isSelected(),
                            before.isSelected(),
                            after.isSelected(),
                            Integer.parseInt(total_tablet.getText()),
                             comboBox.getSelectedIndex());
  }
  public void setMedicineName(String name)
  {
     medicine_name.setText(name);
  }
  public void chnageBackgroundColor(Color c1)
  {
      p.setEndColor(c1);
  }
//   public void updateValue(BandType bt) {
//        medicine_name.setText(bt.medicine_name);
//        morning_chk.setSelected(bt.morning_status);
//        afternoon_chk.setSelected(bt.afternoon_status);
//        evening_chk.setSelected(bt.evening_status);
//        before.setSelected(bt.before);
//        after.setSelected(bt.after);
//        total_tablet.setText(String.valueOf(bt.tab));
//      //  comboBox.setSelectedItem(bt.text);
//       comboBox.setSelectedIndex(bt.selected_index);
//  }

    @Override
            public void mouseClicked(MouseEvent e) {
                //((MedicineRowPanel)e.getSource()).setBackground(new Color(204, 204, 255));
                p.setEndColor(new Color(204, 204, 255));
                this.setBackground(new Color(255, 51, 51));
                System.out.println("clicked");
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                 // ((MedicineRowPanel)e.getSource()).setBackground(new Color(255, 0, 255));
                  p.setEndColor(new Color(204, 204, 255));
                  this.setBackground(new Color(255, 51, 51));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                 //((MedicineRowPanel)e.getSource()).setBackground(new Color(255, 51, 51));
                 //chnageBackgroundColor(new Color(0xC5C5EF));
                  p.setEndColor(new Color(0xC5C5EF));
                   this.setBackground(new Color(0xC5C5EF));
                     System.out.println("exit");
            }
}
