/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package myutil;

import myutil.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.TableView;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


//This class is for the making the card with the label and icon
class InfoBoxPanel
{
    public static JPanel getCardPanel(BooksPanelInfo booksPanelInfo)
    {

       JPanel profile_panel = new JPanel(new BorderLayout());
        profile_panel.setBackground(Color.white);


        GradientPanel image_panel = new GradientPanel(booksPanelInfo.getColor(),130,100);
        image_panel.setDirectionLeft();
        image_panel.add(new SetImageIcon(booksPanelInfo.getIcon(),100,85)) ;
        profile_panel.add(image_panel , BorderLayout.WEST);

        JPanel info = new GradientPanel(new Color(0xE7E7EE),100,80);
        info.setLayout(new GridBagLayout());
        info.setPreferredSize(new Dimension(100,80));
        info.setBackground(new Color(0xD0F8D5));

        GridBagConstraints info_gbc = new GridBagConstraints();

        info_gbc.gridx = 0;
        info_gbc.gridy = 0;
        info_gbc.anchor = GridBagConstraints.CENTER;
        info_gbc.insets.left=10;
        info_gbc.insets.top=0;
        info_gbc.weightx=1;

        JLabel status = new JLabel();
        status.setText("<html>" + booksPanelInfo.getTitle() + "</html>");
        status.setFont(new Font("Serif" , Font.BOLD,14));
        info.add(status,info_gbc);
        info_gbc.insets.top=10;
        info_gbc.gridx = 0;
        info_gbc.gridy = 1;

        JLabel name = new JLabel(booksPanelInfo.getTotal());
        name.setForeground(new Color(0xE3336A));
        name.setFont(new Font("Arial",Font.BOLD,15));
        info.add(name,info_gbc);
        profile_panel.add(info,BorderLayout.CENTER);

        JPanel bottom_panel = new JPanel();
        JLabel  l = new JLabel("More info");
        l.setForeground(new Color(0x3B3B44));
        bottom_panel.add(l);
        bottom_panel.setForeground(Color.white);
        bottom_panel.setBackground(booksPanelInfo.getColor());

        JPanel bottom_panel_icon = new JPanel();
        bottom_panel_icon.add(new SetImageIcon(new ImageIcon("./images/right.png"),15,15));
        profile_panel.add(bottom_panel,BorderLayout.PAGE_END);
        bottom_panel.add(bottom_panel_icon);
        return  profile_panel;
        }
}
public  class NewDashboardPanel extends JPanel implements WindowStateListener, MouseListener
{
    Color color1 = new Color(245, 245, 245);
    Color color2 = new Color(102, 204, 10);

    Color BACKGROUND_COLOR = new Color(0xE7E7FA);

    LineBorder PANEL_BORDER = new LineBorder(new Color(0x505354),2,true);
     LineBorder TABLE_BORDER = new LineBorder(new Color(0x0F152D),1,true);
    LineBorder HOVER_BORDER = new LineBorder(new Color(0x7878EC),2,true);

        Color  MEMBER_PANEL_COLOR = new Color(0xD0D0EE);

    JPanel total_books,total_student,return_today,not_return_today;
    int width = 700;
    int new_books_height = 200;

    Database database = Database.getInstance();

    String TOTAL_PATIENT=database.getTotalNumberOfUser();
    String TOTAL_MONTH_PATIENT=database.getTotalMonthlyPatient();
    String TOTAL_TODAY_PATIENT=database.getTotalTodayPatient();

    public NewDashboardPanel(){
        
        this.setLayout(new BorderLayout());

        //create a dashboard for all the component
        JPanel dashboardPanel = new JPanel();
        dashboardPanel.setBackground(BACKGROUND_COLOR);
        //dashboardPanel.setBorder(PANEL_BORDER);
        dashboardPanel.setLayout(new BorderLayout(20,10));

        //set the header for dashboard panel
        dashboardPanel.add(setHeaderPanel(),BorderLayout.PAGE_START);

        //set the books panel
        dashboardPanel.add(setBooksPanel(),BorderLayout.CENTER);

        //set the foot panel
        
        JPanel temp = new JPanel(new BorderLayout());
        temp.add(setFootPanel(),BorderLayout.CENTER);
        
        JPanel fees_panel = new JPanel();
        fees_panel.setPreferredSize(new Dimension(1100,30));
        fees_panel.setBackground(Color.red);
        
        temp.add(fees_panel , BorderLayout.PAGE_END);
        
        dashboardPanel.add(setFootPanel(),BorderLayout.PAGE_END);

       // dashboardPanel.add(new JPanel() , BorderLayout.PAGE_END);

        //scroll bar added on dashboard panle
        JScrollPane scrollPane =  new JScrollPane(dashboardPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED , JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setWheelScrollingEnabled(true);

        this.add(scrollPane);
        
    }
    public void refresh()
    {
        
    }

    public  JPanel setHeaderPanel()
    {
        JPanel head_title_panel = new JPanel(new GridBagLayout());
        head_title_panel.setBackground(BACKGROUND_COLOR);
        int head_title_height = 100;

       
        head_title_panel.setPreferredSize(new Dimension(width,head_title_height));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx =0;
        gbc.gridy=0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets.left=10;
        gbc.insets.top=0;
        gbc.weightx=1;
        JLabel title = new JLabel("Welcome to Dashboard");
        title.setFont(new Font("Serif",Font.BOLD,22));
        title.setPreferredSize(new Dimension(300,22));
        head_title_panel.add(title,gbc );


        JLabel sub_title = new JLabel("Admin Dashboard");
        sub_title.setFont(new Font("Arial",Font.PLAIN,12));
        sub_title.setPreferredSize(new Dimension(200,10));

        gbc.gridx = 0;
        gbc.gridy=1;
        gbc.insets.left=15;
        gbc.insets.top=15;
        gbc.insets.bottom=15;
        head_title_panel.add(sub_title,gbc );

        return  head_title_panel;
    }
    public  JPanel setBooksPanel()
    {
        GridLayout gl = new GridLayout(1,4);
        gl.setVgap(10);
        gl.setHgap(10);
        JPanel books_panel = new JPanel(gl);
        int books_panel_height = 120;
        books_panel.setPreferredSize(new Dimension(width,books_panel_height));
        books_panel.setBackground(BACKGROUND_COLOR);

        ImageIcon icon =new ImageIcon("images/patient_icon5.png");
        BooksPanelInfo booksPanelInfo = new BooksPanelInfo();
        booksPanelInfo.setInfo("Total Patient",TOTAL_PATIENT,icon);
      
        booksPanelInfo.setColor(new Color(0x8C7EEF));
        total_books =  InfoBoxPanel.getCardPanel(booksPanelInfo);


        icon =new ImageIcon("./images/teamwork.png");
        booksPanelInfo.setInfo("Today's Patient",TOTAL_TODAY_PATIENT,icon);
//        booksPanelInfo.setColor(new Color(0xE1E153));
        booksPanelInfo.setColor(new Color(0xF6D66A));
        total_student =  InfoBoxPanel.getCardPanel(booksPanelInfo);

        icon =new ImageIcon("./images/patient_icon4.png");
        booksPanelInfo.setInfo("Monthly Patient",TOTAL_MONTH_PATIENT,icon);
        booksPanelInfo.setColor(new Color(0x91F589));
        return_today=  InfoBoxPanel.getCardPanel(booksPanelInfo);

        icon =new ImageIcon("./images/arrow.png");
        booksPanelInfo.setColor(new Color(0xEF3442));
        booksPanelInfo.setInfo("Not Return Today",TOTAL_TODAY_PATIENT,icon);

        not_return_today = InfoBoxPanel.getCardPanel(booksPanelInfo);

        total_books.setBorder(PANEL_BORDER);
        total_student.setBorder(PANEL_BORDER);
        return_today.setBorder(PANEL_BORDER);
        not_return_today.setBorder(PANEL_BORDER);

        total_books.addMouseListener(this);
        total_student.addMouseListener(this);
        return_today.addMouseListener(this);
        not_return_today.addMouseListener(this);


        books_panel.add(total_books);
        books_panel.add(total_student);
        books_panel.add(return_today);
//        books_panel.add(not_return_today);

        return  books_panel;
    }

    public  JPanel setFootPanel()
    {
        //Foot panel-------------------------------------------------------------------------------
        JPanel foot_panel = new JPanel();
        GridLayout foot_grid = new GridLayout(1,2);
        foot_grid.setVgap(5);
        foot_grid.setHgap(10);
        foot_panel.setLayout(foot_grid);
        foot_panel.setBackground(BACKGROUND_COLOR);
        int foot_panel_height = 450;
        foot_panel.setPreferredSize(new Dimension(700,foot_panel_height));

        //---------------------------
       // JPanel  new_books = new JPanel( new BorderLayout());
         Color c1  = new Color(0xe8e88f);
         Color c2  = new Color(0xb3f1f2);
        GradientPanel today_patient_panel = new GradientPanel(c1,c2,650,new_books_height);
        today_patient_panel.setLayout(new BorderLayout());
       
        JLabel today_patient_label = new JLabel("Todays Patients", SwingConstants.CENTER);
        today_patient_label.setPreferredSize(new Dimension(300,40));
        today_patient_label.setFont(new Font("arial",Font.BOLD,13));
        today_patient_label.setForeground(new Color(0x020d28));
        
        /*
         This is for showing total fees in the heading with todays fees 
        */
        String total_fees = "Total Fees:Rs." + getTodaysTotalFees(); 
        JLabel today_patient_label_fees = new JLabel(total_fees, SwingConstants.CENTER);
        today_patient_label_fees.setPreferredSize(new Dimension(300,40));
        today_patient_label_fees.setFont(new Font("arial",Font.BOLD,13));
        today_patient_label_fees.setForeground(new Color(0x020d28));
        
        GradientPanel today_patient_panel_head =  new GradientPanel(c1,c2,650,40);
        today_patient_panel_head.setLayout(new BorderLayout());
        
        today_patient_panel_head.add(today_patient_label , BorderLayout.WEST);
        today_patient_panel_head.add(today_patient_label_fees , BorderLayout.EAST);
       
        today_patient_panel.add(today_patient_panel_head,BorderLayout.PAGE_START);

        //add the table
        today_patient_panel.add(getAllTodayPatientTable(),BorderLayout.CENTER);

        today_patient_panel.setBackground(MEMBER_PANEL_COLOR);
        today_patient_panel.setBorder(TABLE_BORDER);
        today_patient_panel.setPreferredSize(new Dimension(650,new_books_height));

        //----------------------------------------------------------------------------------------------

        //JPanel  new_members = new JPanel(new BorderLayout());
       
        GradientPanel monthly_patients_panel = new GradientPanel(c1,c2,650,new_books_height);
        monthly_patients_panel.setLayout(new BorderLayout());
        monthly_patients_panel.setPreferredSize(new Dimension(650,new_books_height));

        monthly_patients_panel.setBorder(TABLE_BORDER);
       // new_members.setBackground(Color.red);

        JLabel monthly_patients_label = new JLabel("Monthly Patients",SwingConstants.CENTER);
        monthly_patients_label.setPreferredSize(new Dimension(300,40));
        monthly_patients_label.setFont(new Font("arial",Font.BOLD,13));
        monthly_patients_label.setForeground(new Color(0x020d28));
        
        String month_total_fees = "Total Fees:Rs." + getMonthlyTotalFees(); 
        JLabel month_patient_label_fees = new JLabel(month_total_fees, SwingConstants.CENTER);
        month_patient_label_fees.setPreferredSize(new Dimension(300,40));
        month_patient_label_fees.setFont(new Font("arial",Font.BOLD,13));
        month_patient_label_fees.setForeground(new Color(0x020d28));
        
        GradientPanel month_patient_panel_head =  new GradientPanel(c1,c2,650,40);
        month_patient_panel_head.setLayout(new BorderLayout());
        
        month_patient_panel_head.add(monthly_patients_label , BorderLayout.WEST);
        month_patient_panel_head.add(month_patient_label_fees , BorderLayout.EAST);
       
        
        monthly_patients_panel.add(month_patient_panel_head,BorderLayout.PAGE_START);
        //add the table
        monthly_patients_panel.add(getAllMonthlyTable(),BorderLayout.CENTER);

        foot_panel.add(today_patient_panel);
        foot_panel.add(monthly_patients_panel);


        return  foot_panel;
    }

    //returns the table with scroll pane
    public  JScrollPane getAllTodayPatientTable()
    {


        ArrayList<PatientDetails> patientDetailsArrayList = database.getTodayPatient();
        int row = patientDetailsArrayList.size();

        String data[][] = new String[row][4];
        int i=0;
        for(PatientDetails patient : patientDetailsArrayList)
        {
           // System.out.println(patient.getName() + " " + patient.getGender());
            data[i][0] = Integer.toString(patient.getPid());
            data[i][1] = patient.getDate().toString();
            data[i][2] = patient.getName();
            data[i][3]=  Float.toString(patient.getFees());
            i++;
        }

        String column[] = {"PID" ,"NAME" , "Date","Fees(Rs.)" };


        LibraryTable new_books_table=new LibraryTable(data,column);
        //new_books_table.setTextColor(new Color(0x000000));
        Color row1  = new Color(0xE9E9EE);
        Color row2  = new Color(0xE8E8EC);
        new_books_table.setRowColor(Color.white,Color.white);
        JScrollPane tableScrollPane = new JScrollPane(new_books_table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScrollPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
        new_books_table.setFillsViewportHeight(true);

        TableModel tableModel = new_books_table.getModel();
//        System.out.println(tableModel.getRowCount());
//        int total_row = tableModel.getRowCount();
//        for (int count = 0; count < tableModel.getRowCount(); count++){
//            (tableModel.getValueAt(count, 0)).;
//
//
//        }


        new_books_table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
               // super.mouseClicked(e);
                int rowIndex = new_books_table.getSelectedRow();


//                System.out.println(tableModel.getValueAt(rowIndex,0));
//                System.out.println(tableModel.getValueAt(rowIndex,1));
//                System.out.println(tableModel.getValueAt(rowIndex,2));
            }
        });
//        new_books_table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
//            public void valueChanged(ListSelectionEvent event) {
//                // do some actions here, for example
//                // print first column value from selected row
//                System.out.println(new_books_table.getValueAt(new_books_table.getSelectedRow(), 0).toString());
//            }
//        });
        return  tableScrollPane;

    }

    public  JScrollPane getAllMonthlyTable()
    {


        ArrayList<PatientDetails> patientDetailsArrayList = database.getMonthlyPatient();
       
        int row = patientDetailsArrayList.size();

        String data[][] = new String[row][4];
        int i=0;
        for(PatientDetails patient : patientDetailsArrayList)
        {
           
            // System.out.println(patient.getName() + " " + patient.getGender());
            data[i][0] = Integer.toString(patient.getPid());
            data[i][1] = patient.getDate().toString();
            data[i][2] = patient.getName();
            data[i][3]=  Float.toString(patient.getFees());
            i++;
        }

        String column[] = {"PID" ,"NAME" , "Date" ,"Fees(Rs.)"};
        LibraryTable new_memeber_table = new LibraryTable(data,column);
       // new_memeber_table.setTextColor(new Color(0x363659));

        new_memeber_table.setRowColor(Color.white,Color.white);

        JScrollPane tableScrollPane = new JScrollPane(new_memeber_table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
       tableScrollPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return  tableScrollPane;
    }
    

    //---------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void windowStateChanged(WindowEvent e) {
//        System.out.println(e.getWindow().getWidth());
//        System.out.println(e.getWindow().getHeight());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource()==total_books) {
            total_books.setBorder(HOVER_BORDER);
        }
        if(e.getSource()==total_student) {
            total_student.setBorder(HOVER_BORDER);
        }if(e.getSource()==return_today) {
            return_today.setBorder(HOVER_BORDER);
        }if(e.getSource()==not_return_today) {
            not_return_today.setBorder(HOVER_BORDER);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==total_books) {
            total_books.setBorder(PANEL_BORDER);
        }
        if(e.getSource()==total_student) {
            total_student.setBorder(PANEL_BORDER);
        }if(e.getSource()==return_today) {
            return_today.setBorder(PANEL_BORDER);
        }if(e.getSource()==not_return_today) {
            not_return_today.setBorder(PANEL_BORDER);
        }

    }
    public String getTodaysTotalFees()
    {
        
        float fees=0.0f;
        ArrayList<PatientDetails> pdetails  = database.getTodayPatient();
        for(PatientDetails patient : pdetails)
        {
            fees+=patient.getFees();
        }
        return Float.toString(fees);
    }
     public String getMonthlyTotalFees()
    {
        
        float fees=0.0f;
        ArrayList<PatientDetails> pdetails  = database.getMonthlyPatient();
        for(PatientDetails patient : pdetails)
        {
            fees+=patient.getFees();
        }
        return Float.toString(fees);
    }

//    public static void main(String[] args) {
//        new NewDashboardPanel();
//    }
}