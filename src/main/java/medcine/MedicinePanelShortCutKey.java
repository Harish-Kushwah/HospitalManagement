package medcine;

import hospitalmanagement.Home;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JTextField;

/**
 *
 * @author haris
 */
public class MedicinePanelShortCutKey {
    
    static ArrayList<MedicineRowPanel> medicine_arraylist = new ArrayList<>();
    private static Color BG_HOVER_COLOR = new Color(0xff6666);
    private static Color BG__COLOR = Color.black;
    
    static Home home;
    
    public static void setMedicineList(ArrayList<MedicineRowPanel> medicine_arraylist1) {
        medicine_arraylist = medicine_arraylist1;
    }
    
    public static void setHome(Home h) {
        home = h;
    }

    //===========================================================================
    /**
     * Total Tablet
     *
     * @param p
     */
    private static void transferTotalTabletFocusToNextComponent(MedicineRowPanel p) {
        // Get the currently focused text field
        JTextField currentTextField = p.total_tablet;
        
        p.erasePreviousLineColor();
        // Find the index of the current panel in the list
        int currentIndex = medicine_arraylist.indexOf(p);

        // Calculate the index of the next panel
        int nextIndex = (currentIndex + 1) % medicine_arraylist.size();

        // Get the next panel
        MedicineRowPanel nextPanel = medicine_arraylist.get(nextIndex);

        // Transfer focus to the name text field of the next panel
        nextPanel.total_tablet.requestFocus();
        
        nextPanel.setNewLineColor();
    } 
    private static void transferTotalTabletFocusToCurrent(MedicineRowPanel p) {
        p.total_tablet.requestFocus();
    }
    private static void transferTotalTabletFocusToRightComponent(MedicineRowPanel p) {
        p.comboBox.requestFocus();
    }    
    private static void transferTotalTabletFocusToLeftComponent(MedicineRowPanel p) {
       // p.after.requestFocus();
    }  
    private static void transferTotalTabletFocusToPreviousComponent(MedicineRowPanel p) {
        // Get the currently focused text field
        JTextField currentTextField = p.total_tablet;
        p.erasePreviousLineColor();

        // Find the index of the current panel in the list
        int currentIndex = medicine_arraylist.indexOf(p);
        if (currentIndex == 0) {
            currentIndex = medicine_arraylist.size();
        }
        int previousIndex = (currentIndex - 1) % medicine_arraylist.size();

        // Get the next panel
        MedicineRowPanel nextPanel = medicine_arraylist.get(previousIndex);

        // Transfer focus to the name text field of the next panel
        nextPanel.total_tablet.requestFocus();
        nextPanel.setNewLineColor();
    }
   
    //===========================================================================
    /**
     * After Tablet
     *
     * @param p
     */
    private static void transferAfterBtnFocusToNextComponent(MedicineRowPanel p) {
        // Get the currently focused text field
        JTextField currentTextField = p.total_tablet;
        
        p.erasePreviousLineColor();
        p.after.setForeground(BG__COLOR);
        // Find the index of the current panel in the list
        int currentIndex = medicine_arraylist.indexOf(p);

        // Calculate the index of the next panel
        int nextIndex = (currentIndex + 1) % medicine_arraylist.size();

        // Get the next panel
        MedicineRowPanel nextPanel = medicine_arraylist.get(nextIndex);

        // Transfer focus to the name text field of the next panel
        nextPanel.after.requestFocus();
       nextPanel.after.setForeground(BG_HOVER_COLOR);
        nextPanel.setNewLineColor();
    }    
    private static void transferAfterBtnFocusToRightComponent(MedicineRowPanel p) {
        p.total_tablet.requestFocus();
    }   
    private static void transferAfterBtnFocusToLeftComponent(MedicineRowPanel p) {
        p.before.requestFocus();
        p.before.setForeground(BG_HOVER_COLOR);
        p.after.setForeground(BG__COLOR);
    }   
    private static void transferAfterBtnFocusToPreviousComponent(MedicineRowPanel p) {
        // Get the currently focused text field
        JTextField currentTextField = p.total_tablet;
        p.erasePreviousLineColor();
        p.after.setForeground(BG__COLOR);

        // Find the index of the current panel in the list
        int currentIndex = medicine_arraylist.indexOf(p);
        if (currentIndex == 0) {
            currentIndex = medicine_arraylist.size();
        }
        int previousIndex = (currentIndex - 1) % medicine_arraylist.size();

        // Get the next panel
        MedicineRowPanel nextPanel = medicine_arraylist.get(previousIndex);

        // Transfer focus to the name text field of the next panel
        nextPanel.after.requestFocus();
        nextPanel.setNewLineColor();
        nextPanel.after.setForeground(BG_HOVER_COLOR);
    }    
    
    //===========================================================================
    /**
     * Before Tablet
     *
     * @param p
     */
    private static void transferBeforBtnFocusToNextComponent(MedicineRowPanel p) {
        // Get the currently focused text field
        JTextField currentTextField = p.total_tablet;
        
        p.erasePreviousLineColor();
        p.before.setForeground(BG__COLOR);
        // Find the index of the current panel in the list
        int currentIndex = medicine_arraylist.indexOf(p);

        // Calculate the index of the next panel
        int nextIndex = (currentIndex + 1) % medicine_arraylist.size();

        // Get the next panel
        MedicineRowPanel nextPanel = medicine_arraylist.get(nextIndex);

        // Transfer focus to the name text field of the next panel
        nextPanel.before.requestFocus();
        
        nextPanel.setNewLineColor();
        nextPanel.before.setForeground(BG_HOVER_COLOR);
    }
    private static void transferBeforBtnFocusToRightComponent(MedicineRowPanel p) {
        p.after.requestFocus();
        p.after.setSelected(true);
        p.after.setForeground(BG_HOVER_COLOR);
        p.before.setForeground(BG__COLOR);
    }   
    private static void transferBeforBtnFocusToLeftComponent(MedicineRowPanel p) {
        p.after.requestFocus();
        p.before.setForeground(BG_HOVER_COLOR);
        p.after.setForeground(BG__COLOR);
    }   
  
    //===========================================================================
    /**
     * Delete Checkbox
     *
     * @param p
     */
    private static void transferDeleteChkFocusToNextComponent(MedicineRowPanel p) {
        // Get the currently focused text field
        JTextField currentTextField = p.total_tablet;
        
        p.erasePreviousLineColor();
        p.delete_chk.setSelected(false);
        p.eraseColorForDeleteChkMode();
        // Find the index of the current panel in the list
        int currentIndex = medicine_arraylist.indexOf(p);
        
        int size = medicine_arraylist.size();
        
        if (size > 0) {
            // Calculate the index of the next panel
            int nextIndex = (currentIndex + 1) % medicine_arraylist.size();

            // Get the next panel
            MedicineRowPanel nextPanel = medicine_arraylist.get(nextIndex);

            // Transfer focus to the name text field of the next panel
            nextPanel.delete_chk.requestFocus();
            nextPanel.delete_chk.setSelected(true);
        }
    }
    private static void transferDeleteChkFocusToRightComponent(MedicineRowPanel p) {
        p.morning_chk.requestFocus();
        p.delete_chk.setSelected(false);
        p.eraseColorForDeleteChkMode();
        p.morning_chk.setForeground(BG_HOVER_COLOR);
        
    }
    private static void transferDeleteChkFocusToLeftComponent(MedicineRowPanel p) {
        p.comboBox.requestFocus();
        p.delete_chk.setSelected(false);
        p.eraseColorForDeleteChkMode();
    }
    private static void transferDeleteChkFocusToPreviousComponent(MedicineRowPanel p) {
        // Get the currently focused text field
        JTextField currentTextField = p.total_tablet;
        p.erasePreviousLineColor();
        
        p.delete_chk.setSelected(false);
        p.eraseColorForDeleteChkMode();

        // Find the index of the current panel in the list
        int currentIndex = medicine_arraylist.indexOf(p);
        if (currentIndex == 0) {
            currentIndex = medicine_arraylist.size();
        }
        int previousIndex = (currentIndex - 1) % medicine_arraylist.size();

        // Get the next panel
        MedicineRowPanel nextPanel = medicine_arraylist.get(previousIndex);

        // Transfer focus to the name text field of the next panel
        nextPanel.delete_chk.requestFocus();
        nextPanel.delete_chk.setSelected(true);
        
    }

    //===========================================================================
    /**
     * Morning Checkbox
     *
     * @param p
     */
    
    private static void selectMorningChk(MedicineRowPanel p) {
        if (p.morning_chk.isSelected()) {
            p.morning_chk.setSelected(false);
        } else {
            p.morning_chk.setSelected(true);
        }
    }
    private static void transferMorningChkFocusToNextComponent(MedicineRowPanel p) {
        // Get the currently focused text field
        JTextField currentTextField = p.total_tablet;
        
        p.erasePreviousLineColor();
        p.morning_chk.setForeground(BG__COLOR);
        // Find the index of the current panel in the list
        int currentIndex = medicine_arraylist.indexOf(p);
        
        int size = medicine_arraylist.size();
        
        if (size > 0) {
            // Calculate the index of the next panel
            int nextIndex = (currentIndex + 1) % medicine_arraylist.size();

            // Get the next panel
            MedicineRowPanel nextPanel = medicine_arraylist.get(nextIndex);

            // Transfer focus to the name text field of the next panel
            nextPanel.morning_chk.requestFocus();
             nextPanel.setNewLineColor();
             nextPanel.morning_chk.setForeground(BG_HOVER_COLOR);
        }
    }
    private static void transferMorningChkFocusToRightComponent(MedicineRowPanel p) {
        p.afternoon_chk.requestFocus();
        p.setNewLineColor();
        
        p.morning_chk.setForeground(BG__COLOR);
        p.afternoon_chk.setForeground(BG_HOVER_COLOR);
       
        
    }
    private static void transferMorningChkFocusToLeftComponent(MedicineRowPanel p) {
        
        p.delete_chk.requestFocus();
        p.delete_chk.setSelected(true);
        p.setColorForDeleteChkMode();
        
        p.morning_chk.setForeground(BG__COLOR);
    }
    private static void transferMorningChkFocusToPreviousComponent(MedicineRowPanel p) {
        // Get the currently focused text field
        JTextField currentTextField = p.total_tablet;
        p.erasePreviousLineColor();
        p.morning_chk.setForeground(BG__COLOR);
        // Find the index of the current panel in the list
        int currentIndex = medicine_arraylist.indexOf(p);
        if (currentIndex == 0) {
            currentIndex = medicine_arraylist.size();
        }
        int previousIndex = (currentIndex - 1) % medicine_arraylist.size();

        // Get the next panel
        MedicineRowPanel nextPanel = medicine_arraylist.get(previousIndex);

        // Transfer focus to the name text field of the next panel
        nextPanel.morning_chk.requestFocus();
        nextPanel.setNewLineColor();
        nextPanel.morning_chk.setForeground(BG_HOVER_COLOR);
        
    }
    //===========================================================================
    /**
     * Afternoon Checkbox
     *
     * @param p
     */
    
    private static void selectAfternoonChk(MedicineRowPanel p) {
        if (p.afternoon_chk.isSelected()) {
            p.afternoon_chk.setSelected(false);
        } else {
            p.afternoon_chk.setSelected(true);
        }
    }
    private static void transferAfternoonChkFocusToNextComponent(MedicineRowPanel p) {
        // Get the currently focused text field
        JTextField currentTextField = p.total_tablet;
        
        p.erasePreviousLineColor();
        p.afternoon_chk.setForeground(BG__COLOR);
        // Find the index of the current panel in the list
        int currentIndex = medicine_arraylist.indexOf(p);
        
        int size = medicine_arraylist.size();
        
        if (size > 0) {
            // Calculate the index of the next panel
            int nextIndex = (currentIndex + 1) % medicine_arraylist.size();

            // Get the next panel
            MedicineRowPanel nextPanel = medicine_arraylist.get(nextIndex);

            // Transfer focus to the name text field of the next panel
            nextPanel.afternoon_chk.requestFocus();
            nextPanel.setNewLineColor();
            nextPanel.afternoon_chk.setForeground(BG_HOVER_COLOR);
        }
    }
    private static void transferAfternoonChkFocusToRightComponent(MedicineRowPanel p) {
        p.evening_chk.requestFocus();
        p.setNewLineColor();
        
         p.afternoon_chk.setForeground(BG__COLOR);
         p.evening_chk.setForeground(BG_HOVER_COLOR);
         
        
    }
    private static void transferAfternoonChkFocusToLeftComponent(MedicineRowPanel p) {
        
        p.morning_chk.requestFocus();
        p.setNewLineColor();
        
        p.afternoon_chk.setForeground(BG__COLOR);
        p.morning_chk.setForeground(BG_HOVER_COLOR);
        
    }    
    private static void transferAfternoonChkFocusToPreviousComponent(MedicineRowPanel p) {
        // Get the currently focused text field
        JTextField currentTextField = p.total_tablet;
        p.erasePreviousLineColor();
        p.afternoon_chk.setForeground(BG__COLOR);

        // Find the index of the current panel in the list
        int currentIndex = medicine_arraylist.indexOf(p);
        if (currentIndex == 0) {
            currentIndex = medicine_arraylist.size();
        }
        int previousIndex = (currentIndex - 1) % medicine_arraylist.size();

        // Get the next panel
        MedicineRowPanel nextPanel = medicine_arraylist.get(previousIndex);

        // Transfer focus to the name text field of the next panel
        nextPanel.afternoon_chk.requestFocus();
        nextPanel.setNewLineColor();
        nextPanel.afternoon_chk.setForeground(BG_HOVER_COLOR);
        
    }
    //===========================================================================
    /**
     * Evening Checkbox
     *
     * @param p
     */
    private static void selectEveningChk(MedicineRowPanel p) {
        if (p.evening_chk.isSelected()) {
            p.evening_chk.setSelected(false);
        } else {
            p.evening_chk.setSelected(true);
        }
    }
    private static void transferEveningChkFocusToNextComponent(MedicineRowPanel p) {
        // Get the currently focused text field
        JTextField currentTextField = p.total_tablet;
        
        p.evening_chk.setForeground(BG__COLOR);
        p.erasePreviousLineColor();
        // Find the index of the current panel in the list
        int currentIndex = medicine_arraylist.indexOf(p);
        
        int size = medicine_arraylist.size();
        
        if (size > 0) {
            // Calculate the index of the next panel
            int nextIndex = (currentIndex + 1) % medicine_arraylist.size();

            // Get the next panel
            MedicineRowPanel nextPanel = medicine_arraylist.get(nextIndex);

            // Transfer focus to the name text field of the next panel
            nextPanel.evening_chk.requestFocus();
            nextPanel.setNewLineColor();
            nextPanel.evening_chk.setForeground(BG_HOVER_COLOR);
           
             
        }
    }
    private static void transferEveningChkFocusToRightComponent(MedicineRowPanel p) {
//        p.before.requestFocus();
//        p.setNewLineColor();
        
//        p.evening_chk.setForeground(BG__COLOR);     
    }
    private static void transferEveningChkFocusToLeftComponent(MedicineRowPanel p) {
        
        p.afternoon_chk.requestFocus();
        p.setNewLineColor();
        
          p.evening_chk.setForeground(BG__COLOR);
          p.afternoon_chk.setForeground(BG_HOVER_COLOR);
        
    }
    private static void transferEveningChkFocusToPreviousComponent(MedicineRowPanel p) {
        // Get the currently focused text field
        JTextField currentTextField = p.total_tablet;
        p.erasePreviousLineColor();
        p.evening_chk.setForeground(BG__COLOR);
        // Find the index of the current panel in the list
        int currentIndex = medicine_arraylist.indexOf(p);
        if (currentIndex == 0) {
            currentIndex = medicine_arraylist.size();
        }
        int previousIndex = (currentIndex - 1) % medicine_arraylist.size();

        // Get the next panel
        MedicineRowPanel nextPanel = medicine_arraylist.get(previousIndex);

        // Transfer focus to the name text field of the next panel
        nextPanel.evening_chk.requestFocus();
        nextPanel.setNewLineColor();
        nextPanel.evening_chk.setForeground(BG_HOVER_COLOR);
        
    }
    public static void removeSelectedMedicineAndTransferFocus(MedicineRowPanel p) {
        home.removeSelectedMedicine();
        //transferTotalTabletFocusToNextComponent(p);
        transferDeleteChkFocusToNextComponent(p);
    }
   
    //===========================================================================
    /**
     * Combobox
     *
     * @param p
     */
    private static void transferComboBoxFocusToNextComponent(MedicineRowPanel p) {
        // Get the currently focused text field
        JTextField currentTextField = p.total_tablet;
        
        p.erasePreviousLineColor();
        // Find the index of the current panel in the list
        int currentIndex = medicine_arraylist.indexOf(p);

        // Calculate the index of the next panel
        int nextIndex = (currentIndex + 1) % medicine_arraylist.size();

        // Get the next panel
        MedicineRowPanel nextPanel = medicine_arraylist.get(nextIndex);

        // Transfer focus to the name text field of the next panel
        nextPanel.comboBox.requestFocus();
        
        nextPanel.setNewLineColor();
    }    
    private static void transferComboBoxFocusToRightComponent(MedicineRowPanel p) {
        p.delete_chk.requestFocus();
        p.delete_chk.setSelected(true);
    }
    private static void transferComboBoxFocusToLeftComponent(MedicineRowPanel p) {
        p.total_tablet.requestFocus();
    }
    private static void transferComboBoxFocusToPreviousComponent(MedicineRowPanel p) {
        // Get the currently focused text field
        JTextField currentTextField = p.total_tablet;
        p.erasePreviousLineColor();

        // Find the index of the current panel in the list
        int currentIndex = medicine_arraylist.indexOf(p);
        if (currentIndex == 0) {
            currentIndex = medicine_arraylist.size();
        }
        int previousIndex = (currentIndex - 1) % medicine_arraylist.size();

        // Get the next panel
        MedicineRowPanel nextPanel = medicine_arraylist.get(previousIndex);

        // Transfer focus to the name text field of the next panel
        nextPanel.comboBox.requestFocus();
        nextPanel.setNewLineColor();
    }
    public static void addShortCutForTotalTabletInput(MedicineRowPanel p) {
        p.total_tablet.addActionListener((ActionEvent e) -> {
            transferTotalTabletFocusToNextComponent(p);
        });
        
        p.comboBox.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER -> transferComboBoxFocusToNextComponent(p);
                    case KeyEvent.VK_RIGHT -> transferComboBoxFocusToRightComponent(p);
                    case KeyEvent.VK_LEFT -> transferComboBoxFocusToLeftComponent(p);
                    default -> {
                    }
                    
                }
            }
        });
        
        p.total_tablet.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> transferTotalTabletFocusToPreviousComponent(p);
                    case KeyEvent.VK_DOWN -> transferTotalTabletFocusToNextComponent(p);
                    case KeyEvent.VK_RIGHT -> transferTotalTabletFocusToRightComponent(p);
                    case KeyEvent.VK_LEFT -> transferTotalTabletFocusToLeftComponent(p);
                   
                }
            }
            
        });
        p.delete_chk.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER -> removeSelectedMedicineAndTransferFocus(p);
                    case KeyEvent.VK_UP -> transferDeleteChkFocusToPreviousComponent(p);
                    case KeyEvent.VK_DOWN -> transferDeleteChkFocusToNextComponent(p);
                    case KeyEvent.VK_RIGHT -> transferDeleteChkFocusToRightComponent(p);
                    case KeyEvent.VK_LEFT -> transferDeleteChkFocusToLeftComponent(p);
                    
                }
            }
            
        });
        p.morning_chk.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER -> selectMorningChk(p);
                    case KeyEvent.VK_UP -> transferMorningChkFocusToPreviousComponent(p);
                    case KeyEvent.VK_DOWN -> transferMorningChkFocusToNextComponent(p);
                    case KeyEvent.VK_RIGHT -> transferMorningChkFocusToRightComponent(p);
                    case KeyEvent.VK_LEFT -> transferMorningChkFocusToLeftComponent(p);
                    
                }
            }
            
        });
        p.afternoon_chk.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER -> selectAfternoonChk(p);
                    case KeyEvent.VK_UP -> transferAfternoonChkFocusToPreviousComponent(p);
                    case KeyEvent.VK_DOWN -> transferAfternoonChkFocusToNextComponent(p);
                    case KeyEvent.VK_RIGHT -> transferAfternoonChkFocusToRightComponent(p);
                    case KeyEvent.VK_LEFT -> transferAfternoonChkFocusToLeftComponent(p);
                   
                }
            }
            
        });
        p.evening_chk.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER -> selectEveningChk(p);
                    case KeyEvent.VK_UP -> transferEveningChkFocusToPreviousComponent(p);
                    case KeyEvent.VK_DOWN -> transferEveningChkFocusToNextComponent(p);
                    case KeyEvent.VK_RIGHT -> transferEveningChkFocusToRightComponent(p);
                    case KeyEvent.VK_LEFT -> transferEveningChkFocusToLeftComponent(p);
                   
                }
            }
            
        });
        
        p.after.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER -> transferAfterBtnFocusToNextComponent(p);
                    case KeyEvent.VK_UP -> transferAfterBtnFocusToPreviousComponent(p);
                    case KeyEvent.VK_DOWN -> transferAfterBtnFocusToNextComponent(p);
                    case KeyEvent.VK_RIGHT -> transferAfterBtnFocusToRightComponent(p);
                    case KeyEvent.VK_LEFT -> transferAfterBtnFocusToLeftComponent(p);
                   
                }
            }
            
        });
        p.before.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_ENTER -> transferBeforBtnFocusToNextComponent(p);
                    case KeyEvent.VK_RIGHT -> transferBeforBtnFocusToRightComponent(p);
                    case KeyEvent.VK_LEFT -> transferBeforBtnFocusToLeftComponent(p);
                    
                }
            }
            
        });
        
    }
    public static ArrayList<MedicineRowPanel> getMedicineList() {
        return medicine_arraylist;
    }

}
