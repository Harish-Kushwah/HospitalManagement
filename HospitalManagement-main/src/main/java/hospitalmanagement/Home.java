/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hospitalmanagement;

import java.awt.CardLayout;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import myutil.Database;
import myutil.GradientPanel;
import myutil.*;
import myutil.M_BandType;

public class Home extends javax.swing.JFrame {

    PatientDetails PATIENT_DETAILS = null;
    CardLayout card;
    String page_showing = null;
    final LineBorder HOVER_BTN_BORDER = new LineBorder(new Color(0x7C7CF1), 2, true);
    final LineBorder DEFAULT_BTN_BORDER = new LineBorder(Color.WHITE, 1, true);
    final LineBorder DEFAULT_BORDER = new LineBorder(Color.blue, 1, true);
    final LineBorder INPUT_BORDER = new LineBorder(new Color(0x7c7cf1), 1, true);
    final LineBorder HOVER_BORDER = new LineBorder(new Color(0x7C7CF1), 2, true);
    final LineBorder WARNING_BORDER = new LineBorder(new Color(0xff3333), 2, true);
    final Color WARNING_COLOR = new Color(16724787);
    final Color SUCCESS_COLOR = new Color(0, 153, 0);

    JPanel newDashboardPanel = new NewDashboardPanel();
    static int total_medicine_selected = 0;
    JPanel main_list;

    ArrayList<MedicineRowPanel> bt = new ArrayList<MedicineRowPanel>();

//  public  JComponent getSelectedMedicineTable()
//    {
//        Object  [][]data={};
//        MultipleComponentCellTest t1 = new MultipleComponentCellTest(data);
//        tableModel  = t1.getTableModel();
//        return  t1.getTable();
//    }
    public void setMedicineOnMedicineInputField() {
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
                Database database = Database.getInstance();
                String text = medicine_input.getText();
                ArrayList<String> medi = database.getLikeMedicine(text);
                DefaultListModel lm = new DefaultListModel();
                for (String m : medi) {
                    lm.addElement(m);
                }
                medicine_list.setModel(lm);
            }
        };
        medicine_input.getDocument().addDocumentListener(dl);
        medicine_list_panel.add(new JScrollPane(medicine_list));

    }
    Dictionary<String, Integer> valid_patients_inputes = new Hashtable();
    Dictionary<String, Integer> valid_prescription_inputes = new Hashtable();

    public void setValidationListenerOnInputs() {
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

                //for patients inputs
                InputValidation validate = new InputValidation();

                if (page_showing.equalsIgnoreCase("patient")) {
                    String name = name_input.getText();
                    if (name.length() != 0 && !validate.isVlalidName(name)) {
                        name_input.setBorder(WARNING_BORDER);
                        status_label.setText("Enter valid name");
                        valid_patients_inputes.put("name", 0);
                    } else {
                        name_input.setBorder(INPUT_BORDER);
                        valid_patients_inputes.put("name", 1);
                    }

                    String mobile = mobile_number_input.getText();
                    mobile = mobile.trim();
                    if (mobile.length() != 0 && !validate.isValidMobileNumber(mobile)) {
                        mobile_number_input.setBorder(WARNING_BORDER);
                        status_label.setText("Enter valid mobile number");
                        valid_patients_inputes.put("mobile", 0);
                    } else {
                        mobile_number_input.setBorder(INPUT_BORDER);
                        valid_patients_inputes.put("mobile", 1);
                        System.out.println("mobile");
                    }

                    String age = age_input.getText();
                    if (age.length() != 0 && !validate.isValidAge(age)) {
                        age_input.setBorder(WARNING_BORDER);
                        status_label.setText("Enter valid Age");
                        valid_patients_inputes.put("age", 0);
                    } else {
                        age_input.setBorder(INPUT_BORDER);
                        valid_patients_inputes.put("age", 1);
                    }
                    String weight = weight_input.getText();
                    if (weight.length() != 0 && !validate.isValidWeight(weight)) {
                        weight_input.setBorder(WARNING_BORDER);
                        status_label.setText("Enter valid Weight");
                        valid_patients_inputes.put("weight", 0);
                    } else {
                        weight_input.setBorder(INPUT_BORDER);
                        valid_patients_inputes.put("weight", 1);
                    }
                    String pressure = blood_pressure_input.getText();
                    if (pressure.length() != 0 && !validate.isValidBloodPressure(pressure)) {
                        blood_pressure_input.setBorder(WARNING_BORDER);
                        status_label.setText("Enter valid blood pressue");
                        valid_patients_inputes.put("pressure", 0);
                    } else {
                        blood_pressure_input.setBorder(INPUT_BORDER);
                        valid_patients_inputes.put("pressure", 1);
                    }

                    String pulse = pulse_input.getText();
                    if (pulse.length() != 0 && !validate.isValidPulseRate(pulse)) {
                        pulse_input.setBorder(WARNING_BORDER);
                        status_label.setText("Enter valid pulse rate");
                        valid_patients_inputes.put("pulse", 0);
                    } else {
                        pulse_input.setBorder(INPUT_BORDER);
                        valid_patients_inputes.put("pulse", 1);
                    }

                    String sugar = sugar_input.getText();
                    if (sugar.length() != 0 && !validate.isValidSugar(sugar)) {
                        sugar_input.setBorder(WARNING_BORDER);
                        status_label.setText("Enter valid sugar level");
                        valid_patients_inputes.put("sugar", 0);
                    } else {
                        sugar_input.setBorder(INPUT_BORDER);
                        valid_patients_inputes.put("sugar", 1);
                    }

                    int pr = 1;
                    Enumeration<String> keys = valid_patients_inputes.keys();
                    while (keys.hasMoreElements()) {
                        String key = keys.nextElement();
                        if (!key.equalsIgnoreCase("is_all_inputes_valid")) {
                            pr *= valid_patients_inputes.get(key);
                        }
                    }
                    valid_patients_inputes.put("is_all_inputes_valid", pr);
                    if (pr == 1) {

                        status_label.setText("");
                        status_label.setForeground(SUCCESS_COLOR);
                    } else {
                        status_label.setForeground(WARNING_COLOR);
                    }
                }//for prescription inputes
                else if (page_showing.equalsIgnoreCase("prescription")) {

                    String p_name = prescription_name_input.getText();
                    if (p_name.length() != 0 && !validate.isVlalidName(p_name)) {
                        prescription_name_input.setBorder(WARNING_BORDER);
                        prescription_status_label.setText("Enter valid name");
                        valid_prescription_inputes.put("p_name", 0);
                    } else {
                        prescription_name_input.setBorder(INPUT_BORDER);
                        valid_prescription_inputes.put("p_name", 1);

                    }

                    String p_mobile = prescription_mobile_number_input.getText();
                    p_mobile = p_mobile.trim();
                    if (p_mobile.length() != 0 && !validate.isValidMobileNumber(p_mobile)) {
                        prescription_mobile_number_input.setBorder(WARNING_BORDER);
                        prescription_status_label.setText("Enter valid mobile number");
                        valid_prescription_inputes.put("p_mobile", 0);
                    } else {
                        prescription_mobile_number_input.setBorder(INPUT_BORDER);
                        valid_prescription_inputes.put("p_mobile", 1);

                    }

                    int p_pr = 1;
                    Enumeration<String> keys1 = valid_prescription_inputes.keys();
                    while (keys1.hasMoreElements()) {
                        String key = keys1.nextElement();
                        if (!key.equalsIgnoreCase("is_all_inputes_valid")) {
                            p_pr *= valid_prescription_inputes.get(key);
                        }

                    }
                    valid_prescription_inputes.put("is_all_inputes_valid", p_pr);

                    if (p_pr == 1) {
                        prescription_status_label.setText("");
                        prescription_status_label.setForeground(SUCCESS_COLOR);
                    } else {
                        prescription_status_label.setForeground(WARNING_COLOR);
                    }
                }

            }
        };
        name_input.getDocument().addDocumentListener(dl);
        mobile_number_input.getDocument().addDocumentListener(dl);
        age_input.getDocument().addDocumentListener(dl);
        weight_input.getDocument().addDocumentListener(dl);
        blood_pressure_input.getDocument().addDocumentListener(dl);
        pulse_input.getDocument().addDocumentListener(dl);
        sugar_input.getDocument().addDocumentListener(dl);

        prescription_name_input.getDocument().addDocumentListener(dl);
        prescription_mobile_number_input.getDocument().addDocumentListener(dl);
    }

    public void addMedicineRowInPanelForm() {
        main_list = new JPanel();
        main_list.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = -1;
        gbc.weighty = 1;
        main_list.add(new JPanel(), gbc);
        JScrollPane sc = new JScrollPane(main_list);
        selected_medicine_panel.add(sc);
    }

    public Home() {
        ImageIcon icon = new ImageIcon("./images/doctor_icon1.png");
        this.setIconImage(icon.getImage());
        setSize(1100, 700);
        setLocationRelativeTo(null);
        initComponents();

        menu_panel.setBackground(new Color(0x021036));
        Dashboard.setLayout(new BorderLayout());
        Dashboard.add(newDashboardPanel, BorderLayout.CENTER);

        card = (CardLayout) main_panel.getLayout();
        card.show(main_panel, "patient");
        page_showing = "patient";

        addMedicineRowInPanelForm();
        setMedicineOnMedicineInputField();

        setValidationListenerOnInputs();  //for patient

        //set cuurent date on the patient page when loads
        date_input.setDate(getCurrentDate());
        male_radio_btn.setSelected(true);

        //set cuurent date on the prescription page when loads
        prescription_date_input.setDate(getCurrentDate());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gender_radio_group = new javax.swing.ButtonGroup();
        jSplitPane1 = new javax.swing.JSplitPane();
        jButton2 = new javax.swing.JButton();
        header = header = new GradientPanel(new Color(0x589BE8),new Color(0x5AEEB2),1100,50);
        heade_label = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        doctor_icon_panel = new JPanel();
        doctor_icon_panel.add(new SetImageIcon(new ImageIcon("./images/doctor_icon1.png"),35,35)) ;
        footer = footer = new GradientPanel(new Color(0x5AEEB2),new Color(0x9FC3EE),1100,30);
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        menu_panel = new javax.swing.JPanel();
        dashboard_label = new javax.swing.JLabel();
        patient_label = new javax.swing.JLabel();
        prescription_label = new javax.swing.JLabel();
        reports_label = new javax.swing.JLabel();
        dashboard_icon = new JPanel();
        dashboard_icon.add(new SetImageIcon(new ImageIcon("./images/dashboard_icon.png"),30,30)) ;
        patient_icon = new JPanel();
        patient_icon.add(new SetImageIcon(new ImageIcon("./images/patient_icon1.png"),30,30)) ;
        prescription_icon = new JPanel();
        prescription_icon.add(new SetImageIcon(new ImageIcon("./images/prescription_icon.png"),30,30)) ;
        reports_icon = new JPanel();
        reports_icon.add(new SetImageIcon(new ImageIcon("./images/reports_icon.png"),30,30)) ;
        new_report_menue = new javax.swing.JPanel();
        dashboard_label13 = new javax.swing.JLabel();
        dashboard_label14 = new javax.swing.JLabel();
        dashboard_label15 = new javax.swing.JLabel();
        main_panel = new javax.swing.JPanel();
        Prescription = new javax.swing.JPanel();
        prescription_form_panel = prescription_form_panel = new GradientPanel(new Color(0xe8feff),new Color(0xe8f3ff) , 300,600);
        prescription_form = new JPanel();
        prescription_form.setBackground(new Color(202,177,18));
        prescription_mobile_number_input = new javax.swing.JTextField();
        name_label16 = new javax.swing.JLabel();
        prescription_name_input = new javax.swing.JTextField();
        name_label17 = new javax.swing.JLabel();
        prescription_save_btn = new javax.swing.JButton();
        prescription_next_btn = new javax.swing.JButton();
        name_label23 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        prescription_date_input = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        medicine_input = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        add_medicine_btn = new javax.swing.JButton();
        selected_medicine_panel = new javax.swing.JPanel();
        prescription_delete_btn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        medicine_list_panel = new javax.swing.JPanel();
        medicine_list = new javax.swing.JList<>();
        jLabel10 = new javax.swing.JLabel();
        prescription_status_label = new javax.swing.JLabel();
        prescription_panel_head1 = new javax.swing.JPanel();
        patient_panel_header_title1 = new javax.swing.JLabel();
        Reports = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        medical_report = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        dashboard_label5 = new javax.swing.JLabel();
        dashboard_label6 = new javax.swing.JLabel();
        mobile_number_input1 = new javax.swing.JTextField();
        mobile_number_input2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        test_application = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        dashboard_label7 = new javax.swing.JLabel();
        dashboard_label8 = new javax.swing.JLabel();
        mobile_number_input3 = new javax.swing.JTextField();
        mobile_number_input4 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        reference_letter = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        dashboard_label9 = new javax.swing.JLabel();
        dashboard_label10 = new javax.swing.JLabel();
        mobile_number_input5 = new javax.swing.JTextField();
        mobile_number_input6 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        dashboard_label11 = new javax.swing.JLabel();
        mobile_number_input7 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        dashboard_label12 = new javax.swing.JLabel();
        Dashboard = new javax.swing.JPanel();
        Patient = new javax.swing.JPanel();
        patient_panel_head = new javax.swing.JPanel();
        patient_panel_header_title = new javax.swing.JLabel();
        patient_form_panel = patient_form_panel = new GradientPanel(new Color(0xe8feff),new Color(0xe8f3ff) , 300,600);
        patient_form_panel1 = new JPanel();
        patient_form_panel1.setBackground(new Color(202,177,18));
        mobile_number_input = new javax.swing.JTextField();
        name_label7 = new javax.swing.JLabel();
        age_input = new javax.swing.JTextField();
        blood_pressure_input = new javax.swing.JTextField();
        name_input = new javax.swing.JTextField();
        name_label8 = new javax.swing.JLabel();
        pulse_input = new javax.swing.JTextField();
        name_label9 = new javax.swing.JLabel();
        weight_input = new javax.swing.JTextField();
        name_label10 = new javax.swing.JLabel();
        name_label11 = new javax.swing.JLabel();
        name_label12 = new javax.swing.JLabel();
        name_label13 = new javax.swing.JLabel();
        male_radio_btn = new javax.swing.JRadioButton();
        female_radio_btn = new javax.swing.JRadioButton();
        save_btn = new javax.swing.JButton();
        next_btn = new javax.swing.JButton();
        name_label14 = new javax.swing.JLabel();
        sugar_input = new javax.swing.JTextField();
        vomiting_chk = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        cold_and_flue_chk = new javax.swing.JCheckBox();
        fever_chk = new javax.swing.JCheckBox();
        runny_nose_chk = new javax.swing.JCheckBox();
        headache_chk = new javax.swing.JCheckBox();
        cough_chk = new javax.swing.JCheckBox();
        diarrhea_chk = new javax.swing.JCheckBox();
        weakness_chk = new javax.swing.JCheckBox();
        bodyache_chk = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        other_symptoms_input = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        name_label15 = new javax.swing.JLabel();
        date_input = new com.toedter.calendar.JDateChooser();
        status_label = new javax.swing.JLabel();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        header.setBackground(new java.awt.Color(153, 255, 255));
        header.setPreferredSize(new java.awt.Dimension(1100, 49));

        heade_label.setFont(new java.awt.Font("Bell MT", 1, 24)); // NOI18N
        heade_label.setText("SHRIGURU CLINIC");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setText("Dr.Ajit Pawar");

        doctor_icon_panel.setPreferredSize(new java.awt.Dimension(35, 35));
        doctor_icon_panel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(heade_label, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 838, Short.MAX_VALUE)
                .addComponent(doctor_icon_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(52, 52, 52))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(heade_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(doctor_icon_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        getContentPane().add(header, java.awt.BorderLayout.PAGE_START);

        footer.setBackground(new java.awt.Color(204, 255, 204));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 153));
        jLabel4.setText(" By PatilInfo Tech");

        jLabel5.setText("2023 Copyright");

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, footerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(17, 17, 17))
        );

        getContentPane().add(footer, java.awt.BorderLayout.PAGE_END);

        menu_panel.setBackground(new java.awt.Color(0, 255, 204));
        menu_panel.setForeground(new java.awt.Color(255, 255, 255));
        menu_panel.setMaximumSize(new java.awt.Dimension(3000, 32767));

        dashboard_label.setBackground(new java.awt.Color(255, 51, 51));
        dashboard_label.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        dashboard_label.setForeground(new java.awt.Color(255, 255, 255));
        dashboard_label.setText("Dashbord");
        dashboard_label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboard_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard_labelMouseClicked(evt);
            }
        });

        patient_label.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        patient_label.setForeground(new java.awt.Color(255, 255, 255));
        patient_label.setText("Patient");
        patient_label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        patient_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patient_labelMouseClicked(evt);
            }
        });

        prescription_label.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        prescription_label.setForeground(new java.awt.Color(255, 255, 255));
        prescription_label.setText("Prescription");
        prescription_label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        prescription_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prescription_labelMouseClicked(evt);
            }
        });

        reports_label.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        reports_label.setForeground(new java.awt.Color(255, 255, 255));
        reports_label.setText("Reports");
        reports_label.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reports_label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reports_labelMouseClicked(evt);
            }
        });

        dashboard_icon.setBackground(new java.awt.Color(0, 0, 102));
        dashboard_icon.setForeground(new java.awt.Color(0, 0, 102));
        dashboard_icon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboard_icon.setPreferredSize(new java.awt.Dimension(30, 30));
        dashboard_icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard_iconMouseClicked(evt);
            }
        });
        dashboard_icon.setLayout(new java.awt.CardLayout());

        patient_icon.setBackground(new java.awt.Color(0, 0, 102));
        patient_icon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        patient_icon.setPreferredSize(new java.awt.Dimension(30, 30));
        patient_icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patient_iconMouseClicked(evt);
            }
        });
        patient_icon.setLayout(new java.awt.CardLayout());

        prescription_icon.setBackground(new java.awt.Color(0, 0, 102));
        prescription_icon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        prescription_icon.setPreferredSize(new java.awt.Dimension(30, 30));
        prescription_icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                prescription_iconMouseClicked(evt);
            }
        });
        prescription_icon.setLayout(new java.awt.CardLayout());

        reports_icon.setBackground(new java.awt.Color(0, 0, 102));
        reports_icon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reports_icon.setPreferredSize(new java.awt.Dimension(30, 30));
        reports_icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reports_iconMouseClicked(evt);
            }
        });
        reports_icon.setLayout(new java.awt.CardLayout());

        new_report_menue.setBackground(new java.awt.Color(0, 255, 204));
        new_report_menue.setPreferredSize(new java.awt.Dimension(170, 787));
        new_report_menue.setVisible(false);

        dashboard_label13.setBackground(new java.awt.Color(255, 51, 51));
        dashboard_label13.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        dashboard_label13.setForeground(new java.awt.Color(255, 255, 255));
        dashboard_label13.setText("Medical Report");
        dashboard_label13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboard_label13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard_label13MouseClicked(evt);
            }
        });

        dashboard_label14.setBackground(new java.awt.Color(255, 51, 51));
        dashboard_label14.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        dashboard_label14.setForeground(new java.awt.Color(255, 255, 255));
        dashboard_label14.setText("Test Application");
        dashboard_label14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboard_label14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard_label14MouseClicked(evt);
            }
        });

        dashboard_label15.setBackground(new java.awt.Color(255, 51, 51));
        dashboard_label15.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        dashboard_label15.setForeground(new java.awt.Color(255, 255, 255));
        dashboard_label15.setText("Reference Letter");
        dashboard_label15.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboard_label15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard_label15MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout new_report_menueLayout = new javax.swing.GroupLayout(new_report_menue);
        new_report_menue.setLayout(new_report_menueLayout);
        new_report_menueLayout.setHorizontalGroup(
            new_report_menueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(new_report_menueLayout.createSequentialGroup()
                .addGroup(new_report_menueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(new_report_menueLayout.createSequentialGroup()
                        .addGroup(new_report_menueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(new_report_menueLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(dashboard_label13, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(new_report_menueLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(dashboard_label15, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, new_report_menueLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(dashboard_label14, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        new_report_menueLayout.setVerticalGroup(
            new_report_menueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(new_report_menueLayout.createSequentialGroup()
                .addComponent(dashboard_label13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dashboard_label14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dashboard_label15, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1095, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout menu_panelLayout = new javax.swing.GroupLayout(menu_panel);
        menu_panel.setLayout(menu_panelLayout);
        menu_panelLayout.setHorizontalGroup(
            menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_panelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(new_report_menue, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menu_panelLayout.createSequentialGroup()
                        .addGroup(menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(menu_panelLayout.createSequentialGroup()
                                .addComponent(prescription_icon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(prescription_label, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(menu_panelLayout.createSequentialGroup()
                                    .addComponent(reports_icon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(reports_label, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(menu_panelLayout.createSequentialGroup()
                                    .addComponent(dashboard_icon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(dashboard_label, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(menu_panelLayout.createSequentialGroup()
                                    .addComponent(patient_icon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(patient_label, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(22, 22, 22)))
                .addContainerGap())
        );
        menu_panelLayout.setVerticalGroup(
            menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menu_panelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dashboard_label, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dashboard_icon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(patient_label, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(patient_icon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prescription_label, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(prescription_icon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(menu_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(reports_label, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reports_icon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(new_report_menue, javax.swing.GroupLayout.DEFAULT_SIZE, 1196, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );

        getContentPane().add(menu_panel, java.awt.BorderLayout.LINE_START);

        main_panel.setLayout(new java.awt.CardLayout());

        Prescription.setBackground(new java.awt.Color(255, 153, 153));
        Prescription.setForeground(new java.awt.Color(255, 255, 255));
        Prescription.setLayout(new java.awt.BorderLayout());

        prescription_form_panel.setBackground(new java.awt.Color(255, 255, 255));
        prescription_form_panel.setPreferredSize(new java.awt.Dimension(1100, 700));

        prescription_form.setBackground(new java.awt.Color(251, 252, 224));
        prescription_form.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        prescription_form.setPreferredSize(new java.awt.Dimension(910, 530));

        prescription_mobile_number_input.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        prescription_mobile_number_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        prescription_mobile_number_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                prescription_mobile_number_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                prescription_mobile_number_inputMouseExited(evt);
            }
        });
        prescription_mobile_number_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prescription_mobile_number_inputActionPerformed(evt);
            }
        });

        name_label16.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        name_label16.setText("Name :-");

        prescription_name_input.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        prescription_name_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        prescription_name_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                prescription_name_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                prescription_name_inputMouseExited(evt);
            }
        });
        prescription_name_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prescription_name_inputActionPerformed(evt);
            }
        });

        name_label17.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        name_label17.setText("Mobile no:-");

        prescription_save_btn.setBackground(new java.awt.Color(51, 102, 255));
        prescription_save_btn.setForeground(new java.awt.Color(255, 255, 255));
        prescription_save_btn.setText("Save");
        prescription_save_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        prescription_save_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        prescription_save_btn.setFocusPainted(false);
        prescription_save_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                prescription_save_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                prescription_save_btnMouseExited(evt);
            }
        });
        prescription_save_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prescription_save_btnActionPerformed(evt);
            }
        });

        prescription_next_btn.setBackground(new java.awt.Color(153, 255, 153));
        prescription_next_btn.setText("Next");
        prescription_next_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        prescription_next_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        prescription_next_btn.setFocusPainted(false);
        prescription_next_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                prescription_next_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                prescription_next_btnMouseExited(evt);
            }
        });
        prescription_next_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prescription_next_btnActionPerformed(evt);
            }
        });

        name_label23.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        name_label23.setText("Date :-");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        prescription_date_input.setDateFormatString("dd-MM-yyyy");

        jLabel8.setText("Enter Medicine :-");

        medicine_input.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        medicine_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                medicine_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                medicine_inputMouseExited(evt);
            }
        });
        medicine_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                medicine_inputActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Selected Medicine ");

        add_medicine_btn.setBackground(new java.awt.Color(0, 51, 255));
        add_medicine_btn.setForeground(new java.awt.Color(255, 255, 255));
        add_medicine_btn.setText("Add");
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
        selected_medicine_panel.setLayout(new java.awt.BorderLayout());

        prescription_delete_btn.setBackground(new java.awt.Color(255, 51, 51));
        prescription_delete_btn.setForeground(new java.awt.Color(255, 255, 255));
        prescription_delete_btn.setText("Delete");
        prescription_delete_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true));
        prescription_delete_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        prescription_delete_btn.setFocusPainted(false);
        prescription_delete_btn.setPreferredSize(new java.awt.Dimension(27, 18));
        prescription_delete_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                prescription_delete_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                prescription_delete_btnMouseExited(evt);
            }
        });
        prescription_delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prescription_delete_btnActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Doses");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("remove");

        medicine_list_panel.setPreferredSize(new java.awt.Dimension(0, 240));
        medicine_list_panel.setLayout(new java.awt.CardLayout());

        medicine_list.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        medicine_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                medicine_listMouseClicked(evt);
            }
        });
        medicine_list_panel.add(medicine_list, "card2");

        prescription_status_label.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        prescription_status_label.setForeground(new java.awt.Color(0, 153, 0));

        javax.swing.GroupLayout prescription_formLayout = new javax.swing.GroupLayout(prescription_form);
        prescription_form.setLayout(prescription_formLayout);
        prescription_formLayout.setHorizontalGroup(
            prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prescription_formLayout.createSequentialGroup()
                .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, prescription_formLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE))
                    .addGroup(prescription_formLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(name_label16, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prescription_name_input, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(name_label17, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(prescription_mobile_number_input, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(name_label23, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prescription_date_input, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(prescription_formLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(prescription_formLayout.createSequentialGroup()
                                .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(prescription_formLayout.createSequentialGroup()
                                        .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(prescription_formLayout.createSequentialGroup()
                                                .addComponent(medicine_input, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(add_medicine_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(prescription_status_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(medicine_list_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(9, 9, 9)))
                                .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(prescription_formLayout.createSequentialGroup()
                                        .addGap(45, 45, 45)
                                        .addComponent(prescription_save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(36, 36, 36)
                                        .addComponent(prescription_next_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(prescription_formLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(selected_medicine_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(prescription_formLayout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addGap(288, 288, 288)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(8, 8, 8))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, prescription_formLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(prescription_delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        prescription_formLayout.setVerticalGroup(
            prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(prescription_formLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(prescription_date_input, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(name_label23, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(prescription_mobile_number_input, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(name_label17, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(prescription_name_input, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(name_label16, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, prescription_formLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(medicine_input, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add_medicine_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(medicine_list_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(prescription_formLayout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selected_medicine_panel, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(prescription_formLayout.createSequentialGroup()
                        .addComponent(prescription_delete_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addGroup(prescription_formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(prescription_next_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(prescription_save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(prescription_formLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(prescription_status_label, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        prescription_form_panel.add(prescription_form);

        Prescription.add(prescription_form_panel, java.awt.BorderLayout.CENTER);

        prescription_panel_head1.setBackground(new java.awt.Color(0, 0, 102));
        prescription_panel_head1.setForeground(new java.awt.Color(51, 255, 255));
        prescription_panel_head1.setMinimumSize(new java.awt.Dimension(169, 40));
        prescription_panel_head1.setPreferredSize(new java.awt.Dimension(169, 35));

        patient_panel_header_title1.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        patient_panel_header_title1.setForeground(new java.awt.Color(102, 255, 255));
        patient_panel_header_title1.setText("Prescription");
        prescription_panel_head1.add(patient_panel_header_title1);

        Prescription.add(prescription_panel_head1, java.awt.BorderLayout.PAGE_START);

        main_panel.add(Prescription, "prescription");

        Reports.setBackground(new java.awt.Color(0, 0, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 153));
        jPanel2.setLayout(new java.awt.CardLayout());

        medical_report.setBackground(new java.awt.Color(255, 102, 255));
        medical_report.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(153, 255, 153));

        dashboard_label5.setBackground(new java.awt.Color(255, 51, 51));
        dashboard_label5.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        dashboard_label5.setForeground(new java.awt.Color(255, 255, 255));
        dashboard_label5.setText("Name");
        dashboard_label5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboard_label5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard_label5MouseClicked(evt);
            }
        });

        dashboard_label6.setBackground(new java.awt.Color(255, 51, 51));
        dashboard_label6.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        dashboard_label6.setForeground(new java.awt.Color(255, 255, 255));
        dashboard_label6.setText("Id");
        dashboard_label6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboard_label6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard_label6MouseClicked(evt);
            }
        });

        mobile_number_input1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mobile_number_input1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        mobile_number_input1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mobile_number_input1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mobile_number_input1MouseExited(evt);
            }
        });
        mobile_number_input1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobile_number_input1ActionPerformed(evt);
            }
        });

        mobile_number_input2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mobile_number_input2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        mobile_number_input2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mobile_number_input2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mobile_number_input2MouseExited(evt);
            }
        });
        mobile_number_input2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobile_number_input2ActionPerformed(evt);
            }
        });

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(dashboard_label6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mobile_number_input1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(dashboard_label5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mobile_number_input2)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(313, 313, 313)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dashboard_label5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dashboard_label6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mobile_number_input1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mobile_number_input2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jButton1))
        );

        medical_report.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel4.setBackground(new java.awt.Color(153, 255, 204));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "NAME", "DATE", "MEDICINE"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jButton3.setText("Print");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(985, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(16, 16, 16))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1064, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(411, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(161, 161, 161))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(246, Short.MAX_VALUE)))
        );

        medical_report.add(jPanel4, java.awt.BorderLayout.CENTER);

        jPanel2.add(medical_report, "card2");

        test_application.setBackground(new java.awt.Color(153, 153, 255));
        test_application.setLayout(new java.awt.BorderLayout());

        jPanel5.setBackground(new java.awt.Color(153, 255, 153));

        dashboard_label7.setBackground(new java.awt.Color(255, 51, 51));
        dashboard_label7.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        dashboard_label7.setForeground(new java.awt.Color(255, 255, 255));
        dashboard_label7.setText("Name");
        dashboard_label7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboard_label7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard_label7MouseClicked(evt);
            }
        });

        dashboard_label8.setBackground(new java.awt.Color(255, 51, 51));
        dashboard_label8.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        dashboard_label8.setForeground(new java.awt.Color(255, 255, 255));
        dashboard_label8.setText("Id");
        dashboard_label8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboard_label8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard_label8MouseClicked(evt);
            }
        });

        mobile_number_input3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mobile_number_input3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        mobile_number_input3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mobile_number_input3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mobile_number_input3MouseExited(evt);
            }
        });
        mobile_number_input3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobile_number_input3ActionPerformed(evt);
            }
        });

        mobile_number_input4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mobile_number_input4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        mobile_number_input4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mobile_number_input4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mobile_number_input4MouseExited(evt);
            }
        });
        mobile_number_input4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobile_number_input4ActionPerformed(evt);
            }
        });

        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(dashboard_label8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mobile_number_input3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(dashboard_label7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mobile_number_input4)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(313, 313, 313)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dashboard_label7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dashboard_label8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mobile_number_input3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mobile_number_input4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jButton4))
        );

        test_application.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        jPanel6.setBackground(new java.awt.Color(153, 255, 204));

        jButton5.setText("Print");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel11.setText("Sono Graphy");

        jCheckBox1.setText("Abdomen");

        jCheckBox2.setText("3D - 4D Obsteric");

        jCheckBox3.setText("Abdomen");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(985, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(16, 16, 16))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox2)
                .addGap(21, 21, 21)
                .addComponent(jCheckBox1)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 262, Short.MAX_VALUE)
                .addComponent(jButton5)
                .addGap(161, 161, 161))
        );

        test_application.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel2.add(test_application, "card3");

        reference_letter.setBackground(new java.awt.Color(153, 204, 255));
        reference_letter.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(153, 255, 153));

        dashboard_label9.setBackground(new java.awt.Color(255, 51, 51));
        dashboard_label9.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        dashboard_label9.setForeground(new java.awt.Color(255, 255, 255));
        dashboard_label9.setText("Name");
        dashboard_label9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboard_label9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard_label9MouseClicked(evt);
            }
        });

        dashboard_label10.setBackground(new java.awt.Color(255, 51, 51));
        dashboard_label10.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        dashboard_label10.setForeground(new java.awt.Color(255, 255, 255));
        dashboard_label10.setText("Id");
        dashboard_label10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboard_label10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard_label10MouseClicked(evt);
            }
        });

        mobile_number_input5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mobile_number_input5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        mobile_number_input5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mobile_number_input5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mobile_number_input5MouseExited(evt);
            }
        });
        mobile_number_input5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobile_number_input5ActionPerformed(evt);
            }
        });

        mobile_number_input6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mobile_number_input6.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        mobile_number_input6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mobile_number_input6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mobile_number_input6MouseExited(evt);
            }
        });
        mobile_number_input6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobile_number_input6ActionPerformed(evt);
            }
        });

        jButton6.setText("Search");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(dashboard_label10, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mobile_number_input5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(dashboard_label9, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(mobile_number_input6)
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(313, 313, 313)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dashboard_label9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dashboard_label10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mobile_number_input5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mobile_number_input6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jButton6))
        );

        reference_letter.add(jPanel7, java.awt.BorderLayout.PAGE_START);

        jPanel8.setBackground(new java.awt.Color(153, 255, 204));

        jButton7.setText("Print");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        dashboard_label11.setBackground(new java.awt.Color(255, 51, 51));
        dashboard_label11.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        dashboard_label11.setForeground(new java.awt.Color(255, 255, 255));
        dashboard_label11.setText("Name of Doctor");
        dashboard_label11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboard_label11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard_label11MouseClicked(evt);
            }
        });

        mobile_number_input7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mobile_number_input7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        mobile_number_input7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mobile_number_input7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mobile_number_input7MouseExited(evt);
            }
        });
        mobile_number_input7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobile_number_input7ActionPerformed(evt);
            }
        });

        dashboard_label12.setBackground(new java.awt.Color(255, 51, 51));
        dashboard_label12.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        dashboard_label12.setForeground(new java.awt.Color(255, 255, 255));
        dashboard_label12.setText("Date");
        dashboard_label12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dashboard_label12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dashboard_label12MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(985, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(16, 16, 16))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dashboard_label11, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dashboard_label12, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(mobile_number_input7, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dashboard_label11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mobile_number_input7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dashboard_label12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 304, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(161, 161, 161))
        );

        reference_letter.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel2.add(reference_letter, "card4");

        javax.swing.GroupLayout ReportsLayout = new javax.swing.GroupLayout(Reports);
        Reports.setLayout(ReportsLayout);
        ReportsLayout.setHorizontalGroup(
            ReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ReportsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1073, Short.MAX_VALUE)
                .addContainerGap())
        );
        ReportsLayout.setVerticalGroup(
            ReportsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ReportsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        main_panel.add(Reports, "reports");

        Dashboard.setBackground(new java.awt.Color(255, 255, 0));
        Dashboard.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout DashboardLayout = new javax.swing.GroupLayout(Dashboard);
        Dashboard.setLayout(DashboardLayout);
        DashboardLayout.setHorizontalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1085, Short.MAX_VALUE)
        );
        DashboardLayout.setVerticalGroup(
            DashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1426, Short.MAX_VALUE)
        );

        main_panel.add(Dashboard, "dashboard");

        Patient.setBackground(new java.awt.Color(204, 255, 255));
        Patient.setLayout(new java.awt.BorderLayout());

        patient_panel_head.setBackground(new java.awt.Color(0, 0, 102));
        patient_panel_head.setForeground(new java.awt.Color(51, 255, 255));
        patient_panel_head.setMinimumSize(new java.awt.Dimension(169, 40));
        patient_panel_head.setPreferredSize(new java.awt.Dimension(169, 35));

        patient_panel_header_title.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        patient_panel_header_title.setForeground(new java.awt.Color(102, 255, 255));
        patient_panel_header_title.setText("Patient Information");
        patient_panel_head.add(patient_panel_header_title);

        Patient.add(patient_panel_head, java.awt.BorderLayout.PAGE_START);

        patient_form_panel.setBackground(new java.awt.Color(255, 255, 255));
        patient_form_panel.setPreferredSize(new java.awt.Dimension(1100, 700));

        patient_form_panel1.setBackground(new java.awt.Color(251, 252, 224));
        patient_form_panel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        patient_form_panel1.setPreferredSize(new java.awt.Dimension(830, 530));

        mobile_number_input.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        mobile_number_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        mobile_number_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mobile_number_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mobile_number_inputMouseExited(evt);
            }
        });
        mobile_number_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobile_number_inputActionPerformed(evt);
            }
        });

        name_label7.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        name_label7.setText("Name :-");

        age_input.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        age_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        age_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                age_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                age_inputMouseExited(evt);
            }
        });
        age_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                age_inputActionPerformed(evt);
            }
        });

        blood_pressure_input.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        blood_pressure_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        blood_pressure_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                blood_pressure_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                blood_pressure_inputMouseExited(evt);
            }
        });
        blood_pressure_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blood_pressure_inputActionPerformed(evt);
            }
        });

        name_input.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        name_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        name_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                name_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                name_inputMouseExited(evt);
            }
        });
        name_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                name_inputActionPerformed(evt);
            }
        });

        name_label8.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        name_label8.setText("Mobile no:-");

        pulse_input.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pulse_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        pulse_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pulse_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pulse_inputMouseExited(evt);
            }
        });
        pulse_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pulse_inputActionPerformed(evt);
            }
        });

        name_label9.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        name_label9.setText("Age :-");

        weight_input.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        weight_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        weight_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                weight_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                weight_inputMouseExited(evt);
            }
        });
        weight_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weight_inputActionPerformed(evt);
            }
        });

        name_label10.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        name_label10.setText("Gender :-");

        name_label11.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        name_label11.setText("Weight :-");

        name_label12.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        name_label12.setText("Blood Pressure :-");

        name_label13.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        name_label13.setText("Pulse :-");

        male_radio_btn.setBackground(new java.awt.Color(251, 252, 224));
        gender_radio_group.add(male_radio_btn);
        male_radio_btn.setText("Male");
        male_radio_btn.setToolTipText("");
        male_radio_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        male_radio_btn.setFocusPainted(false);
        male_radio_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                male_radio_btnActionPerformed(evt);
            }
        });

        female_radio_btn.setBackground(new java.awt.Color(251, 252, 224));
        gender_radio_group.add(female_radio_btn);
        female_radio_btn.setText("Female");
        female_radio_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        female_radio_btn.setFocusPainted(false);
        female_radio_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                female_radio_btnActionPerformed(evt);
            }
        });

        save_btn.setBackground(new java.awt.Color(51, 102, 255));
        save_btn.setForeground(new java.awt.Color(255, 255, 255));
        save_btn.setText("Save");
        save_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        save_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        save_btn.setFocusPainted(false);
        save_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                save_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                save_btnMouseExited(evt);
            }
        });
        save_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_btnActionPerformed(evt);
            }
        });

        next_btn.setBackground(new java.awt.Color(153, 255, 153));
        next_btn.setText("Next");
        next_btn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        next_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        next_btn.setFocusPainted(false);
        next_btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                next_btnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                next_btnMouseExited(evt);
            }
        });
        next_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next_btnActionPerformed(evt);
            }
        });

        name_label14.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        name_label14.setText("Date :-");

        sugar_input.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sugar_input.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(124, 124, 241), 1, true));
        sugar_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sugar_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sugar_inputMouseExited(evt);
            }
        });
        sugar_input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sugar_inputActionPerformed(evt);
            }
        });

        vomiting_chk.setBackground(new java.awt.Color(251, 252, 224));
        vomiting_chk.setText("Vomiting");
        vomiting_chk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        vomiting_chk.setFocusPainted(false);
        vomiting_chk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vomiting_chkActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setText("Symptoms");

        cold_and_flue_chk.setBackground(new java.awt.Color(251, 252, 224));
        cold_and_flue_chk.setText("Cold and Flue");
        cold_and_flue_chk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cold_and_flue_chk.setFocusPainted(false);
        cold_and_flue_chk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cold_and_flue_chkActionPerformed(evt);
            }
        });

        fever_chk.setBackground(new java.awt.Color(251, 252, 224));
        fever_chk.setText("Fever");
        fever_chk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        fever_chk.setFocusPainted(false);
        fever_chk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fever_chkActionPerformed(evt);
            }
        });

        runny_nose_chk.setBackground(new java.awt.Color(251, 252, 224));
        runny_nose_chk.setText("Runny Nose");
        runny_nose_chk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        runny_nose_chk.setFocusable(false);
        runny_nose_chk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runny_nose_chkActionPerformed(evt);
            }
        });

        headache_chk.setBackground(new java.awt.Color(251, 252, 224));
        headache_chk.setText("Headache");
        headache_chk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        headache_chk.setFocusPainted(false);
        headache_chk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                headache_chkActionPerformed(evt);
            }
        });

        cough_chk.setBackground(new java.awt.Color(251, 252, 224));
        cough_chk.setText("Cough");
        cough_chk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cough_chk.setFocusPainted(false);
        cough_chk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cough_chkActionPerformed(evt);
            }
        });

        diarrhea_chk.setBackground(new java.awt.Color(251, 252, 224));
        diarrhea_chk.setText("Diarrhea");
        diarrhea_chk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        diarrhea_chk.setFocusPainted(false);
        diarrhea_chk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diarrhea_chkActionPerformed(evt);
            }
        });

        weakness_chk.setBackground(new java.awt.Color(251, 252, 224));
        weakness_chk.setText("Weakness");
        weakness_chk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        weakness_chk.setFocusPainted(false);
        weakness_chk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weakness_chkActionPerformed(evt);
            }
        });

        bodyache_chk.setBackground(new java.awt.Color(251, 252, 224));
        bodyache_chk.setText("Bodyache");
        bodyache_chk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bodyache_chk.setFocusPainted(false);
        bodyache_chk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bodyache_chkActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Javanese Text", 0, 14)); // NOI18N
        jLabel2.setText("Other :-");

        other_symptoms_input.setColumns(20);
        other_symptoms_input.setRows(5);
        other_symptoms_input.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(124, 124, 241)));
        other_symptoms_input.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                other_symptoms_inputMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                other_symptoms_inputMouseExited(evt);
            }
        });
        jScrollPane2.setViewportView(other_symptoms_input);

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        name_label15.setFont(new java.awt.Font("Segoe UI Emoji", 0, 14)); // NOI18N
        name_label15.setText("Sugar :-");

        date_input.setDateFormatString("dd-MM-yyyy");

        status_label.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        status_label.setForeground(new java.awt.Color(0, 153, 51));

        javax.swing.GroupLayout patient_form_panel1Layout = new javax.swing.GroupLayout(patient_form_panel1);
        patient_form_panel1.setLayout(patient_form_panel1Layout);
        patient_form_panel1Layout.setHorizontalGroup(
            patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patient_form_panel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cough_chk, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cold_and_flue_chk)
                    .addComponent(fever_chk, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(patient_form_panel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(patient_form_panel1Layout.createSequentialGroup()
                        .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(name_label12, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(name_label11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(name_label13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(name_label9, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name_label7, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(patient_form_panel1Layout.createSequentialGroup()
                                .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(vomiting_chk, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(pulse_input, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(blood_pressure_input, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(weight_input, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                                        .addComponent(age_input, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(diarrhea_chk, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(weakness_chk, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45)
                                .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bodyache_chk, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(headache_chk, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(runny_nose_chk, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(name_input, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(394, Short.MAX_VALUE))
                    .addGroup(patient_form_panel1Layout.createSequentialGroup()
                        .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(patient_form_panel1Layout.createSequentialGroup()
                                .addGap(262, 262, 262)
                                .addComponent(save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(79, 79, 79)
                                .addComponent(next_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(patient_form_panel1Layout.createSequentialGroup()
                        .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(patient_form_panel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(name_label8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(name_label10, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, patient_form_panel1Layout.createSequentialGroup()
                                        .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(name_label14, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(name_label15, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(mobile_number_input, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sugar_input, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(patient_form_panel1Layout.createSequentialGroup()
                                        .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(date_input, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(male_radio_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(female_radio_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4))))
                            .addGroup(patient_form_panel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(status_label, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(68, 68, 68)))
                        .addGap(64, 64, 64))))
        );
        patient_form_panel1Layout.setVerticalGroup(
            patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(patient_form_panel1Layout.createSequentialGroup()
                .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, patient_form_panel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(name_label10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name_label9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(name_label11, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(weight_input, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sugar_input, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name_label15, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(patient_form_panel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(name_label7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name_input, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(name_label8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(mobile_number_input, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(female_radio_btn)
                                .addComponent(male_radio_btn))
                            .addComponent(age_input, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(name_label12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(blood_pressure_input, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(name_label14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(date_input, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(name_label13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pulse_input, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(patient_form_panel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cough_chk)
                            .addComponent(vomiting_chk)
                            .addComponent(bodyache_chk))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cold_and_flue_chk)
                            .addComponent(diarrhea_chk)
                            .addComponent(headache_chk))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(runny_nose_chk, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(weakness_chk)
                            .addComponent(fever_chk))
                        .addGap(18, 18, 18)
                        .addComponent(status_label, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(patient_form_panel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(patient_form_panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(next_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(save_btn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19))))
        );

        patient_form_panel.add(patient_form_panel1);

        Patient.add(patient_form_panel, java.awt.BorderLayout.CENTER);

        main_panel.add(Patient, "patient");

        getContentPane().add(main_panel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void dashboard_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboard_labelMouseClicked
        new_report_menue.setVisible(false);

        Dashboard.remove(newDashboardPanel);
        newDashboardPanel = new NewDashboardPanel();
        Dashboard.add(newDashboardPanel, BorderLayout.CENTER); //to refresh while running system    

        card = (CardLayout) main_panel.getLayout();
        card.show(main_panel, "dashboard");
        page_showing = "dashboard";

        dashboard_label.setForeground(Color.CYAN);
        patient_label.setForeground(Color.white);
        prescription_label.setForeground(Color.white);
        reports_label.setForeground(Color.white);

        System.out.println("label clicked");

    }//GEN-LAST:event_dashboard_labelMouseClicked

    private void patient_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patient_labelMouseClicked
        new_report_menue.setVisible(false);

        resetPatientInfoForm();
        card = (CardLayout) main_panel.getLayout();
        card.show(main_panel, "patient");
        page_showing = "patient";

        dashboard_label.setForeground(Color.white);
        patient_label.setForeground(Color.CYAN);
        prescription_label.setForeground(Color.white);
        reports_label.setForeground(Color.white);

    }//GEN-LAST:event_patient_labelMouseClicked

    private void prescription_labelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prescription_labelMouseClicked
       new_report_menue.setVisible(false);

        card = (CardLayout) main_panel.getLayout();
        card.show(main_panel, "prescription");
        page_showing = "prescription";
        dashboard_label.setForeground(Color.white);
        patient_label.setForeground(Color.white);
        prescription_label.setForeground(Color.cyan);
        reports_label.setForeground(Color.white);

    }//GEN-LAST:event_prescription_labelMouseClicked

    private void reports_labelMouseClicked(java.awt.event.MouseEvent evt)
    {//GEN-FIRST:event_reports_labelMouseClicked
       new_report_menue.setVisible(true);
        card = (CardLayout) main_panel.getLayout();
        card.show(main_panel, "reports");
        page_showing = "reports";
        dashboard_label.setForeground(Color.white);
        patient_label.setForeground(Color.white);
        prescription_label.setForeground(Color.white);
        reports_label.setForeground(Color.cyan);
    }//GEN-LAST:event_reports_labelMouseClicked

    private void mobile_number_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobile_number_inputActionPerformed
    }//GEN-LAST:event_mobile_number_inputActionPerformed

    private void age_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_age_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_age_inputActionPerformed

    private void blood_pressure_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blood_pressure_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_blood_pressure_inputActionPerformed

    private void name_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_name_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_name_inputActionPerformed

    private void pulse_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pulse_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pulse_inputActionPerformed

    private void weight_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weight_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_weight_inputActionPerformed

    private void male_radio_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_male_radio_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_male_radio_btnActionPerformed

    private void female_radio_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_female_radio_btnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_female_radio_btnActionPerformed

    private void sugar_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sugar_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sugar_inputActionPerformed

    private void vomiting_chkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vomiting_chkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_vomiting_chkActionPerformed

    private void cold_and_flue_chkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cold_and_flue_chkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cold_and_flue_chkActionPerformed

    private void fever_chkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fever_chkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fever_chkActionPerformed

    private void runny_nose_chkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runny_nose_chkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_runny_nose_chkActionPerformed

    private void headache_chkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_headache_chkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_headache_chkActionPerformed

    private void cough_chkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cough_chkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cough_chkActionPerformed

    private void diarrhea_chkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diarrhea_chkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_diarrhea_chkActionPerformed

    private void weakness_chkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weakness_chkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_weakness_chkActionPerformed

    private void bodyache_chkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bodyache_chkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bodyache_chkActionPerformed

    private Date getCurrentDate() {
        SimpleDateFormat fr = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        fr.format(date);
        return date;

    }

    private StringBuffer getAllSymptoms() {
        StringBuffer symptoms = new StringBuffer();
        if (bodyache_chk.isSelected()) {
            symptoms.append("bodyache, ");
        }
        if (headache_chk.isSelected()) {
            symptoms.append("headache, ");
        }
        if (cold_and_flue_chk.isSelected()) {
            symptoms.append("cold and flue, ");
        }
        if (cough_chk.isSelected()) {
            symptoms.append("cough, ");
        }
        if (vomiting_chk.isSelected()) {
            symptoms.append("vomiting, ");
        }
        if (weakness_chk.isSelected()) {
            symptoms.append("weakness, ");
        }
        if (runny_nose_chk.isSelected()) {
            symptoms.append("runny nose, ");
        }
        if (diarrhea_chk.isSelected()) {
            symptoms.append("diarrhea, ");
        }
        if (fever_chk.isSelected()) {
            symptoms.append("fever, ");
        }

        if (other_symptoms_input.getText().length() != 0) {
            symptoms.append(other_symptoms_input.getText());

        }
        return symptoms;
    }

    private void resetPatientInfoForm() {
        name_input.setText("");
        mobile_number_input.setText("");
        age_input.setText("");
        weight_input.setText("");
        sugar_input.setText("");
        blood_pressure_input.setText("");
        male_radio_btn.setSelected(true);
        date_input.setDate(getCurrentDate());
        pulse_input.setText("");

        bodyache_chk.setSelected(false);
        headache_chk.setSelected(false);
        cold_and_flue_chk.setSelected(false);
        cough_chk.setSelected(false);
        vomiting_chk.setSelected(false);
        weakness_chk.setSelected(false);
        runny_nose_chk.setSelected(false);
        diarrhea_chk.setSelected(false);
        runny_nose_chk.setSelected(false);
        fever_chk.setSelected(false);

        other_symptoms_input.setText("");
        status_label.setText("");

    }

    //this method will use to set the one patient details global to acess detials on any page
    public void setGlobalPatientDetails(PatientDetails patientDetails) {
        this.PATIENT_DETAILS = patientDetails;
    }
    private void save_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_btnActionPerformed

        if (valid_patients_inputes.get("is_all_inputes_valid") == 1) {
            String name = name_input.getText();
            String mobile = mobile_number_input.getText();
            String age = age_input.getText();
            String weight = weight_input.getText();
            String sugar = sugar_input.getText();
            String bp = blood_pressure_input.getText();
            Date date = date_input.getDate();
            String pulse = pulse_input.getText();

            String gender = "Male";
            if (female_radio_btn.isSelected()) {
                gender = "Female";
            }

            if (name.length() > 0 && mobile.length() == 10) {
                PatientDetails patient_details = new PatientDetails();
                patient_details.setName(name);
                patient_details.setMobileNo(mobile);
                patient_details.setAge(age);
                patient_details.setWeight(weight);
                patient_details.setSugar(sugar);
                patient_details.setPulse(pulse);
                patient_details.setGender(gender);
                patient_details.setDate(date);
                patient_details.setBloodPressure(bp);
                patient_details.setSymptoms(new String(getAllSymptoms()));
                // System.out.println(patient_details.getDate());

                Database patient_table = Database.getInstance();

                int patient_id = patient_table.insertRecord(patient_details);
                patient_details.setPid(patient_id);
                status_label.setForeground(SUCCESS_COLOR);
                status_label.setText("Patient Details Saved Successfully.!!");

                setDetailsOnPrecriptionPage(patient_details);
                setGlobalPatientDetails(patient_details);
            } else {
                status_label.setForeground(WARNING_COLOR);
                status_label.setText("Please Add Basic Details of Patients");
            }
        }
    }//GEN-LAST:event_save_btnActionPerformed

    private void dashboard_iconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboard_iconMouseClicked
        Dashboard.remove(newDashboardPanel);
        newDashboardPanel = new NewDashboardPanel();
        Dashboard.add(newDashboardPanel, BorderLayout.CENTER); //to refresh while running system    

        page_showing = "dashboard";
        card = (CardLayout) main_panel.getLayout();
        card.show(main_panel, "dashboard");
        dashboard_label.setForeground(Color.CYAN);

        patient_label.setForeground(Color.white);
        prescription_label.setForeground(Color.white);
        reports_label.setForeground(Color.white);
    }//GEN-LAST:event_dashboard_iconMouseClicked

    private void patient_iconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patient_iconMouseClicked

        resetPatientInfoForm();
        card = (CardLayout) main_panel.getLayout();
        card.show(main_panel, "patient");
        page_showing = "patient";
        dashboard_label.setForeground(Color.white);
        patient_label.setForeground(Color.CYAN);
        prescription_label.setForeground(Color.white);
        reports_label.setForeground(Color.white);

    }//GEN-LAST:event_patient_iconMouseClicked

    private void prescription_iconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prescription_iconMouseClicked
        card = (CardLayout) main_panel.getLayout();
        card.show(main_panel, "prescription");
        page_showing = "prescription";
        dashboard_label.setForeground(Color.white);
        patient_label.setForeground(Color.white);
        prescription_label.setForeground(Color.cyan);
        reports_label.setForeground(Color.white);

    }//GEN-LAST:event_prescription_iconMouseClicked

    private void reports_iconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reports_iconMouseClicked
        card = (CardLayout) main_panel.getLayout();
        card.show(main_panel, "reports");
        page_showing = "reports";
        dashboard_label.setForeground(Color.white);
        patient_label.setForeground(Color.white);
        prescription_label.setForeground(Color.white);
        reports_label.setForeground(Color.cyan);
    }//GEN-LAST:event_reports_iconMouseClicked

    private void save_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_save_btnMouseEntered
        // TODO add your handling code here:
        save_btn.setBorder(new LineBorder(Color.BLACK, 2, true));
    }//GEN-LAST:event_save_btnMouseEntered

    private void save_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_save_btnMouseExited
        save_btn.setBorder(DEFAULT_BTN_BORDER);
    }//GEN-LAST:event_save_btnMouseExited

    private void next_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_next_btnMouseEntered
        next_btn.setBorder(HOVER_BTN_BORDER);
    }//GEN-LAST:event_next_btnMouseEntered

    private void next_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_next_btnMouseExited
        next_btn.setBorder(DEFAULT_BTN_BORDER);
    }//GEN-LAST:event_next_btnMouseExited

    private void name_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name_inputMouseEntered
        if (name_input.getBorder() != WARNING_BORDER) {
            name_input.setBorder(HOVER_BORDER);

        }

    }//GEN-LAST:event_name_inputMouseEntered

    private void name_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name_inputMouseExited
        if (name_input.getBorder() != WARNING_BORDER) {
            name_input.setBorder(INPUT_BORDER);
        }
    }//GEN-LAST:event_name_inputMouseExited

    private void age_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_age_inputMouseEntered
        if (age_input.getBorder() != WARNING_BORDER) {
            age_input.setBorder(HOVER_BORDER);
        }
    }//GEN-LAST:event_age_inputMouseEntered

    private void age_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_age_inputMouseExited

        if (age_input.getBorder() != WARNING_BORDER) {
            age_input.setBorder(INPUT_BORDER);
        }
    }//GEN-LAST:event_age_inputMouseExited

    private void weight_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_weight_inputMouseEntered
        if (weight_input.getBorder() != WARNING_BORDER) {
            weight_input.setBorder(HOVER_BORDER);
        }
    }//GEN-LAST:event_weight_inputMouseEntered

    private void weight_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_weight_inputMouseExited

        if (weight_input.getBorder() != WARNING_BORDER) {
            weight_input.setBorder(INPUT_BORDER);
        }

    }//GEN-LAST:event_weight_inputMouseExited

    private void pulse_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pulse_inputMouseEntered
        if (pulse_input.getBorder() != WARNING_BORDER) {
            pulse_input.setBorder(HOVER_BORDER);
        }

    }//GEN-LAST:event_pulse_inputMouseEntered

    private void pulse_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pulse_inputMouseExited
        if (pulse_input.getBorder() != WARNING_BORDER) {
            pulse_input.setBorder(INPUT_BORDER);
        }

    }//GEN-LAST:event_pulse_inputMouseExited

    private void mobile_number_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobile_number_inputMouseEntered

        if (mobile_number_input.getBorder() != WARNING_BORDER) {
            mobile_number_input.setBorder(HOVER_BORDER);
        }

    }//GEN-LAST:event_mobile_number_inputMouseEntered

    private void mobile_number_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobile_number_inputMouseExited
        if (mobile_number_input.getBorder() != WARNING_BORDER) {
            mobile_number_input.setBorder(INPUT_BORDER);
        }

    }//GEN-LAST:event_mobile_number_inputMouseExited

    private void sugar_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sugar_inputMouseEntered
        if (sugar_input.getBorder() != WARNING_BORDER) {
            sugar_input.setBorder(HOVER_BORDER);
        }
    }//GEN-LAST:event_sugar_inputMouseEntered

    private void sugar_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sugar_inputMouseExited

        if (sugar_input.getBorder() != WARNING_BORDER) {
            sugar_input.setBorder(INPUT_BORDER);
        }

    }//GEN-LAST:event_sugar_inputMouseExited

    private void other_symptoms_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_other_symptoms_inputMouseEntered
        // other_symptoms_input.setBorder(HOVER_BORDER);
    }//GEN-LAST:event_other_symptoms_inputMouseEntered

    private void other_symptoms_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_other_symptoms_inputMouseExited
        // other_symptoms_input.setBorder(INPUT_BORDER);
    }//GEN-LAST:event_other_symptoms_inputMouseExited

    private void blood_pressure_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_blood_pressure_inputMouseEntered
        if (blood_pressure_input.getBorder() != WARNING_BORDER) {
            blood_pressure_input.setBorder(HOVER_BORDER);
        }


    }//GEN-LAST:event_blood_pressure_inputMouseEntered

    private void blood_pressure_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_blood_pressure_inputMouseExited

        if (blood_pressure_input.getBorder() != WARNING_BORDER) {
            blood_pressure_input.setBorder(INPUT_BORDER);
        }
    }//GEN-LAST:event_blood_pressure_inputMouseExited

    private void setDetailsOnPrecriptionPage(PatientDetails patientDetails) {
        // patient_name.setText(patientDetails.getName());

    }
    private void next_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next_btnActionPerformed

        if (PATIENT_DETAILS != null) {

            card = (CardLayout) main_panel.getLayout();
            card.show(main_panel, "prescription");
            page_showing = "prescription";
            resetPrescriptionPage();

            prescription_name_input.setText(PATIENT_DETAILS.getName());
            prescription_mobile_number_input.setText(PATIENT_DETAILS.getMobileNo());
            prescription_date_input.setDate(PATIENT_DETAILS.getDate());
            // redirectOnPrecriptionPage();

            dashboard_label.setForeground(Color.white);
            patient_label.setForeground(Color.white);
            prescription_label.setForeground(Color.cyan);
            reports_label.setForeground(Color.white);

            resetPatientInfoForm();
        } else {
            status_label.setText("Insert Patient details first");
            status_label.setForeground(WARNING_COLOR);
        }


    }//GEN-LAST:event_next_btnActionPerformed

    private void medicine_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_medicine_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_medicine_inputActionPerformed

    private void prescription_next_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prescription_next_btnActionPerformed

        //show the reports and rest the prescription page
        card = (CardLayout) main_panel.getLayout();
        card.show(main_panel, "reports");
        page_showing = "reports";

        dashboard_label.setForeground(Color.white);
        patient_label.setForeground(Color.white);
        prescription_label.setForeground(Color.white);
        reports_label.setForeground(Color.cyan);

        resetPrescriptionPage();
        PATIENT_DETAILS = null; //global patient object removes beacase this is final page;

    }//GEN-LAST:event_prescription_next_btnActionPerformed

    private void prescription_next_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prescription_next_btnMouseExited
        prescription_next_btn.setBorder(DEFAULT_BTN_BORDER);
    }//GEN-LAST:event_prescription_next_btnMouseExited

    private void prescription_next_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prescription_next_btnMouseEntered
        prescription_next_btn.setBorder(HOVER_BORDER);
    }//GEN-LAST:event_prescription_next_btnMouseEntered

    public void resetPrescriptionPage() {
        //remove all the selected medicine
        for (int i = 0; i < bt.size(); i++) {
            MedicineRowPanel p = bt.get(i);
            bt.remove(p);
            main_list.remove(p);
            if (total_medicine_selected != 0) {
                total_medicine_selected--;
            }

        }
        medicine_input.setText("");
        prescription_name_input.setText("");
        prescription_mobile_number_input.setText("");
        prescription_date_input.setDate(getCurrentDate());
        prescription_status_label.setText("");

        //medicine_list_panel.remove(medicine_list);
        validate();
        repaint();
    }
    private void prescription_save_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prescription_save_btnActionPerformed

        boolean is_patient_exists = true;
        if (PATIENT_DETAILS == null) {

            String p_name_input = prescription_name_input.getText();
            String p_mobile_input = prescription_mobile_number_input.getText();
            Date p_date = prescription_date_input.getDate();

            //add new patient
            if (p_name_input.length() > 0 && p_mobile_input.length() > 0) {
                PATIENT_DETAILS = new PatientDetails();
                PATIENT_DETAILS.setName(p_name_input);
                PATIENT_DETAILS.setMobileNo(p_mobile_input);
                PATIENT_DETAILS.setDate(p_date);

                Database database = Database.getInstance();
                int id = database.insertRecord(PATIENT_DETAILS);
                if (id > 0) {
                    PATIENT_DETAILS.setPid(id);
                    prescription_status_label.setText("Patient Added Sussfully.");
                    prescription_status_label.setForeground(SUCCESS_COLOR);
                }
            } else {
                prescription_status_label.setText("Please Add Patient Details first.");
                prescription_status_label.setForeground(Color.red);
                is_patient_exists = false;
            }

        }

        //add medicine details
        for (int i = 0; i < bt.size(); i++) {
            M_BandType row = bt.get(i).getDetials();
            if (is_patient_exists && row != null) {
                Database database = Database.getInstance();
                MedicineDetails medicineDetails = new MedicineDetails();
                medicineDetails.setMedicineName(row.medicine_name);
                medicineDetails.setMedicineMealTime(row.before);
                medicineDetails.setMedicineQuantity(row.selected_combo);
                medicineDetails.setTotalQuantity(row.tab);
                medicineDetails.setPatientDetails(PATIENT_DETAILS);

                medicineDetails.setMedicineTime(row.morning_status, row.afternoon_status, row.evening_status);
                database.insertRecordInMedicine(medicineDetails);
                prescription_status_label.setForeground(new Color(0, 153, 0));
                prescription_status_label.setText("Saved Susscessfuly..!");
            }
        }


         }//GEN-LAST:event_prescription_save_btnActionPerformed


    private void prescription_name_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prescription_name_inputActionPerformed
        // TODO add your handling code here:
        System.out.println(" ");
    }//GEN-LAST:event_prescription_name_inputActionPerformed

    private void prescription_name_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prescription_name_inputMouseExited
        if (prescription_name_input.getBorder() != WARNING_BORDER) {
            prescription_name_input.setBorder(INPUT_BORDER);
        }
    }//GEN-LAST:event_prescription_name_inputMouseExited

    private void prescription_name_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prescription_name_inputMouseEntered
        if (prescription_name_input.getBorder() != WARNING_BORDER) {
            prescription_name_input.setBorder(HOVER_BORDER);
        }
    }//GEN-LAST:event_prescription_name_inputMouseEntered

    private void prescription_mobile_number_inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prescription_mobile_number_inputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prescription_mobile_number_inputActionPerformed

    private void prescription_mobile_number_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prescription_mobile_number_inputMouseExited
        if (prescription_mobile_number_input.getBorder() != WARNING_BORDER) {
            prescription_mobile_number_input.setBorder(INPUT_BORDER);
        }
    }//GEN-LAST:event_prescription_mobile_number_inputMouseExited

    private void prescription_mobile_number_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prescription_mobile_number_inputMouseEntered
        if (prescription_mobile_number_input.getBorder() != WARNING_BORDER) {
            prescription_mobile_number_input.setBorder(HOVER_BORDER);
        }
    }//GEN-LAST:event_prescription_mobile_number_inputMouseEntered

    private void add_medicine_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_medicine_btnActionPerformed
        addMedicine();
    }//GEN-LAST:event_add_medicine_btnActionPerformed

    private void medicine_inputMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_medicine_inputMouseEntered
        medicine_input.setBorder(HOVER_BORDER);
    }//GEN-LAST:event_medicine_inputMouseEntered

    private void medicine_inputMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_medicine_inputMouseExited
        medicine_input.setBorder(INPUT_BORDER);
    }//GEN-LAST:event_medicine_inputMouseExited

    private void prescription_delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prescription_delete_btnActionPerformed

        for (int i = 0; i < bt.size(); i++) {
            System.out.println(bt.size());
            MedicineRowPanel p = bt.get(i);
            M_BandType obj = p.getDetials();
            System.out.println(obj.delete_chk);
            if (obj.delete_chk) {
                bt.remove(p);
                main_list.remove(p);
                if (total_medicine_selected != 0) {
                    total_medicine_selected--;
                }
            }
        }
        validate();
        repaint();
    }//GEN-LAST:event_prescription_delete_btnActionPerformed

    private void add_medicine_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_medicine_btnMouseEntered
        add_medicine_btn.setBorder(HOVER_BTN_BORDER);
    }//GEN-LAST:event_add_medicine_btnMouseEntered

    private void add_medicine_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_medicine_btnMouseExited
        add_medicine_btn.setBorder(DEFAULT_BTN_BORDER);
    }//GEN-LAST:event_add_medicine_btnMouseExited

    private void prescription_save_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prescription_save_btnMouseEntered
        //  prescription_save_btn.setBorder(INPUT_BORDER);
        prescription_save_btn.setBorder(new LineBorder(Color.BLACK, 2, true));
    }//GEN-LAST:event_prescription_save_btnMouseEntered

    private void prescription_save_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prescription_save_btnMouseExited
        prescription_save_btn.setBorder(HOVER_BTN_BORDER);
    }//GEN-LAST:event_prescription_save_btnMouseExited

    private void prescription_delete_btnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prescription_delete_btnMouseEntered
        prescription_delete_btn.setBorder(DEFAULT_BTN_BORDER);
    }//GEN-LAST:event_prescription_delete_btnMouseEntered

    private void prescription_delete_btnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_prescription_delete_btnMouseExited
        prescription_delete_btn.setBorder(new LineBorder(new Color(10066329), 1, true));
    }//GEN-LAST:event_prescription_delete_btnMouseExited

    private void addMedicine() {
        String medicine_name = medicine_list.getSelectedValue();

        if (medicine_name == null) {
            medicine_name = medicine_input.getText();
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        bt.add(new MedicineRowPanel(medicine_name.toUpperCase()));

        main_list.add(bt.get(total_medicine_selected), gbc, 0);
        total_medicine_selected++;
        validate();
        repaint();
    }
    private void medicine_listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_medicine_listMouseClicked

        if (evt.getClickCount() == 2) {
            addMedicine();
        }
        //medicine_input.setText(medicine_list.getSelectedValue());
    }//GEN-LAST:event_medicine_listMouseClicked

    private void dashboard_label5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboard_label5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dashboard_label5MouseClicked

    private void dashboard_label6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboard_label6MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dashboard_label6MouseClicked

    private void mobile_number_input1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobile_number_input1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_number_input1MouseEntered

    private void mobile_number_input1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobile_number_input1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_number_input1MouseExited

    private void mobile_number_input1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobile_number_input1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_number_input1ActionPerformed

    private void mobile_number_input2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobile_number_input2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_number_input2MouseEntered

    private void mobile_number_input2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobile_number_input2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_number_input2MouseExited

    private void mobile_number_input2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobile_number_input2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_number_input2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void dashboard_label7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboard_label7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dashboard_label7MouseClicked

    private void dashboard_label8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboard_label8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dashboard_label8MouseClicked

    private void mobile_number_input3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobile_number_input3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_number_input3MouseEntered

    private void mobile_number_input3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobile_number_input3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_number_input3MouseExited

    private void mobile_number_input3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobile_number_input3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_number_input3ActionPerformed

    private void mobile_number_input4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobile_number_input4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_number_input4MouseEntered

    private void mobile_number_input4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobile_number_input4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_number_input4MouseExited

    private void mobile_number_input4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobile_number_input4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_number_input4ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void dashboard_label9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboard_label9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dashboard_label9MouseClicked

    private void dashboard_label10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboard_label10MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dashboard_label10MouseClicked

    private void mobile_number_input5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobile_number_input5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_number_input5MouseEntered

    private void mobile_number_input5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobile_number_input5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_number_input5MouseExited

    private void mobile_number_input5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobile_number_input5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_number_input5ActionPerformed

    private void mobile_number_input6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobile_number_input6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_number_input6MouseEntered

    private void mobile_number_input6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobile_number_input6MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_number_input6MouseExited

    private void mobile_number_input6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobile_number_input6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_number_input6ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void dashboard_label11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboard_label11MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dashboard_label11MouseClicked

    private void mobile_number_input7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobile_number_input7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_number_input7MouseEntered

    private void mobile_number_input7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mobile_number_input7MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_number_input7MouseExited

    private void mobile_number_input7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobile_number_input7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mobile_number_input7ActionPerformed

    private void dashboard_label12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboard_label12MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_dashboard_label12MouseClicked

    private void dashboard_label13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboard_label13MouseClicked
        medical_report.setVisible(true);
        test_application.setVisible(false);
        reference_letter.setVisible(false);
    }//GEN-LAST:event_dashboard_label13MouseClicked

    private void dashboard_label14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboard_label14MouseClicked
        // TODO add your handling code here:
        medical_report.setVisible(false);
        test_application.setVisible(true);
        reference_letter.setVisible(false);
    }//GEN-LAST:event_dashboard_label14MouseClicked

    private void dashboard_label15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dashboard_label15MouseClicked
        // TODO add your handling code here:
        medical_report.setVisible(false);
        test_application.setVisible(false);
        reference_letter.setVisible(true);
    }//GEN-LAST:event_dashboard_label15MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
 /* try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Dashboard;
    private javax.swing.JPanel Patient;
    private javax.swing.JPanel Prescription;
    private javax.swing.JPanel Reports;
    private javax.swing.JButton add_medicine_btn;
    private javax.swing.JTextField age_input;
    private javax.swing.JTextField blood_pressure_input;
    private javax.swing.JCheckBox bodyache_chk;
    private javax.swing.JCheckBox cold_and_flue_chk;
    private javax.swing.JCheckBox cough_chk;
    private javax.swing.JPanel dashboard_icon;
    private javax.swing.JLabel dashboard_label;
    private javax.swing.JLabel dashboard_label10;
    private javax.swing.JLabel dashboard_label11;
    private javax.swing.JLabel dashboard_label12;
    private javax.swing.JLabel dashboard_label13;
    private javax.swing.JLabel dashboard_label14;
    private javax.swing.JLabel dashboard_label15;
    private javax.swing.JLabel dashboard_label5;
    private javax.swing.JLabel dashboard_label6;
    private javax.swing.JLabel dashboard_label7;
    private javax.swing.JLabel dashboard_label8;
    private javax.swing.JLabel dashboard_label9;
    private com.toedter.calendar.JDateChooser date_input;
    private javax.swing.JCheckBox diarrhea_chk;
    private javax.swing.JPanel doctor_icon_panel;
    private javax.swing.JRadioButton female_radio_btn;
    private javax.swing.JCheckBox fever_chk;
    private javax.swing.JPanel footer;
    private javax.swing.ButtonGroup gender_radio_group;
    private javax.swing.JCheckBox headache_chk;
    private javax.swing.JLabel heade_label;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel main_panel;
    private javax.swing.JRadioButton male_radio_btn;
    private javax.swing.JPanel medical_report;
    private javax.swing.JTextField medicine_input;
    private javax.swing.JList<String> medicine_list;
    private javax.swing.JPanel medicine_list_panel;
    private javax.swing.JPanel menu_panel;
    private javax.swing.JTextField mobile_number_input;
    private javax.swing.JTextField mobile_number_input1;
    private javax.swing.JTextField mobile_number_input2;
    private javax.swing.JTextField mobile_number_input3;
    private javax.swing.JTextField mobile_number_input4;
    private javax.swing.JTextField mobile_number_input5;
    private javax.swing.JTextField mobile_number_input6;
    private javax.swing.JTextField mobile_number_input7;
    private javax.swing.JTextField name_input;
    private javax.swing.JLabel name_label10;
    private javax.swing.JLabel name_label11;
    private javax.swing.JLabel name_label12;
    private javax.swing.JLabel name_label13;
    private javax.swing.JLabel name_label14;
    private javax.swing.JLabel name_label15;
    private javax.swing.JLabel name_label16;
    private javax.swing.JLabel name_label17;
    private javax.swing.JLabel name_label23;
    private javax.swing.JLabel name_label7;
    private javax.swing.JLabel name_label8;
    private javax.swing.JLabel name_label9;
    private javax.swing.JPanel new_report_menue;
    private javax.swing.JButton next_btn;
    private javax.swing.JTextArea other_symptoms_input;
    private javax.swing.JPanel patient_form_panel;
    private javax.swing.JPanel patient_form_panel1;
    private javax.swing.JPanel patient_icon;
    private javax.swing.JLabel patient_label;
    private javax.swing.JPanel patient_panel_head;
    private javax.swing.JLabel patient_panel_header_title;
    private javax.swing.JLabel patient_panel_header_title1;
    private com.toedter.calendar.JDateChooser prescription_date_input;
    private javax.swing.JButton prescription_delete_btn;
    private javax.swing.JPanel prescription_form;
    private javax.swing.JPanel prescription_form_panel;
    private javax.swing.JPanel prescription_icon;
    private javax.swing.JLabel prescription_label;
    private javax.swing.JTextField prescription_mobile_number_input;
    private javax.swing.JTextField prescription_name_input;
    private javax.swing.JButton prescription_next_btn;
    private javax.swing.JPanel prescription_panel_head1;
    private javax.swing.JButton prescription_save_btn;
    private javax.swing.JLabel prescription_status_label;
    private javax.swing.JTextField pulse_input;
    private javax.swing.JPanel reference_letter;
    private javax.swing.JPanel reports_icon;
    private javax.swing.JLabel reports_label;
    private javax.swing.JCheckBox runny_nose_chk;
    private javax.swing.JButton save_btn;
    private javax.swing.JPanel selected_medicine_panel;
    private javax.swing.JLabel status_label;
    private javax.swing.JTextField sugar_input;
    private javax.swing.JPanel test_application;
    private javax.swing.JCheckBox vomiting_chk;
    private javax.swing.JCheckBox weakness_chk;
    private javax.swing.JTextField weight_input;
    // End of variables declaration//GEN-END:variables
}
