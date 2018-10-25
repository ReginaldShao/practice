package com.shaojie.demo.gui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class TextArea extends JFrame {
    private JButton
            button1 = new JButton("Add"),
            button2 = new JButton("Clear");
    private JTextArea jTextArea = new JTextArea(20,40);
    private Map<String,String> m = new HashMap<>();

    public TextArea() throws HeadlessException {
        m.putAll(Countries.capitals());
        button1.addActionListener((e -> {
            for(Map.Entry<String,String> me:m.entrySet()){
                jTextArea.append(me.getKey()+": "+me.getValue()+"\n");
            }
        }));

        button2.addActionListener((e -> {
            jTextArea.setText("");
        }));

        setLayout(new FlowLayout());
        add(button1);
        add(button2);
        add(new JScrollPane(jTextArea));
    }

    public static void main(String[] args) {
        SwingConsole.run(new TextArea(),300,400);
    }
}
