package com.shaojie.demo.gui;

import javax.swing.*;
import java.awt.*;

public class Button1 extends JFrame {
    private JButton
            button1 = new JButton("Button 1"),
            button2 = new JButton("Button 2");

    public Button1() throws HeadlessException {
        setLayout(new FlowLayout());
        add(button1);
        add(button2);
    }

    public static void main(String[] args) {
        Button1 f = new Button1();
        SwingConsole.run(f,300,300);
    }
}
