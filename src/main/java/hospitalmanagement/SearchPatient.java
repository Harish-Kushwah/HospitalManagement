/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hospitalmanagement;

import static hospitalmanagement.Home.total_medicine_selected;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneLayout;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import myutil.MyCustomRenderer;
import myutil.Database;
import myutil.GradientPanel;
import myutil.InputValidation;
import util.LibraryTable;
import myutil.MedicineDetails;
import myutil.MedicineRowPanel;
import myutil.PatientDetails;
import myutil.ReportInfomartion;
import myutil.SetImageIcon;
import java.util.regex.*;

/**
 *
 * @author haris
 */
public class SearchPatient extends javax.swing.JPanel {

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
    static int total_medicine_selected = 0;
    ArrayList<MedicineRowPanel> temp_medicine_arraylist = new ArrayList<MedicineRowPanel>();
    String refresh_page_icon = "./images/refresh3.png";
    Home home;
    PatientDetails patient_for_report_page;

    public SearchPatient(Home home) {
        initComponents();
        addMouseListerOnTabel();
        AddlisterOnGenderRadioBtn();
        this.home = home;

        refresh.add(new SetImageIcon(new ImageIcon(refresh_page_icon), 30, 30), BorderLayout.CENTER);

    }

    public JTextField getSearchPatientNameField() {
        return name_input;
    }

    public void AddlisterOnGenderRadioBtn() {

        male_btn.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (male_btn.isSelected()) {
                    searchByGender("Male");
                }
                System.out.println("Male" + male_btn.isSelected());
                System.out.println("Female" + female_btn.isSelected());
            }

        });

        female_btn.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (female_btn.isSelected()) {
                    searchByGender("Female");
                }
                System.out.println("Male" + male_btn.isSelected());
                System.out.println("Female" + female_btn.isSelected());
            }

        });
    }

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

    public void searchByGender(String gender) {
        Database database = Database.getInstance();
        ArrayList<String> patient_details = database.getLikePatientByGender(gender);

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

    public void searchByPno(int patient_number) {
        try {
            Database database = Database.getInstance();
            ArrayList<String> patient_details = database.getLikePatient(patient_number);

            if (patient_details != null )  {

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
        } catch (NullPointerException exp) {
            System.out.println("Error in search by name");
            exp.printStackTrace();
        }
    }

    public void searchByMobileNumber(String mobile_number) {
        Database database = Database.getInstance();
        ArrayList<String> patient_details = database.getLikePatientByMobileNo(mobile_number);

        if (patient_details != null) {
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
        } else {
            System.out.println("Patient Details are null");
        }
    }

    public void searchByDate(long date_time_format) {
        Database database = Database.getInstance();
        ArrayList<String> patient_details = database.getLikePatientByDate(date_time_format);

        if (patient_details != null) {
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
        } else {
            System.out.println("Patient Details are null");
        }
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
                    addMedicineOnPanel(patient_number);
                    addTestReport(patient_number);
                    addPatientDetails(patient_number);
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

    public void addPatientDetails(int patient_number) {
        Database db = Database.getInstance();
        PatientDetails patient = db.getPatientDetails(patient_number);
        name_input_label.setText(patient.getName());
        mobile_number_input_label.setText(patient.getMobileNo());
        age_input_label.setText(Integer.toString(patient.getAge()));
        date_input_label.setText(patient.getDate().toString());
        gender_input_label.setText(patient.getGender());
        weight_input_label.setText(Integer.toString(patient.getWeight()));
        diseases_input_label.setText(patient.getSymptoms());
        bp_input_label.setText(patient.getBloodPressure());

        //updating the patient object for report page click
        patient_for_report_page = patient;

    }

    public void addMedicineOnPanel(int patient_number) {
        try {

            Database db = Database.getInstance();
            ArrayList<MedicineDetails> medicineDetailsList = db.getMedicineList(patient_number);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = -1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            selected_medicine_panel.removeAll();

            JPanel main_list = new JPanel(new GridBagLayout());

            for (MedicineDetails m : medicineDetailsList) {

                main_list.add(new MedicineRowPanel(m), gbc);

            }
            JPanel jp = new JPanel(new BorderLayout());
            jp.add(main_list, BorderLayout.PAGE_START);
            JScrollPane sc = new JScrollPane(jp);

            sc.setPreferredSize(new Dimension(100, 100));

            sc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            sc.setCursor(new Cursor(Cursor.HAND_CURSOR));
            sc.setVisible(true);
            selected_medicine_panel.add(sc);
            revalidate();
            repaint();
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        }

    }

    public void addTestReport(int patient_number) {
        Database db = Database.getInstance();

        ReportInfomartion test_report = db.getAllTestReports(patient_number);

        DefaultListModel lm = new DefaultListModel();
        if (test_report != null) {

            test_date.setDate(test_report.getDate());
            ArrayList<String> tests = test_report.getReportNames();
            for (String m : tests) {
                lm.addElement(m);

            }

        }
        test_list.setModel(lm);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        group_btn = new javax.swing.ButtonGroup();
        Prescription = new javax.swing.JPanel();
        prescription_form_panel = prescription_form_panel = new GradientPanel(new Color(0xe8feff),new Color(0xe8f3ff) , 300,600);
        prescription_form = new JPanel();
        prescription_form.setBackground(new Color(202,177,18));
        name_input = new javax.swing.JTextField();
        name_label17 = new javax.swing.JLabel();
        new_test_btn = new javax.swing.JButton();
        name_label23 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        date_input = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        add_medicine_btn = new javax.swing.JButton();
        selected_medicine_panel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        patient_list_panel = new javax.swing.JPanel();
        patient_table_scroll = new javax.swing.JScrollPane();
        patient_table = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        male_btn = new javax.swing.JRadioButton();
        female_btn = new javax.swing.JRadioButton();
        refresh = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        pno_input = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        mobile_number_input = new javax.swing.JTextField();
        name_label18 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        new_prescription_btn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        test_list = new javax.swing.JList<>();
        jLabel5 = new javax.swing.JLabel();
        test_date = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        old_test_btn = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        old_prescription_btn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        reffer_btn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        name_input_label = new javax.swing.JLabel();
        mobile_number_input_label = new javax.swing.JLabel();
        age_input_label = new javax.swing.JLabel();
        weight_input_label = new javax.swing.JLabel();
        diseases_input_label = new javax.swing.JLabel();
        date_input_label = new javax.swing.JLabel();
        gender_input_label = new javax.swing.JLabel();
        bp_input_label = new javax.swing.JLabel();
        prescription_foot_panel = new javax.swing.JPanel();
        prescription_head_panel = new javax.swing.JPanel();
        patient_panel_head = new javax.swing.JPanel();
        patient_panel_header_title = new javax.swing.JLabel();
        prescrption_left_panel = new javax.swing.JPanel();
        prescrption_right_panel = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        Prescription.setBackground(new java.awt.Color(255, 153, 153));
        Prescription.setForeground(new java.awt.Color(255, 255, 255));
        Prescription.setPreferredSize(new java.awt.Dimension(900, 735));
        Prescription.setLayout(new java.awt.BorderLayout());

        prescription_form_panel.setBackground(new java.awt.Color(204, 255, 255));
        prescription_form_panel.setAutoscrolls(true);
        prescription_form_panel.setPreferredSize(new java.awt.Dimension(800, 700));
        prescription_form_panel.setRequestFocusEnabled(false);
        prescription_form_panel.setLayout(new java.awt.BorderLayout(3, 3));

        prescription_form.setBackground(new java.awt.Color(251, 252, 224));
        prescription_form.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        prescription_form.setPreferredSize(new java.awt.Dimension(600, 550));

        name_input.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        name_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        name_input.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                name_inputCaretUpdate(evt);
            }
        });
        name_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                name_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                name_inputMouseExited(evt);
            }
        });

        name_label17.setBackground(new java.awt.Color(251, 252, 224));
        name_label17.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        name_label17.setText("Gender :-");

        new_test_btn.setBackground(new java.awt.Color(51, 102, 255));
        new_test_btn.setForeground(new java.awt.Color(255, 255, 255));
        new_test_btn.setText("Test");
        new_test_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        new_test_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        new_test_btn.setFocusPainted(false);
        new_test_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                new_test_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                new_test_btnMouseExited(evt);
            }
        });
        new_test_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new_test_btnActionPerformed(evt);
            }
        });

        name_label23.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        name_label23.setText("Date :-");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        date_input.setDateFormatString("dd-MM-yyyy");

        jLabel8.setText("All Patient :-");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Medicine Prescribed");

        add_medicine_btn.setBackground(new java.awt.Color(0, 51, 255));
        add_medicine_btn.setForeground(new java.awt.Color(255, 255, 255));
        add_medicine_btn.setText("Show");
        add_medicine_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        add_medicine_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        add_medicine_btn.setFocusPainted(false);
        add_medicine_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                add_medicine_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                add_medicine_btnMouseExited(evt);
            }
        });
        add_medicine_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_medicine_btnActionPerformed(evt);
            }
        });

        selected_medicine_panel.setBackground(new java.awt.Color(255, 255, 255));
        selected_medicine_panel.setLayout(new java.awt.CardLayout());

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Doses");

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

        male_btn.setBackground(new java.awt.Color(251, 252, 224));
        group_btn.add(male_btn);
        male_btn.setText("Male");
        male_btn.setFocusPainted(false);

        female_btn.setBackground(new java.awt.Color(251, 252, 224));
        group_btn.add(female_btn);
        female_btn.setText("Female");
        female_btn.setFocusPainted(false);

        refresh.setBackground(new java.awt.Color(51, 255, 255));
        refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refresh.setPreferredSize(new java.awt.Dimension(29, 29));
        refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                refreshMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                refreshMouseReleased(evt);
            }
        });
        refresh.setLayout(new java.awt.BorderLayout());

        jLabel15.setText("Pno :");

        pno_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        pno_input.setPreferredSize(new java.awt.Dimension(64, 20));
        pno_input.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                pno_inputCaretUpdate(evt);
            }
        });
        pno_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pno_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pno_inputMouseExited(evt);
            }
        });

        jLabel18.setText("Mob:-");

        mobile_number_input.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mobile_number_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        mobile_number_input.setPreferredSize(new java.awt.Dimension(64, 20));
        mobile_number_input.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                mobile_number_inputCaretUpdate(evt);
            }
        });
        mobile_number_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mobile_number_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mobile_number_inputMouseExited(evt);
            }
        });

        name_label18.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        name_label18.setText("Name :-");

        jLabel1.setText("New Test Report : ");

        jLabel2.setText("New Prescription  : ");

        jLabel3.setText("Give Referal Letter :-");

        new_prescription_btn.setBackground(new java.awt.Color(51, 102, 255));
        new_prescription_btn.setForeground(new java.awt.Color(255, 255, 255));
        new_prescription_btn.setText("Prescribe");
        new_prescription_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        new_prescription_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        new_prescription_btn.setFocusPainted(false);
        new_prescription_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                new_prescription_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                new_prescription_btnMouseExited(evt);
            }
        });
        new_prescription_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new_prescription_btnActionPerformed(evt);
            }
        });

        jLabel4.setText("Tests - ");

        jScrollPane1.setViewportView(test_list);
        test_list.getAccessibleContext().setAccessibleName("");

        jLabel5.setText("Date :- ");

        jLabel11.setText("Get Prescription Report :- ");

        old_test_btn.setBackground(new java.awt.Color(51, 102, 255));
        old_test_btn.setForeground(new java.awt.Color(255, 255, 255));
        old_test_btn.setText("Test");
        old_test_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        old_test_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        old_test_btn.setFocusPainted(false);
        old_test_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                old_test_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                old_test_btnMouseExited(evt);
            }
        });
        old_test_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                old_test_btnActionPerformed(evt);
            }
        });

        jLabel12.setText("Get Test Report :- ");

        old_prescription_btn.setBackground(new java.awt.Color(51, 102, 255));
        old_prescription_btn.setForeground(new java.awt.Color(255, 255, 255));
        old_prescription_btn.setText("Prescribe");
        old_prescription_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        old_prescription_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        old_prescription_btn.setFocusPainted(false);
        old_prescription_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                old_prescription_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                old_prescription_btnMouseExited(evt);
            }
        });
        old_prescription_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                old_prescription_btnActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 204));

        reffer_btn.setBackground(new java.awt.Color(51, 102, 255));
        reffer_btn.setForeground(new java.awt.Color(255, 255, 255));
        reffer_btn.setText("Reffer");
        reffer_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        reffer_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reffer_btn.setFocusPainted(false);
        reffer_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reffer_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                reffer_btnMouseExited(evt);
            }
        });
        reffer_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reffer_btnActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 204), 1, true));

        jLabel7.setText("Name : - ");

        jLabel13.setText("Mobile No : - ");

        jLabel14.setText("Age  : - ");

        jLabel16.setText("Weight :- ");

        jLabel17.setText("Date :- ");

        jLabel19.setText("BP :-");

        jLabel20.setText("Diseases:-");

        jLabel21.setText("Gender :-");

        name_input_label.setText("________________________________________________");

        mobile_number_input_label.setText("_____________");

        age_input_label.setText("_________");

        weight_input_label.setText("_________");

        diseases_input_label.setText("_______________________________");

        date_input_label.setText("___________");

        gender_input_label.setText("_________");

        bp_input_label.setText("_______");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(mobile_number_input_label))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(age_input_label))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(gender_input_label)
                            .addComponent(date_input_label, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(5, 5, 5)
                                .addComponent(bp_input_label, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(119, 119, 119)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(weight_input_label))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(name_input_label, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(diseases_input_label, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(name_input_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17)
                    .addComponent(mobile_number_input_label)
                    .addComponent(date_input_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel21)
                    .addComponent(age_input_label)
                    .addComponent(gender_input_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel19)
                    .addComponent(weight_input_label)
                    .addComponent(bp_input_label))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(diseases_input_label))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout prescription_formLayout = new javax.swing.GroupLayout(prescription_form);
        prescription_form.setLayout(prescription_formLayout);
        prescription_formLayout.setHorizontalGroup(
            prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prescription_formLayout.createSequentialGroup()
                .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, prescription_formLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(add_medicine_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(514, 514, 514)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, prescription_formLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pno_input, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(name_label18, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(name_input, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mobile_number_input, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(name_label17)
                        .addGap(18, 18, 18)
                        .addComponent(male_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(female_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(name_label23)
                        .addGap(18, 18, 18)
                        .addComponent(date_input, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(prescription_formLayout.createSequentialGroup()
                        .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(prescription_formLayout.createSequentialGroup()
                                .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(prescription_formLayout.createSequentialGroup()
                                        .addGap(19, 19, 19)
                                        .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel1)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, prescription_formLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel3)))
                                .addGap(18, 18, 18)
                                .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(prescription_formLayout.createSequentialGroup()
                                        .addComponent(new_prescription_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, prescription_formLayout.createSequentialGroup()
                                        .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(reffer_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(new_test_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)))
                                .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(prescription_formLayout.createSequentialGroup()
                                        .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12)
                                            .addComponent(jLabel11))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(old_test_btn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(old_prescription_btn, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)))
                                    .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(prescription_formLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator1)
                                    .addComponent(patient_list_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, prescription_formLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(prescription_formLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(test_date, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel9)
                                .addComponent(selected_medicine_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 654, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(prescription_formLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        prescription_formLayout.setVerticalGroup(
            prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prescription_formLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(date_input, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(name_label23, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(name_label17, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(name_input, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(male_btn)
                        .addComponent(female_btn)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(mobile_number_input, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15)
                        .addComponent(pno_input, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(name_label18, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(add_medicine_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(prescription_formLayout.createSequentialGroup()
                        .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(patient_list_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                            .addComponent(selected_medicine_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(prescription_formLayout.createSequentialGroup()
                                .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(prescription_formLayout.createSequentialGroup()
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, prescription_formLayout.createSequentialGroup()
                                        .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(test_date, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel4)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(prescription_formLayout.createSequentialGroup()
                                        .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(prescription_formLayout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(old_prescription_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(10, 10, 10))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, prescription_formLayout.createSequentialGroup()
                                                .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(new_prescription_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel2))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                        .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(old_test_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(new_test_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(prescription_formLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(reffer_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel3)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, prescription_formLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(20, 20, 20))
        );

        prescription_form_panel.add(prescription_form, java.awt.BorderLayout.CENTER);

        prescription_foot_panel.setBackground(new java.awt.Color(204, 255, 255));
        prescription_foot_panel.setPreferredSize(new java.awt.Dimension(800, 8));

        javax.swing.GroupLayout prescription_foot_panelLayout = new javax.swing.GroupLayout(prescription_foot_panel);
        prescription_foot_panel.setLayout(prescription_foot_panelLayout);
        prescription_foot_panelLayout.setHorizontalGroup(
            prescription_foot_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1364, Short.MAX_VALUE)
        );
        prescription_foot_panelLayout.setVerticalGroup(
            prescription_foot_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        prescription_form_panel.add(prescription_foot_panel, java.awt.BorderLayout.SOUTH);

        prescription_head_panel.setBackground(new java.awt.Color(204, 255, 255));
        prescription_head_panel.setPreferredSize(new java.awt.Dimension(800, 8));

        javax.swing.GroupLayout prescription_head_panelLayout = new javax.swing.GroupLayout(prescription_head_panel);
        prescription_head_panel.setLayout(prescription_head_panelLayout);
        prescription_head_panelLayout.setHorizontalGroup(
            prescription_head_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1364, Short.MAX_VALUE)
        );
        prescription_head_panelLayout.setVerticalGroup(
            prescription_head_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );

        prescription_form_panel.add(prescription_head_panel, java.awt.BorderLayout.NORTH);

        Prescription.add(prescription_form_panel, java.awt.BorderLayout.CENTER);

        patient_panel_head.setBackground(new java.awt.Color(0, 0, 102));
        patient_panel_head.setForeground(new java.awt.Color(51, 255, 255));
        patient_panel_head.setMinimumSize(new java.awt.Dimension(169, 40));
        patient_panel_head.setPreferredSize(new java.awt.Dimension(169, 35));

        patient_panel_header_title.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        patient_panel_header_title.setForeground(new java.awt.Color(102, 255, 255));
        patient_panel_header_title.setText("Search Patient");
        patient_panel_head.add(patient_panel_header_title);

        Prescription.add(patient_panel_head, java.awt.BorderLayout.PAGE_START);

        prescrption_left_panel.setBackground(new java.awt.Color(204, 255, 255));
        prescrption_left_panel.setPreferredSize(new java.awt.Dimension(10, 400));

        javax.swing.GroupLayout prescrption_left_panelLayout = new javax.swing.GroupLayout(prescrption_left_panel);
        prescrption_left_panel.setLayout(prescrption_left_panelLayout);
        prescrption_left_panelLayout.setHorizontalGroup(
            prescrption_left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        prescrption_left_panelLayout.setVerticalGroup(
            prescrption_left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        Prescription.add(prescrption_left_panel, java.awt.BorderLayout.LINE_START);

        prescrption_right_panel.setBackground(new java.awt.Color(204, 255, 255));
        prescrption_right_panel.setPreferredSize(new java.awt.Dimension(10, 400));

        javax.swing.GroupLayout prescrption_right_panelLayout = new javax.swing.GroupLayout(prescrption_right_panel);
        prescrption_right_panel.setLayout(prescrption_right_panelLayout);
        prescrption_right_panelLayout.setHorizontalGroup(
            prescrption_right_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        prescrption_right_panelLayout.setVerticalGroup(
            prescrption_right_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        Prescription.add(prescrption_right_panel, java.awt.BorderLayout.LINE_END);

        add(Prescription, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void mobile_number_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobile_number_inputMouseExited
        mobile_number_input.setBorder(INPUT_BORDER);
    }//GEN-LAST:event_mobile_number_inputMouseExited

    private void mobile_number_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobile_number_inputMouseEntered
        mobile_number_input.setBorder(HOVER_BORDER);
    }//GEN-LAST:event_mobile_number_inputMouseEntered

    private void pno_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pno_inputMouseExited
        pno_input.setBorder(INPUT_BORDER);
    }//GEN-LAST:event_pno_inputMouseExited

    private void pno_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pno_inputMouseEntered
        pno_input.setBorder(HOVER_BORDER);
    }//GEN-LAST:event_pno_inputMouseEntered

    private void refreshMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseReleased

    }//GEN-LAST:event_refreshMouseReleased

    private void refreshMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseExited
        //        refresh.removeAll();
        //        refresh.add(new SetImageIcon(new ImageIcon(refresh_page_icon_on_exit), 30, 30), BorderLayout.CENTER);
        //        validate();
        //        repaint();
    }//GEN-LAST:event_refreshMouseExited

    private void refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseClicked
        resetSearchPage();
//               
//                refresh.removeAll();
//                refresh.add(new SetImageIcon(new ImageIcon(refresh_page_icon_on_click), 30, 30), BorderLayout.CENTER);
//        
//                fees_status_label.setText("");
//                validate();
//                repaint();
    }//GEN-LAST:event_refreshMouseClicked

    public void resetSearchPage() {
        pno_input.setText("");
        name_input.setText("");
        mobile_number_input.setText("");
        selected_medicine_panel.removeAll();
        test_list.setModel(new DefaultListModel());
        test_date.setDate(getCurrentDate());
        date_input.setDate(getCurrentDate());

        DefaultTableModel dm = (DefaultTableModel) patient_table.getModel();

        int total_row = patient_table.getRowCount();
        for (int i = 0; i < total_row; i++) {
            dm.removeRow(0);
            validate();
            repaint();
        }

        name_input_label.setText("____________");
        mobile_number_input_label.setText("__________");
        age_input_label.setText("____");
        date_input_label.setText("______");
        gender_input_label.setText("______");
        weight_input_label.setText("______");
        diseases_input_label.setText("_____________");
        bp_input_label.setText("______");

        validate();
        repaint();

    }

    public Date getCurrentDate() {
        SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        fr.format(date);
        return date;

    }

    public void resetMedicinePanel() {
        selected_medicine_panel.removeAll();
        validate();
        repaint();
    }

    private void add_medicine_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_medicine_btnActionPerformed
        long date_in_time_format = date_input.getDate().getTime();
        searchByDate(date_in_time_format);

    }//GEN-LAST:event_add_medicine_btnActionPerformed

    private void add_medicine_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_medicine_btnMouseExited
        //        add_medicine_btn.setBorder(DEFAULT_BTN_BORDER);
    }//GEN-LAST:event_add_medicine_btnMouseExited

    private void add_medicine_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_medicine_btnMouseEntered
        //        add_medicine_btn.setBorder(HOVER_BTN_BORDER);
    }//GEN-LAST:event_add_medicine_btnMouseEntered

    private void new_test_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_new_test_btnActionPerformed
        home.showPageOnWindow("reports");
        home.showReportOnWindow("Test");
        //        savePrescriptionPatientDetails();
    }//GEN-LAST:event_new_test_btnActionPerformed

    private void new_test_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_new_test_btnMouseExited
        new_test_btn.setBorder(HOVER_BTN_BORDER);
    }//GEN-LAST:event_new_test_btnMouseExited

    private void new_test_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_new_test_btnMouseEntered
        //  prescription_save_btn.setBorder(INPUT_BORDER);
        new_test_btn.setBorder(new LineBorder(Color.BLACK, 2, true));
    }//GEN-LAST:event_new_test_btnMouseEntered

    private void name_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name_inputMouseExited
        if (name_input.getBorder() != WARNING_BORDER) {
            name_input.setBorder(INPUT_BORDER);
        }
    }//GEN-LAST:event_name_inputMouseExited

    private void name_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name_inputMouseEntered
        if (name_input.getBorder() != WARNING_BORDER) {
            name_input.setBorder(HOVER_BORDER);
        }
    }//GEN-LAST:event_name_inputMouseEntered

    private void new_prescription_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_new_prescription_btnMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_new_prescription_btnMouseEntered

    private void new_prescription_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_new_prescription_btnMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_new_prescription_btnMouseExited

    private void new_prescription_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_new_prescription_btnActionPerformed
        home.showPageOnWindow("prescription");
    }//GEN-LAST:event_new_prescription_btnActionPerformed

    private void old_test_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_old_test_btnMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_old_test_btnMouseEntered

    private void old_test_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_old_test_btnMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_old_test_btnMouseExited

    private void old_test_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_old_test_btnActionPerformed
        home.showPageOnWindow("reports");
        home.showReportOnWindow("Test");
        home.setTestReport(patient_for_report_page);
    }//GEN-LAST:event_old_test_btnActionPerformed

    private void old_prescription_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_old_prescription_btnMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_old_prescription_btnMouseEntered

    private void old_prescription_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_old_prescription_btnMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_old_prescription_btnMouseExited

    private void old_prescription_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_old_prescription_btnActionPerformed
        home.showPageOnWindow("reports");
        home.showReportOnWindow("Prescription");
        home.searchReport(patient_for_report_page);

    }//GEN-LAST:event_old_prescription_btnActionPerformed

    private void reffer_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reffer_btnMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_reffer_btnMouseEntered

    private void reffer_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reffer_btnMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_reffer_btnMouseExited

    private void reffer_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reffer_btnActionPerformed
        home.showPageOnWindow("reports");
        home.showReportOnWindow("Medical");
    }//GEN-LAST:event_reffer_btnActionPerformed

    private void name_inputCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_name_inputCaretUpdate
        if (evt.getMark() != 0) {
            String name = name_input.getText().trim();
            searchByName(name);
        }
    }//GEN-LAST:event_name_inputCaretUpdate

    private void pno_inputCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_pno_inputCaretUpdate

        if (evt.getMark() != 0) {
            String pno = pno_input.getText().trim();
            try {
                searchByPno(Integer.parseInt(pno));
            } catch (NumberFormatException exp) {
                System.err.println("Enter valid patien number");
            }

        }
    }//GEN-LAST:event_pno_inputCaretUpdate

    private void mobile_number_inputCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_mobile_number_inputCaretUpdate

        if (evt.getMark() != 0) {
            String mobile_number = mobile_number_input.getText().trim();
            try {

                searchByMobileNumber(mobile_number);

            } catch (NumberFormatException exp) {
                System.err.println("Enter valid patien number");
            }

        }
    }//GEN-LAST:event_mobile_number_inputCaretUpdate


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Prescription;
    private javax.swing.JButton add_medicine_btn;
    private javax.swing.JLabel age_input_label;
    private javax.swing.JLabel bp_input_label;
    private com.toedter.calendar.JDateChooser date_input;
    private javax.swing.JLabel date_input_label;
    private javax.swing.JLabel diseases_input_label;
    private javax.swing.JRadioButton female_btn;
    private javax.swing.JLabel gender_input_label;
    private javax.swing.ButtonGroup group_btn;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JRadioButton male_btn;
    private javax.swing.JTextField mobile_number_input;
    private javax.swing.JLabel mobile_number_input_label;
    private javax.swing.JTextField name_input;
    private javax.swing.JLabel name_input_label;
    private javax.swing.JLabel name_label17;
    private javax.swing.JLabel name_label18;
    private javax.swing.JLabel name_label23;
    private javax.swing.JButton new_prescription_btn;
    private javax.swing.JButton new_test_btn;
    private javax.swing.JButton old_prescription_btn;
    private javax.swing.JButton old_test_btn;
    private javax.swing.JPanel patient_list_panel;
    private javax.swing.JPanel patient_panel_head;
    private javax.swing.JLabel patient_panel_header_title;
    private javax.swing.JTable patient_table;
    private javax.swing.JScrollPane patient_table_scroll;
    private javax.swing.JTextField pno_input;
    private javax.swing.JPanel prescription_foot_panel;
    private javax.swing.JPanel prescription_form;
    private javax.swing.JPanel prescription_form_panel;
    private javax.swing.JPanel prescription_head_panel;
    private javax.swing.JPanel prescrption_left_panel;
    private javax.swing.JPanel prescrption_right_panel;
    private javax.swing.JButton reffer_btn;
    private javax.swing.JPanel refresh;
    private javax.swing.JPanel selected_medicine_panel;
    private com.toedter.calendar.JDateChooser test_date;
    private javax.swing.JList<String> test_list;
    private javax.swing.JLabel weight_input_label;
    // End of variables declaration//GEN-END:variables
}
