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
        RegForm.setSize(300,310);

    }

    private void Login(ActionEvent e) { // 로그인 버튼 클릭 시
        // TODO add your code here
        //mainPage.setVisible(true);
        //f.setSize(585,330);

        UserDAO userDAO = new UserDAO();
        String passPW = new String(PWtext.getPassword());
        int result = userDAO.login(IDtext.getText(), passPW);
        System.out.println("result = " + result);
        //result = 1;
        if (result == 1)
        {
            // 로그인 성공
            JOptionPane.showMessageDialog(null, "로그인에 성공하였습니다");
            // 현재사용자 수
            String currentuser = Integer.toString(userDAO.countCurUser());
            mainPage frame = new mainPage();
            frame.CurUser.setText(currentuser);
            // 시간별 통계
            String dateList[] = new String[6];
            int[] timeArr = new int[15];
            dateList = userDAO.calcPastWeekDates(1);
            timeArr = userDAO.alignByTime(dateList);
            for(int i=0;i<15;i++)
            {
                frame.table1.setValueAt("            "+ timeArr[i],i,1);
            }
            //요일별 그래프
            float[] dayArr = new float[6]; // 월~토
            dayArr = userDAO.alignByDay(dateList);
            float MonValue,TueValue,WenValue,ThuValue,FriValue,SatValue;
            float max_num = -1;
            int max_index = -1;
            // 가장 많은 요일 찾기
            for(int i =0;i<6;i++)
            {
                if(max_num < dayArr[i])
                {
                    max_index = i;
                    max_num = dayArr[i];
                }
            }
            // 그래프 상대값 계산
            if(max_index == 0)
            {
                frame.MonGraph.setValue(100);
                frame.TueGraph.setValue((int)(dayArr[1]/dayArr[0]*100));
                frame.WenGraph.setValue((int)(dayArr[2]/dayArr[0]*100));
                frame.ThuGraph.setValue((int)(dayArr[3]/dayArr[0]*100));
                frame.FriGraph.setValue((int)(dayArr[4]/dayArr[0]*100));
                frame.SatGraph.setValue((int)(dayArr[5]/dayArr[0]*100));
            }
            else if(max_index == 1)
            {
                    frame.TueGraph.setValue(100);
                    frame.MonGraph.setValue((int)(dayArr[0]/dayArr[1]*100));
                    frame.WenGraph.setValue((int)(dayArr[2]/dayArr[1]*100));
                    frame.ThuGraph.setValue((int)(dayArr[3]/dayArr[1]*100));
                    frame.FriGraph.setValue((int)(dayArr[4]/dayArr[1]*100));
                    frame.SatGraph.setValue((int)(dayArr[5]/dayArr[1]*100));
            }
            else if(max_index == 2)
            {
                frame.WenGraph.setValue(100);
                frame.TueGraph.setValue((int)(dayArr[1]/dayArr[2]*100));
                frame.MonGraph.setValue((int)(dayArr[0]/dayArr[2]*100));
                frame.ThuGraph.setValue((int)(dayArr[3]/dayArr[2]*100));
                frame.FriGraph.setValue((int)(dayArr[4]/dayArr[2]*100));
                frame.SatGraph.setValue((int)(dayArr[5]/dayArr[2]*100));
            }
            else if(max_index == 3)
            {
                frame.ThuGraph.setValue(100);
                frame.MonGraph.setValue((int)(dayArr[0]/dayArr[3]*100));
                frame.WenGraph.setValue((int)(dayArr[2]/dayArr[3]*100));
                frame.TueGraph.setValue((int)(dayArr[1]/dayArr[3]*100));
                frame.FriGraph.setValue((int)(dayArr[4]/dayArr[3]*100));
                frame.SatGraph.setValue((int)(dayArr[5]/dayArr[3]*100));
            }
            else if(max_index == 4)
            {
                frame.FriGraph.setValue(100);
                frame.TueGraph.setValue((int)(dayArr[1]/dayArr[4]*100));
                frame.WenGraph.setValue((int)(dayArr[2]/dayArr[4]*100));
                frame.ThuGraph.setValue((int)(dayArr[3]/dayArr[4]*100));
                frame.MonGraph.setValue((int)(dayArr[0]/dayArr[4]*100));
                frame.SatGraph.setValue((int)(dayArr[5]/dayArr[4]*100));
            }
            else if(max_index == 5)
            {
                frame.SatGraph.setValue(100);
                frame.MonGraph.setValue((int)(dayArr[0]/dayArr[5]*100));
                frame.WenGraph.setValue((int)(dayArr[2]/dayArr[5]*100));
                frame.ThuGraph.setValue((int)(dayArr[3]/dayArr[5]*100));
                frame.FriGraph.setValue((int)(dayArr[4]/dayArr[5]*100));
                frame.TueGraph.setValue((int)(dayArr[1]/dayArr[5]*100));
            }
            if(userDAO.checkLockerUse(1)==1)
            {
                frame.Locker1.setBackground(new Color(0xcccccc));
            }
            if(userDAO.checkLockerUse(2)==1)
            {
                frame.Locker2.setBackground(new Color(0xcccccc));
            }
            if(userDAO.checkLockerUse(3)==1)
            {
                frame.Locker3.setBackground(new Color(0xcccccc));
            }
            if(userDAO.checkLockerUse(4)==1)
            {
                frame.Locker4.setBackground(new Color(0xcccccc));
            }
            if(userDAO.checkLockerUse(5)==1)
            {
                frame.Locker5.setBackground(new Color(0xcccccc));
            }
            if(userDAO.checkLockerUse(6)==1)
            {
                frame.Locker6.setBackground(new Color(0xcccccc));
            }
            if(userDAO.checkLockerUse(7)==1)
            {
                frame.Locker7.setBackground(new Color(0xcccccc));
            }if(userDAO.checkLockerUse(8)==1)
            {
                frame.Locker8.setBackground(new Color(0xcccccc));
            }
            if(userDAO.checkLockerUse(9)==1)
            {
                frame.Locker9.setBackground(new Color(0xcccccc));
            }
            if(userDAO.checkLockerUse(10)==1)
            {
                frame.Locker10.setBackground(new Color(0xcccccc));
            }
            if(userDAO.checkLockerUse(11)==1)
            {
                frame.Locker11.setBackground(new Color(0xcccccc));
            }
            if(userDAO.checkLockerUse(12)==1)
            {
                frame.Locker12.setBackground(new Color(0xcccccc));
            }
            if(userDAO.checkLockerUse(13)==1)
            {
                frame.Locker13.setBackground(new Color(0xcccccc));
            }
            if(userDAO.checkLockerUse(14)==1)
            {
                frame.Locker14.setBackground(new Color(0xcccccc));
            }
            if(userDAO.checkLockerUse(15)==1)
            {
                frame.Locker15.setBackground(new Color(0xcccccc));
            }if(userDAO.checkLockerUse(16)==1)
            {
                frame.Locker16.setBackground(new Color(0xcccccc));
            }
            if(userDAO.checkLockerUse(17)==1)
            {
                frame.Locker17.setBackground(new Color(0xcccccc));
            }
            if(userDAO.checkLockerUse(18)==1)
            {
                frame.Locker18.setBackground(new Color(0xcccccc));
            }
            if(userDAO.checkLockerUse(19)==1)
            {
                frame.Locker19.setBackground(new Color(0xcccccc));
            }
            if(userDAO.checkLockerUse(20)==1)
            {
                frame.Locker20.setBackground(new Color(0xcccccc));
            }
            String[] UserInfo = new String[10];
            UserInfo = userDAO.showUserInfo(1234);
            frame.Name.setText(UserInfo[0]);
            frame.TrainerName.setText(UserInfo[1]);
            frame.PTRemain.setText(UserInfo[2]);
            frame.HealthRemain.setText(UserInfo[3]);
            frame.HealthStart.setText(UserInfo[4]);
            frame.HealthEnd.setText(UserInfo[5]);
            frame.LockerNumber.setText(UserInfo[6]);
            frame.LockerRemain.setText(UserInfo[7]);
            frame.LockerStart.setText(UserInfo[8]);
            frame.LockerEnd.setText(UserInfo[9]);
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
        // 회원가입 비밀번호란에서 받아온 문자 string으로 변환
        String passPW = new String(Reg_PW.getPassword());
        String passPWAgain = new String(Reg_PWagain.getPassword());
        if(passPW == passPWAgain)
        {
            User user = new User(); // user 인스턴스 생성

            // DB에 각 정보 저장
            user.setUserID(Reg_ID.getText()); // ID 저장
            user.setPassword(passPW); // PW 저장


            if(Gen_B.isSelected()) { // 성별 저장
                user.setGender("남");
            }
            else if(Gen_G.isSelected())
            {
                user.setGender("여");
            }

            if(Type.getSelectedIndex()==0) // type 저장
            {
                user.setType("재학생");
            }
            else if(Type.getSelectedIndex()==1)
            {
                user.setType("교직원");
            }
            else if(Type.getSelectedIndex()==2)
            {
                user.setType("일반인");
            }

            if(LockerUse.isSelected()) // 개인락커 사용여부 저장
            {
                user.setLockerFlag(true);
            }
            else {
                user.setLockerFlag(false);
            }

            RegForm.setVisible(false); // 로그인창 숨기기

            UserDAO userDAO = new UserDAO(); // userDAO 객체 생성

            int result = userDAO.signUp(user); // 회원가입 성공 여부 result에 담음


            if (result == 1) {
                JOptionPane.showMessageDialog(null, "회원가입 완료");
                //  성공
            } else if (result == -1) {
                JOptionPane.showMessageDialog(null, "DB오류");
                // 데이터베이스 오류
            }

        }
        else {
            JOptionPane.showMessageDialog(null, "비밀번호가 일치하지않습니다");
        }




        /* System.out.println("result = " + result); */


    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - 김민재
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
        Type = new JComboBox<>();
        label8 = new JLabel();
        LockerUse = new JCheckBox();
        Reg_PW = new JPasswordField();
        Reg_PWagain = new JPasswordField();
        CreateAccount = new JButton();
        label9 = new JLabel();
        textField1 = new JTextField();

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
            label3.setBounds(new Rectangle(new Point(30, 45), label3.getPreferredSize()));

            //---- label4 ----
            label4.setText("userPW");
            RegFormContentPane.add(label4);
            label4.setBounds(new Rectangle(new Point(25, 80), label4.getPreferredSize()));
            RegFormContentPane.add(Reg_ID);
            Reg_ID.setBounds(85, 40, 175, 25);

            //---- label5 ----
            label5.setText("gender");
            RegFormContentPane.add(label5);
            label5.setBounds(25, 145, 50, label5.getPreferredSize().height);

            //---- Gen_B ----
            Gen_B.setText("\ub0a8");
            Gen_B.setSelected(true);
            RegFormContentPane.add(Gen_B);
            Gen_B.setBounds(new Rectangle(new Point(85, 145), Gen_B.getPreferredSize()));

            //---- Gen_G ----
            Gen_G.setText("\uc5ec");
            RegFormContentPane.add(Gen_G);
            Gen_G.setBounds(new Rectangle(new Point(125, 145), Gen_G.getPreferredSize()));

            //---- label6 ----
            label6.setText("PW Again");
            RegFormContentPane.add(label6);
            label6.setBounds(15, 115, 60, 17);

            //---- label7 ----
            label7.setText("state");
            RegFormContentPane.add(label7);
            label7.setBounds(new Rectangle(new Point(30, 175), label7.getPreferredSize()));

            //---- Type ----
            Type.setModel(new DefaultComboBoxModel<>(new String[] {
                "\uc7ac\ud559\uc0dd",
                "\uad50\uc9c1\uc6d0",
                "\uc77c\ubc18\uc778"
            }));
            RegFormContentPane.add(Type);
            Type.setBounds(85, 175, 75, Type.getPreferredSize().height);

            //---- label8 ----
            label8.setText("locker usage");
            RegFormContentPane.add(label8);
            label8.setBounds(10, 205, 70, label8.getPreferredSize().height);

            //---- LockerUse ----
            LockerUse.setText("\uc0ac\uc6a9\ud568");
            LockerUse.setSelected(true);
            RegFormContentPane.add(LockerUse);
            LockerUse.setBounds(new Rectangle(new Point(85, 205), LockerUse.getPreferredSize()));
            RegFormContentPane.add(Reg_PW);
            Reg_PW.setBounds(85, 75, 175, Reg_PW.getPreferredSize().height);
            RegFormContentPane.add(Reg_PWagain);
            Reg_PWagain.setBounds(85, 110, 175, Reg_PWagain.getPreferredSize().height);

            //---- CreateAccount ----
            CreateAccount.setText("Create Account");
            CreateAccount.addActionListener(e -> CreateAccount(e));
            RegFormContentPane.add(CreateAccount);
            CreateAccount.setBounds(15, 235, 260, 30);

            //---- label9 ----
            label9.setText("name");
            RegFormContentPane.add(label9);
            label9.setBounds(new Rectangle(new Point(35, 15), label9.getPreferredSize()));
            RegFormContentPane.add(textField1);
            textField1.setBounds(new Rectangle(new Point(85, 10), textField1.getPreferredSize()));

            RegFormContentPane.setPreferredSize(new Dimension(290, 305));
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
        UserDAO userDAO = new UserDAO();
        userDAO.checkExpiration();
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
    // Generated using JFormDesigner Evaluation license - 김민재
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
    private JComboBox<String> Type;
    private JLabel label8;
    private JCheckBox LockerUse;
    private JPasswordField Reg_PW;
    private JPasswordField Reg_PWagain;
    private JButton CreateAccount;
    private JLabel label9;
    private JTextField textField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
