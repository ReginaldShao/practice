package com.shaojie.demo.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexGui extends JFrame {
    private JButton
            button1 = new JButton(new ImageIcon("/home/shaojie/downloads/Button-Blank-Red-icon.png")),
            button2 = new JButton("清空");
    private JTextArea jTextArea1 = new JTextArea(10,40),
            jTextArea2 = new JTextArea(10,40);

    private JTextField jTextField = new JTextField(10);

    public RegexGui() throws HeadlessException {
        button1.addActionListener((e -> {
            Pattern p = Pattern.compile(jTextField.getText());
            Matcher m = p.matcher(jTextArea1.getText());
            while (m.find()) {
                jTextArea2.append("Match \""+m.group()+"\" at positions "+
                        m.start()+"-"+(m.end()-1)+"\n");
            }
        }));

        button1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //JButton button = ((JButton)e.getSource());
                //ImageIcon imageIcon = new ImageIcon("/home/shaojie/downloads/icon.png");
                //button.setIcon(imageIcon);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                JButton button = ((JButton)e.getSource());
                ImageIcon imageIcon = new ImageIcon("/home/shaojie/downloads/Button-Blank-Gray-icon.png");
                button.setIcon(imageIcon);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                JButton button = ((JButton)e.getSource());
                ImageIcon imageIcon = new ImageIcon("/home/shaojie/downloads/Button-Blank-Red-icon.png");
                button.setIcon(imageIcon);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        button2.addActionListener((e -> {
            jTextArea2.setText("");
        }));

        setLayout(new FlowLayout());
//        add(new JMenu());
//        add(new JMenuBar());
        add(button1);
        add(button2);
        add(jTextField);
        add(new JScrollPane(jTextArea1));
        add(new JScrollPane(jTextArea2));
    }

    public static void main(String[] args) {
        SwingConsole.run(new RegexGui(),300,400);
    }
}
