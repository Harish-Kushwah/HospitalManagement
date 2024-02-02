package auth;

import hospitalmanagement.Home;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import database.Database;
import database.DatabaseConfig;
import database.UserDatabase;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import EmailTemplates.*;
import email.SendingHtmlMail;
import email.SendingOtp;
public class Authentication extends javax.swing.JFrame {

    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private MigLayout layout;
    private PanelCover cover;
    private PanelLoginAndRegister loginAndRegister;
    private boolean isLogin = true;
    private final double addSize = 30;
    private final double coverSize = 45;
    private final double loginSize = 60;

//    Home home;
    public Authentication() {
        // setLookAndFeel();
        initComponents();
//        this.home = home;

        ImageIcon icon = new ImageIcon("./images/doctor_icon1.png");
        this.setIconImage(icon.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        init();
    }

    private void init() {
        layout = new MigLayout("fill, insets 0");
        cover = new PanelCover();

        ActionListener eventRegister = (ActionEvent ae) -> {
            register();
        };
        ActionListener eventLogin = (ActionEvent e) -> {
            login();
        };

        MouseAdapter resendAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                resendVerificationCode();
            }

        };
        loginAndRegister = new PanelLoginAndRegister(eventRegister, eventLogin);
        loginAndRegister.setResendMouseAdapter(resendAdapter);

        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double fractionLogin;
                double size = coverSize;
                if (fraction <= 0.5f) {
                    size += fraction * addSize;
                } else {
                    size += addSize - fraction * addSize;
                }
                if (isLogin) {
                    fractionCover = 1f - fraction;
                    fractionLogin = fraction;
                    if (fraction >= 0.5f) {
                        cover.registerRight(fractionCover * 100);
                    } else {
                        cover.loginRight(fractionLogin * 100);
                    }
                } else {
                    fractionCover = fraction;
                    fractionLogin = 1f - fraction;
                    if (fraction <= 0.5f) {
                        cover.registerLeft(fraction * 100);
                    } else {
                        cover.loginLeft((1f - fraction) * 100);
                    }
                }
                if (fraction >= 0.5f) {
                    loginAndRegister.showRegister(isLogin);
                }
                fractionCover = Double.valueOf(df.format(fractionCover));
                fractionLogin = Double.valueOf(df.format(fractionLogin));
                layout.setComponentConstraints(cover, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(loginAndRegister, "width " + loginSize + "%, pos " + fractionLogin + "al 0 n 100%");
                bg.revalidate();
            }

            @Override
            public void end() {
                isLogin = !isLogin;
            }
        };
        Animator animator = new Animator(800, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0);  //  for smooth animation
        bg.setLayout(layout);

        bg.add(cover, "width " + coverSize + "%, pos " + (isLogin ? "1al" : "0al") + " 0 n 100%");
        bg.add(loginAndRegister, "width " + loginSize + "%, pos " + (isLogin ? "0al" : "1al") + " 0 n 100%"); //  1al as 100%
        loginAndRegister.showRegister(!isLogin);

        cover.login(isLogin);
        cover.addEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });

    }
    VerificationCode verificationCode = new VerificationCode();
    User user;
    Runnable sendVerificationThread;

    public void initSendVerificationThread(User user) {
        sendVerificationThread = () -> {
            if (sendVerificationCode(user)) {
                loginAndRegister.setMessageLabels();
                loginAndRegister.setVerificationStatus("Verfification code send to email");

                // loading.setVisible(false);
            } else {
                loginAndRegister.setMessageLabels();
                loginAndRegister.setVerificationStatus("Unable to send Verification code to email");
            }
        };
    }

    private void register() {
        user = loginAndRegister.getSignUpUserDetails();
        if (user.getUserName() == null || user.getEmail() == null || user.getPassword() == null) {
            showMessage(Message.MessageType.ERROR, "Enter All the detaisl first");
        } else {

            initSendVerificationThread(user);
          
            if (loginAndRegister.isSignUpBtnMode()) {
                loginAndRegister.addVerificationCodeUI();
                new Thread(sendVerificationThread).start();
           
            } else if (loginAndRegister.isSubmitBtnMode()) {

                boolean status = loginAndRegister.verifyEnteredCode(verificationCode.getCode());

                if (status) {
                    if (user.getEmail().length() != 0) {
                        UserDatabase db = UserDatabase.getInstance();

                        if (db.insertNewUser(user)) {
                            showMessage(Message.MessageType.SUCCESS, "Register sucess");
                            loginAndRegister.resetRegistrationPage();

                            Runnable create_db = () -> {
                                user = db.isValidUserByName(user);
                                String db_name ="db_" + String.valueOf(user.getUserId());
                                if (DatabaseConfig.initNewDatabase(user)) {                                    
                                    user.setDatabase_name(db_name);
                                    db.updateUserDatabase(user);
                                }
                                
                            };
                            new Thread(create_db).start();

                        } else {
                            showMessage(Message.MessageType.ERROR, "Error Register");

                        }
                    } else {
                        showMessage(Message.MessageType.ERROR, "Enter valid email");
                    }
                } else {
                    showMessage(Message.MessageType.ERROR, "Enter Valid code");
                }
            }

        }
        revalidate();
        repaint();
    }

    public boolean sendVerificationCode(User user) {
        loginAndRegister.setLoadingGif();
        return verificationCode.sendVerificationCode(user.getEmail());
    }

    public void resendVerificationCode() {
        //update the user details if old email was wrong the upadate it and resend it
        loginAndRegister.setUserDetails();
        user = loginAndRegister.getSignUpUserDetails();
        initSendVerificationThread(user);
        new Thread(sendVerificationThread).start();
        //sendVerificationCode( this.registering_user);
    }

    private void login() {
        if (loginAndRegister.isForgotBtnActive()) {
            forgotBtnAction();
        } else {
            loginBtnAction();
        }

    }

    private void loginBtnAction() {

        UserDatabase db = UserDatabase.getInstance();
        User login_page_user = loginAndRegister.getLoginUser();
        if (login_page_user.getEmail() == null || login_page_user.getPassword() == null) {
            showMessage(Message.MessageType.ERROR, "Enter All the details first");
        } else {

            System.out.println("here");
            User login_user = db.isValidUser(login_page_user);

            if (login_user != null) {

                showMessage(Message.MessageType.SUCCESS, "Login successful");
                 
                Runnable create_db = () -> {
                    if (DatabaseConfig.LoadDatabase(login_user)) {                       
                       Home home = new Home(login_user);
                        
                        try {
                            showMessage(Message.MessageType.SUCCESS, "Loding database...");
                            Thread.sleep(1000);
                            this.dispose();
                            showMessage(Message.MessageType.SUCCESS, "Completed..");
                            Thread.sleep(100);
                            home.setVisible(true);
                        } catch (InterruptedException ex) {
                            
                        }
                        
                    }

                };
                Thread th = new Thread(create_db);             
                th.start();
             
//                Runnable ui =()->{
//                  
//                };
                
//                Thread ui_th = new Thread(ui);
//                synchronized(th){
//                    ui_th.start();
//                }
                
            } else {
                showMessage(Message.MessageType.ERROR, "Enter Valid Details");
            }

        }
    }

    private void forgotBtnAction() {
        UserDatabase db = UserDatabase.getInstance();
        User login_page_user = loginAndRegister.getLoginUser();
        if (login_page_user.getEmail() == null || login_page_user.getUserName() == null || login_page_user.getPassword() == null) {
            showMessage(Message.MessageType.ERROR, "Enter All the details first");
        } else {
            User login_user = db.isValidUserByName(login_page_user);
            if (login_user != null) {

                String to = login_page_user.getEmail();
                String subject = "Healix Password";
                String message = UpdatePassword.getHtml(login_page_user.getUserName());

                if (db.updateUserAccountPassword(login_page_user)) {
                    SendingHtmlMail sendPasswordToEmail = new SendingHtmlMail(to, subject, message);
                    sendPasswordToEmail.usingAdminEmail();
                    sendPasswordToEmail.start();
                    showMessage(Message.MessageType.SUCCESS, "Password Update Successfully");
                    loginAndRegister.resetLogin();

                } else {
                    showMessage(Message.MessageType.ERROR, "No Details found");
                }
            } else {
                showMessage(Message.MessageType.ERROR, "No Details found");
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 933, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 537, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void showMessage(Message.MessageType messageType, String message) {
        Message ms = new Message();
        ms.showMessage(messageType, message);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (!ms.isShow()) {
                    bg.add(ms, "pos 0.5al -30", 0); //  Insert to bg fist index 0
                    ms.setVisible(true);
                    bg.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                if (ms.isShow()) {
                    f = 40 * (1f - fraction);
                } else {
                    f = 40 * fraction;
                }
                layout.setComponentConstraints(ms, "pos 0.5al " + (int) (f - 30));
                bg.repaint();
                bg.revalidate();
            }

            @Override
            public void end() {
                if (ms.isShow()) {
                    bg.remove(ms);
                    bg.repaint();
                    bg.revalidate();
                } else {
                    ms.setShow(true);
                }
            }
        };
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    animator.start();
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
            }
        }).start();
    }

    public void setLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
                System.out.println(info.getClassName());

            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Authentication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Authentication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Authentication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Authentication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

//    public static void main(String args[]) {
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Authentication(null).setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables

}
