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
public class MedicineRowPanel extends JPanel implements MouseListener, ItemListener {

    private static String DEFAULT = "0";

    public final String []medicine_quantity = {"1 TAB", "1 CAP", "1/2 TAB", "10 ML", "5 ML","_____"};
    public final JLabel medicine_name = new JLabel("none");
    public final JCheckBox morning_chk = new JCheckBox("M");
    public final JCheckBox afternoon_chk = new JCheckBox("A");
    public final JCheckBox evening_chk = new JCheckBox("E");
    public final JCheckBox delete_chk = new JCheckBox();

    public final ButtonGroup btg = new ButtonGroup();
    public final JRadioButton before = new JRadioButton("Before");
    public final JRadioButton after = new JRadioButton("after");

    // public final JButton save = new JButton("Save");
    public final JTextField total_tablet = new JTextField(5);
    public final JComboBox<String> comboBox = new JComboBox<>(
          medicine_quantity
    );
    public GradientPanel name, p;

    public void setDefaultValues() {
        morning_chk.setSelected(true);
        evening_chk.setSelected(true);
        before.setSelected(false);
        after.setSelected(true);
    }

    public void initComponents()
    {
        name = new GradientPanel(new Color(0xC5C5EF), new Color(0xFFFFFF), 170, 35);
//    name.setBackground(new Color(0x9C9CE7));
        p = new GradientPanel(new Color(0xFFFFFF), new Color(0xC5C5EF), 400, 35);
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
        delete_chk.setBackground(new Color(0xccccff));
        delete_chk.addItemListener(this);
        
        total_tablet.addMouseListener(this);

        name.add(medicine_name);
        p.add(morning_chk);
        p.add(afternoon_chk);
        p.add(evening_chk);
        p.add(before);
        p.add(after);
        p.add(total_tablet);
        p.add(comboBox);
        p.add(delete_chk);
        //p.add(save);
        add(name, BorderLayout.WEST);
        add(p, BorderLayout.CENTER);
    }
    public MedicineRowPanel(String m_name) {

        super(new BorderLayout(5, 10));
        initComponents();
        
        medicine_name.setText(m_name);
        total_tablet.setText("4");
        this.setBackground(new Color(0xC5C5EF));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btg.add(before);
        btg.add(after);
        setDefaultValues();

//    comboBox.addItemListener(new ItemListener() {
//      @Override public void itemStateChanged(ItemEvent e) {
//        if (e.getStateChange() == ItemEvent.SELECTED) {
//
//            if("1 TAB".equals(e.getItem()))
//            {
//                  total_tablet.setText("1");
//            }
//            else if("2 TAB".equals(e.getItem()))
//            {
//                total_tablet.setText("2");
//            }
//        }
//      }
//    });
        

    }
    public MedicineRowPanel(MedicineDetails medicineDetails) {

        super(new BorderLayout(5, 10));
        initComponents();
        
        setDefaultValues();
       
        medicine_name.setText(medicineDetails.getMedicineName());
        total_tablet.setText(medicineDetails.getTotalQuantity());
       
        morning_chk.setSelected(medicineDetails.morning);
        afternoon_chk.setSelected(medicineDetails.afternoon);
        evening_chk.setSelected(medicineDetails.evening);
        
        String meal_time = medicineDetails.getMedicineMealTime();
        if(meal_time.equalsIgnoreCase("1"))
        {
            before.setSelected(true);
            after.setSelected(false);
        }
        else{
            before.setSelected(false);
            after.setSelected(true);
        }
        
        this.setBackground(new Color(0xC5C5EF));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btg.add(before);
        btg.add(after);
        
        
        for(int i=0;i<medicine_quantity.length;i++)
        {
            if(medicine_quantity[i].equalsIgnoreCase(medicineDetails.getMedicineQuantity()))
            {
                comboBox.setSelectedIndex(i);
                break;
            }
        }
        
    }

    public M_BandType getDetials() {
        System.out.println("Medicine name :" + this.medicine_name.getText());
        System.out.println("Morning Status  :" + this.morning_chk.isSelected());

        return new M_BandType(medicine_name.getText(),
                morning_chk.isSelected(),
                afternoon_chk.isSelected(),
                evening_chk.isSelected(),
                before.isSelected(),
                after.isSelected(),
               total_tablet.getText(),
                (String) comboBox.getSelectedItem(), delete_chk.isSelected());
    }

    public void setMedicineName(String name) {
        medicine_name.setText(name);
    }

    public void chnageBackgroundColor(Color c1) {
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
         if (!delete_chk.isSelected()) {
         p.setEndColor(new Color(0xC5C5EF));
          this.setBackground(new Color(0xC5C5EF));
          System.out.println("exit");
         }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (delete_chk.isSelected()) {
            p.setEndColor(new Color(0xff6666));
            this.setBackground(new Color(0xff3333));
        } else {
            p.setEndColor(new Color(0xC5C5EF));
            this.setBackground(new Color(0xC5C5EF));
        }
    }
}
