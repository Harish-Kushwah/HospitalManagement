package swing;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.*;
import  java.awt.event.*;


public class SetProgessBar extends JFrame {

    final  Timer  progess_timer;

    SetProgessBar()
    {
       
     this.setSize(500, 500);
     this.setVisible(true);
     this.setLocationRelativeTo(null);
     this.setLayout(new BorderLayout());
     
      JProgressBar progressBar = new JProgressBar(0,100);
     progressBar.setBounds(100,300,100,10);
     
     Container con = this.getContentPane();
     con.add(progressBar , BorderLayout.PAGE_END);
     
     progess_timer = new Timer(100, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int x = progressBar.getValue();
        if(x==100)
        {
        System.out.println("break");
        progess_timer.stop();
        }
        else{
        progressBar.setValue(x+4);
        }

        }
        });
        progess_timer.start();
    }

}