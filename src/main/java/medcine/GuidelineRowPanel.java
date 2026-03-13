package medcine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import util.swing.panel.GradientPanel;

public class GuidelineRowPanel extends JPanel {

    public final JTextField guidelineInput = new JTextField(28);
    public final JCheckBox delete_chk = new JCheckBox();
    public GradientPanel p;

    public GuidelineRowPanel(String guideline) {
        super(new BorderLayout(5, 10));
        initComponents();
        guidelineInput.setText(guideline);
    }

    private void initComponents() {
        JPanel name = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        name.setOpaque(false);
        JLabel bullet = new JLabel("*");
        name.add(bullet);

        p = new GradientPanel(new Color(0xFFFFFF), new Color(0xC5C5EF), 400, 35);
        p.setLayout(new FlowLayout(FlowLayout.LEFT, 8, 6));

        guidelineInput.setBorder(null);
        guidelineInput.setBackground(new Color(0xE7EAF3));

        delete_chk.setBackground(new Color(0xccccff));
        delete_chk.setFocusPainted(false);

        p.add(guidelineInput);
        p.add(delete_chk);

        this.setBackground(new Color(0xC5C5EF));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        add(name, BorderLayout.WEST);
        add(p, BorderLayout.CENTER);
    }

    public String getGuidelineText() {
        return guidelineInput.getText() == null ? "" : guidelineInput.getText().trim();
    }

    public boolean getDeleteCheckStatus() {
        return delete_chk.isSelected();
    }
}
