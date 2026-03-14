package medcine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import util.swing.panel.GradientPanel;

public class InvestigationRowPanel extends JPanel {

    public final JTextField investigationInput = new JTextField();
    public final JCheckBox delete_chk = new JCheckBox();
    public GradientPanel p;

    public InvestigationRowPanel(String investigation) {
        super(new BorderLayout(5, 10));
        initComponents();
        investigationInput.setText(investigation);
    }

    private void initComponents() {
        JPanel name = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        name.setOpaque(false);
        JLabel bullet = new JLabel("*");
        name.add(bullet);

        p = new GradientPanel(new Color(0xFFFFFF), new Color(0xF5E8C5), 500, 35);
        p.setLayout(new BorderLayout(8, 0));

        investigationInput.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        investigationInput.setBackground(new Color(0xFDF5E0));

        delete_chk.setBackground(new Color(0xffddaa));
        delete_chk.setFocusPainted(false);
        delete_chk.setOpaque(true);
        delete_chk.setPreferredSize(new Dimension(22, 22));

        p.add(investigationInput, BorderLayout.CENTER);
        p.add(delete_chk, BorderLayout.EAST);

        this.setBackground(new Color(0xF5E8C5));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));

        add(name, BorderLayout.WEST);
        add(p, BorderLayout.CENTER);
    }

    public String getInvestigationText() {
        return investigationInput.getText() == null ? "" : investigationInput.getText().trim();
    }

    public boolean getDeleteCheckStatus() {
        return delete_chk.isSelected();
    }
}
