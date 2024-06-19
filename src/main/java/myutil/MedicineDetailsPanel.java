package myutil;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javaswingdev.swing.RoundPanel;
import javaswingdev.swing.table.Table;
import javaswingdev.swing.table.TablePanel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import raven.crazypanel.CrazyPanel;
import raven.toast.Notifications;
import util.swing.MyJScrollPane;

/**
 *
 * @author haris
 */
public class MedicineDetailsPanel extends CrazyPanel {

    public TablePanel outputTablePanel;
    public JPanel outputPanel;

    public MedicineDetailsPanel(String patientName , int pno) {
        setLayout(new MigLayout("fillx , insets 10 "));

        //===================================================================================
        /* Header of the form with the gradient line below */
        String selectedPatientName = "Name :" + patientName;
        add(new JLabel(selectedPatientName), "split 3 ,growx ");
        add(new JButton("More"), "al right");

//        ImageIcon icon = new ImageIcon("images/cross_arrow.png");
        JButton close = new JButton("X");
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Notifications.getInstance().clear(Notifications.Location.TOP_RIGHT);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                close.setBackground(Color.red);
                close.setForeground(Color.white);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                close.setForeground(new Color(51, 51, 255));
                close.setBackground(Color.white);
            }

        });
        close.setForeground(new Color(51, 51, 255));
//        close.setPreferredSize(new Dimension(40,40));
        add(close, "al right , wrap 5");

        //===================================================================================
        addTableSection();

        add(outputTablePanel, "grow");

    }

    public void addTableSection() {
        String columns[] = new String[]{
            "Medicine Name", "Qty", "Time", "before"
        };
        outputTablePanel = new TablePanel(columns);

        Table table = outputTablePanel.getTable();
        table.addRow(new String[]{"Paracetamole", "10", "After meal", "after"});
        table.addRow(new String[]{"Paracetamole", "10", "After meal", "after"});
        table.addRow(new String[]{"Paracetamole", "10", "After meal", "after"});
        table.addRow(new String[]{"Paracetamole", "10", "After meal", "after"});
        table.addRow(new String[]{"Paracetamole", "10", "After meal", "after"});
        table.addRow(new String[]{"Paracetamole", "10", "After meal", "after"});
        table.addRow(new String[]{"Paracetamole", "10", "After meal", "after"});
        table.addRow(new String[]{"Paracetamole", "10", "After meal", "after"});
        table.addRow(new String[]{"Paracetamole", "10", "After meal", "after"});
        table.addRow(new String[]{"Paracetamole", "10", "After meal", "after"});

    }

}
