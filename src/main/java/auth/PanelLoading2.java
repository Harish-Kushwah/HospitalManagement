package auth;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import myutil.SetImageIcon;

public class PanelLoading2 extends javax.swing.JPanel {

    public PanelLoading2() {
        initComponents();
        loading_label.setIcon(new ImageIcon("./images/loading_1.gif"));
        
       // loading_panel.add(new SetImageIcon(new ImageIcon("./images/loading_1.gif"), 400, 400), BorderLayout.CENTER);
        setOpaque(false);
        setFocusCycleRoot(true);
        setVisible(false);
        setFocusable(true);
       // loading_label.setVisible(false);
        addMouseListener(new MouseAdapter() {
        });
        
       System.out.print("created");
      // this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loading_label = new javax.swing.JLabel();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loading_label, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loading_label, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setColor(new Color(255, 255, 255));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setComposite(AlphaComposite.SrcOver);
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel loading_label;
    // End of variables declaration//GEN-END:variables
}
