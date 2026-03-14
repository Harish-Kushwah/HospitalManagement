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

public class AdviceRowPanel extends JPanel {

    public final JTextField adviceInput = new JTextField();
    public final JCheckBox delete_chk = new JCheckBox();
    public GradientPanel p;

    public AdviceRowPanel(String advice) {
        super(new BorderLayout(5, 10));
        initComponents();
        adviceInput.setText(advice);
    }

    private void initComponents() {
        JPanel name = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        name.setOpaque(false);
        JLabel bullet = new JLabel("*");
        name.add(bullet);

        p = new GradientPanel(new Color(0xFFFFFF), new Color(0xF5C5D5), 500, 35);
        p.setLayout(new BorderLayout(8, 0));

        adviceInput.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        adviceInput.setBackground(new Color(0xFDE8EF));

        delete_chk.setBackground(new Color(0xffaacc));
        delete_chk.setFocusPainted(false);
        delete_chk.setOpaque(true);
        delete_chk.setPreferredSize(new Dimension(22, 22));

        p.add(adviceInput, BorderLayout.CENTER);
        p.add(delete_chk, BorderLayout.EAST);

        this.setBackground(new Color(0xF5C5D5));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));

        add(name, BorderLayout.WEST);
        add(p, BorderLayout.CENTER);
    }

    public String getAdviceText() {
        return adviceInput.getText() == null ? "" : adviceInput.getText().trim();
    }

    public boolean getDeleteCheckStatus() {
        return delete_chk.isSelected();
    }
}
