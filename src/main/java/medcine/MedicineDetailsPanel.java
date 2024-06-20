package medcine;

import hospitalmanagement.Home;
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
import database.Database;
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

    int pno;
    Home home;

    public MedicineDetailsPanel(String patientName, int pno, Home home) {
        this.pno = pno;
        this.home = home;
        setLayout(new MigLayout("fillx , insets 10 "));

        //===================================================================================
        /* Header of the form with the gradient line below */
        String selectedPatientName = "Name :" + patientName;

        JPanel info = new JPanel(new MigLayout("fillx "));
        info.add(new JLabel("Pno :" + Integer.toString(pno)), "wrap");
        info.add(new JLabel(selectedPatientName));

        add(info, "split 3 ,growx ");
        JButton more = new JButton("More");
        add(more, "al right");
        
        more.addActionListener((e) -> {
            home.search_patient.setPno(Integer.toString(pno));
            home.search_patient.initPage(pno);
            home.showPageOnWindow("search_patient");
            Notifications.getInstance().clear(Notifications.Location.TOP_RIGHT);

        });

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
            "Medicine Name", "Before/After", "Quantity", "Time"
        };
        outputTablePanel = new TablePanel(columns);

        Table table = outputTablePanel.getTable();

        Database db = Database.getInstance();

        ArrayList<MedicineDetails> list = db.getMedicineList(pno);

        for (MedicineDetails medicine : list) {
            table.addRow(new String[]{medicine.getMedicineName(), medicine.getMedicineMealTimeInWords(), medicine.getMedicineQuantity(), medicine.getMedicineTime()});

        }
    }

}
