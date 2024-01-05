package auth;

import hospitalmanagement.Home;
import java.awt.BorderLayout;
import util.Button;
import util.MyPasswordField;
import util.MyTextField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import myutil.SetImageIcon;
import net.miginfocom.swing.MigLayout;

public class PanelLoginAndRegister extends javax.swing.JLayeredPane {

    JPanel panel = new JPanel();
    Button cmd;

    Home home;
    User user;
    int adding_index;
    JLabel verification_label;
    MyTextField code, txtName, txtHospital, txtUser, txtEmail;
    MyPasswordField txtPass;
    boolean registration_started = false;
    JRadioButton doctor_signup, compounder;
    MouseAdapter resendAdapter;
//     VerificationCode verificationCode = new VerificationCode();

    public User getSignUpUserDetails() {
        return user;
    }

    private ActionListener eventRegister;

    public void setResendMouseAdapter(MouseAdapter resendAdapter) {
        this.resendAdapter = resendAdapter;
    }

    public PanelLoginAndRegister(ActionListener eventRegister, ActionListener eventLogin) {

        initComponents();
        initLogin(eventLogin);
        initRegister(eventRegister);

        login.setVisible(false);
        register.setVisible(true);

        this.eventRegister = eventRegister;

        setCutomeLookAndFeel();
//        this.home = home;
    }

    private void initRegister(ActionListener eventRegister) {
        initRegisterForDoctor(eventRegister);

        ButtonGroup grp = new ButtonGroup();
        // login.add(btn);
        doctor_signup = new JRadioButton();
        doctor_signup.setText("Doctor");
        doctor_signup.setSelected(true);

        compounder = new JRadioButton();
        compounder.setText("Compounder");
        grp.add(compounder);
        grp.add(doctor_signup);

        //login.add(btn1);
        panel.setPreferredSize(new Dimension(100, 25));
        panel.setLayout(new FlowLayout(10));
        panel.setBackground(Color.WHITE);

        doctor_signup.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (doctor_signup.isSelected()) {
                    initRegisterForDoctor(eventRegister);
                } else {
                    initRegisterCompounder(eventRegister);
                }
                 
            }

        });
        panel.add(doctor_signup);
        panel.add(compounder);

    }

    private void initRegisterCompounder(ActionListener eventRegister) {
        register.removeAll();
        //register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]25[]push"));
         MigLayout m = new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]10[]10[]10[]25[]push");
        register.setLayout(m);
        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(0, 0, 102));
        register.add(label);

        register.add(panel);

        txtUser = new MyTextField();
        txtUser.setPrefixIcon(new ImageIcon("./images/user.png"));
        txtUser.setHint("Name");
        register.add(txtUser, "w 60%");

        txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon("./images/mail.png"));
        txtEmail.setHint("Email");

        register.add(txtEmail, "w 60%");
        txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon("./images/pass.png"));
        txtPass.setHint("Password");

        register.add(txtPass, "w 60%");
        cmd = new Button();
        cmd.setBackground(new Color(0, 196, 147));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN UP");
        cmd.addActionListener(eventRegister);
        cmd.addActionListener((ActionEvent e) -> {
            setUserDetails();

        });
        register.add(cmd, "w 40%, h 40");

        adding_index = 5; //for adding verification code field;
        register.revalidate();
        register.repaint();
    }
//    MigLayout m = new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]10[]10[]10[]25[]push");
    // MigLayout m1 = new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]10[]10[]25[]push");

    private void initRegisterForDoctor(ActionListener eventRegister) {
        register.removeAll();
//        register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]10[]25[]push"));

        //register.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]10[]25[]push"));
       MigLayout m = new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]10[]10[]10[]25[]push");
        register.setLayout(m);

        JLabel label = new JLabel("Create Account");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(0, 0, 102));
        register.add(label);

        //add the radio btns
        register.add(panel);

        txtHospital = new MyTextField();
        txtHospital.setHint("Hospital Name");
        txtHospital.setPrefixIcon(new ImageIcon("./images/hospital3.png"));
        register.add(txtHospital, "w 60%");

        txtUser = new MyTextField();
        txtUser.setPrefixIcon(new ImageIcon("./images/user.png"));
        txtUser.setHint("Name");
        register.add(txtUser, "w 60%");

        txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon("./images/mail.png"));
        txtEmail.setHint("Email");

        register.add(txtEmail, "w 60%");
        txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon("./images/pass.png"));
        txtPass.setHint("Password");

        register.add(txtPass, "w 60%");
        cmd = new Button();
        cmd.setBackground(new Color(0, 196, 147));
        cmd.setForeground(new Color(250, 250, 250));
        cmd.setText("SIGN UP");
        register.add(cmd, "w 40%, h 40");

        System.out.println(cmd.getText());
        cmd.addActionListener(eventRegister);

        cmd.addActionListener((ActionEvent e) -> {
            setUserDetails();

        });

        register.revalidate();
        register.repaint();
        adding_index = 6; // for adding the code verification field 
    }

    public void resetRegistrationPage() {
        txtUser.setText("");
        txtEmail.setText("");
        txtHospital.setText("");
        code.setText(" ");

        txtPass.setText("");
        cmd.setText("SIGN UP");
        user = null;
//        info.removeAll();

        if (doctor_signup.isSelected()) {
            initRegisterForDoctor(this.eventRegister);

        } else {
            initRegisterCompounder(this.eventRegister);
        }

        revalidate();
        repaint();

    }
    JPanel info = new JPanel();

    public void setLoadingGif() {
        info.removeAll();
        info.add(new SetImageIcon(new ImageIcon("./images/loading.gif"), 100, 30));
        info.setBackground(Color.WHITE);
        info.setOpaque(true);
        revalidate();
        repaint();

    }

    public void setUserDetails() {
        user = new User();
        if (doctor_signup.isSelected()) {
            user.setUserType("Doctor");

            String hospital_name = txtHospital.getText();
            if (hospital_name.length() == 0) {
                hospital_name = null;
            }
            user.setHospitalName(hospital_name);

        } else {
            user.setUserType("Compounder");

        }
        String email = txtEmail.getText();
        String username = txtUser.getText();
        String password = String.valueOf(txtPass.getPassword());
        if (email.length() == 0) {
            email = null;
        }
        if (username.length() == 0) {
            username = null;
        }
        if (password.length() == 0) {
            password = null;
        }

        user.setEmail(email);
        user.setUserName(username);
        user.setPassword(password);
    }

    public boolean isSubmitBtnMode() {
        return cmd.getText().startsWith("SUBMIT");
    }

    public boolean isSignUpBtnMode() {
        return cmd.getText().startsWith("SIGN UP");
    }

    public void setMessageLabels() {
        info.removeAll();
        verification_label = new JLabel();
        JLabel resend = new JLabel("Resend");

        resend.addMouseListener(this.resendAdapter);

        resend.setCursor(new Cursor(Cursor.HAND_CURSOR));
        resend.setForeground(new Color(0, 0, 204));
        info.add(verification_label);
        info.add(resend);

        revalidate();
        repaint();
    }

    public void addVerificationCodeUI() {
        code = new MyTextField();
        code.setPrefixIcon(new ImageIcon("./images/user.png"));
        code.setHint("Enter Code");
        code.setPreferredSize(new Dimension(150, 40));

        cmd.setText("SUBMIT");
        register.add(code, "w 60%", adding_index);

        register.add(info, "w 60%", adding_index + 1);

        revalidate();
        repaint();
    }

    public boolean verifyEnteredCode(String send_code) {

        if (code.getText().equals(send_code)) {

            // cmd.setText("SIGN UP");
            //code.setText("");
            // System.out.println("Sign Up Succesfull");
            return true;
        }

        return false;
    }

    public void setVerificationStatus(String msg) {
        verification_label.setText(msg);
    }

    //============================================================[Login page ]====================================================
    MyTextField login_textEmail, login_name;
    MyPasswordField login_txtPass;
    JRadioButton login_doctor, login_admin, login_com;
    User login_user;
    Button login_btn;
    boolean forgot_btn_clicked = true;

    public User getLoginUser() {
        return this.login_user;
    }

    public void setCutomeLookAndFeel() {
        doctor_signup.setFocusPainted(false);
        doctor_signup.setBackground(Color.WHITE);
        compounder.setBackground(Color.WHITE);

        compounder.setFocusPainted(false);

        login_btn.setFocusPainted(false);

        login_com.setBackground(Color.WHITE);
        login_admin.setBackground(Color.WHITE);
        login_doctor.setBackground(Color.WHITE);

        login_doctor.setFocusPainted(false);
        login_admin.setFocusPainted(false);
        login_com.setFocusPainted(false);
        
       
    }

    public void addRadioBtnsOnLoginPage() {
        ButtonGroup grp = new ButtonGroup();

        login_admin = new JRadioButton();
        login_admin.setText("Admin");
        // login.add(btn);
        login_doctor = new JRadioButton();
        login_doctor.setText("Doctor");

        login_com = new JRadioButton();
        login_com.setText("Compounder");

        grp.add(login_com);
        grp.add(login_doctor);

        grp.add(login_admin);

        login_doctor.setSelected(true);
        //login.add(btn1);
        JPanel login_radio_panel = new JPanel();
        login_radio_panel.setPreferredSize(new Dimension(100, 25));
        login_radio_panel.setLayout(new FlowLayout(10));
        login_radio_panel.setBackground(Color.WHITE);

        login_radio_panel.add(login_doctor);
        login_radio_panel.add(login_com);
        login_radio_panel.add(login_admin);

        login.add(login_radio_panel);
    }

    public void setLoginUserDetails() {
        login_user = new User();
        if (login_doctor.isSelected()) {
            login_user.setUserType("Doctor");
        } else if (login_admin.isSelected()) {
            login_user.setUserType("Admin");
        } else {
            login_user.setUserType("Compounder");
        }

        //this is for the forgot button 
        if (login_name != null) {
            String username = login_name.getText();

            if (username.length() == 0) {
                username = null;
            } else {
                login_user.setUserName(username);
            }

        }
        String email = login_textEmail.getText();
        String password = String.valueOf(login_txtPass.getPassword());

        if (email.length() == 0) {
            email = null;
        }
        if (password.length() == 0) {
            password = null;
        }
        login_user.setEmail(email);
        login_user.setPassword(password);
    }

    private void initLogin(ActionListener eventLogin) {

//        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]25[]push"));
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]25[]10[]10[]10[]10[]25[]push"));

        JLabel label = new JLabel("Login");
        label.setFont(new Font("sansserif", 1, 30));
        label.setForeground(new Color(0, 0, 102));
        login.add(label);

        addRadioBtnsOnLoginPage();

        login_textEmail = new MyTextField();
        login_textEmail.setPrefixIcon(new ImageIcon("./images/mail.png"));
        login_textEmail.setHint("Email");
        login.add(login_textEmail, "w 60%");

        login_txtPass = new MyPasswordField();
        login_txtPass.setPrefixIcon(new ImageIcon("./images/pass.png"));
        login_txtPass.setHint("Password");
        login.add(login_txtPass, "w 60%");

        JButton cmdForget = new JButton("Forgot your password ?");
        cmdForget.setForeground(new Color(100, 100, 100));
        cmdForget.setFont(new Font("sansserif", 1, 12));
        cmdForget.setContentAreaFilled(false);
        cmdForget.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdForget.setFocusPainted(false);
        cmdForget.setBorder(null);

        login.add(cmdForget);

        cmdForget.addActionListener((ActionEvent e) -> {

            if (forgot_btn_clicked) {
                login.remove(login_txtPass);
                login_name = new MyTextField();
                login_name.setPrefixIcon(new ImageIcon("./images/user.png"));
                login_name.setHint("Name");
                login.add(login_name, "w 60%", 3);
                login_btn.setText("SUBMIT");

                cmdForget.setForeground(new Color(0, 196, 147));
                forgot_btn_clicked = false;
            } else {
                login.remove(login_name);
                login.add(login_txtPass, "w 60%", 3);
                login_btn.setText("LOGIN");

                cmdForget.setForeground(new Color(100, 100, 100));
                forgot_btn_clicked = true;
            }

            revalidate();
            repaint();

        });

        login_btn = new Button();
        login_btn.setBackground(new Color(0, 196, 147));
        login_btn.setForeground(new Color(250, 250, 250));
        login_btn.setText("LOGIN");

        login_btn.addActionListener(eventLogin);

        login_btn.addActionListener((ActionEvent e) -> {
            setLoginUserDetails();
        });

        login.add(login_btn, "w 40%, h 40");
    }

    public boolean isForgotBtnActive() {
        return !forgot_btn_clicked;
    }

    public void showRegister(boolean show) {
        if (show) {
            register.setVisible(true);
            login.setVisible(false);
        } else {
            register.setVisible(false);
            login.setVisible(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JPanel();
        register = new javax.swing.JPanel();

        setLayout(new java.awt.CardLayout());

        login.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(login, "card3");

        register.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout registerLayout = new javax.swing.GroupLayout(register);
        register.setLayout(registerLayout);
        registerLayout.setHorizontalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );
        registerLayout.setVerticalGroup(
            registerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        add(register, "card2");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel login;
    private javax.swing.JPanel register;
    // End of variables declaration//GEN-END:variables
}
