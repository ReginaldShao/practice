package com.shaojie.demo.gui;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class SubmitSwingProgram extends JFrame {
    public SubmitSwingProgram() throws HeadlessException {
        super("Hello,Swing");
        label = new JLabel("A Label");
        add(label);
        for(int i=0;i<4;i++){
            add(new JLabel("label:"+i));
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,300);
        setVisible(true);
    }
    JLabel label;
    private static SubmitSwingProgram ssp;
    public static void main(String[] args) throws InterruptedException {
        SwingUtilities.invokeLater(()->ssp = new SubmitSwingProgram());
        TimeUnit.SECONDS.sleep(2);
        SwingUtilities.invokeLater(()->ssp.label.setText("Hey! This is different!"));
    }
}
