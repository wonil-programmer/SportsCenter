package gui;

import java.awt.event.*;
import javax.swing.table.*;
import java.awt.*;
import javax.swing.*;

import api.UserDAO;
import gui.mainPage;
//import com.intellij.ide.ui.laf.*;
/*
 * Created by JFormDesigner on Wed Nov 16 09:47:05 KST 2022
 */



/**
 * @author Minjae
 */
public class loginPage extends JFrame {
    public loginPage() {
        initComponents();
    }

    private void Reg(ActionEvent e) {
        // TODO add your code here
        this2.setVisible(true);
        this2.setSize(300,280);

    }

    private void Login(ActionEvent e) {
        // TODO add your code here
        new mainPage();
        //mainPage.setVisible(true);
        //f.setSize(585,330);
        this.setVisible(false);

    }

    // 회원가입
    private void button1(ActionEvent e) {
        // TODO add your code here
        this2.setVisible(false);
        JOptionPane.showMessageDialog(null, "회원가입에 성공하였습니다");
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        label1 = new JLabel();
        label2 = new JLabel();
        PWtext = new JPasswordField();
        IDtext = new JTextField();
        LoginButton = new JButton();
        RememberCheck = new JCheckBox();
        RegButton = new JButton();
        this2 = new JFrame();
        label3 = new JLabel();
        label4 = new JLabel();
        textField1 = new JTextField();
        label5 = new JLabel();
        radioButton1 = new JRadioButton();
        radioButton2 = new JRadioButton();
        label6 = new JLabel();
        label7 = new JLabel();
        comboBox1 = new JComboBox<>();
        label8 = new JLabel();
        checkBox1 = new JCheckBox();
        button1 = new JButton();
        passwordField1 = new JPasswordField();
        passwordField2 = new JPasswordField();

        //======== this ========
        setTitle("LOGIN");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("ID");
        contentPane.add(label1);
        label1.setBounds(15, 15, 20, 17);

        //---- label2 ----
        label2.setText("PW");
        contentPane.add(label2);
        label2.setBounds(10, 45, 18, 20);
        contentPane.add(PWtext);
        PWtext.setBounds(35, 45, 195, 23);
        contentPane.add(IDtext);
        IDtext.setBounds(35, 15, 195, 23);

        //---- LoginButton ----
        LoginButton.setText("LOGIN");
        LoginButton.addActionListener(e -> Login(e));
        contentPane.add(LoginButton);
        LoginButton.setBounds(240, 15, 150, 55);

        //---- RememberCheck ----
        RememberCheck.setText("Remember me");
        RememberCheck.setSelected(true);
        contentPane.add(RememberCheck);
        RememberCheck.setBounds(10, 75, 110, 21);

        //---- RegButton ----
        RegButton.setText("REGISTER");
        RegButton.addActionListener(e -> Reg(e));
        contentPane.add(RegButton);
        RegButton.setBounds(240, 75, 150, 23);

        contentPane.setPreferredSize(new Dimension(400, 135));
        pack();
        setLocationRelativeTo(getOwner());

        //======== this2 ========
        {
            this2.setTitle("REGISTER");
            this2.setResizable(false);
            var this2ContentPane = this2.getContentPane();
            this2ContentPane.setLayout(null);

            //---- label3 ----
            label3.setText("userID");
            this2ContentPane.add(label3);
            label3.setBounds(new Rectangle(new Point(30, 15), label3.getPreferredSize()));

            //---- label4 ----
            label4.setText("userPW");
            this2ContentPane.add(label4);
            label4.setBounds(new Rectangle(new Point(25, 50), label4.getPreferredSize()));
            this2ContentPane.add(textField1);
            textField1.setBounds(85, 10, 175, 25);

            //---- label5 ----
            label5.setText("gender");
            this2ContentPane.add(label5);
            label5.setBounds(25, 115, 50, label5.getPreferredSize().height);

            //---- radioButton1 ----
            radioButton1.setText("\ub0a8");
            radioButton1.setSelected(true);
            this2ContentPane.add(radioButton1);
            radioButton1.setBounds(new Rectangle(new Point(85, 115), radioButton1.getPreferredSize()));

            //---- radioButton2 ----
            radioButton2.setText("\uc5ec");
            this2ContentPane.add(radioButton2);
            radioButton2.setBounds(new Rectangle(new Point(125, 115), radioButton2.getPreferredSize()));

            //---- label6 ----
            label6.setText("PW Again");
            this2ContentPane.add(label6);
            label6.setBounds(15, 85, 60, 17);

            //---- label7 ----
            label7.setText("state");
            this2ContentPane.add(label7);
            label7.setBounds(new Rectangle(new Point(30, 145), label7.getPreferredSize()));

            //---- comboBox1 ----
            comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                "\uc7ac\ud559\uc0dd",
                "\uc77c\ubc18\uc778",
                "\uad50\uc9c1\uc6d0"
            }));
            this2ContentPane.add(comboBox1);
            comboBox1.setBounds(85, 145, 75, comboBox1.getPreferredSize().height);

            //---- label8 ----
            label8.setText("locker usage");
            this2ContentPane.add(label8);
            label8.setBounds(10, 175, 70, label8.getPreferredSize().height);

            //---- checkBox1 ----
            checkBox1.setText("\uc0ac\uc6a9\ud568");
            checkBox1.setSelected(true);
            this2ContentPane.add(checkBox1);
            checkBox1.setBounds(new Rectangle(new Point(85, 175), checkBox1.getPreferredSize()));

            //---- button1 ----
            button1.setText("Create Account");
            button1.addActionListener(e -> button1(e));
            this2ContentPane.add(button1);
            button1.setBounds(15, 205, 260, 30);
            this2ContentPane.add(passwordField1);
            passwordField1.setBounds(85, 45, 175, passwordField1.getPreferredSize().height);
            this2ContentPane.add(passwordField2);
            passwordField2.setBounds(85, 80, 175, passwordField2.getPreferredSize().height);

            this2ContentPane.setPreferredSize(new Dimension(290, 275));
            this2.pack();
            this2.setLocationRelativeTo(this2.getOwner());
        }

        //---- buttonGroup1 ----
        var buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(radioButton1);
        buttonGroup1.add(radioButton2);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public static void main(String[] args) {

        com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme.setup();
        loginPage frame = new loginPage();
        frame.pack();
        frame.setSize(415, 145);
        frame.setVisible(true);

        UserDAO userDAO = new UserDAO();
        // addeventlistner
        /*
        int result = userDAO.login(username, password);
        if (result == 1) {
            // 로그인 성공
        }
        else if (result == 0) {
            // 비밀번호 오류
        }
        else if (result == -1) {
            // 존재하지 않는 아이디
        }
        else if (result == -2) {
            // 데이터베이스 오류
        }
    */
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JLabel label1;
    private JLabel label2;
    private JPasswordField PWtext;
    private JTextField IDtext;
    private JButton LoginButton;
    private JCheckBox RememberCheck;
    private JButton RegButton;
    private JFrame this2;
    private JLabel label3;
    private JLabel label4;
    private JTextField textField1;
    private JLabel label5;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JLabel label6;
    private JLabel label7;
    private JComboBox<String> comboBox1;
    private JLabel label8;
    private JCheckBox checkBox1;
    private JButton button1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
