package gui;

import javax.swing.*;

public class MainRegist {
    private JPanel jPanel;
    private JButton NewReg;
    private JButton ReReg;
    private JButton Back;
    private JButton Home;


    public static void main(String args[]) {
        JFrame frame = new JFrame("등록");
        frame.setContentPane(new MainRegist().jPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}