package gui;

import java.awt.event.*;
import javax.swing.table.*;
import java.awt.*;
import javax.swing.*;

import api.User;
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
        RegForm.setVisible(true);
        RegForm.setSize(300,280);

    }

    private void Login(ActionEvent e) { // 로그인 버튼 클릭 시
        // TODO add your code here
        //mainPage.setVisible(true);
        //f.setSize(585,330);

        UserDAO userDAO = new UserDAO();
        String passPW = new String(PWtext.getPassword());
        int result = userDAO.login(IDtext.getText(), passPW);
        System.out.println("result = " + result);
        if (result == 1)
        {
            // 로그인 성공
            JOptionPane.showMessageDialog(null, "로그인에 성공하였습니다");
            new mainPage();
            this.setVisible(false);

        } else if (result == 0) {
            // 아이디 또는 비밀번호 오류
            JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호를 잘못 입력했습니다. 입력하신 내용을 다시 확인해주세요.");
        } else if (result == -1) {
            // 아이디 없음
            JOptionPane.showMessageDialog(null, "존재하지 않는 아이디입니다.");
        } else if (result == -2) {
            // 데이터베이스 오류
            JOptionPane.showMessageDialog(null, "DB 오류");
        }
        
    }

    private void CreateAccount(ActionEvent e) { // 회원가입 버튼 누를 시
        // TODO add your code here
        RegForm.setVisible(false);


        User user = new User(); // user 인스턴스 생성

        // 회원가입 비밀번호란에서 받아온 문자 string으로 변환
        String passPW = new String(Reg_PW.getPassword());

        user.setUserID(Reg_ID.getText());
        user.setPassword(passPW);
        user.setLocker(false);

        UserDAO userDAO = new UserDAO();

        int result = userDAO.signUp(user);
        System.out.println("result = " + result);

        if (result == 1) {
            //  성공
        } else if (result == -1) {
            // 데이터베이스 오류
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Minjae
        label1 = new JLabel();
        label2 = new JLabel();
        PWtext = new JPasswordField();
        IDtext = new JTextField();
        LoginButton = new JButton();
        RememberCheck = new JCheckBox();
        RegButton = new JButton();
        RegForm = new JFrame();
        label3 = new JLabel();
        label4 = new JLabel();
        Reg_ID = new JTextField();
        label5 = new JLabel();
        Gen_B = new JRadioButton();
        Gen_G = new JRadioButton();
        label6 = new JLabel();
        label7 = new JLabel();
        State = new JComboBox<>();
        label8 = new JLabel();
        LockerUse = new JCheckBox();
        Reg_PW = new JPasswordField();
        Reg_PWagain = new JPasswordField();
        CreateAccount = new JButton();

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

        //======== RegForm ========
        {
            RegForm.setTitle("REGISTER");
            RegForm.setResizable(false);
            var RegFormContentPane = RegForm.getContentPane();
            RegFormContentPane.setLayout(null);

            //---- label3 ----
            label3.setText("userID");
            RegFormContentPane.add(label3);
            label3.setBounds(new Rectangle(new Point(30, 15), label3.getPreferredSize()));

            //---- label4 ----
            label4.setText("userPW");
            RegFormContentPane.add(label4);
            label4.setBounds(new Rectangle(new Point(25, 50), label4.getPreferredSize()));
            RegFormContentPane.add(Reg_ID);
            Reg_ID.setBounds(85, 10, 175, 25);

            //---- label5 ----
            label5.setText("gender");
            RegFormContentPane.add(label5);
            label5.setBounds(25, 115, 50, label5.getPreferredSize().height);

            //---- Gen_B ----
            Gen_B.setText("\ub0a8");
            Gen_B.setSelected(true);
            RegFormContentPane.add(Gen_B);
            Gen_B.setBounds(new Rectangle(new Point(85, 115), Gen_B.getPreferredSize()));

            //---- Gen_G ----
            Gen_G.setText("\uc5ec");
            RegFormContentPane.add(Gen_G);
            Gen_G.setBounds(new Rectangle(new Point(125, 115), Gen_G.getPreferredSize()));

            //---- label6 ----
            label6.setText("PW Again");
            RegFormContentPane.add(label6);
            label6.setBounds(15, 85, 60, 17);

            //---- label7 ----
            label7.setText("state");
            RegFormContentPane.add(label7);
            label7.setBounds(new Rectangle(new Point(30, 145), label7.getPreferredSize()));

            //---- State ----
            State.setModel(new DefaultComboBoxModel<>(new String[] {
                "\uc7ac\ud559\uc0dd",
                "\uc77c\ubc18\uc778",
                "\uad50\uc9c1\uc6d0"
            }));
            RegFormContentPane.add(State);
            State.setBounds(85, 145, 75, State.getPreferredSize().height);

            //---- label8 ----
            label8.setText("locker usage");
            RegFormContentPane.add(label8);
            label8.setBounds(10, 175, 70, label8.getPreferredSize().height);

            //---- LockerUse ----
            LockerUse.setText("\uc0ac\uc6a9\ud568");
            LockerUse.setSelected(true);
            RegFormContentPane.add(LockerUse);
            LockerUse.setBounds(new Rectangle(new Point(85, 175), LockerUse.getPreferredSize()));
            RegFormContentPane.add(Reg_PW);
            Reg_PW.setBounds(85, 45, 175, Reg_PW.getPreferredSize().height);
            RegFormContentPane.add(Reg_PWagain);
            Reg_PWagain.setBounds(85, 80, 175, Reg_PWagain.getPreferredSize().height);

            //---- CreateAccount ----
            CreateAccount.setText("Create Account");
            CreateAccount.addActionListener(e -> CreateAccount(e));
            RegFormContentPane.add(CreateAccount);
            CreateAccount.setBounds(15, 205, 260, 30);

            RegFormContentPane.setPreferredSize(new Dimension(290, 275));
            RegForm.pack();
            RegForm.setLocationRelativeTo(RegForm.getOwner());
        }

        //---- buttonGroup1 ----
        var buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(Gen_B);
        buttonGroup1.add(Gen_G);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public static void main(String[] args) {

        com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme.setup();
        loginPage frame = new loginPage();
        frame.pack();
        frame.setSize(415, 145);
        frame.setVisible(true);
        //UserDAO userDAO = new UserDAO();
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
    // Generated using JFormDesigner Evaluation license - Minjae
    private JLabel label1;
    private JLabel label2;
    private JPasswordField PWtext;
    private JTextField IDtext;
    private JButton LoginButton;
    private JCheckBox RememberCheck;
    private JButton RegButton;
    private JFrame RegForm;
    private JLabel label3;
    private JLabel label4;
    private JTextField Reg_ID;
    private JLabel label5;
    private JRadioButton Gen_B;
    private JRadioButton Gen_G;
    private JLabel label6;
    private JLabel label7;
    private JComboBox<String> State;
    private JLabel label8;
    private JCheckBox LockerUse;
    private JPasswordField Reg_PW;
    private JPasswordField Reg_PWagain;
    private JButton CreateAccount;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
