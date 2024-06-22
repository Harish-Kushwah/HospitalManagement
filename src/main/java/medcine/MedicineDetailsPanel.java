package medcine;

import pages.Home;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javaswingdev.swing.table.Table;
import javaswingdev.swing.table.TablePanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import database.Database;
import javaswingdev.system.SystemStrings;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import raven.crazypanel.CrazyPanel;
import raven.toast.Notifications;

/**
 *
 * @author haris This Panel is pop up when user click on the dashboard patient
 * names
 */
public class MedicineDetailsPanel extends CrazyPanel {

    public TablePanel outputTablePanel;
    public JPanel outputPanel;

    int pno;
    String name;
    Home home;

    public MedicineDetailsPanel(String name, Home home, boolean typeOfNotification) {
        this.home = home;
        this.name = name;
        setLayout(new MigLayout("fillx , insets 10 "));

        //===================================================================================
        /* Header of the form with the gradient line below */
        String selectedName = "Name :" + name;

        add(new JLabel(selectedName), "split 3 ,growx ");
        JButton more = new JButton("Remove");
        add(more, "al right");

        more.addActionListener((e) -> {

            Table tb = outputTablePanel.getTable();
            DefaultTableModel model = (DefaultTableModel) tb.getModel();
            if (model.getRowCount() > 0) {
                int selectedRow = tb.getSelectedRow();
                if (selectedRow >= 0) {
                    Database db = Database.getInstance();
                    String medicineName = (String) tb.getValueAt(selectedRow, 0);

                    if (typeOfNotification == SystemStrings.CATEGORY) {

                        int mno = db.getMedicineNo(medicineName);
                        if (mno != -1 && db.removeCategoryMedicine(this.name, mno)) {
                            model.removeRow(selectedRow);
                            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, medicineName + " Removed Successfully");
                        } else {
                            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, medicineName + " Unable To Remove");

                        }
                    } else {
                        if (db.removeBookmarkMedicine(this.name, medicineName)) {
                            model.removeRow(selectedRow);
                            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, medicineName + " Removed Successfully");
                        } else {
                            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, medicineName + " Unable To Remove");

                        }
                    }
                }
            }
        });

//        ImageIcon icon = new ImageIcon("images/cross_arrow.png");
        JButton close = new JButton("X");
        close.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Notifications.getInstance().clearAll();
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
        addCategoryTableSection(typeOfNotification);

        add(outputTablePanel, "grow");

        if (typeOfNotification == SystemStrings.CATEGORY) {
            Table tb = outputTablePanel.getTable();
            tb.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int selectedRow = tb.getSelectedRow();
                    if (selectedRow >= 0) {
                        String medicineName = (String) tb.getValueAt(selectedRow, 0);
                        home.addMedicine(medicineName);
                        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_RIGHT, medicineName + " Added for prescription");
                    }
                }
            });
        }
    }

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
        addPatientTableSection();

        add(outputTablePanel, "grow");

    }

    public void addPatientTableSection() {
        String columns[] = new String[]{
            "Medicine Name", "Before/After", "Quantity", "Time"
        };
        outputTablePanel = new TablePanel(columns, 600);

        Table table = outputTablePanel.getTable();

        Database db = Database.getInstance();
        ArrayList<MedicineDetails> list = db.getMedicineList(pno);
        for (MedicineDetails medicine : list) {
            table.addRow(new String[]{medicine.getMedicineName(), medicine.getMedicineMealTimeInWords(), medicine.getMedicineQuantity(), medicine.getMedicineTime()});
        }
    }

    public void addCategoryTableSection(boolean typeOfNotification) {
        String columns[] = new String[]{
            "Medicine Name"
        };
        outputTablePanel = new TablePanel(columns, 250);

        Table table = outputTablePanel.getTable();

        Database db = Database.getInstance();
        if (typeOfNotification == SystemStrings.CATEGORY) {

            ArrayList<String> list = db.getAllCategoryMedicine(name);
            for (String medicine : list) {
                table.addRow(new String[]{medicine});
            }
        } else {
            ArrayList<MedicineDetails> list = db.getLikeBookmarkMedicine(name);
            for (MedicineDetails medicine : list) {
                table.addRow(new String[]{medicine.getMedicineName()});
            }
        }

    }

}
