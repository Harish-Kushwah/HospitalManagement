/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagement;

/**
 *
 * @author haris
 */
import myutil.GradientPanel;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.tree.TreeCellEditor;

public class MultipleComponentCellTest {
    
  Color TABLE_HEADER_BACKGROUND_COLOR = new Color(13421772);
  Color TABLE_HEADER_FORGROUND_COLOR = new Color(0x000000);
  private final String[] columnNames = {"Medicine Doses"};

  private  Object[][] data;
  MultipleComponentCellTest(Object[][] data)
  {
   this.data = data;   
  }
  private final DefaultTableModel model = new DefaultTableModel(data, columnNames) {
    @Override public Class<?> getColumnClass(int column) {
      return BandType.class;
    }
  };
  private final JTable table = new JTable(model);
  
  public JComponent getTable() {
    table.setRowHeight(35);

    table.setDefaultRenderer(BandType.class, new BandTypeRenderer());
    
     BandTypeEditor edit  = new BandTypeEditor();
    table.setDefaultEditor(BandType.class, edit);
    table.getTableHeader().setPreferredSize(new Dimension(570,35));
    table.getTableHeader().setBackground(TABLE_HEADER_BACKGROUND_COLOR);
    table.getTableHeader().setForeground(TABLE_HEADER_FORGROUND_COLOR);
    table.getTableHeader().setBorder(new LineBorder(Color.black,1,false));
      // ((TreeCellEditor)table.getDefaultEditor(Object.class)).setClickCountToStart(1);

//   table.addPropertyChangeListener("tableCellEditor", new PropertyChangeListener(){
//        @Override
//        public void propertyChange(PropertyChangeEvent evt) {
//            if(evt.getNewValue()==null){
//                System.out.println("null");
//            }
//            else{
//                System.out.println("Not null");
//                System.out.println(((BandTypeEditor)evt.getNewValue()));
//            }
//                  
//        }
//   
//   });
    table.addMouseListener(new MouseListener(){
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
//            table.setDefaultEditor(BandType.class, new BandTypeEditor());
         //  BandType bt  = (BandType) edit.getCellEditorValue();
//            TableModel tableModel=table.getModel();
//            System.out.println("Table Mouse  exited");
//            int total_row = tableModel.getRowCount();
//        for(int i=0;i<total_row;i++)
//        {
//          BandType bt = (BandType  )tableModel.getValueAt(i, 0);
//          System.out.println(bt.medicine_name);
//          System.out.println(bt.morning_status);
//          System.out.println(bt.afternoon_status);
//          System.out.println(bt.evening_status);
//          System.out.println(bt.before);
//          System.out.println(bt.after);
//          System.out.println(bt.tab);
//          
//          if (bt instanceof BandType) {
//            updateValue(bt);
//            }
//    }     
        }
        
        
    });
    
    return new JScrollPane(table);
  }
  public DefaultTableModel getTableModel()
  {
      return model;
  }
//  public static void main(String... args) {
//    EventQueue.invokeLater(new Runnable() {
//      @Override public void run() {
//        createAndShowGUI();
//      }
//    });
//  }
  
}

class BandType {
    public final String medicine_name;
    public  boolean morning_status;
    public  boolean afternoon_status;
    public  boolean evening_status;
    public boolean before,after;
    String text ;
    int tab,selected_index;
  
  public BandType(String medicine_name, boolean morning_status,boolean afternoon_status,boolean evening_status,boolean before,boolean after, int tab , int selected_index) {
    this.medicine_name = medicine_name;
    this.morning_status = morning_status;
    this.afternoon_status = afternoon_status;
    this.evening_status = evening_status;
    this.before = before;
    this.after = after;
    this.tab = tab;
//    this.text = check;
    this.selected_index = selected_index;
  }
  public BandType(String medicine_name)
  {
      this.medicine_name = medicine_name;
  }
}

class BandTypePanel extends JPanel {
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

  public BandTypePanel() {
    super(new BorderLayout(5, 10));
          total_tablet.setText("4");
          this.setBackground(Color.red);
   // setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    btg.add(before);
    btg.add(after);
    comboBox.addItemListener(new ItemListener() {
      @Override public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
//          boolean f = "manual".equals(e.getItem());
//          slider.setEnabled(f);
//          textField.setEnabled(f);
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
    before.addMouseListener(new MouseListener(){
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
             if(before.isSelected()){
               System.out.println("before Selected");
              before.setSelected(true);
           }
           else{
                  before.setSelected(false);
               System.out.println("before Not Slected");
           }
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    
    });
    after.addMouseListener(new MouseListener(){
        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
             if(after.isSelected()){
               System.out.println("after Selected");
              after.setSelected(true);
           }
           else{
                  after.setSelected(false);
               System.out.println("before Not Slected");
           }
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    
    });

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
  public void updateValue(BandType bt) {
        medicine_name.setText(bt.medicine_name);
        morning_chk.setSelected(bt.morning_status);
        afternoon_chk.setSelected(bt.afternoon_status);
        evening_chk.setSelected(bt.evening_status);
        before.setSelected(bt.before);
        after.setSelected(bt.after);
        total_tablet.setText(String.valueOf(bt.tab));
      //  comboBox.setSelectedItem(bt.text);
       comboBox.setSelectedIndex(bt.selected_index);

//      if(comboBox.getSelectedItem()!=null){
//          if(comboBox.getSelectedItem().toString().equalsIgnoreCase("1 TAB")){
//           total_tablet.setText("1");
//          }
//          else if(comboBox.getSelectedItem().toString().equalsIgnoreCase("2 TAB"))
//          {
//               total_tablet.setText("2");
//          }
//      }
  }
}

class BandTypeRenderer extends BandTypePanel implements TableCellRenderer {
    
     private int selectedRow = -1;
  public BandTypeRenderer() {
    super();
    setName("Table.cellRenderer");
   
  }
  
    
  @Override public Component getTableCellRendererComponent(
      JTable table, Object value, boolean isSelected, boolean hasFocus,
      int row, int column) {
    setBackground(isSelected ? new Color(0xE11B1B) : new Color(0xC5C5EF));
    
  //  value  = getCellRendeerValue();
 
    if (value instanceof BandType) {
      updateValue((BandType) value);
    }
  
   // updateValue((BandType) value);

    
    System.out.println("Value  update during rendering");

    return this;
  }
  
  
}

class BandTypeEditor extends BandTypePanel implements TableCellEditor {
  protected transient ChangeEvent changeEvent;
  
  public BandTypeEditor()
  {
      System.out.println("call");
      
  }
  @Override public Component getTableCellEditorComponent(
    JTable table, Object value, boolean isSelected, int row, int column) {
      if(isSelected){
          p.setEndColor(new Color(102, 255, 153));
          this.setBackground(new Color(0x000066));
          
         
      }
      else{
           p.setEndColor(new Color(0xC5C5EF));
          this.setBackground(new Color(16724787));
      }
       if (value instanceof BandType) {
            updateValue((BandType) value);
            }
 
    
   
     
        System.out.println("Value not update during editing");
    return this;
  }
  @Override
  public Object getCellEditorValue() {
      //System.out.println("This is after editiong" + comboBox.getSelectedItem().toString());
      return new BandType(medicine_name.getText() ,
                            morning_chk.isSelected(),
                            afternoon_chk.isSelected(),
                            evening_chk.isSelected(),
                            before.isSelected(),
                            after.isSelected(),
                            Integer.parseInt(total_tablet.getText()),
                             comboBox.getSelectedIndex());
    
//     return "this is from get cell editor hello";
  }
  //Copied from AbstractCellEditor
  //protected EventListenerList listenerList = new EventListenerList();
  @Override public boolean isCellEditable(EventObject e) {
//      if(e instanceof MouseEvent){
//          System.out.println("Mouse click");
//          return ((MouseEvent)e).getClickCount()>0;
//      }
    return true;
  }
  @Override public boolean shouldSelectCell(EventObject anEvent) {
    return true;
  }
  @Override public boolean stopCellEditing() {
    fireEditingStopped();
    return true;
  }
  @Override public void cancelCellEditing() {
    fireEditingCanceled();
  }
  @Override public void addCellEditorListener(CellEditorListener l) {
     
    listenerList.add(CellEditorListener.class, l);
  }
  @Override public void removeCellEditorListener(CellEditorListener l) {
    listenerList.remove(CellEditorListener.class, l);
  }
  public CellEditorListener[] getCellEditorListeners() {
    return listenerList.getListeners(CellEditorListener.class);
  }
 
  protected void fireEditingStopped() {
    // Guaranteed to return a non-null array
    Object[] listeners = listenerList.getListenerList();
    // Process the listeners last to first, notifying
    // those that are interested in this event
    for (int i = listeners.length - 2; i >= 0; i -= 2) {
      if (listeners[i] == CellEditorListener.class) {
        // Lazily create the event:
        if (Objects.isNull(changeEvent)) {
          changeEvent = new ChangeEvent(this);
        }
        ((CellEditorListener) listeners[i + 1]).editingStopped(changeEvent);
      }
    }
  }
  protected void fireEditingCanceled() {
    // Guaranteed to return a non-null array
    Object[] listeners = listenerList.getListenerList();
    // Process the listeners last to first, notifying
    // those that are interested in this event
    for (int i = listeners.length - 2; i >= 0; i -= 2) {
      if (listeners[i] == CellEditorListener.class) {
        // Lazily create the event:
        if (Objects.isNull(changeEvent)) {
          changeEvent = new ChangeEvent(this);
        }
        ((CellEditorListener) listeners[i + 1]).editingCanceled(changeEvent);
      }
    }
  }
}