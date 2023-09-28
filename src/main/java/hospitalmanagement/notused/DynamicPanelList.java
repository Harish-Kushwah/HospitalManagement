/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagement.notused;


import java.awt.*;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import myutil.GradientPanel;

/**
 *
 * @author haris
 */


class BandTypePanel2 extends JPanel {
  private static String DEFAULT = "0";
  
  public final JLabel medicine_name = new JLabel("hello");
  public final JCheckBox morning_chk = new  JCheckBox("M");
  public final JCheckBox afternoon_chk = new  JCheckBox("A");
  public final JCheckBox evening_chk = new  JCheckBox("E");
  
  public final ButtonGroup btg = new ButtonGroup();
  public final JRadioButton before = new JRadioButton("Before");
  public final JRadioButton after = new JRadioButton("after");
  public final JButton save = new JButton("Save");
  
  
  public final JTextField total_tablet = new JTextField(5);
    
  public final JComboBox<String> comboBox = new JComboBox<>(
     new String[] {"1 TAB", "2 TAB"}
  );
  public GradientPanel name,p;

  public BandTypePanel2() {
    super(new BorderLayout(5, 10));
          total_tablet.setText("4");
          this.setBackground(Color.red);
    btg.add(before);
    btg.add(after);
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
    afternoon_chk.setBackground(new Color(0xFFFFFF));
    evening_chk.setBackground(new Color(0xFFFFFF));
    before.setBackground(new Color(0xE7EAF3));
    after.setBackground(new Color(0xE7EAF3));
    comboBox.setBackground(new Color(0xE7EAF3));
   
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
  public void setMedicineName(String name)
  {
     medicine_name.setText(name);
  }
//  public void updateValue(BandType bt) {
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
}
public class DynamicPanelList {

    public static void main(String[] args) {
        new DynamicPanelList();
    }

    public DynamicPanelList() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception ex) {
                }

                JFrame frame = new JFrame("Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(new TestPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

class TestPane extends JPanel implements ActionListener {

        private JPanel mainList;
        static int i=0;
        BandTypePanel2 bt[] = new BandTypePanel2[20];
        public TestPane() {
            setLayout(new BorderLayout());

            mainList = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = -1;
            gbc.weighty = 1;
            mainList.add(new JPanel(), gbc);

            add(new JScrollPane(mainList));

            JButton add = new JButton("Add");
            
            add.addActionListener(this);

            add(add, BorderLayout.SOUTH);

        }
        
        

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(500, 500);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
           
                    GridBagConstraints gbc = new GridBagConstraints();
                    gbc.gridwidth = GridBagConstraints.REMAINDER;
                    gbc.weightx = 1;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    
                    bt[i] = new BandTypePanel2();
                    mainList.add(bt[i], gbc, 0);
                    i++;
                    validate();
                    repaint();
        }
    }
}