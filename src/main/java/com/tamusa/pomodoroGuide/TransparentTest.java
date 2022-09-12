package com.tamusa.pomodoroGuide;

import javax.swing.*;
import java.awt.*;

public class TransparentTest extends JFrame {
    static boolean status = true;
    static JPanel jp;

    public static void changeStatus(){
        jp.setVisible(false);
    }


    /*
    public static void newStatus(){
        new TransparentTest().transparent();
    }
    */

    public void transparent() {
        // super("Transparent Window");
        setBackground(new Color(0, 0, 0, 0));

        //setting it causes the frame to be transparent .Hence both panel and frame are transparent.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(2080, 1080);
        getContentPane().setLayout(new FlowLayout());

        jp = new JPanel() {
            public void paintComponent(Graphics g) {
                //super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                Paint gp = new GradientPaint(0, 0, new Color(119, 119, 119, 255), 0, 200, new Color(117, 114, 114, 255));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        jp.setFocusable(false);
        setOpacity(0.7f);
        setContentPane(jp);
        setVisible(true);
        }

    }