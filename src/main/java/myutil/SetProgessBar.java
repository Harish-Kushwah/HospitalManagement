package myutil;

import javax.swing.*;
import  java.awt.event.*;


public class SetProgessBar extends JFrame {

    final  Timer  progess_timer;

    SetProgessBar()
    {
        JProgressBar progressBar = new JProgressBar(0,100);
     progressBar.setBounds(100,300,100,10);
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