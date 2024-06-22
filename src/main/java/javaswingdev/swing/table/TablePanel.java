
package javaswingdev.swing.table;

import java.awt.Dimension;
import javaswingdev.swing.RoundPanel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author haris
 */
public class TablePanel extends RoundPanel{
    
    private int MAX_COL = 10;
    private String columns[] = new String[MAX_COL];
    private Table table = new Table();
    private int MAX_WIDTH = 600;

    public int getMAX_WIDTH() {
        return MAX_WIDTH;
    }

    public void setMAX_WIDTH(int MAX_WIDTH) {
        this.MAX_WIDTH = MAX_WIDTH;
    }

    public int getPREF_WIDTH() {
        return PREF_WIDTH;
    }

    public void setPREF_WIDTH(int PREF_WIDTH) {
        this.PREF_WIDTH = PREF_WIDTH;
    }
    private int PREF_WIDTH = 600;
        
    public void setColumnNames(String col[])
    {
        this.columns = col;
    }
    public void addRow(Object row[])
    {
        this.table.addRow(row);
    }
    public void removeAllRow()
    {
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        while(model.getRowCount()>0){ 
          this.table.removeRow();
        }
    }
    public void setRowHeight(int height)
    {
        this.table.setRowHeight(height);
    }
    
    public Table getTable()
    {
        return this.table;
    }
    
    
    public TablePanel(String columns[] , int MAX_WIDTH)
    {
        this.MAX_WIDTH = MAX_WIDTH;
        this.PREF_WIDTH = MAX_WIDTH;
//        setRound(10);
        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JScrollPane sp = new JScrollPane();

         table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            columns
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
        sp.setViewportView(table);        
        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(this);
        this.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
//                .addComponent(sp)
                    .addComponent(sp, 250, this.PREF_WIDTH, this.MAX_WIDTH)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sp, 250, 250, 250)
                .addContainerGap(10, 20))
        );
        
       
        table.fixTable(sp);
//        table.setPreferredSize(new Dimension(600,300));

    }
}
