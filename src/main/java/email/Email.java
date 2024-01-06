package email;

import auth.User;
import hospitalmanagement.Home;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import myutil.Database;
import myutil.MyCustomRenderer;
import myutil.SetImageIcon;
import myutil.AdminInformation;

/**
 *
 * @author haris
 */
public class Email extends javax.swing.JPanel {

    public final LineBorder HOVER_BTN_BORDER = new LineBorder(new Color(0x7C7CF1), 2, true);
    public final LineBorder DEFAULT_BTN_BORDER = new LineBorder(Color.WHITE, 1, true);
    public final LineBorder DEFAULT_BORDER = new LineBorder(Color.blue, 1, true);
    public final LineBorder INPUT_BORDER = new LineBorder(new Color(0x7c7cf1), 1, true);
    public final LineBorder HOVER_BORDER = new LineBorder(new Color(0x7C7CF1), 2, true);
    public final LineBorder WARNING_BORDER = new LineBorder(new Color(0xff3333), 2, true);

    public final Color WARNING_COLOR = new Color(16724787);
    public final Color SUCCESS_COLOR = new Color(0, 153, 0);
    public final Color CLICKED_LABEL_COLOR = new Color(0, 0, 204);
    public final Color REPORT_LABEL_COLOR = new Color(0, 0, 102);
    String delete_icon = "./images/delete_file.png";
    String refresh_page_icon_on_click = "./images/refresh3.png";
    CardLayout card;
    JFileChooser em_file_chooser, ad_file_chooser, pd_file_chooser;
    String em_file_path, ad_file_path, pd_file_path;
    String page_showing;
    User HOME_USER;

    public Email() {
        initComponents();

        showEmailTypePage("email_mode");
        addMouseListerOnTabel();

        em_body_input.setLineWrap(true);
        pd_body_input.setLineWrap(true);
        ad_body_input.setLineWrap(true);
        pd_report_refresh.add(new SetImageIcon(new ImageIcon(refresh_page_icon_on_click), 30, 30), BorderLayout.CENTER);
        ad_report_refresh.add(new SetImageIcon(new ImageIcon(refresh_page_icon_on_click), 30, 30), BorderLayout.CENTER);
        em_report_refresh.add(new SetImageIcon(new ImageIcon(refresh_page_icon_on_click), 30, 30), BorderLayout.CENTER);
        tm_report_refresh.add(new SetImageIcon(new ImageIcon(refresh_page_icon_on_click), 30, 30), BorderLayout.CENTER);
        up_report_refresh.add(new SetImageIcon(new ImageIcon(refresh_page_icon_on_click), 30, 30), BorderLayout.CENTER);
//        try {
//            if (InternetAvailabilityChecker.isInternetAvailable()) {
//                sendingEmail = new SendingEmail();
//                sendingEmail.setAuthenicationDetails("testify8953@gmail.com", "kaom ridr dvep qlfe");
//            } else {
//                System.out.println("Internet is not connecetd");
//            }
//        } catch (Exception exp) {
//            exp.printStackTrace();
//        }

    }

    public void setUserOnEmailPage(User user) {
        if (user != null) {
            this.HOME_USER = user;

            User rs_user = (Database.getInstance()).getEmailSecurityCodes(user);
            if (rs_user != null && rs_user.getEmailVerificationStatus() == true) {

                this.HOME_USER.setEmailPassword(rs_user.getEmailPassword());
                this.HOME_USER.setEmailVerificationStatus(true);
            } else {
                email_verified_status.setText("Please add email security codes for sending emails");
                email_verified_status.setForeground(WARNING_COLOR);
            }

            from_email_input.setText(user.getEmail());
        }

    }

    public boolean isEmailModePage() {
        return page_showing.equalsIgnoreCase("email_mode");
    }

    public boolean isPatientModePage() {
        return page_showing.equalsIgnoreCase("patient_mode");
    }

    public boolean isAddEmailModePage() {
        return page_showing.equalsIgnoreCase("add_email");
    }

    //when the email page is in custome email mode 
    public void resetEMPage() {
        em_email_to_input.setText("");
        em_subject_input.setText("");
        em_body_input.setText("");
        em_status_label.setText("");
        removeEMPageSelectedFile();
    }

    public void removeEMPageSelectedFile() {
        em_selected_file_name.setText("");
        em_remove_attach_file_icon_panel.removeAll();
        em_file_chooser = null;
        em_file_path = null;
        revalidate();
        repaint();
    }

    public void setEMPageFile(File file) {
        if (file != null) {
            em_file_path = file.getAbsolutePath();
            em_selected_file_name.setText(file.getName());
            em_remove_attach_file_icon_panel.add(new SetImageIcon(new ImageIcon(delete_icon), 15, 13), BorderLayout.CENTER);
            revalidate();
            repaint();
        }

    }

    public void setPDPageFile(File file) {
        if (file != null) {
            pd_file_path = file.getAbsolutePath();
            pd_selected_file_name.setText(file.getName());
            pd_remove_attach_file_icon_panel.add(new SetImageIcon(new ImageIcon(delete_icon), 15, 13), BorderLayout.CENTER);
            revalidate();
            repaint();
        }

    }

    public void setADPageFile(File file) {
        if (file != null) {
            ad_file_path = file.getAbsolutePath();
            ad_selected_file_name.setText(file.getName());
            ad_remove_attach_file_icon_panel.add(new SetImageIcon(new ImageIcon(delete_icon), 15, 13), BorderLayout.CENTER);
            revalidate();
            repaint();
        }
    }

    public void resetPDPage() {
        pd_email_to_input.setText("");
        pd_subject_input.setText("");
        pd_body_input.setText("");
        pd_status_label.setText("");
        removePDPageSelectedFile();
    }

    public void removePDPageSelectedFile() {
        pd_selected_file_name.setText("");
        pd_remove_attach_file_icon_panel.removeAll();
        pd_file_chooser = null;
        pd_file_path = null;
        revalidate();
        repaint();
    }

    public void resetADPage() {
        ad_template_input.setText("");
        ad_subject_input.setText("");
        ad_body_input.setText("");
        ad_status_label.setText("");

        ad_save_btn.setText("Save");
        removeADPageSelectedFile();
    }

    public void removeADPageSelectedFile() {
        ad_selected_file_name.setText("");
        ad_remove_attach_file_icon_panel.removeAll();
        ad_file_chooser = null;
        ad_file_path = null;
        revalidate();
        repaint();
    }

    public void setIocns() {
        pd_remove_attach_file_icon_panel.add(new SetImageIcon(new ImageIcon(delete_icon), 15, 13), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void removeIcon() {
        pd_remove_attach_file_icon_panel.removeAll();
        revalidate();
        repaint();
    }

    public void showEmailTypePage(String page_name) {
        card = (CardLayout) modes.getLayout();
        card.show(modes, page_name);

        HashMap<String, JLabel> mp = new HashMap<String, JLabel>();
        mp.put("email_mode", custome_email_label);
        mp.put("patient_mode", patient_details_email_label);
        mp.put("add_email", add_email_label);
        JLabel menu_panel_label_list[] = {custome_email_label, patient_details_email_label, add_email_label};

        // mp.get(page_name).setForeground(Color.CYAN);
        for (JLabel menu_panel_label_list1 : menu_panel_label_list) {
            if (menu_panel_label_list1 == mp.get(page_name)) {
                menu_panel_label_list1.setForeground(Color.BLUE);
                menu_panel_label_list1.setBackground(new Color(205, 207, 185));
                page_showing = page_name;

            } else {
                menu_panel_label_list1.setForeground(Color.black);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        email_panel_head = new javax.swing.JPanel();
        patient_panel_header_title2 = new javax.swing.JLabel();
        content_panel = new javax.swing.JPanel();
        main_panel = new javax.swing.JPanel();
        email_sending_panel = new javax.swing.JPanel();
        modes = new javax.swing.JPanel();
        patient_details_mode = new javax.swing.JPanel();
        pd_email_to_input = new javax.swing.JTextField();
        patient_name_input = new javax.swing.JTextField();
        add_patient_email_btn = new javax.swing.JButton();
        patient_list_panel = new javax.swing.JPanel();
        patient_table_scroll = new javax.swing.JScrollPane();
        patient_table = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        pd_subject_input = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        pd_body_input = new javax.swing.JTextArea();
        pd_save_btn = new javax.swing.JButton();
        pd_send_btn = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        pd_attach_file_btn = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        pd_selected_file_name = new javax.swing.JLabel();
        pd_remove_attach_file_icon_panel = new javax.swing.JPanel();
        pd_status_label = new javax.swing.JLabel();
        pd_report_refresh = new javax.swing.JPanel();
        email_mode = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        em_body_input = new javax.swing.JTextArea();
        em_send_btn = new javax.swing.JButton();
        em_save_btn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        em_attach_file_btn = new javax.swing.JButton();
        em_subject_input = new javax.swing.JTextField();
        em_email_to_input = new javax.swing.JTextField();
        em_selected_file_name = new javax.swing.JLabel();
        em_remove_attach_file_icon_panel = new javax.swing.JPanel();
        em_status_label = new javax.swing.JLabel();
        em_report_refresh = new javax.swing.JPanel();
        add_email_panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ad_subject_input = new javax.swing.JTextField();
        ad_template_input = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ad_body_input = new javax.swing.JTextArea();
        ad_attach_file_btn = new javax.swing.JButton();
        ad_save_btn = new javax.swing.JButton();
        ad_selected_file_name = new javax.swing.JLabel();
        ad_remove_attach_file_icon_panel = new javax.swing.JPanel();
        ad_status_label = new javax.swing.JLabel();
        ad_report_refresh = new javax.swing.JPanel();
        update_email = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        up_email_input = new javax.swing.JTextField();
        ad_remove_attach_file_icon_panel1 = new javax.swing.JPanel();
        up_status_label = new javax.swing.JLabel();
        up_report_refresh = new javax.swing.JPanel();
        up_cancle_btn = new javax.swing.JButton();
        up_password_input = new javax.swing.JPasswordField();
        up_submit_btn = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        up_view_code_checkbox = new javax.swing.JCheckBox();
        custome_email_label = new javax.swing.JLabel();
        patient_details_email_label = new javax.swing.JLabel();
        update_from_email_btn = new javax.swing.JButton();
        from_email_label = new javax.swing.JLabel();
        from_email_input = new javax.swing.JTextField();
        add_email_label = new javax.swing.JLabel();
        email_verified_status = new javax.swing.JLabel();
        composing_email_panel = new javax.swing.JPanel();
        template_input = new javax.swing.JTextField();
        add_template_btn = new javax.swing.JButton();
        template_list_panel = new javax.swing.JPanel();
        template_list = new javax.swing.JList<>();
        jLabel6 = new javax.swing.JLabel();
        delete_template_btn = new javax.swing.JButton();
        selected_template = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel16 = new javax.swing.JLabel();
        update_template_btn = new javax.swing.JButton();
        tm_status_label = new javax.swing.JLabel();
        tm_report_refresh = new javax.swing.JPanel();
        upper_pannel_for_margin = new javax.swing.JPanel();
        left_pannel_for_margin = new javax.swing.JPanel();
        down_pannel_for_margin = new javax.swing.JPanel();
        right_pannel_for_margin = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        email_panel_head.setBackground(new java.awt.Color(0, 0, 102));
        email_panel_head.setForeground(new java.awt.Color(51, 255, 255));
        email_panel_head.setMinimumSize(new java.awt.Dimension(169, 40));
        email_panel_head.setPreferredSize(new java.awt.Dimension(169, 35));

        patient_panel_header_title2.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        patient_panel_header_title2.setForeground(new java.awt.Color(102, 255, 255));
        patient_panel_header_title2.setText("Email");
        email_panel_head.add(patient_panel_header_title2);

        add(email_panel_head, java.awt.BorderLayout.PAGE_START);

        content_panel.setBackground(new java.awt.Color(204, 255, 255));
        content_panel.setLayout(new java.awt.BorderLayout());

        main_panel.setBackground(new java.awt.Color(204, 255, 255));
        main_panel.setLayout(new java.awt.BorderLayout(3, 0));

        email_sending_panel.setBackground(new java.awt.Color(251, 252, 224));
        email_sending_panel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 153), 1, true));
        email_sending_panel.setPreferredSize(new java.awt.Dimension(600, 100));

        modes.setLayout(new java.awt.CardLayout());

        patient_details_mode.setBackground(new java.awt.Color(239, 240, 209));
        patient_details_mode.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 1, true));

        pd_email_to_input.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        pd_email_to_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        pd_email_to_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pd_email_to_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pd_email_to_inputMouseExited(evt);
            }
        });
        pd_email_to_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pd_email_to_inputActionPerformed(evt);
            }
        });

        patient_name_input.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        patient_name_input.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                patient_name_inputCaretUpdate(evt);
            }
        });
        patient_name_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                patient_name_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                patient_name_inputMouseExited(evt);
            }
        });
        patient_name_input.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                patient_name_inputKeyPressed(evt);
            }
        });

        add_patient_email_btn.setBackground(new java.awt.Color(0, 51, 255));
        add_patient_email_btn.setForeground(new java.awt.Color(255, 255, 255));
        add_patient_email_btn.setText("Add");
        add_patient_email_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        add_patient_email_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add_patient_email_btn.setFocusPainted(false);
        add_patient_email_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                add_patient_email_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                add_patient_email_btnMouseExited(evt);
            }
        });
        add_patient_email_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_patient_email_btnActionPerformed(evt);
            }
        });

        patient_list_panel.setPreferredSize(new java.awt.Dimension(0, 240));
        patient_list_panel.setLayout(new java.awt.BorderLayout());

        patient_table_scroll.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        patient_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        patient_table.getTableHeader().setReorderingAllowed(false);
        patient_table_scroll.setViewportView(patient_table);

        patient_list_panel.add(patient_table_scroll, java.awt.BorderLayout.CENTER);

        jLabel11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel11.setText("Patient Email :");
        jLabel11.setPreferredSize(new java.awt.Dimension(38, 28));

        pd_subject_input.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        pd_subject_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        pd_subject_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pd_subject_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pd_subject_inputMouseExited(evt);
            }
        });
        pd_subject_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pd_subject_inputActionPerformed(evt);
            }
        });

        jLabel12.setText("Subject:-");

        jLabel13.setText("Body:-");

        pd_body_input.setColumns(20);
        pd_body_input.setRows(5);
        jScrollPane2.setViewportView(pd_body_input);

        pd_save_btn.setBackground(new java.awt.Color(0, 51, 255));
        pd_save_btn.setForeground(new java.awt.Color(255, 255, 255));
        pd_save_btn.setText("Save");
        pd_save_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        pd_save_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pd_save_btn.setFocusPainted(false);
        pd_save_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pd_save_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pd_save_btnMouseExited(evt);
            }
        });
        pd_save_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pd_save_btnActionPerformed(evt);
            }
        });

        pd_send_btn.setBackground(new java.awt.Color(0, 51, 255));
        pd_send_btn.setForeground(new java.awt.Color(255, 255, 255));
        pd_send_btn.setText("Send");
        pd_send_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        pd_send_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pd_send_btn.setFocusPainted(false);
        pd_send_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pd_send_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pd_send_btnMouseExited(evt);
            }
        });
        pd_send_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pd_send_btnActionPerformed(evt);
            }
        });

        jLabel15.setText("Attach:-");

        pd_attach_file_btn.setBackground(new java.awt.Color(0, 51, 255));
        pd_attach_file_btn.setForeground(new java.awt.Color(255, 255, 255));
        pd_attach_file_btn.setText("Add File");
        pd_attach_file_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        pd_attach_file_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pd_attach_file_btn.setFocusPainted(false);
        pd_attach_file_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pd_attach_file_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pd_attach_file_btnMouseExited(evt);
            }
        });
        pd_attach_file_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pd_attach_file_btnActionPerformed(evt);
            }
        });

        jLabel14.setText("Enter Patient Name :-");

        pd_remove_attach_file_icon_panel.setBackground(new java.awt.Color(239, 240, 209));
        pd_remove_attach_file_icon_panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pd_remove_attach_file_icon_panel.setFocusable(false);
        pd_remove_attach_file_icon_panel.setPreferredSize(new java.awt.Dimension(15, 13));
        pd_remove_attach_file_icon_panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pd_remove_attach_file_icon_panelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pd_remove_attach_file_icon_panelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pd_remove_attach_file_icon_panelMouseExited(evt);
            }
        });
        pd_remove_attach_file_icon_panel.setLayout(new java.awt.BorderLayout());

        pd_report_refresh.setBackground(new java.awt.Color(251, 252, 224));
        pd_report_refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pd_report_refresh.setPreferredSize(new java.awt.Dimension(29, 29));
        pd_report_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pd_report_refreshMouseClicked(evt);
            }
        });
        pd_report_refresh.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout patient_details_modeLayout = new javax.swing.GroupLayout(patient_details_mode);
        patient_details_mode.setLayout(patient_details_modeLayout);
        patient_details_modeLayout.setHorizontalGroup(
            patient_details_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patient_details_modeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(patient_details_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pd_report_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(patient_details_modeLayout.createSequentialGroup()
                        .addGroup(patient_details_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(patient_details_modeLayout.createSequentialGroup()
                                .addGroup(patient_details_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(patient_details_modeLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(pd_status_label, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel15))
                                    .addComponent(jLabel14)
                                    .addGroup(patient_details_modeLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(patient_name_input, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(add_patient_email_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(pd_email_to_input, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, patient_details_modeLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(patient_details_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pd_subject_input)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                            .addGroup(patient_details_modeLayout.createSequentialGroup()
                                .addComponent(pd_attach_file_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pd_remove_attach_file_icon_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pd_selected_file_name, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(pd_save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pd_send_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(patient_details_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(patient_details_modeLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(patient_list_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(435, Short.MAX_VALUE)))
        );
        patient_details_modeLayout.setVerticalGroup(
            patient_details_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patient_details_modeLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(patient_details_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(patient_details_modeLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pd_email_to_input, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(patient_details_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(patient_name_input, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add_patient_email_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(234, 234, 234)
                        .addComponent(pd_status_label, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(patient_details_modeLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pd_subject_input, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(patient_details_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pd_remove_attach_file_icon_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(patient_details_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(pd_save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pd_send_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15)
                                .addComponent(pd_attach_file_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(pd_selected_file_name, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(38, 38, 38)
                .addComponent(pd_report_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
            .addGroup(patient_details_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(patient_details_modeLayout.createSequentialGroup()
                    .addGap(132, 132, 132)
                    .addComponent(patient_list_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(199, 199, 199)))
        );

        modes.add(patient_details_mode, "patient_mode");

        email_mode.setBackground(new java.awt.Color(239, 240, 209));
        email_mode.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 1, true));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("To:- ");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Subject :-");
        jLabel1.setPreferredSize(new java.awt.Dimension(38, 28));

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText("Body :-");
        jLabel7.setPreferredSize(new java.awt.Dimension(38, 28));

        em_body_input.setColumns(20);
        em_body_input.setRows(5);
        em_body_input.setWrapStyleWord(true);
        jScrollPane1.setViewportView(em_body_input);

        em_send_btn.setBackground(new java.awt.Color(0, 51, 255));
        em_send_btn.setForeground(new java.awt.Color(255, 255, 255));
        em_send_btn.setText("Send");
        em_send_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 1, true));
        em_send_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        em_send_btn.setFocusPainted(false);
        em_send_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                em_send_btnActionPerformed(evt);
            }
        });

        em_save_btn.setBackground(new java.awt.Color(0, 51, 255));
        em_save_btn.setForeground(new java.awt.Color(255, 255, 255));
        em_save_btn.setText("Save");
        em_save_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 1, true));
        em_save_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        em_save_btn.setFocusPainted(false);
        em_save_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                em_save_btnActionPerformed(evt);
            }
        });

        jLabel8.setText("Attach :-");

        em_attach_file_btn.setBackground(new java.awt.Color(0, 51, 255));
        em_attach_file_btn.setForeground(new java.awt.Color(255, 255, 255));
        em_attach_file_btn.setText("Add File");
        em_attach_file_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 1, true));
        em_attach_file_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        em_attach_file_btn.setFocusPainted(false);
        em_attach_file_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                em_attach_file_btnActionPerformed(evt);
            }
        });

        em_subject_input.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        em_subject_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        em_subject_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                em_subject_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                em_subject_inputMouseExited(evt);
            }
        });
        em_subject_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                em_subject_inputActionPerformed(evt);
            }
        });

        em_email_to_input.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        em_email_to_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        em_email_to_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                em_email_to_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                em_email_to_inputMouseExited(evt);
            }
        });
        em_email_to_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                em_email_to_inputActionPerformed(evt);
            }
        });

        em_remove_attach_file_icon_panel.setBackground(new java.awt.Color(239, 240, 209));
        em_remove_attach_file_icon_panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        em_remove_attach_file_icon_panel.setFocusable(false);
        em_remove_attach_file_icon_panel.setPreferredSize(new java.awt.Dimension(15, 13));
        em_remove_attach_file_icon_panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                em_remove_attach_file_icon_panelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                em_remove_attach_file_icon_panelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                em_remove_attach_file_icon_panelMouseExited(evt);
            }
        });
        em_remove_attach_file_icon_panel.setLayout(new java.awt.BorderLayout());

        em_report_refresh.setBackground(new java.awt.Color(251, 252, 224));
        em_report_refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        em_report_refresh.setPreferredSize(new java.awt.Dimension(29, 29));
        em_report_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                em_report_refreshMouseClicked(evt);
            }
        });
        em_report_refresh.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout email_modeLayout = new javax.swing.GroupLayout(email_mode);
        email_mode.setLayout(email_modeLayout);
        email_modeLayout.setHorizontalGroup(
            email_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(email_modeLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(email_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(email_modeLayout.createSequentialGroup()
                        .addGroup(email_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(email_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(em_subject_input, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(em_email_to_input, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(email_modeLayout.createSequentialGroup()
                        .addGroup(email_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(email_modeLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(email_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(email_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, email_modeLayout.createSequentialGroup()
                                    .addComponent(em_status_label, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(em_report_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, email_modeLayout.createSequentialGroup()
                                    .addComponent(em_attach_file_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(9, 9, 9)
                                    .addComponent(em_remove_attach_file_icon_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(em_selected_file_name, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(186, 186, 186)
                                    .addComponent(em_save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(40, 40, 40)
                                    .addComponent(em_send_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        email_modeLayout.setVerticalGroup(
            email_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(email_modeLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(email_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(em_email_to_input, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(email_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(em_subject_input, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(email_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(email_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(email_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(em_send_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(em_selected_file_name, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(email_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(em_attach_file_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(em_save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(em_remove_attach_file_icon_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(email_modeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(em_status_label, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(em_report_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        modes.add(email_mode, "email_mode");

        add_email_panel.setBackground(new java.awt.Color(239, 240, 209));
        add_email_panel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 1, true));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Subject :-");
        jLabel2.setPreferredSize(new java.awt.Dimension(38, 28));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Template Name :-");
        jLabel4.setPreferredSize(new java.awt.Dimension(38, 28));

        ad_subject_input.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        ad_subject_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        ad_subject_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ad_subject_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ad_subject_inputMouseExited(evt);
            }
        });
        ad_subject_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ad_subject_inputActionPerformed(evt);
            }
        });

        ad_template_input.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        ad_template_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        ad_template_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ad_template_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ad_template_inputMouseExited(evt);
            }
        });
        ad_template_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ad_template_inputActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Body:-");
        jLabel5.setPreferredSize(new java.awt.Dimension(38, 28));

        jLabel9.setText("Attach :-");

        ad_body_input.setColumns(20);
        ad_body_input.setRows(5);
        jScrollPane3.setViewportView(ad_body_input);

        ad_attach_file_btn.setBackground(new java.awt.Color(0, 51, 255));
        ad_attach_file_btn.setForeground(new java.awt.Color(255, 255, 255));
        ad_attach_file_btn.setText("Add File");
        ad_attach_file_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 1, true));
        ad_attach_file_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ad_attach_file_btn.setFocusPainted(false);
        ad_attach_file_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ad_attach_file_btnActionPerformed(evt);
            }
        });

        ad_save_btn.setBackground(new java.awt.Color(0, 51, 255));
        ad_save_btn.setForeground(new java.awt.Color(255, 255, 255));
        ad_save_btn.setText("Save");
        ad_save_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 1, true));
        ad_save_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ad_save_btn.setFocusPainted(false);
        ad_save_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ad_save_btnActionPerformed(evt);
            }
        });

        ad_remove_attach_file_icon_panel.setBackground(new java.awt.Color(239, 240, 209));
        ad_remove_attach_file_icon_panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ad_remove_attach_file_icon_panel.setFocusable(false);
        ad_remove_attach_file_icon_panel.setPreferredSize(new java.awt.Dimension(15, 13));
        ad_remove_attach_file_icon_panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ad_remove_attach_file_icon_panelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ad_remove_attach_file_icon_panelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ad_remove_attach_file_icon_panelMouseExited(evt);
            }
        });
        ad_remove_attach_file_icon_panel.setLayout(new java.awt.BorderLayout());

        ad_report_refresh.setBackground(new java.awt.Color(251, 252, 224));
        ad_report_refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ad_report_refresh.setPreferredSize(new java.awt.Dimension(29, 29));
        ad_report_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ad_report_refreshMouseClicked(evt);
            }
        });
        ad_report_refresh.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout add_email_panelLayout = new javax.swing.GroupLayout(add_email_panel);
        add_email_panel.setLayout(add_email_panelLayout);
        add_email_panelLayout.setHorizontalGroup(
            add_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(add_email_panelLayout.createSequentialGroup()
                .addContainerGap(52, Short.MAX_VALUE)
                .addGroup(add_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(add_email_panelLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, add_email_panelLayout.createSequentialGroup()
                        .addGroup(add_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(add_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(add_email_panelLayout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(29, 29, 29)))
                .addGroup(add_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(add_email_panelLayout.createSequentialGroup()
                        .addComponent(ad_template_input, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(add_email_panelLayout.createSequentialGroup()
                        .addGroup(add_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(add_email_panelLayout.createSequentialGroup()
                                .addComponent(ad_status_label, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ad_report_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(add_email_panelLayout.createSequentialGroup()
                                .addComponent(ad_attach_file_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ad_remove_attach_file_icon_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ad_selected_file_name, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ad_save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                            .addComponent(ad_subject_input))
                        .addContainerGap(70, Short.MAX_VALUE))))
        );
        add_email_panelLayout.setVerticalGroup(
            add_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(add_email_panelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(add_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ad_template_input, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(add_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ad_subject_input, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(add_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(add_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ad_save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(add_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ad_attach_file_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(add_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(ad_selected_file_name, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ad_remove_attach_file_icon_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addGroup(add_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ad_status_label, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ad_report_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(84, Short.MAX_VALUE))
        );

        modes.add(add_email_panel, "add_email");

        update_email.setBackground(new java.awt.Color(239, 240, 209));
        update_email.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 1, true));

        jLabel18.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel18.setText("Email:-");
        jLabel18.setPreferredSize(new java.awt.Dimension(38, 28));

        up_email_input.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        up_email_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        up_email_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                up_email_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                up_email_inputMouseExited(evt);
            }
        });
        up_email_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                up_email_inputActionPerformed(evt);
            }
        });

        ad_remove_attach_file_icon_panel1.setBackground(new java.awt.Color(239, 240, 209));
        ad_remove_attach_file_icon_panel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ad_remove_attach_file_icon_panel1.setFocusable(false);
        ad_remove_attach_file_icon_panel1.setPreferredSize(new java.awt.Dimension(15, 13));
        ad_remove_attach_file_icon_panel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ad_remove_attach_file_icon_panel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ad_remove_attach_file_icon_panel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ad_remove_attach_file_icon_panel1MouseExited(evt);
            }
        });
        ad_remove_attach_file_icon_panel1.setLayout(new java.awt.BorderLayout());

        up_report_refresh.setBackground(new java.awt.Color(251, 252, 224));
        up_report_refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        up_report_refresh.setPreferredSize(new java.awt.Dimension(29, 29));
        up_report_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                up_report_refreshMouseClicked(evt);
            }
        });
        up_report_refresh.setLayout(new java.awt.BorderLayout());

        up_cancle_btn.setBackground(new java.awt.Color(0, 51, 255));
        up_cancle_btn.setForeground(new java.awt.Color(255, 255, 255));
        up_cancle_btn.setText("Cancle");
        up_cancle_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 1, true));
        up_cancle_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        up_cancle_btn.setFocusPainted(false);
        up_cancle_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                up_cancle_btnActionPerformed(evt);
            }
        });

        up_password_input.setEchoChar('*');

        up_submit_btn.setBackground(new java.awt.Color(0, 51, 255));
        up_submit_btn.setForeground(new java.awt.Color(255, 255, 255));
        up_submit_btn.setText("Submit");
        up_submit_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 1, true));
        up_submit_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        up_submit_btn.setFocusPainted(false);
        up_submit_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                up_submit_btnActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel20.setText("Enter the security key code of email ");
        jLabel20.setPreferredSize(new java.awt.Dimension(38, 28));

        jLabel17.setText("Update Email Details");

        up_view_code_checkbox.setBackground(new java.awt.Color(239, 240, 209));
        up_view_code_checkbox.setText("view codes");
        up_view_code_checkbox.setFocusPainted(false);
        up_view_code_checkbox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                up_view_code_checkboxItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout update_emailLayout = new javax.swing.GroupLayout(update_email);
        update_email.setLayout(update_emailLayout);
        update_emailLayout.setHorizontalGroup(
            update_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, update_emailLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(up_status_label, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(update_emailLayout.createSequentialGroup()
                .addContainerGap(205, Short.MAX_VALUE)
                .addGroup(update_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(up_password_input, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(up_view_code_checkbox)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(update_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(up_report_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(up_email_input, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(update_emailLayout.createSequentialGroup()
                        .addGroup(update_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(up_cancle_btn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(update_emailLayout.createSequentialGroup()
                                .addComponent(ad_remove_attach_file_icon_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(203, 203, 203)))
                        .addGap(29, 29, 29)
                        .addComponent(up_submit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 199, Short.MAX_VALUE))
            .addGroup(update_emailLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        update_emailLayout.setVerticalGroup(
            update_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(update_emailLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(up_email_input, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(up_password_input, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(up_view_code_checkbox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(up_status_label, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(update_emailLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(up_cancle_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(up_submit_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(up_report_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(ad_remove_attach_file_icon_panel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );

        modes.add(update_email, "update_email");

        custome_email_label.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        custome_email_label.setText("Custom Email");
        custome_email_label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        custome_email_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                custome_email_labelMouseClicked(evt);
            }
        });

        patient_details_email_label.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        patient_details_email_label.setText("Patient Details");
        patient_details_email_label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        patient_details_email_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patient_details_email_labelMouseClicked(evt);
            }
        });

        update_from_email_btn.setBackground(new java.awt.Color(0, 51, 255));
        update_from_email_btn.setForeground(new java.awt.Color(255, 255, 255));
        update_from_email_btn.setText("Update");
        update_from_email_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 1, true));
        update_from_email_btn.setFocusPainted(false);
        update_from_email_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_from_email_btnActionPerformed(evt);
            }
        });

        from_email_label.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        from_email_label.setText("From : ");
        from_email_label.setPreferredSize(new java.awt.Dimension(38, 28));

        from_email_input.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        from_email_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        from_email_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                from_email_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                from_email_inputMouseExited(evt);
            }
        });
        from_email_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                from_email_inputActionPerformed(evt);
            }
        });

        add_email_label.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        add_email_label.setText("Add Email");
        add_email_label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add_email_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add_email_labelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout email_sending_panelLayout = new javax.swing.GroupLayout(email_sending_panel);
        email_sending_panel.setLayout(email_sending_panelLayout);
        email_sending_panelLayout.setHorizontalGroup(
            email_sending_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(email_sending_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(email_sending_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(email_sending_panelLayout.createSequentialGroup()
                        .addComponent(custome_email_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(patient_details_email_label, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(add_email_label, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(email_sending_panelLayout.createSequentialGroup()
                        .addComponent(from_email_label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(from_email_input, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(update_from_email_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(email_verified_status, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(modes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        email_sending_panelLayout.setVerticalGroup(
            email_sending_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(email_sending_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(email_sending_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update_from_email_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(from_email_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(from_email_input, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(email_verified_status, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(email_sending_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add_email_label)
                    .addComponent(patient_details_email_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(custome_email_label, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(modes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        main_panel.add(email_sending_panel, java.awt.BorderLayout.CENTER);

        composing_email_panel.setBackground(new java.awt.Color(251, 252, 224));
        composing_email_panel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 153), 1, true));
        composing_email_panel.setPreferredSize(new java.awt.Dimension(400, 100));

        template_input.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        template_input.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                template_inputCaretUpdate(evt);
            }
        });
        template_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                template_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                template_inputMouseExited(evt);
            }
        });
        template_input.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                template_inputKeyPressed(evt);
            }
        });

        add_template_btn.setBackground(new java.awt.Color(0, 51, 255));
        add_template_btn.setForeground(new java.awt.Color(255, 255, 255));
        add_template_btn.setText("Add");
        add_template_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        add_template_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add_template_btn.setFocusPainted(false);
        add_template_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                add_template_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                add_template_btnMouseExited(evt);
            }
        });
        add_template_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_template_btnActionPerformed(evt);
            }
        });

        template_list_panel.setPreferredSize(new java.awt.Dimension(0, 240));
        template_list_panel.setLayout(new java.awt.CardLayout());

        template_list.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        template_list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        template_list.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        template_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                template_listMouseClicked(evt);
            }
        });
        template_list.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                template_listKeyPressed(evt);
            }
        });
        template_list_panel.add(template_list, "card2");

        jLabel6.setText("Enter Template Name");

        delete_template_btn.setBackground(new java.awt.Color(255, 51, 51));
        delete_template_btn.setForeground(new java.awt.Color(255, 255, 255));
        delete_template_btn.setText("Delete");
        delete_template_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        delete_template_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        delete_template_btn.setFocusPainted(false);
        delete_template_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                delete_template_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                delete_template_btnMouseExited(evt);
            }
        });
        delete_template_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_template_btnActionPerformed(evt);
            }
        });

        selected_template.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        selected_template.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                selected_templateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                selected_templateMouseExited(evt);
            }
        });
        selected_template.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                selected_templateKeyPressed(evt);
            }
        });

        jLabel10.setText("Selected Template");

        jSeparator1.setForeground(new java.awt.Color(0, 0, 255));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Choose Email Templates");

        update_template_btn.setBackground(new java.awt.Color(0, 51, 255));
        update_template_btn.setForeground(new java.awt.Color(255, 255, 255));
        update_template_btn.setText("Update");
        update_template_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 1, true));
        update_template_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        update_template_btn.setFocusPainted(false);
        update_template_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_template_btnActionPerformed(evt);
            }
        });

        tm_report_refresh.setBackground(new java.awt.Color(251, 252, 224));
        tm_report_refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tm_report_refresh.setPreferredSize(new java.awt.Dimension(29, 29));
        tm_report_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tm_report_refreshMouseClicked(evt);
            }
        });
        tm_report_refresh.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout composing_email_panelLayout = new javax.swing.GroupLayout(composing_email_panel);
        composing_email_panel.setLayout(composing_email_panelLayout);
        composing_email_panelLayout.setHorizontalGroup(
            composing_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(composing_email_panelLayout.createSequentialGroup()
                .addGroup(composing_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(composing_email_panelLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(composing_email_panelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, composing_email_panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(composing_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(composing_email_panelLayout.createSequentialGroup()
                        .addComponent(template_input, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(add_template_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(composing_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(composing_email_panelLayout.createSequentialGroup()
                            .addComponent(tm_status_label, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tm_report_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(composing_email_panelLayout.createSequentialGroup()
                            .addComponent(selected_template, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(update_template_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(delete_template_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25))
            .addGroup(composing_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(composing_email_panelLayout.createSequentialGroup()
                    .addGap(23, 23, 23)
                    .addComponent(template_list_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(26, Short.MAX_VALUE)))
        );
        composing_email_panelLayout.setVerticalGroup(
            composing_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(composing_email_panelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(composing_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(add_template_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(template_input, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(276, 276, 276)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(composing_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selected_template, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(update_template_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(delete_template_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(composing_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tm_status_label, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tm_report_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(96, Short.MAX_VALUE))
            .addGroup(composing_email_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(composing_email_panelLayout.createSequentialGroup()
                    .addGap(161, 161, 161)
                    .addComponent(template_list_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(216, Short.MAX_VALUE)))
        );

        main_panel.add(composing_email_panel, java.awt.BorderLayout.LINE_END);

        content_panel.add(main_panel, java.awt.BorderLayout.CENTER);

        upper_pannel_for_margin.setBackground(new java.awt.Color(204, 255, 255));
        upper_pannel_for_margin.setPreferredSize(new java.awt.Dimension(800, 8));
        content_panel.add(upper_pannel_for_margin, java.awt.BorderLayout.PAGE_START);

        left_pannel_for_margin.setBackground(new java.awt.Color(204, 255, 255));
        left_pannel_for_margin.setPreferredSize(new java.awt.Dimension(10, 400));
        content_panel.add(left_pannel_for_margin, java.awt.BorderLayout.LINE_START);

        down_pannel_for_margin.setBackground(new java.awt.Color(204, 255, 255));
        down_pannel_for_margin.setPreferredSize(new java.awt.Dimension(800, 8));
        content_panel.add(down_pannel_for_margin, java.awt.BorderLayout.PAGE_END);

        right_pannel_for_margin.setBackground(new java.awt.Color(204, 255, 255));
        right_pannel_for_margin.setPreferredSize(new java.awt.Dimension(10, 400));
        content_panel.add(right_pannel_for_margin, java.awt.BorderLayout.LINE_END);

        add(content_panel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void update_from_email_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_from_email_btnActionPerformed
        // insertUserEmailInforamtion();
        showEmailTypePage("update_email");
        String from_email = from_email_input.getText();
        if (from_email.length() != 0) {
            up_email_input.setText(from_email);
        }
    }//GEN-LAST:event_update_from_email_btnActionPerformed

    private void em_send_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_em_send_btnActionPerformed
        String to = em_email_to_input.getText();
        String subject = em_subject_input.getText();
        String message = em_body_input.getText();

        try {

            if (em_file_path != null) {
                File file = new File(em_file_path);
                SendingEmailWithAttachment sendEmailWithAttatch = new SendingEmailWithAttachment(to, subject, message, file);
                sendEmailWithAttatch.usingCutomeUserDetails(this.HOME_USER);
                sendEmailWithAttatch.start();
            } else {
                SendingEmailWithoutAttachment sendEmailWithoutAttatch = new SendingEmailWithoutAttachment(to, subject, message);
                sendEmailWithoutAttatch.usingCutomeUserDetails(this.HOME_USER);
                sendEmailWithoutAttatch.start();
            }
            saveEMPageEmail();
            em_status_label.setText("Email send successfully");
            em_status_label.setForeground(SUCCESS_COLOR);
        } catch (Exception exp) {
            exp.printStackTrace();
            System.out.println("Something went wrong");
            em_status_label.setText("Email Not send");
            em_status_label.setForeground(WARNING_COLOR);
        }
    }//GEN-LAST:event_em_send_btnActionPerformed

    private void em_save_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_em_save_btnActionPerformed
        saveEMPageEmail();
    }//GEN-LAST:event_em_save_btnActionPerformed
    public void saveEMPageEmail() {
        try {

            String from = from_email_input.getText();
            String to = em_email_to_input.getText();
            String subject = em_subject_input.getText();
            String body = em_body_input.getText();
            String template = selected_template.getText();
            if (template.length() == 0) {
                template = "None";
            }
            EmailInformation emailInformation = new EmailInformation();
            emailInformation.setSendFrom(from);
            emailInformation.setSendTo(to);
            emailInformation.setSubject(subject);
            emailInformation.setBody(body);
            emailInformation.setTemplate(template);

            Database database = Database.getInstance();
            database.insertEmail(emailInformation);
            System.out.println("Email Saved successfully");
            resetEMPage();
            em_status_label.setText("Email Saved successfully");
            em_status_label.setForeground(SUCCESS_COLOR);
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
            em_status_label.setText("Email Not Saved ");
            em_status_label.setForeground(WARNING_COLOR);
        }
    }
    private void em_attach_file_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_em_attach_file_btnActionPerformed
        try {
            em_file_chooser = new JFileChooser();
            em_file_chooser.showOpenDialog(null);
            File file = em_file_chooser.getSelectedFile();
            em_file_path = file.getAbsolutePath();
            setEMPageFile(file);

        } catch (HeadlessException exp) {
        }

    }//GEN-LAST:event_em_attach_file_btnActionPerformed

    private void from_email_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_from_email_inputMouseEntered
        if (from_email_input.getBorder() != WARNING_BORDER) {
            from_email_input.setBorder(HOVER_BORDER);
        }
    }//GEN-LAST:event_from_email_inputMouseEntered

    private void from_email_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_from_email_inputMouseExited
        if (from_email_input.getBorder() != WARNING_BORDER) {
            from_email_input.setBorder(INPUT_BORDER);
        }
    }//GEN-LAST:event_from_email_inputMouseExited

    private void from_email_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_from_email_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_from_email_inputActionPerformed

    private void em_subject_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_em_subject_inputMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_em_subject_inputMouseEntered

    private void em_subject_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_em_subject_inputMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_em_subject_inputMouseExited

    private void em_subject_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_em_subject_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_em_subject_inputActionPerformed

    private void em_email_to_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_em_email_to_inputMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_em_email_to_inputMouseEntered

    private void em_email_to_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_em_email_to_inputMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_em_email_to_inputMouseExited

    private void em_email_to_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_em_email_to_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_em_email_to_inputActionPerformed

    private void pd_email_to_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pd_email_to_inputMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_pd_email_to_inputMouseEntered

    private void pd_email_to_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pd_email_to_inputMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_pd_email_to_inputMouseExited

    private void pd_email_to_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pd_email_to_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pd_email_to_inputActionPerformed

    private void patient_name_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patient_name_inputMouseEntered
        patient_name_input.setBorder(HOVER_BORDER);
    }//GEN-LAST:event_patient_name_inputMouseEntered

    private void patient_name_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patient_name_inputMouseExited
        patient_name_input.setBorder(INPUT_BORDER);
    }//GEN-LAST:event_patient_name_inputMouseExited

    private void patient_name_inputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_patient_name_inputKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            addMedicine();
        }

//        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
//            medicine_list.grabFocus();
//            medicine_list.setSelectedIndex(0);
//
//        }
//        if (evt.getKeyCode() == KeyEvent.VK_UP) {
//
//            if (medicine_list.getSelectedIndex() == 0) {
//                patient_name_input.grabFocus();
//            } else {
//                medicine_list.grabFocus();
//            }
//
//        }
    }//GEN-LAST:event_patient_name_inputKeyPressed
    public void searchByName(String name) {
        Database database = Database.getInstance();
        ArrayList<String> patient_details = database.getLikePatient(name);

        String data[][] = new String[patient_details.size()][3];

        int i = 0;
        for (String m : patient_details) {
//                    lm.addElement(m);
            String str[] = m.split("/");
            data[i][0] = str[0];
            data[i][1] = str[1];
            data[i][2] = str[2];
            i++;
        }
        String column[] = {"Pno", "Name", "Date"};
        setTable(data, column);
    }

    public void setTable(String data[][], String[] column) {
        Color TABLE_BACKGROUND_COLOR = new Color(0xE9E9EE);
        Color TABLE_HEADER_BACKGROUND_COLOR = new Color(0x6b676b);
        Color TABLE_HEADER_FORGROUND_COLOR = new Color(0xFDFDFD);

        int row_height = 30;

        DefaultTableModel defaultTableModel = new DefaultTableModel(data, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        MyCustomRenderer customRenderer = new MyCustomRenderer();

        patient_table.setModel(defaultTableModel);
        int total_col = defaultTableModel.getColumnCount();
        for (int k = 0; k < total_col; k++) {
            TableColumn col = patient_table.getColumnModel().getColumn(k);

            col.setCellRenderer(customRenderer);
        }

        patient_table.setCursor(new Cursor(Cursor.HAND_CURSOR));
        patient_table.setRowHeight(row_height);
        patient_table.setBackground(TABLE_BACKGROUND_COLOR);
        patient_table.setIntercellSpacing(new Dimension(0, 5));
        patient_table.setShowGrid(false);
        patient_table.setRowMargin(5);
        patient_table.getTableHeader().setPreferredSize(new Dimension(100, 30));
        patient_table.getTableHeader().setBackground(TABLE_HEADER_BACKGROUND_COLOR);
        patient_table.getTableHeader().setForeground(TABLE_HEADER_FORGROUND_COLOR);
        patient_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        patient_table.getColumnModel().getColumn(0).setPreferredWidth(10);
        patient_table.getColumnModel().getColumn(1).setPreferredWidth(80);

    }
    private void add_patient_email_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_patient_email_btnMouseEntered
        add_patient_email_btn.setBorder(HOVER_BTN_BORDER);
    }//GEN-LAST:event_add_patient_email_btnMouseEntered

    private void add_patient_email_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_patient_email_btnMouseExited
        add_patient_email_btn.setBorder(DEFAULT_BTN_BORDER);
    }//GEN-LAST:event_add_patient_email_btnMouseExited

    private void add_patient_email_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_patient_email_btnActionPerformed
        addMedicine();
    }//GEN-LAST:event_add_patient_email_btnActionPerformed

    private void pd_subject_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pd_subject_inputMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_pd_subject_inputMouseEntered

    private void pd_subject_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pd_subject_inputMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_pd_subject_inputMouseExited

    private void pd_subject_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pd_subject_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pd_subject_inputActionPerformed

    private void pd_save_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pd_save_btnMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_pd_save_btnMouseEntered

    private void pd_save_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pd_save_btnMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_pd_save_btnMouseExited

    private void pd_save_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pd_save_btnActionPerformed
        savePDEmail();
    }//GEN-LAST:event_pd_save_btnActionPerformed
    public void savePDEmail() {
        try {

            String from = from_email_input.getText();
            String to = pd_email_to_input.getText();
            String subject = pd_subject_input.getText();
            String body = pd_body_input.getText();
            String template = selected_template.getText();
            if (template.length() == 0) {
                template = "None";
            }
            EmailInformation emailInformation = new EmailInformation();
            emailInformation.setSendFrom(from);
            emailInformation.setSendTo(to);
            emailInformation.setSubject(subject);
            emailInformation.setBody(body);
            emailInformation.setTemplate(template);

            Database database = Database.getInstance();
            database.insertEmail(emailInformation);
            resetEMPage();
            pd_status_label.setText("Email Saved Succesfully");
            pd_status_label.setForeground(SUCCESS_COLOR);
            System.out.println("Emial inserted successfully");
        } catch (Exception exp) {
            System.out.println("Unable to insert email");
        }
    }
    private void pd_send_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pd_send_btnMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_pd_send_btnMouseEntered

    private void pd_send_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pd_send_btnMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_pd_send_btnMouseExited

    private void pd_send_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pd_send_btnActionPerformed

        String to = pd_email_to_input.getText();
        String subject = pd_subject_input.getText();
        String message = pd_body_input.getText();

        try {

            if (pd_file_path != null) {
                File file = new File(pd_file_path);
                SendingEmailWithAttachment sendEmailWithAttatch = new SendingEmailWithAttachment(to, subject, message, file);
                sendEmailWithAttatch.usingCutomeUserDetails(this.HOME_USER);
                sendEmailWithAttatch.start();
            } else {
                SendingEmailWithoutAttachment sendEmailWithoutAttatch = new SendingEmailWithoutAttachment(to, subject, message);
                sendEmailWithoutAttatch.usingCutomeUserDetails(this.HOME_USER);
                sendEmailWithoutAttatch.start();
            }
            savePDEmail();
            pd_status_label.setText("Email send successfully");
            pd_status_label.setForeground(SUCCESS_COLOR);

        } catch (Exception exp) {
            exp.printStackTrace();
            System.out.println("Something went wrong");
        }
    }//GEN-LAST:event_pd_send_btnActionPerformed

    private void pd_attach_file_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pd_attach_file_btnMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_pd_attach_file_btnMouseEntered

    private void pd_attach_file_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pd_attach_file_btnMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_pd_attach_file_btnMouseExited

    private void pd_attach_file_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pd_attach_file_btnActionPerformed
        try {
            pd_file_chooser = new JFileChooser();
            pd_file_chooser.showOpenDialog(null);
            File file = pd_file_chooser.getSelectedFile();
            setPDPageFile(file);

        } catch (HeadlessException exp) {
        }

    }//GEN-LAST:event_pd_attach_file_btnActionPerformed

    private void custome_email_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_custome_email_labelMouseClicked
        showEmailTypePage("email_mode");
    }//GEN-LAST:event_custome_email_labelMouseClicked

    private void patient_details_email_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patient_details_email_labelMouseClicked
        showEmailTypePage("patient_mode");
    }//GEN-LAST:event_patient_details_email_labelMouseClicked

    private void add_email_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_email_labelMouseClicked
        showEmailTypePage("add_email");
    }//GEN-LAST:event_add_email_labelMouseClicked

    private void ad_subject_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ad_subject_inputMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ad_subject_inputMouseEntered

    private void ad_subject_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ad_subject_inputMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ad_subject_inputMouseExited

    private void ad_subject_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ad_subject_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ad_subject_inputActionPerformed

    private void ad_template_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ad_template_inputMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ad_template_inputMouseEntered

    private void ad_template_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ad_template_inputMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ad_template_inputMouseExited

    private void ad_template_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ad_template_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ad_template_inputActionPerformed

    private void ad_attach_file_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ad_attach_file_btnActionPerformed
        try {
            ad_file_chooser = new JFileChooser();
            ad_file_chooser.showOpenDialog(null);
            File file = ad_file_chooser.getSelectedFile();

            setADPageFile(file);
        } catch (HeadlessException exp) {
        }
    }//GEN-LAST:event_ad_attach_file_btnActionPerformed

    private void ad_save_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ad_save_btnActionPerformed
        //save template email

        String subject = ad_subject_input.getText();
        String body = ad_body_input.getText();
        String template = ad_template_input.getText();
        String file_path = null;
        if (ad_file_chooser != null) {
            File file = ad_file_chooser.getSelectedFile();

            if (file != null) {
                file_path = file.getAbsolutePath();
            }

        }

        if (template.length() == 0) {
            template = "None";
        }

        EmailInformation emailInformation = new EmailInformation();
        emailInformation.setSubject(subject);
        emailInformation.setBody(body);
        emailInformation.setTemplate(template);
        emailInformation.setAttachFilePath(file_path);

        Database database = Database.getInstance();
        if (ad_save_btn.getText().startsWith("Save")) {
            database.insertEmailTemplate(emailInformation);
            resetADPage();
            ad_status_label.setText("Template saved successfully");
            ad_status_label.setForeground(SUCCESS_COLOR);

        } else {
            if (TEMPLATE_ID_FOR_UPDATING != -1) {
                database.updateTemplate(TEMPLATE_ID_FOR_UPDATING, emailInformation);

                resetADPage();
                ad_status_label.setText("Template Upadted successfully");
                ad_status_label.setForeground(SUCCESS_COLOR);
                TEMPLATE_ID_FOR_UPDATING = -1;
            }
        }


    }//GEN-LAST:event_ad_save_btnActionPerformed

    private void template_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_template_inputMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_template_inputMouseEntered

    private void template_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_template_inputMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_template_inputMouseExited

    private void template_inputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_template_inputKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_template_inputKeyPressed

    private void add_template_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_template_btnMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_add_template_btnMouseEntered

    private void add_template_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_template_btnMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_add_template_btnMouseExited

    private void add_template_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_template_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_template_btnActionPerformed

    private void template_listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_template_listMouseClicked
        //if (evt.getClickCount() == 1) {
        copyTemplateToPage();
        // evt.consume();
        //}
    }//GEN-LAST:event_template_listMouseClicked
    public void copyTemplateToPage() {
        String template_name = template_list.getSelectedValue().trim();
        System.out.println(template_name);
        selected_template.setText(template_name);
        Database database = Database.getInstance();
        if (isEmailModePage()) {
            EmailInformation emailInformation = database.getEmail(template_name);
            if (emailInformation != null) {
                em_subject_input.setText(emailInformation.getSubject());
                em_body_input.setText(emailInformation.getBody());
                String file_path = emailInformation.getAttachFilePath();
                if (file_path != null) {
                    setEMPageFile(new File(file_path));

                } else {
                    removeEMPageSelectedFile();
                }

            } else {
                System.out.println("Email information not found");
            }

        } else if (isPatientModePage()) {
            EmailInformation emailInformation = database.getEmail(template_name);
            if (emailInformation != null) {
                pd_subject_input.setText(emailInformation.getSubject());
                pd_body_input.setText(emailInformation.getBody());
                String file_path = emailInformation.getAttachFilePath();
                System.out.println(file_path);
                if (file_path != null) {
                    setPDPageFile(new File(file_path));
                } else {
                    removePDPageSelectedFile();
                }
            } else {
                System.out.println("Email information not found");
            }
        }
    }


    private void template_listKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_template_listKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_template_listKeyPressed

    private void delete_template_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delete_template_btnMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_delete_template_btnMouseEntered

    private void delete_template_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_delete_template_btnMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_delete_template_btnMouseExited

    private void delete_template_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_template_btnActionPerformed
        String template_name = selected_template.getText();
        Database database = Database.getInstance();
        try {

            if (database.removeTemplate(template_name)) {

                tm_status_label.setText("Template removed succesfully");
                tm_status_label.setForeground(SUCCESS_COLOR);
                template_input.grabFocus();
                setTemplateOnList();
                selected_template.setText("");
                template_list.remove(template_list.getSelectedIndex());
                template_list.revalidate();
                template_list.repaint();

                revalidate();
                repaint();
            } else {

                tm_status_label.setText("Unable to remove template");
                tm_status_label.setForeground(WARNING_COLOR);

            }
        } catch (ArrayIndexOutOfBoundsException exp) {

        }
    }//GEN-LAST:event_delete_template_btnActionPerformed
    public void setTemplateOnList() {
        Database database = Database.getInstance();
        if (template_input.getText().length() != 0) {
            String text = template_input.getText();
            ArrayList<String> templates = database.getLikeTemplate(text);
            DefaultListModel lm = new DefaultListModel();
            for (String m : templates) {
                lm.addElement(m);
            }
            template_list.setModel(lm);
        }
    }
    private void selected_templateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selected_templateMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_selected_templateMouseEntered

    private void selected_templateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selected_templateMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_selected_templateMouseExited

    private void selected_templateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_selected_templateKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_selected_templateKeyPressed

    int TEMPLATE_ID_FOR_UPDATING = -1;
    private void update_template_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_template_btnActionPerformed
        //copy the template into add email page
        //update the all the details based on the template id
        try {
            if (isAddEmailModePage()) {
                String template_name = selected_template.getText();

                ad_save_btn.setText("Update");

                Database db = Database.getInstance();
                EmailInformation emailInformation = db.getEmail(template_name);
                ad_template_input.setText(emailInformation.getTemplate());
                ad_subject_input.setText(emailInformation.getSubject());
                ad_body_input.setText(emailInformation.getBody());
                ad_file_path = emailInformation.getAttachFilePath();
                if (ad_file_path != null) {
                    setADPageFile(new File(ad_file_path));
                }

                TEMPLATE_ID_FOR_UPDATING = emailInformation.getTemplateId();
                System.out.println(TEMPLATE_ID_FOR_UPDATING);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
//       updateTemplate(int template_id , EmailInformation emailInformation);
    }//GEN-LAST:event_update_template_btnActionPerformed

    private void pd_remove_attach_file_icon_panelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pd_remove_attach_file_icon_panelMouseClicked
        pd_selected_file_name.setText("");
        removeIcon();

    }//GEN-LAST:event_pd_remove_attach_file_icon_panelMouseClicked

    private void pd_remove_attach_file_icon_panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pd_remove_attach_file_icon_panelMouseEntered
        // setDownArrowIconForReportDropdown();
    }//GEN-LAST:event_pd_remove_attach_file_icon_panelMouseEntered

    private void pd_remove_attach_file_icon_panelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pd_remove_attach_file_icon_panelMouseExited

    }//GEN-LAST:event_pd_remove_attach_file_icon_panelMouseExited

    private void em_remove_attach_file_icon_panelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_em_remove_attach_file_icon_panelMouseClicked
        em_selected_file_name.setText("");
        em_remove_attach_file_icon_panel.removeAll();
        revalidate();
        repaint();
    }//GEN-LAST:event_em_remove_attach_file_icon_panelMouseClicked

    private void em_remove_attach_file_icon_panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_em_remove_attach_file_icon_panelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_em_remove_attach_file_icon_panelMouseEntered

    private void em_remove_attach_file_icon_panelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_em_remove_attach_file_icon_panelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_em_remove_attach_file_icon_panelMouseExited

    private void ad_remove_attach_file_icon_panelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ad_remove_attach_file_icon_panelMouseClicked
        ad_selected_file_name.setText("");
        ad_remove_attach_file_icon_panel.removeAll();
        revalidate();
        repaint();
    }//GEN-LAST:event_ad_remove_attach_file_icon_panelMouseClicked

    private void ad_remove_attach_file_icon_panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ad_remove_attach_file_icon_panelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ad_remove_attach_file_icon_panelMouseEntered

    private void ad_remove_attach_file_icon_panelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ad_remove_attach_file_icon_panelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ad_remove_attach_file_icon_panelMouseExited

    private void patient_name_inputCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_patient_name_inputCaretUpdate
        if (evt.getMark() != 0) {
            String name = patient_name_input.getText().trim();
            searchByName(name);
        }
    }//GEN-LAST:event_patient_name_inputCaretUpdate

    private void ad_report_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ad_report_refreshMouseClicked
        resetADPage();
    }//GEN-LAST:event_ad_report_refreshMouseClicked

    private void pd_report_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pd_report_refreshMouseClicked
        resetPDPage();
    }//GEN-LAST:event_pd_report_refreshMouseClicked

    private void em_report_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_em_report_refreshMouseClicked
        resetEMPage();
    }//GEN-LAST:event_em_report_refreshMouseClicked

    private void template_inputCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_template_inputCaretUpdate
        setTemplateOnList();
    }//GEN-LAST:event_template_inputCaretUpdate

    private void tm_report_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tm_report_refreshMouseClicked
        resetTemplatePage();
    }//GEN-LAST:event_tm_report_refreshMouseClicked

    private void up_email_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_up_email_inputMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_up_email_inputMouseEntered

    private void up_email_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_up_email_inputMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_up_email_inputMouseExited

    private void up_email_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_up_email_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_up_email_inputActionPerformed

    private void ad_remove_attach_file_icon_panel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ad_remove_attach_file_icon_panel1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ad_remove_attach_file_icon_panel1MouseClicked

    private void ad_remove_attach_file_icon_panel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ad_remove_attach_file_icon_panel1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_ad_remove_attach_file_icon_panel1MouseEntered

    private void ad_remove_attach_file_icon_panel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ad_remove_attach_file_icon_panel1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_ad_remove_attach_file_icon_panel1MouseExited

    private void up_report_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_up_report_refreshMouseClicked
        resetEmailUpdatePage();
    }//GEN-LAST:event_up_report_refreshMouseClicked

    private void up_cancle_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_up_cancle_btnActionPerformed
        showEmailTypePage("email_mode");
        resetEmailUpdatePage();
    }//GEN-LAST:event_up_cancle_btnActionPerformed

    private void up_submit_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_up_submit_btnActionPerformed

        String email = up_email_input.getText();
        String email_password = String.valueOf(up_password_input.getPassword());
        if (email.length() == 0 || email_password.length() == 0) {
            up_status_label.setText("Enter valid details");
            up_status_label.setForeground(WARNING_COLOR);
        } else {
            User user = this.HOME_USER;
            if (user != null) {

                user.setEmail(email);
                user.setEmailPassword(email_password);

                VerifyUserEmailSession verification = new VerifyUserEmailSession(user);
                Database db = Database.getInstance();

//            verification.isValidUserEmail()  //NOTE:this line should be add in final code in the below if condition
                if (true) {

                    User is_user_verified = db.getEmailSecurityCodes(user);
                    user.setEmailVerificationStatus(true);
                    boolean result;
                    if (is_user_verified != null && is_user_verified.getEmailVerificationStatus() == true) {
                        result = db.updateUserEmailInforamtion(user);
                    } else {
                        result = db.insertUserEmailInforamtion(user);
                    }
                    if (result) {

                        resetEmailUpdatePage();

                        up_status_label.setText("Succesfully email codes saved");
                        up_status_label.setForeground(SUCCESS_COLOR);
                    } else {
                        up_status_label.setText("Unable to save record");
                        up_status_label.setForeground(WARNING_COLOR);
                    }
                } else {
                    up_status_label.setText("Enter valid details");
                    up_status_label.setForeground(WARNING_COLOR);

                }
            } else {
                System.out.println("No User loged in ");
            }
        }

    }//GEN-LAST:event_up_submit_btnActionPerformed

    private void up_view_code_checkboxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_up_view_code_checkboxItemStateChanged
        //if(evt.)

        if (up_view_code_checkbox.isSelected()) {
            up_password_input.setEchoChar((char) 0);
        } else {
            up_password_input.setEchoChar('*');
        }
    }//GEN-LAST:event_up_view_code_checkboxItemStateChanged
    public void resetTemplatePage() {
        selected_template.setText("");
        template_input.setText("");
//        template_list.getModel();
//        template_list.removeAll();

        DefaultListModel lm = new DefaultListModel();
        template_list.setModel(lm);

        tm_status_label.setText("");
        revalidate();
        repaint();

    }

    public void resetEmailUpdatePage() {
        up_email_input.setText("");
        up_password_input.setText("");
        up_status_label.setText("");

        if (this.HOME_USER != null && this.HOME_USER.getEmailVerificationStatus()) {
            email_verified_status.setText("");
        }
        revalidate();
        repaint();
    }

    public void addMouseListerOnTabel() {

        patient_table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = patient_table.getSelectedRow();

                String pno = (String) patient_table.getModel().getValueAt(index, 0);
                String name = (String) patient_table.getModel().getValueAt(index, 1);
                String date = (String) patient_table.getModel().getValueAt(index, 2);

                try {
                    int patient_number = Integer.parseInt(pno);
                    Database database = Database.getInstance();
                    String patient_email = database.getEmail(patient_number);
                    if (patient_email == null) {
                        patient_email = "No Email Found";
                    }
                    pd_email_to_input.setText(patient_email);
                } catch (NumberFormatException exp) {
                    System.out.println("patient number not in number format");
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                int index = patient_table.getSelectedRow();
                //  patient_table.getColumnModel().se;
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ad_attach_file_btn;
    private javax.swing.JTextArea ad_body_input;
    private javax.swing.JPanel ad_remove_attach_file_icon_panel;
    private javax.swing.JPanel ad_remove_attach_file_icon_panel1;
    private javax.swing.JPanel ad_report_refresh;
    private javax.swing.JButton ad_save_btn;
    private javax.swing.JLabel ad_selected_file_name;
    private javax.swing.JLabel ad_status_label;
    private javax.swing.JTextField ad_subject_input;
    private javax.swing.JTextField ad_template_input;
    private javax.swing.JLabel add_email_label;
    private javax.swing.JPanel add_email_panel;
    private javax.swing.JButton add_patient_email_btn;
    private javax.swing.JButton add_template_btn;
    private javax.swing.JPanel composing_email_panel;
    private javax.swing.JPanel content_panel;
    private javax.swing.JLabel custome_email_label;
    private javax.swing.JButton delete_template_btn;
    private javax.swing.JPanel down_pannel_for_margin;
    private javax.swing.JButton em_attach_file_btn;
    private javax.swing.JTextArea em_body_input;
    private javax.swing.JTextField em_email_to_input;
    private javax.swing.JPanel em_remove_attach_file_icon_panel;
    private javax.swing.JPanel em_report_refresh;
    private javax.swing.JButton em_save_btn;
    private javax.swing.JLabel em_selected_file_name;
    private javax.swing.JButton em_send_btn;
    private javax.swing.JLabel em_status_label;
    private javax.swing.JTextField em_subject_input;
    private javax.swing.JPanel email_mode;
    private javax.swing.JPanel email_panel_head;
    private javax.swing.JPanel email_sending_panel;
    private javax.swing.JLabel email_verified_status;
    private javax.swing.JTextField from_email_input;
    private javax.swing.JLabel from_email_label;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel left_pannel_for_margin;
    private javax.swing.JPanel main_panel;
    private javax.swing.JPanel modes;
    private javax.swing.JLabel patient_details_email_label;
    private javax.swing.JPanel patient_details_mode;
    private javax.swing.JPanel patient_list_panel;
    private javax.swing.JTextField patient_name_input;
    private javax.swing.JLabel patient_panel_header_title2;
    private javax.swing.JTable patient_table;
    private javax.swing.JScrollPane patient_table_scroll;
    private javax.swing.JButton pd_attach_file_btn;
    private javax.swing.JTextArea pd_body_input;
    private javax.swing.JTextField pd_email_to_input;
    private javax.swing.JPanel pd_remove_attach_file_icon_panel;
    private javax.swing.JPanel pd_report_refresh;
    private javax.swing.JButton pd_save_btn;
    private javax.swing.JLabel pd_selected_file_name;
    private javax.swing.JButton pd_send_btn;
    private javax.swing.JLabel pd_status_label;
    private javax.swing.JTextField pd_subject_input;
    private javax.swing.JPanel right_pannel_for_margin;
    private javax.swing.JTextField selected_template;
    private javax.swing.JTextField template_input;
    private javax.swing.JList<String> template_list;
    private javax.swing.JPanel template_list_panel;
    private javax.swing.JPanel tm_report_refresh;
    private javax.swing.JLabel tm_status_label;
    private javax.swing.JButton up_cancle_btn;
    private javax.swing.JTextField up_email_input;
    private javax.swing.JPasswordField up_password_input;
    private javax.swing.JPanel up_report_refresh;
    private javax.swing.JLabel up_status_label;
    private javax.swing.JButton up_submit_btn;
    private javax.swing.JCheckBox up_view_code_checkbox;
    private javax.swing.JPanel update_email;
    private javax.swing.JButton update_from_email_btn;
    private javax.swing.JButton update_template_btn;
    private javax.swing.JPanel upper_pannel_for_margin;
    // End of variables declaration//GEN-END:variables

    private void addMedicine() {
    }
}
