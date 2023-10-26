/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hospitalmanagement;

import static hospitalmanagement.Home.total_medicine_selected;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import myutil.Database;
import myutil.M_BandType;
import myutil.MedicineDetails;
import myutil.MedicineRowPanel;
import myutil.SetImageIcon;

/**
 *
 * @author haris
 */
public class BookmarkPanel extends javax.swing.JPanel {

    /**
     * Creates new form BookmarkPanel
     */
    Home home;
    String next_page_icon = "./images/right_arrow.png";

    public BookmarkPanel(JFrame home) {
        initComponents();

        this.home = (Home) home;

        setBookmarkOnInputField();
        displayBookmark();

        next.add(new SetImageIcon(new ImageIcon(next_page_icon), 25, 25), BorderLayout.CENTER);
    }

    public void displayBookmark() {
        Database database = Database.getInstance();
        ArrayList<String> medi = database.getBookmark();
        DefaultListModel lm = new DefaultListModel();
        for (String m : medi) {
            lm.addElement(m);
        }
        bookmark_list.setModel(lm);
    }

    public JList<String> getBookmarkList() {
        return this.bookmark_list;
    }

    public void setBookmarkOnInputField() {
        DocumentListener dl = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFieldState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFieldState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFieldState();
            }

            void updateFieldState() {

                try {
                    Database database = Database.getInstance();
                    String text = bookmark_input.getText();
                    if (text.length() > 0) {
                        ArrayList<String> bookmark = database.getLikeBookmark(text);
                        DefaultListModel lm = new DefaultListModel();

                        for (String m : bookmark) {
                            lm.addElement(m);
                        }
                        bookmark_list.setModel(lm);
                    }
                   else{
                      displayBookmark();  
                    }
                } catch (Exception exp) {
                    System.out.println("No bookmark found");
                }
                System.out.println("worked");
            }

        };
        bookmark_input.getDocument().addDocumentListener(dl);
        bookmark_list_panel.add(new JScrollPane(bookmark_list));
        validate();
        repaint();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bookmark_panel = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        bookmark_input = new javax.swing.JTextField();
        bookmark_list_panel = new javax.swing.JPanel();
        bookmark_list = new javax.swing.JList<>();
        add_bookmark_btn = new javax.swing.JButton();
        bookmark_delete_btn = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        new_bookmark_input = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        save_bookmark_btn = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        bookmark_status_label = new javax.swing.JLabel();
        next = new javax.swing.JPanel();

        setBackground(new java.awt.Color(251, 252, 224));
        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        setPreferredSize(new java.awt.Dimension(200, 528));
        setLayout(new java.awt.BorderLayout());

        bookmark_panel.setBackground(new java.awt.Color(251, 252, 224));
        bookmark_panel.setAutoscrolls(true);
        bookmark_panel.setPreferredSize(new java.awt.Dimension(200, 528));

        jLabel7.setText("Bookmark");

        bookmark_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bookmark_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bookmark_inputMouseExited(evt);
            }
        });

        bookmark_list_panel.setLayout(new java.awt.CardLayout());

        bookmark_list.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        bookmark_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        bookmark_list.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bookmark_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookmark_listMouseClicked(evt);
            }
        });
        bookmark_list_panel.add(bookmark_list, "card2");

        add_bookmark_btn.setBackground(new java.awt.Color(0, 51, 255));
        add_bookmark_btn.setForeground(new java.awt.Color(255, 255, 255));
        add_bookmark_btn.setText("Add");
        add_bookmark_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add_bookmark_btn.setFocusPainted(false);
        add_bookmark_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                add_bookmark_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                add_bookmark_btnMouseExited(evt);
            }
        });
        add_bookmark_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_bookmark_btnActionPerformed(evt);
            }
        });

        bookmark_delete_btn.setBackground(new java.awt.Color(255, 51, 51));
        bookmark_delete_btn.setForeground(new java.awt.Color(255, 255, 255));
        bookmark_delete_btn.setText("Delete");
        bookmark_delete_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 1, true));
        bookmark_delete_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bookmark_delete_btn.setFocusPainted(false);
        bookmark_delete_btn.setPreferredSize(new java.awt.Dimension(27, 18));
        bookmark_delete_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bookmark_delete_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bookmark_delete_btnMouseExited(evt);
            }
        });
        bookmark_delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookmark_delete_btnActionPerformed(evt);
            }
        });

        new_bookmark_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                new_bookmark_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                new_bookmark_inputMouseExited(evt);
            }
        });

        jLabel15.setText("Add New Bookmark");

        jLabel16.setText("Bookmark Name :-");

        jLabel17.setText("Select the medicine from adjacent window");

        save_bookmark_btn.setBackground(new java.awt.Color(0, 0, 255));
        save_bookmark_btn.setForeground(new java.awt.Color(255, 255, 255));
        save_bookmark_btn.setText("Save");
        save_bookmark_btn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        save_bookmark_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        save_bookmark_btn.setFocusPainted(false);
        save_bookmark_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                save_bookmark_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                save_bookmark_btnMouseExited(evt);
            }
        });
        save_bookmark_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_bookmark_btnActionPerformed(evt);
            }
        });

        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        next.setBackground(new java.awt.Color(251, 252, 224));
        next.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        next.setPreferredSize(new java.awt.Dimension(25, 25));
        next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nextMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                nextMouseReleased(evt);
            }
        });
        next.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout bookmark_panelLayout = new javax.swing.GroupLayout(bookmark_panel);
        bookmark_panel.setLayout(bookmark_panelLayout);
        bookmark_panelLayout.setHorizontalGroup(
            bookmark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookmark_panelLayout.createSequentialGroup()
                .addGroup(bookmark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bookmark_panelLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(jLabel15))
                    .addGroup(bookmark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookmark_panelLayout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(save_bookmark_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(bookmark_panelLayout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addGroup(bookmark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(bookmark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(bookmark_panelLayout.createSequentialGroup()
                                        .addGap(94, 94, 94)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookmark_panelLayout.createSequentialGroup()
                                        .addComponent(bookmark_input, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(add_bookmark_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(bookmark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSeparator3)
                                    .addComponent(bookmark_delete_btn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bookmark_list_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(bookmark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(bookmark_panelLayout.createSequentialGroup()
                                            .addComponent(jLabel16)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(new_bookmark_input, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookmark_panelLayout.createSequentialGroup()
                                        .addComponent(bookmark_status_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)))))))
                .addGap(10, 10, 10))
        );
        bookmark_panelLayout.setVerticalGroup(
            bookmark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookmark_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(bookmark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bookmark_input, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_bookmark_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookmark_list_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookmark_delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(bookmark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(new_bookmark_input, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(save_bookmark_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(bookmark_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bookmark_panelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(next, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookmark_panelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bookmark_status_label, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))))
        );

        add(bookmark_panel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void add_bookmark_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_bookmark_btnActionPerformed

        home.addBookmarkMedicine(bookmark_list);
    }//GEN-LAST:event_add_bookmark_btnActionPerformed

    private void bookmark_delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookmark_delete_btnActionPerformed

        try {

            DefaultListModel lm = (DefaultListModel) bookmark_list.getModel();
            int index = bookmark_list.getSelectedIndex();

            Database database = Database.getInstance();
            database.removeBookmark(bookmark_list.getSelectedValue());
            lm.remove(index);

            bookmark_status_label.setText("Bookmark Removed Successfuly.");
            bookmark_status_label.setForeground(home.SUCCESS_COLOR);
            validate();
            repaint();
        } catch (Exception exp) {
            bookmark_status_label.setText("Unabel to remove bookmark");
            bookmark_status_label.setForeground(home.WARNING_COLOR);
        }
    }//GEN-LAST:event_bookmark_delete_btnActionPerformed

    private void save_bookmark_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_bookmark_btnActionPerformed

        validate();
        repaint();
        try {
            ArrayList<MedicineRowPanel> medicine_arraylist = home.getMedicineList();

            String bookmark_name = new_bookmark_input.getText();
            if (!medicine_arraylist.isEmpty() && bookmark_name.length() > 0) {
                Database database = Database.getInstance();
                for (int i = 0; i < medicine_arraylist.size(); i++) {
                    M_BandType row = medicine_arraylist.get(i).getDetials();
                    if (row != null) {

                        MedicineDetails medicineDetails = new MedicineDetails();
                        medicineDetails.setMedicineName(row.medicine_name);
                        medicineDetails.setMedicineMealTime(row.before);
                        medicineDetails.setMedicineQuantity(row.selected_combo);
                        medicineDetails.setTotalQuantity(row.tab);
                        medicineDetails.setMedicineTime(row.morning_status, row.afternoon_status, row.evening_status);
                        database.addBookmark(bookmark_name, medicineDetails);

                    }
                }

                bookmark_status_label.setText("Bookmark added Successfuly.");
                bookmark_status_label.setForeground(home.SUCCESS_COLOR);
                new_bookmark_input.setText("");

                displayBookmark();
                validate();
                repaint();
            } else {
                throw new NullPointerException();
            }
        } catch (NullPointerException expt) {
            bookmark_status_label.setText("Unabel to add bookmark");
            bookmark_status_label.setForeground(home.WARNING_COLOR);
        }
        System.out.println("save btn clicked");
    }//GEN-LAST:event_save_bookmark_btnActionPerformed

    private void bookmark_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookmark_inputMouseEntered
        bookmark_input.setBorder(home.HOVER_BORDER);
    }//GEN-LAST:event_bookmark_inputMouseEntered

    private void add_bookmark_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_bookmark_btnMouseEntered
        add_bookmark_btn.setBorder(home.HOVER_BTN_BORDER);
    }

    private void add_medicine_btnMouseExited(java.awt.event.MouseEvent evt) {
        add_bookmark_btn.setBorder(home.DEFAULT_BTN_BORDER);
    }//GEN-LAST:event_add_bookmark_btnMouseEntered

    private void bookmark_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookmark_inputMouseExited
        bookmark_input.setBorder(home.INPUT_BORDER);
    }//GEN-LAST:event_bookmark_inputMouseExited

    private void add_bookmark_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_bookmark_btnMouseExited
        add_bookmark_btn.setBorder(home.DEFAULT_BTN_BORDER);
    }//GEN-LAST:event_add_bookmark_btnMouseExited

    private void new_bookmark_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_new_bookmark_inputMouseEntered
        new_bookmark_input.setBorder(home.HOVER_BORDER);
    }//GEN-LAST:event_new_bookmark_inputMouseEntered

    private void new_bookmark_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_new_bookmark_inputMouseExited
        new_bookmark_input.setBorder(home.INPUT_BORDER);

    }//GEN-LAST:event_new_bookmark_inputMouseExited

    private void save_bookmark_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_save_bookmark_btnMouseEntered
        save_bookmark_btn.setBorder(new LineBorder(Color.BLACK, 2, true));
    }//GEN-LAST:event_save_bookmark_btnMouseEntered

    private void save_bookmark_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_save_bookmark_btnMouseExited
        save_bookmark_btn.setBorder(home.HOVER_BTN_BORDER);
    }//GEN-LAST:event_save_bookmark_btnMouseExited

    private void bookmark_delete_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookmark_delete_btnMouseEntered
        bookmark_delete_btn.setBorder(home.DEFAULT_BTN_BORDER);
    }//GEN-LAST:event_bookmark_delete_btnMouseEntered

    private void bookmark_delete_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookmark_delete_btnMouseExited
        bookmark_delete_btn.setBorder(new LineBorder(new Color(10066329), 1, true));
    }//GEN-LAST:event_bookmark_delete_btnMouseExited

    private void bookmark_listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookmark_listMouseClicked
        if (evt.getClickCount() == 1) {
            home.addBookmarkMedicine(bookmark_list);
            bookmark_input.setText("");
           //resetBookmarkPanel();

        }
    }//GEN-LAST:event_bookmark_listMouseClicked

    private void nextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextMouseClicked
        home.showPageOnWindow("reports");
        home.showReportOnWindow("Prescription");
    }//GEN-LAST:event_nextMouseClicked

    private void nextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_nextMouseExited

    private void nextMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_nextMouseReleased

    public void resetBookmarkPanel() {
        bookmark_input.setText("");
        new_bookmark_input.setText("");
        bookmark_status_label.setText("");
        DefaultListModel lm = (DefaultListModel) bookmark_list.getModel();
        lm.removeAllElements();
        displayBookmark();
        validate();
        repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_bookmark_btn;
    private javax.swing.JButton bookmark_delete_btn;
    private javax.swing.JTextField bookmark_input;
    private javax.swing.JList<String> bookmark_list;
    private javax.swing.JPanel bookmark_list_panel;
    private javax.swing.JPanel bookmark_panel;
    private javax.swing.JLabel bookmark_status_label;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField new_bookmark_input;
    private javax.swing.JPanel next;
    private javax.swing.JButton save_bookmark_btn;
    // End of variables declaration//GEN-END:variables
}
