package com.shaojie.demo.gui;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class SubmitSwingProgram1 extends JFrame {
    private static SubmitSwingProgram1 ssp;
    public static void main(String[] args) throws InterruptedException {
        ssp = new SubmitSwingProgram1();
        JLabel label = new JLabel("A Label");
        ssp.add(label);
        SwingConsole.run(ssp,300,400);
        TimeUnit.SECONDS.sleep(2);
        SwingUtilities.invokeLater(()->label.setText("Hey! This is different!"));
    }
}
