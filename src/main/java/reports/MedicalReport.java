package reports;

import database.Database;
import hospitalmanagement.Home;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import database.Database;
import myutil.MultithredingReports;
import myutil.PatientDetails;
import myutil.SetImageIcon;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author haris
 */
class ValidInputException extends Exception {

    ValidInputException() {

    }

    ValidInputException(String msg) {
        super(msg);
    }

    @Override
    public String toString() {
        return "Inputs are not in in correct format";
    }
}

public class MedicalReport extends javax.swing.JPanel {

    final LineBorder HOVER_BTN_BORDER = new LineBorder(new Color(0x7C7CF1), 2, true);
    final LineBorder DEFAULT_BTN_BORDER = new LineBorder(Color.WHITE, 1, true);
    final LineBorder DEFAULT_BORDER = new LineBorder(Color.blue, 1, true);
    final LineBorder INPUT_BORDER = new LineBorder(new Color(0x7c7cf1), 1, true);
    final LineBorder HOVER_BORDER = new LineBorder(new Color(0x7C7CF1), 2, true);
    final LineBorder WARNING_BORDER = new LineBorder(new Color(0xff3333), 2, true);

    final Color WARNING_COLOR = new Color(16724787);
    final Color SUCCESS_COLOR = new Color(0, 153, 0);
    final Color CLICKED_LABEL_COLOR = new Color(0, 0, 204);
    final Color REPORT_LABEL_COLOR = new Color(0, 0, 102);

    PatientDetails MEDICAL_REPORT_PATIENT_DETAILS = null;
    MultithredingReports REPORTS_THREAD = new MultithredingReports();
    Home home;

    boolean report_showing_format_1 = true;
    public MedicalReport(Home home, PatientDetails patientDetails) {
        initComponents();
        REPORTS_THREAD.start();
        this.home = home;

        addAllNavigationButtons();
        setDoctorNamesOnDoctorInputField();
        date_report_input.setDate(home.getCurrentDate());
        shortKeyForRefreshingPage();
        addPopupMenuForMedicineReport();

    }

    public void addPopupMenuForMedicineReport() {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem m1 = new JMenuItem("format-1");
        JMenuItem m2 = new JMenuItem("format-2");
        JMenuItem m3 = new JMenuItem("other");

        popupMenu.add(m1);
        popupMenu.add(m2);
        popupMenu.add(m3);
        m1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                report_showing_format_1 = true;
               if(name_report_input.getText().length()!=0)
                  setReportPrint();
            }

        });
        
        m2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Format -2 ");
                report_showing_format_1 = false;
                if(name_report_input.getText().length()!=0)
                  setReportPrint();
            }

        });

        home.getMedicalReportsDropdownLabel().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popupMenu.show(home.getMenuPanel(), e.getX() + 80, e.getY() + 240);

                }
            }
        });
    }

    public void addAllNavigationButtons() {

        String refresh_page_icon = "./images/refresh3.png";
        String back_page_icon = "./images/left_arrow.png";

        report_refresh.add(new SetImageIcon(new ImageIcon(refresh_page_icon), 30, 30), BorderLayout.CENTER);
        report_back.add(new SetImageIcon(new ImageIcon(back_page_icon), 25, 25), BorderLayout.CENTER);
    }

    public void shortKeyForRefreshingPage() {
        KeyStroke clt_r = KeyStroke.getKeyStroke(KeyEvent.VK_R, KeyEvent.CTRL_DOWN_MASK);
        Action refresh = new AbstractAction("refresh") {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetReportPage();
            }
        };
        String k = "refresh";
        refresh.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        medical_report_form.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(clt_r, k);
        medical_report_form.getActionMap().put(k, refresh);
    }

    public JTextField getMedicalReportNameInput() {
        return name_report_input;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        medical_report_panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        medical_report_form = new javax.swing.JPanel();
        age_label = new javax.swing.JLabel();
        age_report_input = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        name_report_input = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        date_report_input = new com.toedter.calendar.JDateChooser();
        report_status = new javax.swing.JLabel();
        print = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        report_input = new javax.swing.JTextField();
        add_report_btn = new javax.swing.JButton();
        report_list_panel = new javax.swing.JPanel();
        doctor_list = new javax.swing.JList<>();
        generate_report = new javax.swing.JButton();
        report_refresh = new javax.swing.JPanel();
        report_back = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        selected_doctor = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        patient_designation = new javax.swing.JComboBox<>();
        report_show_panel = new javax.swing.JPanel();
        age = new javax.swing.JLabel();
        gender = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        medical_report_panel.setBackground(new java.awt.Color(255, 102, 102));
        medical_report_panel.setPreferredSize(new java.awt.Dimension(900, 700));
        medical_report_panel.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(933, 486));
        jPanel1.setLayout(new java.awt.BorderLayout());

        medical_report_form.setBackground(new java.awt.Color(255, 255, 204));
        medical_report_form.setPreferredSize(new java.awt.Dimension(250, 800));

        age_label.setText("Age :-");

        age_report_input.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        age_report_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 1, true));
        age_report_input.setPreferredSize(new java.awt.Dimension(64, 30));
        age_report_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                age_report_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                age_report_inputMouseExited(evt);
            }
        });
        age_report_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                age_report_inputActionPerformed(evt);
            }
        });
        age_report_input.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                age_report_inputKeyPressed(evt);
            }
        });

        jLabel14.setText("Name:-");

        name_report_input.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        name_report_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 1, true));
        name_report_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                name_report_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                name_report_inputMouseExited(evt);
            }
        });
        name_report_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_report_inputActionPerformed(evt);
            }
        });

        jLabel13.setText("Date:-");

        report_status.setBackground(new java.awt.Color(0, 204, 0));

        print.setBackground(new java.awt.Color(102, 255, 102));
        print.setText("Print");
        print.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        print.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        print.setFocusable(false);
        print.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                printMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                printMouseExited(evt);
            }
        });
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        jLabel5.setText("Enter Doctor Name :");

        report_input.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        report_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 1, true));
        report_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                report_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                report_inputMouseExited(evt);
            }
        });
        report_input.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                report_inputKeyPressed(evt);
            }
        });

        add_report_btn.setBackground(new java.awt.Color(0, 51, 255));
        add_report_btn.setForeground(new java.awt.Color(255, 255, 255));
        add_report_btn.setText("Add");
        add_report_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        add_report_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add_report_btn.setFocusPainted(false);
        add_report_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                add_report_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                add_report_btnMouseExited(evt);
            }
        });
        add_report_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_report_btnActionPerformed(evt);
            }
        });

        report_list_panel.setPreferredSize(new java.awt.Dimension(0, 200));
        report_list_panel.setLayout(new java.awt.CardLayout());

        doctor_list.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        doctor_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                doctor_listMouseClicked(evt);
            }
        });
        doctor_list.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                doctor_listKeyPressed(evt);
            }
        });
        report_list_panel.add(doctor_list, "card2");

        generate_report.setBackground(new java.awt.Color(102, 255, 102));
        generate_report.setText("Report");
        generate_report.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        generate_report.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        generate_report.setFocusable(false);
        generate_report.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                generate_reportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                generate_reportMouseExited(evt);
            }
        });
        generate_report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generate_reportActionPerformed(evt);
            }
        });

        report_refresh.setBackground(new java.awt.Color(251, 252, 224));
        report_refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        report_refresh.setPreferredSize(new java.awt.Dimension(29, 29));
        report_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                report_refreshMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                report_refreshMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                report_refreshMouseReleased(evt);
            }
        });
        report_refresh.setLayout(new java.awt.BorderLayout());

        report_back.setBackground(new java.awt.Color(251, 252, 224));
        report_back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        report_back.setPreferredSize(new java.awt.Dimension(25, 25));
        report_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                report_backMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                report_backMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                report_backMouseReleased(evt);
            }
        });
        report_back.setLayout(new java.awt.BorderLayout());

        selected_doctor.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        selected_doctor.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 204), 1, true));
        selected_doctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selected_doctorActionPerformed(evt);
            }
        });

        jLabel1.setText("To Dr.");

        patient_designation.setBackground(new java.awt.Color(255, 255, 204));
        patient_designation.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mr.", "Mrs.", "Miss.", "Mast." }));
        patient_designation.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 255), 0, true));
        patient_designation.setFocusable(false);

        javax.swing.GroupLayout medical_report_formLayout = new javax.swing.GroupLayout(medical_report_form);
        medical_report_form.setLayout(medical_report_formLayout);
        medical_report_formLayout.setHorizontalGroup(
            medical_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medical_report_formLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(medical_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(medical_report_formLayout.createSequentialGroup()
                        .addGroup(medical_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(medical_report_formLayout.createSequentialGroup()
                                .addGroup(medical_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(patient_designation, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(name_report_input, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(medical_report_formLayout.createSequentialGroup()
                                .addGroup(medical_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(age_label, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(14, 14, 14)
                                .addGroup(medical_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(date_report_input, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(age_report_input, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(selected_doctor, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(report_list_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, medical_report_formLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(medical_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, medical_report_formLayout.createSequentialGroup()
                                .addGroup(medical_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(report_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, medical_report_formLayout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(23, 23, 23))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, medical_report_formLayout.createSequentialGroup()
                                .addComponent(report_input, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(add_report_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24))))
                    .addGroup(medical_report_formLayout.createSequentialGroup()
                        .addGroup(medical_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(medical_report_formLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, medical_report_formLayout.createSequentialGroup()
                        .addGroup(medical_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(medical_report_formLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(generate_report, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(medical_report_formLayout.createSequentialGroup()
                                .addComponent(report_back, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(report_status, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(47, 47, 47))))
        );
        medical_report_formLayout.setVerticalGroup(
            medical_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(medical_report_formLayout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(medical_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(name_report_input)
                    .addComponent(patient_designation, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(medical_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(age_report_input, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(age_label, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(medical_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date_report_input, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(medical_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(report_input, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_report_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(report_list_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selected_doctor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(medical_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(generate_report, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(medical_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(report_status, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(report_refresh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(report_back, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(235, Short.MAX_VALUE))
        );

        jPanel1.add(medical_report_form, java.awt.BorderLayout.WEST);

        report_show_panel.setPreferredSize(new java.awt.Dimension(500, 800));

        javax.swing.GroupLayout report_show_panelLayout = new javax.swing.GroupLayout(report_show_panel);
        report_show_panel.setLayout(report_show_panelLayout);
        report_show_panelLayout.setHorizontalGroup(
            report_show_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(report_show_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(age, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(gender, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        report_show_panelLayout.setVerticalGroup(
            report_show_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(report_show_panelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(report_show_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(age, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(gender, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addContainerGap(646, Short.MAX_VALUE))
        );

        jPanel1.add(report_show_panel, java.awt.BorderLayout.CENTER);

        medical_report_panel.add(jPanel1, java.awt.BorderLayout.CENTER);

        add(medical_report_panel, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    public void setDoctorNamesOnDoctorInputField() {
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
                String text = report_input.getText();
                ArrayList<String> report = (Database.getInstance()).getAllDoctorNames(text);
                DefaultListModel lm = new DefaultListModel();
                for (String m : report) {
                    lm.addElement(m);
                }
                doctor_list.setModel(lm);

                System.out.println("This is run");
            }
        };
        report_input.getDocument().addDocumentListener(dl);
        report_list_panel.add(new JScrollPane(doctor_list));

    }

    private void age_report_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_age_report_inputMouseEntered
        if (age_report_input.getBorder() != WARNING_BORDER) {
            age_report_input.setBorder(HOVER_BORDER);
        }
    }//GEN-LAST:event_age_report_inputMouseEntered

    private void age_report_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_age_report_inputMouseExited
        if (age_report_input.getBorder() != WARNING_BORDER) {
            age_report_input.setBorder(INPUT_BORDER);
        }
    }//GEN-LAST:event_age_report_inputMouseExited

    private void age_report_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_age_report_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_age_report_inputActionPerformed

    private void age_report_inputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_age_report_inputKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            searchReport();
        }
    }//GEN-LAST:event_age_report_inputKeyPressed
    public void searchReport() {
        //Search report        
    }
    private void name_report_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name_report_inputMouseEntered
        if (name_report_input.getBorder() != WARNING_BORDER) {
            name_report_input.setBorder(HOVER_BORDER);
        }
    }//GEN-LAST:event_name_report_inputMouseEntered

    private void name_report_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name_report_inputMouseExited
        if (name_report_input.getBorder() != WARNING_BORDER) {
            name_report_input.setBorder(INPUT_BORDER);
        }
    }//GEN-LAST:event_name_report_inputMouseExited

    private void name_report_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_report_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_name_report_inputActionPerformed

    private void printMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printMouseEntered
        print.setBorder(HOVER_BORDER);
    }//GEN-LAST:event_printMouseEntered

    private void printMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printMouseExited
        print.setBorder(DEFAULT_BTN_BORDER);
    }//GEN-LAST:event_printMouseExited

    JasperPrint medical_jasper_print = null;
    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        try {
            if (medical_jasper_print != null) {
                JasperPrintManager.printReport(medical_jasper_print, false);
                resetReportPage();
                report_status.setText("Report printed Succesfully");
                report_status.setForeground(SUCCESS_COLOR);
                medical_jasper_print = null;
            } else {
                setReportPrint();
                printActionPerformed(evt);
                // throw new JRException("No report avilable");
            }

        } catch (JRException ex) {
            report_status.setText("Report not printed");
            report_status.setForeground(WARNING_COLOR);
        }
    }//GEN-LAST:event_printActionPerformed

    private void report_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_inputMouseEntered
        report_input.setBorder(HOVER_BORDER);
    }//GEN-LAST:event_report_inputMouseEntered

    private void report_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_inputMouseExited
        report_input.setBorder(INPUT_BORDER);
    }//GEN-LAST:event_report_inputMouseExited

    private void report_inputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_report_inputKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            addReport();
            report_input.setText("");
            report_input.grabFocus();
        }

        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            doctor_list.grabFocus();
            doctor_list.setSelectedIndex(0);

        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {

            if (doctor_list.getSelectedIndex() == 0) {
                report_input.grabFocus();
            } else {
                doctor_list.grabFocus();
            }

        }
    }//GEN-LAST:event_report_inputKeyPressed

    private void add_report_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_report_btnMouseEntered
        add_report_btn.setBorder(HOVER_BTN_BORDER);
    }//GEN-LAST:event_add_report_btnMouseEntered

    private void add_report_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_report_btnMouseExited
        add_report_btn.setBorder(DEFAULT_BTN_BORDER);
    }//GEN-LAST:event_add_report_btnMouseExited

    private void add_report_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_report_btnActionPerformed
        addReport();
    }//GEN-LAST:event_add_report_btnActionPerformed

    private void doctor_listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doctor_listMouseClicked

        if (evt.getClickCount() == 1) {
            addReport();
        }
        report_input.setText("");
        report_input.grabFocus();
        //medicine_input.setText(medicine_list.getSelectedValue());
    }//GEN-LAST:event_doctor_listMouseClicked

    private void doctor_listKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_doctor_listKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            addReport();
            report_input.setText("");
            report_input.grabFocus();
        }

        if (evt.getKeyCode() == KeyEvent.VK_UP) {

            if (doctor_list.getSelectedIndex() == 0) {
                report_input.grabFocus();
            } else {
                doctor_list.grabFocus();
            }

        }
    }//GEN-LAST:event_doctor_listKeyPressed

    public void addReport() {
        String str = doctor_list.getSelectedValue();

        if (str == null) {
            String report_name = report_input.getText();
            Database db = Database.getInstance();
            db.insertDoctorName(report_name);
            str = report_name;
        }

        selected_doctor.setText(str);

        revalidate();
        repaint();

    }
    
    public void setReportPrint() {
        report_show_panel.removeAll();
        report_show_panel.revalidate();
        report_show_panel.repaint();
        try {

            HashMap a = new HashMap();
            String doctor_name = selected_doctor.getText();
            int id = (Database.getInstance()).getDoctorID(doctor_name);

            String patient_name = name_report_input.getText();
            String patient_age = age_report_input.getText();
            String designation = patient_designation.getSelectedItem().toString();
            System.out.println(designation);
            if (doctor_name == null || id == -1 || patient_name == null || doctor_name.length() == 0) {
                throw new ValidInputException();
            }

            System.out.println(id);
             System.out.println(doctor_name);
            a.put("dname", doctor_name);
            a.put("date", date_report_input.getDate());
            a.put("dno", id);
            a.put("name", patient_name);
            a.put("age", patient_age);
            a.put("designation", designation);

          

            Connection con = REPORTS_THREAD.getConnection();
            if (con != null) {
                
                JasperReport jr;
                if(report_showing_format_1 == true){
                     jr = REPORTS_THREAD.getCompliedMedicalReportFormat2();
                }
                else{
                    jr = REPORTS_THREAD.getCompliedMedicalReportFormat1();
                }
                
                if (jr != null) {
                    medical_jasper_print = JasperFillManager.fillReport(jr, a, con);
                    JRViewer v = new JRViewer(medical_jasper_print);
                    report_show_panel.setLayout(new BorderLayout());
                    report_show_panel.add(v);
                } else {
                    System.out.println("jr is null");
                    report_status.setText("No report is availalble");
                    report_status.setForeground(Color.red);
                }

            }
        } catch (NumberFormatException | JRException ex) {
            System.out.println(ex.getMessage());
            report_status.setText("No report is availalble ");
            report_status.setForeground(Color.red);
        } catch (ValidInputException exp) {
            report_status.setText("Enter valid inputs");
            report_status.setForeground(Color.red);
        }
        report_show_panel.revalidate();
        report_show_panel.repaint();
    }

    public void setReportsIntoDatabase() {

    }

    public void resetReportPage() {
        name_report_input.setText("");
        age_report_input.setText("");
        date_report_input.setDate(home.getCurrentDate());
        doctor_list.clearSelection();
        selected_doctor.setText("");

        report_input.setText("");
        report_status.setText("");

        report_show_panel.removeAll();
        report_show_panel.revalidate();
        report_show_panel.repaint();
    }
    private void generate_reportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generate_reportMouseEntered
        generate_report.setBorder(HOVER_BTN_BORDER);
    }//GEN-LAST:event_generate_reportMouseEntered

    private void generate_reportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generate_reportMouseExited
        generate_report.setBorder(DEFAULT_BTN_BORDER);
    }//GEN-LAST:event_generate_reportMouseExited

    private void generate_reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generate_reportActionPerformed

        // setReportsIntoDatabase();
        setReportPrint();

    }//GEN-LAST:event_generate_reportActionPerformed

    private void report_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_refreshMouseClicked
        resetReportPage();
//        setTestReportPatientDetailsObject(null);
    }//GEN-LAST:event_report_refreshMouseClicked

    private void report_refreshMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_refreshMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_report_refreshMouseExited

    private void report_refreshMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_refreshMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_report_refreshMouseReleased

    private void report_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_backMouseClicked
        home.showReportOnWindow("Test");
    }//GEN-LAST:event_report_backMouseClicked

    private void report_backMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_backMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_report_backMouseExited

    private void report_backMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_backMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_report_backMouseReleased

    private void selected_doctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selected_doctorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_selected_doctorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_report_btn;
    private javax.swing.JLabel age;
    private javax.swing.JLabel age_label;
    private javax.swing.JTextField age_report_input;
    private com.toedter.calendar.JDateChooser date_report_input;
    private javax.swing.JList<String> doctor_list;
    private javax.swing.JLabel gender;
    private javax.swing.JButton generate_report;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel medical_report_form;
    private javax.swing.JPanel medical_report_panel;
    private javax.swing.JTextField name_report_input;
    private javax.swing.JComboBox<String> patient_designation;
    private javax.swing.JButton print;
    private javax.swing.JPanel report_back;
    private javax.swing.JTextField report_input;
    private javax.swing.JPanel report_list_panel;
    private javax.swing.JPanel report_refresh;
    private javax.swing.JPanel report_show_panel;
    private javax.swing.JLabel report_status;
    private javax.swing.JTextField selected_doctor;
    // End of variables declaration//GEN-END:variables
}
