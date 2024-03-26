package hospitalmanagement;

import java.sql.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import myutil.Database;
import myutil.MultithredingReports;
import myutil.PatientDetails;
import myutil.ReportInfomartion;
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
public class MedicalCertificate1 extends javax.swing.JPanel {

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

    PatientDetails TEST_REPORT_PATIENT_DETAILS = null;
    @SuppressWarnings("empty-statement")
    MultithredingReports REPORTS_THREAD = new MultithredingReports();

    Home home;

    public MedicalCertificate1(Home home, PatientDetails patientDetails) {
        initComponents();

        this.home = home;

        setTestReportPatientDetailsObject(patientDetails);
        setReportOnReportInputField();
        addAllNavigationButtons();

        REPORTS_THREAD.start();
        shortKeyForRefreshingPage();

    }

    public JTextField getName_report_inputs() {
        return name_report_inputs;
    }

    public void addAllNavigationButtons() {

        String refresh_page_icon = "./images/refresh3.png";
        String back_page_icon = "./images/left_arrow.png";
        String next_page_icon = "./images/right_arrow.png";

        report_refresh.add(new SetImageIcon(new ImageIcon(refresh_page_icon), 30, 30), BorderLayout.CENTER);
        report_back.add(new SetImageIcon(new ImageIcon(back_page_icon), 25, 25), BorderLayout.CENTER);
        report_next.add(new SetImageIcon(new ImageIcon(next_page_icon), 25, 25), BorderLayout.CENTER);
    }

    public void setTestReportPatientDetailsObject(PatientDetails patientDetails) {
        if (patientDetails != null) {
            TEST_REPORT_PATIENT_DETAILS = patientDetails;
            setTestReportDetails();
        }
    }

    public PatientDetails getTestReportPatientDetailsObject() {
        return this.TEST_REPORT_PATIENT_DETAILS;
    }

    public void setTestReportDetails() {
        try {
//            pno_report_input.setText(Integer.toString(TEST_REPORT_PATIENT_DETAILS.getPid()));
            ReportInfomartion test_report = (Database.getInstance()).getAllTestReports(TEST_REPORT_PATIENT_DETAILS.getPid());
           

            if (test_report != null) {
                 ArrayList<String> tests = test_report.getReportNames();
                lm.removeAllElements();
                for (String t : tests) {
                    lm.addElement(t);
                }
//                selected_report_list.setModel(lm);
            } else {
                lm.removeAllElements();

            }
            revalidate();
            repaint();

        } catch (NullPointerException exp) {
            System.out.println("Enter integer value only");
        }

        name_report_inputs.setText(TEST_REPORT_PATIENT_DETAILS.getName());
        // date_report_input.setDate(TEST_REPORT_PATIENT_DETAILS.getDate());
        //Test report date should be that date on which test report given       
//        date_report_input.setDate(home.getCurrentDate());

    }

    public void setReportOnReportInputField() {
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
//                String text = report_input.getText();
//                ArrayList<String> report = (Database.getInstance()).getLikeReport(text);
//                DefaultListModel lm = new DefaultListModel();
//                for (String m : report) {
//                    lm.addElement(m);
//                }
//                report_list.setModel(lm);
            }
        };
//        report_input.getDocument().addDocumentListener(dl);
//        report_list_panel.add(new JScrollPane(report_list));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        prescription_report_panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        test_report_form = new javax.swing.JPanel();
        name_report_inputs = new javax.swing.JTextField();
        report_status = new javax.swing.JLabel();
        print = new javax.swing.JButton();
        generate_report = new javax.swing.JButton();
        report_refresh = new javax.swing.JPanel();
        report_next = new javax.swing.JPanel();
        report_back = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        name_report_inputs1 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jComboBox2 = new javax.swing.JComboBox<>();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        jCheckBox3 = new javax.swing.JCheckBox();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jCheckBox4 = new javax.swing.JCheckBox();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jCheckBox5 = new javax.swing.JCheckBox();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jDateChooser6 = new com.toedter.calendar.JDateChooser();
        name_report_inputs2 = new javax.swing.JTextField();
        name_report_inputs3 = new javax.swing.JTextField();
        jDateChooser7 = new com.toedter.calendar.JDateChooser();
        jDateChooser8 = new com.toedter.calendar.JDateChooser();
        jDateChooser9 = new com.toedter.calendar.JDateChooser();
        report_show_panel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 51));
        setLayout(new java.awt.BorderLayout());

        prescription_report_panel.setBackground(new java.awt.Color(255, 102, 102));
        prescription_report_panel.setPreferredSize(new java.awt.Dimension(900, 700));
        prescription_report_panel.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(933, 486));
        jPanel1.setLayout(new java.awt.BorderLayout());

        test_report_form.setBackground(new java.awt.Color(255, 255, 204));
        test_report_form.setPreferredSize(new java.awt.Dimension(290, 800));

        name_report_inputs.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        name_report_inputs.setPreferredSize(new java.awt.Dimension(64, 30));
        name_report_inputs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                name_report_inputsMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                name_report_inputsMouseExited(evt);
            }
        });
        name_report_inputs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_report_inputsActionPerformed(evt);
            }
        });

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

        report_next.setBackground(new java.awt.Color(251, 252, 224));
        report_next.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        report_next.setPreferredSize(new java.awt.Dimension(25, 25));
        report_next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                report_nextMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                report_nextMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                report_nextMouseReleased(evt);
            }
        });
        report_next.setLayout(new java.awt.BorderLayout());

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

        jLabel2.setText("This is certify that");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mr", "Mrs", "Master", "Miss" }));

        jLabel3.setText("Age:- ");

        jLabel4.setText("Years:-");

        jLabel5.setText("Diagnosis:-");

        name_report_inputs1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        name_report_inputs1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                name_report_inputs1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                name_report_inputs1MouseExited(evt);
            }
        });
        name_report_inputs1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_report_inputs1ActionPerformed(evt);
            }
        });

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 204));
        jCheckBox1.setText("Is under my treatment as an");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "out-patient", "in Patient at this clinic", "Hospital" }));

        jCheckBox2.setBackground(new java.awt.Color(255, 255, 204));
        jCheckBox2.setText("Was treated as an OPD Patient from");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jLabel6.setText("to");

        jCheckBox3.setBackground(new java.awt.Color(255, 255, 204));
        jCheckBox3.setText("Was admitted as an in-door Patient on");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(255, 255, 204));
        jLabel7.setText("and discharged on");

        jCheckBox4.setBackground(new java.awt.Color(255, 255, 204));
        jCheckBox4.setText("had been advised rest for");
        jCheckBox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox4ActionPerformed(evt);
            }
        });

        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 255, 204));
        jLabel8.setText("days");

        jCheckBox5.setBackground(new java.awt.Color(255, 255, 204));
        jCheckBox5.setText("is fit to resume normal duties from");
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        jLabel9.setText("Date:-");

        name_report_inputs2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        name_report_inputs2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                name_report_inputs2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                name_report_inputs2MouseExited(evt);
            }
        });
        name_report_inputs2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_report_inputs2ActionPerformed(evt);
            }
        });

        name_report_inputs3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        name_report_inputs3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                name_report_inputs3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                name_report_inputs3MouseExited(evt);
            }
        });
        name_report_inputs3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_report_inputs3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout test_report_formLayout = new javax.swing.GroupLayout(test_report_form);
        test_report_form.setLayout(test_report_formLayout);
        test_report_formLayout.setHorizontalGroup(
            test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(test_report_formLayout.createSequentialGroup()
                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(test_report_formLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, test_report_formLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(test_report_formLayout.createSequentialGroup()
                                        .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(12, 12, 12)
                                        .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(test_report_formLayout.createSequentialGroup()
                                                .addComponent(name_report_inputs2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(18, 18, 18)
                                                .addComponent(name_report_inputs3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(name_report_inputs, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, test_report_formLayout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jDateChooser6, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(name_report_inputs1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(9, 9, 9)))
                                .addGap(16, 16, 16))
                            .addGroup(test_report_formLayout.createSequentialGroup()
                                .addComponent(jCheckBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(test_report_formLayout.createSequentialGroup()
                                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox1)
                                    .addGroup(test_report_formLayout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jDateChooser3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))))
                                    .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(test_report_formLayout.createSequentialGroup()
                                        .addGap(134, 134, 134)
                                        .addComponent(jDateChooser9, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(test_report_formLayout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(jDateChooser7, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jDateChooser8, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, test_report_formLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(generate_report, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(test_report_formLayout.createSequentialGroup()
                                .addComponent(report_back, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(78, 78, 78)
                                .addComponent(report_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79)
                                .addComponent(report_next, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16)))
                .addGap(12, 12, 12))
            .addGroup(test_report_formLayout.createSequentialGroup()
                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(test_report_formLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(report_status, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(test_report_formLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(test_report_formLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(test_report_formLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(test_report_formLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jCheckBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        test_report_formLayout.setVerticalGroup(
            test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(test_report_formLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1)
                    .addComponent(name_report_inputs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(name_report_inputs2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(name_report_inputs3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser6, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(name_report_inputs1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(test_report_formLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox4)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(test_report_formLayout.createSequentialGroup()
                        .addComponent(jDateChooser9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)))
                .addGap(18, 18, 18)
                .addComponent(jCheckBox5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generate_report, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(report_status, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(report_back, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(report_next, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(report_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(126, 126, 126))
        );

        jPanel1.add(test_report_form, java.awt.BorderLayout.WEST);

        report_show_panel.setPreferredSize(new java.awt.Dimension(500, 800));

        javax.swing.GroupLayout report_show_panelLayout = new javax.swing.GroupLayout(report_show_panel);
        report_show_panel.setLayout(report_show_panelLayout);
        report_show_panelLayout.setHorizontalGroup(
            report_show_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
        report_show_panelLayout.setVerticalGroup(
            report_show_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        jPanel1.add(report_show_panel, java.awt.BorderLayout.CENTER);

        prescription_report_panel.add(jPanel1, java.awt.BorderLayout.CENTER);

        add(prescription_report_panel, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void name_report_inputsMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name_report_inputsMouseEntered
        if (name_report_inputs.getBorder() != WARNING_BORDER) {
            name_report_inputs.setBorder(HOVER_BORDER);
        }
    }//GEN-LAST:event_name_report_inputsMouseEntered

    private void name_report_inputsMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name_report_inputsMouseExited
        if (name_report_inputs.getBorder() != WARNING_BORDER) {
            name_report_inputs.setBorder(INPUT_BORDER);
        }
    }//GEN-LAST:event_name_report_inputsMouseExited

    private void name_report_inputsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_report_inputsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_name_report_inputsActionPerformed

    public void searchReport() {
        try {
            Database database = Database.getInstance();
//            int pno = Integer.parseInt(pno_report_input.getText());
//            setTestReportPatientDetailsObject(database.getPatientDetails(pno));

            resetTestReportLists();
            setTestReportDetails();

//            ReportInfomartion test_report = database.getAllTestReports(pno);
           
//            if (test_report != null) {
//                 ArrayList<String> tests = test_report.getReportNames();
//                setReportPrint();
//            }
        } catch (NumberFormatException exp) {
            report_status.setText("Enter valid patient number");
            report_status.setForeground(WARNING_COLOR);
        }
    }

    public void searchReport(int patient_number) {
        try {
            Database database = Database.getInstance();
            setTestReportPatientDetailsObject(database.getPatientDetails(patient_number));

            resetTestReportLists();
            setTestReportDetails();
            ReportInfomartion test_report = database.getAllTestReports(patient_number);
           
            if (test_report != null) {
                 ArrayList<String> tests = test_report.getReportNames();
                setReportPrint();
            }
        } catch (NumberFormatException exp) {
            report_status.setText("Enter valid patient number");
            report_status.setForeground(WARNING_COLOR);
        }
    }
    public void searchReport(PatientDetails patientDetails) {

        try {
            Database database = Database.getInstance();
            setTestReportPatientDetailsObject(patientDetails);

            resetTestReportLists();
            setTestReportDetails();
            ReportInfomartion test_report = database.getAllTestReports(patientDetails.getPid());
          if(test_report!=null){
            ArrayList<String> tests = test_report.getReportNames();
            if (tests != null) {
                setReportPrint();
            }
          }
        } catch (NumberFormatException exp) {
            report_status.setText("Enter valid patient number");
            report_status.setForeground(WARNING_COLOR);
        }
    }

    public void resetTestReportLists() {
//        report_input.setText("");
//        report_list.clearSelection();
//        selected_report_list.setModel(new DefaultListModel());

        report_show_panel.removeAll();
        report_show_panel.revalidate();
        report_show_panel.repaint();

        report_status.setText("");
    }
    private void printMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printMouseEntered
        print.setBorder(HOVER_BORDER);
    }//GEN-LAST:event_printMouseEntered

    private void printMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printMouseExited
        print.setBorder(DEFAULT_BTN_BORDER);
    }//GEN-LAST:event_printMouseExited

    JasperPrint test_jasper_print = null;
    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        try {
            if (test_jasper_print != null) {
                JasperPrintManager.printReport(test_jasper_print, false);
                resetReportPage();
                report_status.setText("Report printed Succesfully");
                report_status.setForeground(SUCCESS_COLOR);
                test_jasper_print = null;
            } else {
                setReportPrint();
                printActionPerformed(evt);

            }
        } catch (JRException ex) {
            report_status.setText("Report not printed");
            report_status.setForeground(WARNING_COLOR);
        }
    }//GEN-LAST:event_printActionPerformed


    private void generate_reportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generate_reportMouseEntered
        generate_report.setBorder(HOVER_BTN_BORDER);
    }//GEN-LAST:event_generate_reportMouseEntered

    private void generate_reportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_generate_reportMouseExited
        generate_report.setBorder(DEFAULT_BTN_BORDER);
    }//GEN-LAST:event_generate_reportMouseExited

    private void generate_reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generate_reportActionPerformed

        setReportsIntoDatabase();
        setReportPrint();


    }//GEN-LAST:event_generate_reportActionPerformed

    private void report_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_refreshMouseClicked
        resetReportPage();
        setTestReportPatientDetailsObject(null);
    }//GEN-LAST:event_report_refreshMouseClicked

    private void report_refreshMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_refreshMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_report_refreshMouseExited

    private void report_refreshMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_refreshMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_report_refreshMouseReleased

    private void report_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_backMouseClicked
        home.showReportOnWindow("Prescription");
    }//GEN-LAST:event_report_backMouseClicked

    private void report_backMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_backMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_report_backMouseExited

    private void report_backMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_backMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_report_backMouseReleased

    private void report_nextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_nextMouseClicked
        home.showReportOnWindow("Medical");
    }//GEN-LAST:event_report_nextMouseClicked

    private void report_nextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_nextMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_report_nextMouseExited

    private void report_nextMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_nextMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_report_nextMouseReleased

    private void name_report_inputs1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name_report_inputs1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_name_report_inputs1MouseEntered

    private void name_report_inputs1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name_report_inputs1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_name_report_inputs1MouseExited

    private void name_report_inputs1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_report_inputs1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_name_report_inputs1ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void jCheckBox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void name_report_inputs2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name_report_inputs2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_name_report_inputs2MouseEntered

    private void name_report_inputs2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name_report_inputs2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_name_report_inputs2MouseExited

    private void name_report_inputs2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_report_inputs2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_name_report_inputs2ActionPerformed

    private void name_report_inputs3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name_report_inputs3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_name_report_inputs3MouseEntered

    private void name_report_inputs3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name_report_inputs3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_name_report_inputs3MouseExited

    private void name_report_inputs3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_report_inputs3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_name_report_inputs3ActionPerformed
    public void removeSelectedReport() {

//        int index = selected_report_list.getSelectedIndex();
//        System.out.println(index);
//        if (index >= 0) {
//            lm.remove(index);
//
//            selected_report_list.setModel(lm);
//
//            report_status.setText("Test Removed");
//            report_status.setForeground(SUCCESS_COLOR);
//
//        } else {
//            report_status.setText("First Select Test");
//            report_status.setForeground(WARNING_COLOR);
//        }
//        revalidate();
//        repaint();
    }

    DefaultListModel lm = new DefaultListModel();

    public void addReport() {
//        String str = report_list.getSelectedValue();
//
//        if (str == null) {
//            String report_name = report_input.getText();
//            Database db = Database.getInstance();
//            db.insertNewTestReportName(report_name);
//            str = report_name;
//        }
//        lm.addElement(str);
//        selected_report_list.setModel(lm);
//        revalidate();
//        repaint();
    }

    public void removeSelecetdReportUsingKey() {
        KeyStroke clt_x = KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK);
        Action remove = new AbstractAction("remove") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("hhh");
            }
        };
        String k = "remove";
        remove.putValue(Action.MNEMONIC_KEY, KeyEvent.VK_X);
        test_report_form.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(clt_x, k);
        test_report_form.getActionMap().put(k, remove);
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
        test_report_form.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(clt_r, k);
        test_report_form.getActionMap().put(k, refresh);
    }

    public void setReportsIntoDatabase() {
//        try {
//            Database db = Database.getInstance();
//            int size = selected_report_list.getModel().getSize();
//            int pno = Integer.parseInt(pno_report_input.getText());
//            long date_in_time = date_report_input.getDate().getTime();
//            for (int i = 0; i < size; i++) {
//                db.insertTestReport(pno, (String) lm.getElementAt(i), date_in_time);
//            }
//        } catch (NumberFormatException exp) {
//            report_status.setText("Enter valid patient number");
//            report_status.setForeground(Color.red);
//        }
//        report_show_panel.revalidate();
//        report_show_panel.repaint();

    }

    private void setReportPrint() {
//        report_show_panel.removeAll();
//        report_show_panel.revalidate();
//        report_show_panel.repaint();
//        try {
//
//            HashMap a = new HashMap();
//
//            a.put("pno", Integer.parseInt(pno_report_input.getText()));
//
//            Connection con = REPORTS_THREAD.getConnection();
//            if (con != null) {
//                JasperReport jr = REPORTS_THREAD.getCompliedTestReport();
//                if (jr != null) {
//                    test_jasper_print = JasperFillManager.fillReport(jr, a, con);
//                    JRViewer v = new JRViewer(test_jasper_print);
//                    report_show_panel.setLayout(new BorderLayout());
//                    report_show_panel.add(v);
//                } else {
//                    report_status.setText("No report is availalble");
//                    report_status.setForeground(Color.red);
//                }
//
//            }
//        } catch (NumberFormatException | JRException ex) {
//            report_status.setText("No report is availalble");
//            report_status.setForeground(Color.red);
//        }
//        report_show_panel.revalidate();
//        report_show_panel.repaint();
    }

    /*
    public void printReport(PatientDetails patientDetails, boolean with_dialog) throws JRException {

        if (patientDetails != null) {

            HashMap a = new HashMap();
            a.put("pno", patientDetails.getPid());

            Connection con = REPORTS_THREAD.getConnection();
            if (con != null) {
                JasperReport jr = REPORTS_THREAD.getCompliedTestReport();
                if (jr != null) {
                    JasperPrint jp = JasperFillManager.fillReport(jr, a, con);
                    JasperPrintManager.printReport(jp, with_dialog);

                    resetReportPage();
                    setTestReportPatientDetailsObject(null);

                } else {

                }
                report_show_panel.revalidate();
                report_show_panel.repaint();
            }
        } else {
            report_status.setText("No report is availalble");
            report_status.setForeground(Color.red);
        }

    }*/
    public void resetReportPage() {
//        pno_report_input.setText("");
//        name_report_inputs.setText("");
//        date_report_input.setDate(home.getCurrentDate());
//        report_list.clearSelection();
//        selected_report_list.clearSelection();
////        selected_report_list.removeAll();
//        lm.removeAllElements();
////        selected_report_list = new javax.swing.JList<String>();
////        selected_report_list.setModel(new DefaultListModel());
//        report_input.setText("");
//        report_status.setText("");
//
//        report_show_panel.removeAll();
//        report_show_panel.revalidate();
//        report_show_panel.repaint();
    }

    public void updateTestReport() {
        try {
//            resetReportPage();
//           
            PatientDetails patientDetails = getTestReportPatientDetailsObject();
//           
//            setTestReportDetails();

            (Database.getInstance()).removeALlTestReport(patientDetails.getPid());

            setReportsIntoDatabase();
            setReportPrint();

            report_status.setText("Update Successful.");
            report_status.setForeground(SUCCESS_COLOR);
        } catch (Exception exp) {
            report_status.setText("Unable ot update");
            report_status.setForeground(WARNING_COLOR);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton generate_report;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private com.toedter.calendar.JDateChooser jDateChooser6;
    private com.toedter.calendar.JDateChooser jDateChooser7;
    private com.toedter.calendar.JDateChooser jDateChooser8;
    private com.toedter.calendar.JDateChooser jDateChooser9;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField name_report_inputs;
    private javax.swing.JTextField name_report_inputs1;
    private javax.swing.JTextField name_report_inputs2;
    private javax.swing.JTextField name_report_inputs3;
    private javax.swing.JPanel prescription_report_panel;
    private javax.swing.JButton print;
    private javax.swing.JPanel report_back;
    private javax.swing.JPanel report_next;
    private javax.swing.JPanel report_refresh;
    private javax.swing.JPanel report_show_panel;
    private javax.swing.JLabel report_status;
    private javax.swing.JPanel test_report_form;
    // End of variables declaration//GEN-END:variables
}
