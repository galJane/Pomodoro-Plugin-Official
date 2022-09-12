package com.tamusa.pomodoroGuide;

import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static javax.sound.sampled.AudioSystem.getAudioInputStream;

public class breakMenu extends JFrame {
    private static int ORIGINAL_COUNTDOWN_MINUTES = 1;
    private static int ORIGINAL_COUNTDOWN_SECONDS = 0;
    private static final int INTERVAL = 1000; // Iteration interval for the Timers.
    private Font timerStyle = new Font("MonoAlphabet", Font.BOLD, 100);
    private static breakMenu breakframe;


    private Timer breakCountDown;
    private int secondsRemaining;
    private int minutesRemaining;
    private JLabel minuteLabel;
    private JLabel separator;
    private JLabel secondsLabel;
    private JButton AddButton;
    private JButton OneMinButton;
    private JButton exitBT;
    private JButton ReturnToMain;

    public breakMenu() {
        // Call super class constructor with a title
        super("Break");

        JPanel pane1 = new JPanel(new GridBagLayout());
        pane1.setSize(500, 300);
        pane1.setFocusable(true);
        GridBagConstraints c = new GridBagConstraints();

        AddButton = new JButton("Add 5 More Minutes");
        OneMinButton = new JButton("Add 1 More Minute");

        exitBT = new JButton("Exit Session");

        minuteLabel = new JLabel(String.format("%02d", ORIGINAL_COUNTDOWN_MINUTES));
        minuteLabel.setFont(timerStyle);
        minuteLabel.setForeground(Color.white);

        separator = new JLabel(":");
        separator.setFont(timerStyle);
        separator.setForeground(Color.white);

        secondsLabel = new JLabel(String.format("%02d", ORIGINAL_COUNTDOWN_SECONDS));
        secondsLabel.setFont(timerStyle);
        secondsLabel.setForeground(Color.white);

        c.gridx = 4;
        c.gridy = 2;
        pane1.add(exitBT, c);

        c.gridx = 2;
        c.gridy = 2;
        pane1.add(AddButton, c);

        c.gridx = 3;
        c.gridy = 2;
        pane1.add(OneMinButton, c);

        c.gridx = 2;
        c.gridy = 1;
        pane1.add(minuteLabel, c);

        c.gridx = 3;
        c.gridy = 1;
        pane1.add(separator, c);

        c.gridx = 4;
        c.gridy = 1;
        pane1.add(secondsLabel, c);

        add(pane1);

        exitBT.addActionListener((ActionEvent event) -> {
            breakCountDown.stop();
            breakframe.setVisible(false);
            TransparentTest.changeStatus();
            breakframe.dispose();
            Menu.endSession();
        });

        AddButton.addActionListener((ActionEvent event) -> {
            breakCountDown.stop();
            ORIGINAL_COUNTDOWN_MINUTES=ORIGINAL_COUNTDOWN_MINUTES+5;
            ORIGINAL_COUNTDOWN_SECONDS = secondsRemaining;
            startCountDown();
            breakCountDown.start();
        });

        OneMinButton.addActionListener((ActionEvent event) -> {
            breakCountDown.stop();
            ORIGINAL_COUNTDOWN_MINUTES=ORIGINAL_COUNTDOWN_MINUTES+1;
            ORIGINAL_COUNTDOWN_SECONDS = secondsRemaining;
            startCountDown();
            breakCountDown.start();
        });

        startCountDown();
        breakCountDown.start();
    }


    private void startCountDown() {

        minutesRemaining = ORIGINAL_COUNTDOWN_MINUTES;
        secondsRemaining = ORIGINAL_COUNTDOWN_SECONDS;

        minuteLabel.setText(String.format("%02d", ORIGINAL_COUNTDOWN_MINUTES));
        secondsLabel.setText(String.format("%02d", ORIGINAL_COUNTDOWN_SECONDS));

        breakCountDown = new Timer(INTERVAL, (ActionEvent event) -> {
            if (secondsRemaining == 0) {
                if  (minutesRemaining == 0) {
                    breakCountDown.stop();
                    UIUtil.playSoundFromResource("/ring.wav");
                    breakframe.setVisible(false);
                    TransparentTest.changeStatus();
                    breakframe.dispose();
                    Menu.continueSession();

                } else {
                    minutesRemaining -= 1;
                    secondsRemaining = 59;
                    minuteLabel.setText(String.format("%02d", minutesRemaining));
                    secondsLabel.setText(String.format("%02d", secondsRemaining));
                }
            } else {
                secondsRemaining -= 1;
                secondsLabel.setText(String.format("%02d", secondsRemaining));
            }

        });
    }

    public static void startbreak(int breakTime){
        ORIGINAL_COUNTDOWN_MINUTES = breakTime;
        main();
    }

    public static Component main() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        if (!gd.isWindowTranslucencySupported(GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSLUCENT)) {
            System.exit(0);
        }
        JFrame.setDefaultLookAndFeelDecorated(true);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransparentTest().transparent();
                breakframe = new breakMenu();
                breakframe.getContentPane().setBackground(Color.BLACK);
                breakframe.pack();
                breakframe.setSize(new Dimension(523, 300));
                breakframe.setLocationRelativeTo(null);
                breakframe.setVisible(true);
                breakframe.setResizable(true);
                breakframe.setAlwaysOnTop(true);
                breakframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            }
        });
        //return null;
        return null;
    }

    /*
    public static void main() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                breakframe = new breakMenu();
                breakframe.getContentPane().setBackground(Color.BLACK);
                breakframe.pack();
                breakframe.setSize(new Dimension(2080, 1080));
                breakframe.setLocationRelativeTo(null);
                breakframe.setVisible(true);
                breakframe.setResizable(true);
                breakframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
     */


}
