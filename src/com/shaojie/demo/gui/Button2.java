package com.shaojie.demo.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button2 extends JFrame {
    private JButton
        button1 = new JButton("Button 1"),
        button2 = new JButton("Button 2");
    private JTextField jTextField = new JTextField(10);

    public Button2() throws HeadlessException {
        button1.addActionListener(buttonListener);
        button2.addActionListener(buttonListener);
        setLayout(new FlowLayout());
        add(button1);
        add(button2);
        add(jTextField);
    }

    class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = ((JButton)e.getSource()).getText();
            jTextField.setText(name);
        }
    }
    private ButtonListener buttonListener = new ButtonListener();

    public static void main(String[] args) {
        Button2 button2 = new Button2();
        SwingConsole.run(button2,300,300);
    }
}
