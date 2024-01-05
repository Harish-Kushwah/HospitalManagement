package hospitalmanagement;

import auth.Authentication;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import myutil.GradientPanel;
import myutil.SetImageIcon;

public class Main extends javax.swing.JFrame {

    public Main() {
        initComponents();
        ImageIcon icon = new ImageIcon("./images/doctor_icon1.png");
        this.setIconImage(icon.getImage());

        hospital_icon_panel.add(new SetImageIcon(new ImageIcon("./images/name_logo3.png"), 258, 197), BorderLayout.CENTER);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        screen = new GradientPanel(new Color(0x589BE8),new Color(0x5AEEB2),1100,50);
        hospital_icon_panel = new GradientPanel(new Color(0x589BE8),new Color(0x5AEEB2),200,348);
        info_panel = new javax.swing.JPanel();
        completed = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        info_msg = new javax.swing.JLabel();
        bar_panel = new javax.swing.JPanel();
        progress = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(500, 400));

        screen.setBackground(new java.awt.Color(255, 255, 255));
        screen.setPreferredSize(new java.awt.Dimension(400, 368));
        screen.setLayout(new java.awt.BorderLayout());

        hospital_icon_panel.setBackground(new java.awt.Color(255, 255, 51));
        hospital_icon_panel.setPreferredSize(new java.awt.Dimension(200, 396));
        hospital_icon_panel.setLayout(new java.awt.BorderLayout());

        info_panel.setBackground(new java.awt.Color(4, 22, 53));
        info_panel.setForeground(new java.awt.Color(255, 255, 255));
        info_panel.setMinimumSize(new java.awt.Dimension(100, 20));

        completed.setForeground(new java.awt.Color(255, 255, 255));
        completed.setText("0");
        completed.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("V.3.0.0  by Patil Infotech");

        info_msg.setForeground(new java.awt.Color(255, 255, 255));
        info_msg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout info_panelLayout = new javax.swing.GroupLayout(info_panel);
        info_panel.setLayout(info_panelLayout);
        info_panelLayout.setHorizontalGroup(
            info_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(info_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 244, Short.MAX_VALUE)
                .addComponent(info_msg, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
            .addGroup(info_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, info_panelLayout.createSequentialGroup()
                    .addContainerGap(644, Short.MAX_VALUE)
                    .addComponent(completed, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        info_panelLayout.setVerticalGroup(
            info_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(info_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(info_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(info_panelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(info_msg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(info_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(info_panelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(completed, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        hospital_icon_panel.add(info_panel, java.awt.BorderLayout.SOUTH);

        screen.add(hospital_icon_panel, java.awt.BorderLayout.CENTER);

        bar_panel.setBackground(new java.awt.Color(255, 255, 255));
        bar_panel.setPreferredSize(new java.awt.Dimension(200, 5));
        bar_panel.setLayout(new java.awt.BorderLayout());

        progress.setForeground(new java.awt.Color(20, 148, 255));
        progress.setPreferredSize(new java.awt.Dimension(146, 5));
        bar_panel.add(progress, java.awt.BorderLayout.CENTER);

        screen.add(bar_panel, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(screen, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(screen, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(682, 374));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        Main sc = new Main();
        Home home = new Home();
      
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                sc.setVisible(true);

            }
        });

        String[] msg = {"Establishing Database Connection...", "Loading Patient Records...", "Preparing User Interface...", "Syncing Data...", "Preparing Dashboard..."};
        int k = 0;

        try {

            for (int i = 0; i < 100; i++) {

                if (i == 60) {
                    Thread.sleep(1000);
                }
                if (i < 30) {
                    Thread.sleep(40);
                } else if (i < 60) {
                    Thread.sleep(60);
                } else if (i < 80) {
                    Thread.sleep(30);
                } else {
                    Thread.sleep(20);

                }
                info_msg.setText(msg[k]);
                if (i == 40 || i == 60 || i == 80 || i == 90) {
                    k++;
                }

                Thread.sleep(0);

                sc.progress.setValue(i);
                sc.completed.setText(Integer.toString(i) + "%");
            }
        } catch (InterruptedException exp) {

        }
        new Main().setVisible(false);
//        home.setVisible(true);
         Authentication authentication  = new Authentication(home);
         authentication.setVisible(true);

        sc.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bar_panel;
    private javax.swing.JLabel completed;
    private javax.swing.JPanel hospital_icon_panel;
    private static javax.swing.JLabel info_msg;
    private javax.swing.JPanel info_panel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar progress;
    private javax.swing.JPanel screen;
    // End of variables declaration//GEN-END:variables
}
