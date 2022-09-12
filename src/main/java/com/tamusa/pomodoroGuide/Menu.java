package com.tamusa.pomodoroGuide;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

    private static boolean sessionOn = false;
    private static  int ORIGINAL_COUNTDOWN_MINUTES;
    private static  int ORIGINAL_COUNTDOWN_SECONDS;

    private static final int TOTAL_DELAY_TIME = 30;
    private static final int INTERVAL = 1000; // Iteration interval for the Timers.
    private static  int POMODORO_CYCLE ; // No of rounds in a Pomodoro cycle.
    private static int currentRounds = 0;

    private Font timerStyle = new Font("MonoAlphabet", Font.BOLD, 100);
    private static Menu frame;

    private static Timer countDown;
    private static int secondsRemaining;
    private static int minutesRemaining;
    private static int breakTime;

    private static JLabel minuteLabel;
    private JLabel separator;
    private static JLabel secondsLabel;
    private JButton startButton;
    private JButton pauseButton;
    private JButton beginButton;
    private JButton enterButton;
    private JButton endSessionButton;
    private JButton XButton;

    public Menu() {
        // Call super class constructor with a title
        super("Menu");
        JPanel pane = new JPanel(new GridBagLayout());
        pane.setSize(550,500);
        GridBagConstraints c = new GridBagConstraints();

        JPanel pane2 = new JPanel();
        pane.setSize(250,500);
        BoxLayout boxlayout = new BoxLayout(pane2, BoxLayout.Y_AXIS);
        pane2.setBorder(new EmptyBorder(new Insets(50, 0, 50,10)));
        pane2.setLayout(boxlayout);

        JLabel taskLabel = new JLabel("Tasks: ");
        JTextField taskEntry = new JTextField();
        taskEntry.setColumns(25);
        taskEntry.setMaximumSize( taskEntry.getPreferredSize());
        enterButton = new JButton("Enter");

        pane2.add(taskLabel);
        pane2.add(taskEntry);
        pane2.add(enterButton);
        add(pane2, BorderLayout.EAST);

        JLabel settingsLabel = new JLabel("Settings");
        JLabel workLabel= new JLabel("Work time: 25 ");
        JLabel breakLabel = new JLabel("Break time: 5 ");
        JLabel workLabel2= new JLabel("Work time: 50 ");
        JLabel breakLabel2 = new JLabel("Break time: 10 ");
        JLabel workLabel3 = new JLabel("Work time: ");
        JLabel breaklabel3 = new JLabel("Break time: ");
        JLabel roundsLabel = new JLabel("Number of Pomodoro Rounds: ");

        JRadioButton jRadioButton25 = new JRadioButton();
        jRadioButton25.setActionCommand("25");
        JRadioButton jRadioButton50 = new JRadioButton();
        jRadioButton50.setActionCommand("50");
        JRadioButton jRadioButtonCustom = new JRadioButton();
        jRadioButtonCustom.setActionCommand("Custom");

        ButtonGroup group = new ButtonGroup();
        group.add(jRadioButton25);
        group.add(jRadioButton50);
        group.add(jRadioButtonCustom);

        JTextField workCustom = new JTextField();
        JTextField breakCustom = new JTextField();
        JTextField roundsEntry = new JTextField("4");

        jRadioButton25.setText("25/5");
        jRadioButton50.setText("50/10");
        jRadioButtonCustom.setText("Custom");

        XButton = new JButton("X");

        startButton = new JButton("Start");
        startButton.setVisible(false);

        pauseButton = new JButton("Pause");
        pauseButton.setVisible(false);

        beginButton = new JButton("Begin");

        endSessionButton = new JButton("End Session");
        endSessionButton.setVisible(false);




        minuteLabel = new JLabel(String.format("%02d", ORIGINAL_COUNTDOWN_MINUTES));
        minuteLabel.setFont(timerStyle);
        minuteLabel.setForeground(Color.white);

        separator = new JLabel(":");
        separator.setFont(timerStyle);
        separator.setForeground(Color.white);

        secondsLabel = new JLabel(String.format("%02d", ORIGINAL_COUNTDOWN_SECONDS));
        secondsLabel.setFont(timerStyle);
        secondsLabel.setForeground(Color.white);

        c.gridx=6;
        c.gridy=0;
        pane.add(XButton);

        c.gridx=3;
        c.gridy=0;
        pane.add(settingsLabel,c);

        c.gridx=1;
        c.gridy=1;
        pane.add(jRadioButton25,c);

        c.gridx=1;
        c.gridy=2;
        pane.add(jRadioButton50,c);

        c.gridx=1;
        c.gridy=3;
        pane.add(jRadioButtonCustom,c);

        c.gridx=2;
        c.gridy=1;
        pane.add(workLabel,c);

        c.gridx=4;
        c.gridy=1;
        pane.add(breakLabel,c);

        c.gridx=2;
        c.gridy=2;
        pane.add(workLabel2,c);

        c.gridx=4;
        c.gridy=2;
        pane.add(breakLabel2,c);

        c.gridx=2;
        c.gridy=3;
        pane.add(workLabel3,c);

        c.gridx=3;
        c.gridy=3;
        pane.add(workCustom,c);

        c.gridx=4;
        c.gridy=3;
        pane.add(breaklabel3,c);

        c.gridx=5;
        c.gridy=3;
        pane.add(breakCustom,c);

        c.gridx=2;
        c.gridy=4;
        pane.add(roundsLabel,c);

        c.gridx=3;
        c.gridy=4;
        pane.add(roundsEntry,c);

        c.gridx=1;
        c.gridy=5;
        pane.add(startButton,c);

        c.gridx=3;
        c.gridy=5;
        pane.add(beginButton,c);

        c.gridx=6;
        c.gridy=5;
        pane.add(pauseButton,c);

        c.gridx=2;
        c.gridy=6;
        pane.add(minuteLabel,c);

        c.gridx=3;
        c.gridy=6;
        pane.add(separator,c);

        c.gridx=4;
        c.gridy=6;
        pane.add(secondsLabel,c);

        c.gridx=3;
        c.gridy=7;
        pane.add(endSessionButton,c);

        add(pane, BorderLayout.CENTER);

        beginButton.addActionListener((ActionEvent event) -> {
            if(group.getSelection() != null){
                String choice = group.getSelection().getActionCommand();
                if(choice == "25"){
                    ORIGINAL_COUNTDOWN_MINUTES = 25;
                    ORIGINAL_COUNTDOWN_SECONDS = 0;
                    breakTime = 5;
                } else if(choice == "50"){
                    ORIGINAL_COUNTDOWN_MINUTES = 50;
                    ORIGINAL_COUNTDOWN_SECONDS = 0;
                    breakTime = 10;
                } else{
                    // int userChoice = Integer.parseInt(workCustom.getText());
                    ORIGINAL_COUNTDOWN_MINUTES = Integer.parseInt(workCustom.getText());
                    ORIGINAL_COUNTDOWN_SECONDS = 0;
                    breakTime = Integer.parseInt(breakCustom.getText());
                }
                POMODORO_CYCLE = Integer.parseInt(roundsEntry.getText());
                workCustom.setEditable(false);
                breakCustom.setEditable(false);
                roundsEntry.setEditable(false);
            }
            startCountDown(ORIGINAL_COUNTDOWN_MINUTES, ORIGINAL_COUNTDOWN_SECONDS );
            countDown.start();
            taskEntry.setVisible(false);
            taskLabel.setVisible(false);
            enterButton.setVisible(false);
            beginButton.setVisible(false);
            startButton.setVisible(true);
            pauseButton.setVisible(true);
            endSessionButton.setVisible(true);
        });

        startButton.addActionListener((ActionEvent event) -> {
            countDown.start();
        });

        pauseButton.addActionListener((ActionEvent event) -> {
            countDown.stop();
        });

        enterButton.addActionListener((ActionEvent event) -> {
            pane2.add(new JCheckBox(taskEntry.getText()));
            pane2.validate();
            pane2.repaint();
        });

        XButton.addActionListener((ActionEvent event) -> {
            frame.setVisible(false);
            frame.dispose();
        });

        endSessionButton.addActionListener((ActionEvent event) -> {
            sessionOn=false;
            countDown.stop();
            frame.dispose();
            JOptionPane.showMessageDialog(null,"Your Pomodoro Session is over");
            currentSession();
        });

    }

    public static void startCountDown(int ORIGINAL_COUNTDOWN_MINUTES, int ORIGINAL_COUNTDOWN_SECONDS ){
        minutesRemaining = ORIGINAL_COUNTDOWN_MINUTES;
        secondsRemaining = ORIGINAL_COUNTDOWN_SECONDS;

        minuteLabel.setText(String.format("%02d", ORIGINAL_COUNTDOWN_MINUTES));
        secondsLabel.setText(String.format("%02d", ORIGINAL_COUNTDOWN_SECONDS));

        countDown = new Timer(INTERVAL, (ActionEvent event) -> {
            if(secondsRemaining == 0)
            {
                if(minutesRemaining == 0)
                {
                    currentRounds++;
                    if(currentRounds==POMODORO_CYCLE){
                        endSession();
                    } else {
                        frame.setVisible(false);
                        frame.dispose();
                        countDown.stop();
                        breakMenu.startbreak(breakTime);
                    }

                }
                else
                {
                    minutesRemaining -= 1;
                    secondsRemaining = 59;
                    minuteLabel.setText(String.format("%02d", minutesRemaining));
                    secondsLabel.setText(String.format("%02d", secondsRemaining));
                }
            }
            else
            {
                secondsRemaining -= 1;
                secondsLabel.setText(String.format("%02d", secondsRemaining));
            }
        });
    }
    public static void continueSession(){
        startCountDown(ORIGINAL_COUNTDOWN_MINUTES, ORIGINAL_COUNTDOWN_SECONDS);
        countDown.start();
    }
    public static void endSession(){
        sessionOn=false;
        countDown.stop();
        frame.dispose();
        JOptionPane.showMessageDialog(null,"Your Pomodoro Session is over");
        currentSession();
    }
    public static void currentSession(){
        if(sessionOn==false){
            currentRounds = 0;
            sessionOn=true;
            frame = new Menu();
            frame.getContentPane().setBackground(Color.BLACK);
            frame.pack();
            frame.setSize(new Dimension(810,500));
            frame.setLocationRelativeTo(null);
            frame.setVisible(false);
            frame.setResizable(true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } else {
            frame.getContentPane().setBackground(Color.BLACK);
            frame.pack();
            frame.setSize(new Dimension(810,500));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
            frame.setResizable(true);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }

    }
    public static void main() {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                currentSession();
            }
        });
    }
}

