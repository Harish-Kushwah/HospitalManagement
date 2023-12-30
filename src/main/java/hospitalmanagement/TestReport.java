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
public class TestReport extends javax.swing.JPanel {

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

    public TestReport(Home home, PatientDetails patientDetails) {
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
            pno_report_input.setText(Integer.toString(TEST_REPORT_PATIENT_DETAILS.getPid()));
            ReportInfomartion test_report = (Database.getInstance()).getAllTestReports(TEST_REPORT_PATIENT_DETAILS.getPid());
           

            if (test_report != null) {
                 ArrayList<String> tests = test_report.getReportNames();
                lm.removeAllElements();
                for (String t : tests) {
                    lm.addElement(t);
                }
                selected_report_list.setModel(lm);
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
        date_report_input.setDate(home.getCurrentDate());

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
                String text = report_input.getText();
                ArrayList<String> report = (Database.getInstance()).getLikeReport(text);
                DefaultListModel lm = new DefaultListModel();
                for (String m : report) {
                    lm.addElement(m);
                }
                report_list.setModel(lm);
            }
        };
        report_input.getDocument().addDocumentListener(dl);
        report_list_panel.add(new JScrollPane(report_list));

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
        jLabel12 = new javax.swing.JLabel();
        pno_report_input = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        name_report_inputs = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        date_report_input = new com.toedter.calendar.JDateChooser();
        report_status = new javax.swing.JLabel();
        search_report = new javax.swing.JButton();
        print = new javax.swing.JButton();
        update = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        report_input = new javax.swing.JTextField();
        add_report_btn = new javax.swing.JButton();
        report_list_panel = new javax.swing.JPanel();
        report_list = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        selected_report_list = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        generate_report = new javax.swing.JButton();
        report_refresh = new javax.swing.JPanel();
        report_next = new javax.swing.JPanel();
        report_back = new javax.swing.JPanel();
        remove_selected_report_btn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        report_show_panel = new javax.swing.JPanel();
        age = new javax.swing.JLabel();
        gender = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 51));
        setLayout(new java.awt.BorderLayout());

        prescription_report_panel.setBackground(new java.awt.Color(255, 102, 102));
        prescription_report_panel.setPreferredSize(new java.awt.Dimension(900, 700));
        prescription_report_panel.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));
        jPanel1.setPreferredSize(new java.awt.Dimension(933, 486));
        jPanel1.setLayout(new java.awt.BorderLayout());

        test_report_form.setBackground(new java.awt.Color(255, 255, 204));
        test_report_form.setPreferredSize(new java.awt.Dimension(250, 800));

        jLabel12.setText("Pno :-");

        pno_report_input.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        pno_report_input.setPreferredSize(new java.awt.Dimension(64, 30));
        pno_report_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pno_report_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pno_report_inputMouseExited(evt);
            }
        });
        pno_report_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pno_report_inputActionPerformed(evt);
            }
        });
        pno_report_input.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pno_report_inputKeyPressed(evt);
            }
        });

        jLabel14.setText("Name:-");

        name_report_inputs.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
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

        jLabel13.setText("Date:-");

        report_status.setBackground(new java.awt.Color(0, 204, 0));

        search_report.setBackground(new java.awt.Color(102, 255, 102));
        search_report.setForeground(new java.awt.Color(51, 51, 51));
        search_report.setText("Search");
        search_report.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        search_report.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        search_report.setFocusPainted(false);
        search_report.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                search_reportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                search_reportMouseExited(evt);
            }
        });
        search_report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search_reportActionPerformed(evt);
            }
        });

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

        update.setBackground(new java.awt.Color(0, 0, 255));
        update.setForeground(new java.awt.Color(255, 255, 255));
        update.setText("Update");
        update.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 255), 1, true));
        update.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        update.setFocusable(false);
        update.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateMouseExited(evt);
            }
        });
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        jLabel5.setText("Enter Test :");

        report_input.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

        report_list.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        report_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                report_listMouseClicked(evt);
            }
        });
        report_list.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                report_listKeyPressed(evt);
            }
        });
        report_list_panel.add(report_list, "card2");

        selected_report_list.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        selected_report_list.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                selected_report_listKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(selected_report_list);

        jLabel1.setText("Selecetd Test");

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

        remove_selected_report_btn.setBackground(new java.awt.Color(255, 51, 51));
        remove_selected_report_btn.setForeground(new java.awt.Color(255, 255, 255));
        remove_selected_report_btn.setText("Delete");
        remove_selected_report_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        remove_selected_report_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        remove_selected_report_btn.setFocusPainted(false);
        remove_selected_report_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                remove_selected_report_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                remove_selected_report_btnMouseExited(evt);
            }
        });
        remove_selected_report_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_selected_report_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout test_report_formLayout = new javax.swing.GroupLayout(test_report_form);
        test_report_form.setLayout(test_report_formLayout);
        test_report_formLayout.setHorizontalGroup(
            test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, test_report_formLayout.createSequentialGroup()
                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(test_report_formLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(report_status, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(test_report_formLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(report_list_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, test_report_formLayout.createSequentialGroup()
                                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, test_report_formLayout.createSequentialGroup()
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(date_report_input, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, test_report_formLayout.createSequentialGroup()
                                        .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(test_report_formLayout.createSequentialGroup()
                                                .addComponent(pno_report_input, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(search_report, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(name_report_inputs)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, test_report_formLayout.createSequentialGroup()
                                        .addComponent(report_input, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(add_report_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(test_report_formLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(remove_selected_report_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(15, 15, 15))
            .addGroup(test_report_formLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(test_report_formLayout.createSequentialGroup()
                        .addComponent(report_back, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(report_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(report_next, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(test_report_formLayout.createSequentialGroup()
                        .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(generate_report, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        test_report_formLayout.setVerticalGroup(
            test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(test_report_formLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pno_report_input, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(search_report, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(name_report_inputs, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date_report_input, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(report_input, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_report_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(report_list_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(test_report_formLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(test_report_formLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(remove_selected_report_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)))
                .addGap(7, 7, 7)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generate_report, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(report_status, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(test_report_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(report_next, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(report_back, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(report_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(264, Short.MAX_VALUE))
        );

        jPanel1.add(test_report_form, java.awt.BorderLayout.WEST);

        report_show_panel.setPreferredSize(new java.awt.Dimension(500, 800));

        javax.swing.GroupLayout report_show_panelLayout = new javax.swing.GroupLayout(report_show_panel);
        report_show_panel.setLayout(report_show_panelLayout);
        report_show_panelLayout.setHorizontalGroup(
            report_show_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(report_show_panelLayout.createSequentialGroup()
                .addContainerGap(492, Short.MAX_VALUE)
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

        prescription_report_panel.add(jPanel1, java.awt.BorderLayout.CENTER);

        add(prescription_report_panel, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void pno_report_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pno_report_inputMouseEntered
        if (pno_report_input.getBorder() != WARNING_BORDER) {
            pno_report_input.setBorder(HOVER_BORDER);
        }

    }//GEN-LAST:event_pno_report_inputMouseEntered

    private void pno_report_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pno_report_inputMouseExited
        if (pno_report_input.getBorder() != WARNING_BORDER) {
            pno_report_input.setBorder(INPUT_BORDER);
        }
    }//GEN-LAST:event_pno_report_inputMouseExited

    private void pno_report_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pno_report_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pno_report_inputActionPerformed

    private void pno_report_inputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pno_report_inputKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            searchReport();
        }
    }//GEN-LAST:event_pno_report_inputKeyPressed

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

    private void search_reportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_reportMouseEntered

        search_report.setBorder(HOVER_BORDER);
    }//GEN-LAST:event_search_reportMouseEntered

    private void search_reportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_reportMouseExited
        search_report.setBorder(DEFAULT_BTN_BORDER);
    }//GEN-LAST:event_search_reportMouseExited

    private void search_reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_search_reportActionPerformed
        searchReport();
    }//GEN-LAST:event_search_reportActionPerformed

    public void searchReport() {
        try {
            Database database = Database.getInstance();
            int pno = Integer.parseInt(pno_report_input.getText());
            setTestReportPatientDetailsObject(database.getPatientDetails(pno));

            resetTestReportLists();
            setTestReportDetails();

            ReportInfomartion test_report = database.getAllTestReports(pno);
           
            if (test_report != null) {
                 ArrayList<String> tests = test_report.getReportNames();
                setReportPrint();
            }
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
        report_input.setText("");
        report_list.clearSelection();
        selected_report_list.setModel(new DefaultListModel());

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

    private void updateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseEntered
        update.setBorder(HOVER_BORDER);
    }//GEN-LAST:event_updateMouseEntered

    private void updateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateMouseExited
        update.setBorder(DEFAULT_BTN_BORDER);
    }//GEN-LAST:event_updateMouseExited

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        updateTestReport();
    }//GEN-LAST:event_updateActionPerformed

    private void report_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_inputMouseEntered
        report_input.setBorder(HOVER_BORDER);
    }//GEN-LAST:event_report_inputMouseEntered

    private void report_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_inputMouseExited
        report_input.setBorder(INPUT_BORDER);
    }//GEN-LAST:event_report_inputMouseExited


    private void report_listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_listMouseClicked

        if (evt.getClickCount() == 1) {
            addReport();
        }
        report_input.setText("");
        report_input.grabFocus();
        //medicine_input.setText(medicine_list.getSelectedValue());
    }//GEN-LAST:event_report_listMouseClicked

    private void add_report_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_report_btnMouseEntered
        add_report_btn.setBorder(HOVER_BTN_BORDER);
    }//GEN-LAST:event_add_report_btnMouseEntered

    private void add_report_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_report_btnMouseExited
        add_report_btn.setBorder(DEFAULT_BTN_BORDER);
    }//GEN-LAST:event_add_report_btnMouseExited

    private void add_report_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_report_btnActionPerformed
        addReport();

    }//GEN-LAST:event_add_report_btnActionPerformed

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

    private void selected_report_listKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_selected_report_listKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_selected_report_listKeyPressed

    private void remove_selected_report_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_selected_report_btnActionPerformed
        removeSelectedReport();
    }//GEN-LAST:event_remove_selected_report_btnActionPerformed

    private void remove_selected_report_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_remove_selected_report_btnMouseEntered
        remove_selected_report_btn.setBorder(HOVER_BORDER);
    }//GEN-LAST:event_remove_selected_report_btnMouseEntered

    private void remove_selected_report_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_remove_selected_report_btnMouseExited
        remove_selected_report_btn.setBorder(DEFAULT_BTN_BORDER);
    }//GEN-LAST:event_remove_selected_report_btnMouseExited

    private void report_inputKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_report_inputKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            addReport();
            report_input.setText("");
            report_input.grabFocus();
        }

        if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            report_list.grabFocus();
            report_list.setSelectedIndex(0);

        }
        if (evt.getKeyCode() == KeyEvent.VK_UP) {

            if (report_list.getSelectedIndex() == 0) {
                report_input.grabFocus();
            } else {
                report_list.grabFocus();
            }

        }
    }//GEN-LAST:event_report_inputKeyPressed

    private void report_listKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_report_listKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            addReport();
            report_input.setText("");
            report_input.grabFocus();
        }

        if (evt.getKeyCode() == KeyEvent.VK_UP) {

            if (report_list.getSelectedIndex() == 0) {
                report_input.grabFocus();
            } else {
                report_list.grabFocus();
            }

        }
    }//GEN-LAST:event_report_listKeyPressed

    private void report_nextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_nextMouseClicked
        home.showReportOnWindow("Medical");
    }//GEN-LAST:event_report_nextMouseClicked

    private void report_nextMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_nextMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_report_nextMouseExited

    private void report_nextMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_report_nextMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_report_nextMouseReleased
    public void removeSelectedReport() {

        int index = selected_report_list.getSelectedIndex();
        System.out.println(index);
        if (index >= 0) {
            lm.remove(index);

            selected_report_list.setModel(lm);

            report_status.setText("Test Removed");
            report_status.setForeground(SUCCESS_COLOR);

        } else {
            report_status.setText("First Select Test");
            report_status.setForeground(WARNING_COLOR);
        }
        revalidate();
        repaint();
    }

    DefaultListModel lm = new DefaultListModel();

    public void addReport() {
        String str = report_list.getSelectedValue();

        if (str == null) {
            String report_name = report_input.getText();
            Database db = Database.getInstance();
            db.insertNewTestReportName(report_name);
            str = report_name;
        }
        lm.addElement(str);
        selected_report_list.setModel(lm);
        revalidate();
        repaint();
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
        try {
            Database db = Database.getInstance();
            int size = selected_report_list.getModel().getSize();
            int pno = Integer.parseInt(pno_report_input.getText());
            long date_in_time = date_report_input.getDate().getTime();
            for (int i = 0; i < size; i++) {
                db.insertTestReport(pno, (String) lm.getElementAt(i), date_in_time);
            }
        } catch (NumberFormatException exp) {
            report_status.setText("Enter valid patient number");
            report_status.setForeground(Color.red);
        }
        report_show_panel.revalidate();
        report_show_panel.repaint();

    }

    private void setReportPrint() {
        report_show_panel.removeAll();
        report_show_panel.revalidate();
        report_show_panel.repaint();
        try {

            HashMap a = new HashMap();

            a.put("pno", Integer.parseInt(pno_report_input.getText()));

            Connection con = REPORTS_THREAD.getConnection();
            if (con != null) {
                JasperReport jr = REPORTS_THREAD.getCompliedTestReport();
                if (jr != null) {
                    test_jasper_print = JasperFillManager.fillReport(jr, a, con);
                    JRViewer v = new JRViewer(test_jasper_print);
                    report_show_panel.setLayout(new BorderLayout());
                    report_show_panel.add(v);
                } else {
                    report_status.setText("No report is availalble internal");
                    report_status.setForeground(Color.red);
                }

            }
            else{
                System.out.println("cONNECTION IS NULL");
            }
        } catch (NumberFormatException | JRException ex) {
             ex.printStackTrace();
            report_status.setText("No report is availalbleexternal");
            report_status.setForeground(Color.blue);
        }
        report_show_panel.revalidate();
        report_show_panel.repaint();
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
        pno_report_input.setText("");
        name_report_inputs.setText("");
        date_report_input.setDate(home.getCurrentDate());
        report_list.clearSelection();
        selected_report_list.clearSelection();
//        selected_report_list.removeAll();
        lm.removeAllElements();
//        selected_report_list = new javax.swing.JList<String>();
//        selected_report_list.setModel(new DefaultListModel());
        report_input.setText("");
        report_status.setText("");

        report_show_panel.removeAll();
        report_show_panel.revalidate();
        report_show_panel.repaint();
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
    private javax.swing.JButton add_report_btn;
    private javax.swing.JLabel age;
    private com.toedter.calendar.JDateChooser date_report_input;
    private javax.swing.JLabel gender;
    private javax.swing.JButton generate_report;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField name_report_inputs;
    private javax.swing.JTextField pno_report_input;
    private javax.swing.JPanel prescription_report_panel;
    private javax.swing.JButton print;
    private javax.swing.JButton remove_selected_report_btn;
    private javax.swing.JPanel report_back;
    private javax.swing.JTextField report_input;
    private javax.swing.JList<String> report_list;
    private javax.swing.JPanel report_list_panel;
    private javax.swing.JPanel report_next;
    private javax.swing.JPanel report_refresh;
    private javax.swing.JPanel report_show_panel;
    private javax.swing.JLabel report_status;
    private javax.swing.JButton search_report;
    private javax.swing.JList<String> selected_report_list;
    private javax.swing.JPanel test_report_form;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
