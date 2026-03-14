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

public class ExerciseRowPanel extends JPanel {

    public final JTextField exerciseInput = new JTextField();
    public final JCheckBox delete_chk = new JCheckBox();
    public GradientPanel p;

    public ExerciseRowPanel(String exercise) {
        super(new BorderLayout(5, 10));
        initComponents();
        exerciseInput.setText(exercise);
    }

    private void initComponents() {
        JPanel name = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        name.setOpaque(false);
        JLabel bullet = new JLabel("*");
        name.add(bullet);

        p = new GradientPanel(new Color(0xFFFFFF), new Color(0xC5EFC5), 500, 35);
        p.setLayout(new BorderLayout(8, 0));

        exerciseInput.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        exerciseInput.setBackground(new Color(0xE3F5E3));

        delete_chk.setBackground(new Color(0xaaddaa));
        delete_chk.setFocusPainted(false);
        delete_chk.setOpaque(true);
        delete_chk.setPreferredSize(new Dimension(22, 22));

        p.add(exerciseInput, BorderLayout.CENTER);
        p.add(delete_chk, BorderLayout.EAST);

        this.setBackground(new Color(0xC5EFC5));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));

        add(name, BorderLayout.WEST);
        add(p, BorderLayout.CENTER);
    }

    public String getExerciseText() {
        return exerciseInput.getText() == null ? "" : exerciseInput.getText().trim();
    }

    public boolean getDeleteCheckStatus() {
        return delete_chk.isSelected();
    }
}
