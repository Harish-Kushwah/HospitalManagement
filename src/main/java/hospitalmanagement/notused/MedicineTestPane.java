///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package hospitalmanagement;
//
//import hospitalmanagement.notused.BandTypePanel2;
//import java.awt.BorderLayout;
//import java.awt.Dimension;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import javax.swing.JButton;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//
///**
// *
// * @author haris
// */
//public class MedicineTestPane extends JPanel implements ActionListener {
//
//        private JPanel mainList;
//        static int i=0;
//        BandTypePanel2 bt[] = new BandTypePanel2[20];
//        public MedicineTestPane() {
//            setLayout(new BorderLayout());
//
//            mainList = new JPanel(new GridBagLayout());
//            GridBagConstraints gbc = new GridBagConstraints();
//            gbc.gridwidth = GridBagConstraints.REMAINDER;
//            gbc.weightx = -1;
//            gbc.weighty = 1;
//            mainList.add(new JPanel(), gbc);
//
//            add(new JScrollPane(mainList));
//
//            JButton add = new JButton("Add");
//            
//            add.addActionListener(this);
//
//            add(add, BorderLayout.SOUTH);
//
//        }
//        
//        
//
//        @Override
//        public Dimension getPreferredSize() {
//            return new Dimension(500, 500);
//        }
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//           
//                    GridBagConstraints gbc = new GridBagConstraints();
//                    gbc.gridwidth = GridBagConstraints.REMAINDER;
//                    gbc.weightx = 1;
//                    gbc.fill = GridBagConstraints.HORIZONTAL;
//                    
//                    bt[i] = new BandTypePanel2();
//                    mainList.add(bt[i], gbc, 0);
//                    i++;
//                    validate();
//                    repaint();
//        }
//    
//}