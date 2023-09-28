package hospitalmanagement.notused;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomJTableExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Custom JTable Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a table model with 5 columns
        DefaultTableModel model = new DefaultTableModel(new Object[]{"Checkbox 1", "Checkbox 2", "Radio", "Text", "Combo"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex < 2 ? Boolean.class : Object.class;
            }
        };

        JTable table = new JTable(model);

        // Create a renderer for the radio button column
        table.getColumnModel().getColumn(2).setCellRenderer(new RadioButtonRenderer());

        // Add a row to the table
        model.addRow(new Object[]{false, false, "Option 1", "", "Select"});

        frame.add(new JScrollPane(table));
        frame.pack();
        frame.setVisible(true);
    }

    // Custom renderer for radio buttons
    static class RadioButtonRenderer implements TableCellRenderer {
        private JPanel panel = new JPanel();
        private ButtonGroup group = new ButtonGroup();
        private JRadioButton option1 = new JRadioButton("Option 1");
        private JRadioButton option2 = new JRadioButton("Option 2");

        public RadioButtonRenderer() {
            panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
            group.add(option1);
            group.add(option2);
            panel.add(option1);
            panel.add(option2);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value != null && value.equals("Option 1")) {
                option1.setSelected(true);
            } else {
                option2.setSelected(true);
            }
            return panel;
        }
    }
}
