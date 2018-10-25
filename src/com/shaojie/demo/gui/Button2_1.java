package com.shaojie.demo.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Button2_1 extends JFrame {
    private JButton
        button1 = new JButton("Button 1"),
        button2 = new JButton("Button 2");
    private JTextField jTextField = new JTextField(10);

    public Button2_1() throws HeadlessException {
        ActionListener actionListener =  (e)-> {
            String name = ((JButton)e.getSource()).getText();
            jTextField.setText(name);
        };
        button1.addActionListener(actionListener);
        button2.addActionListener(actionListener);
        setLayout(new FlowLayout());
        add(button1);
        add(button2);
        add(jTextField);
    }

    public static void main(String[] args) {
        Button2 button2 = new Button2();
        SwingConsole.run(button2,300,300);
    }
}
