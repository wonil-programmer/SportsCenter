package gui;

import api.user.UserDAO;
import java.awt.*;
import javax.swing.*;
/*
 * Created by JFormDesigner on Wed Nov 16 09:47:05 KST 2022
 */



/**
 * @author Minjae
 */
public class login extends JFrame {
    public login() {
        initComponents();
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

        //======== this ========
        setTitle("LOGIN");
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
        contentPane.add(LoginButton);
        LoginButton.setBounds(240, 15, 150, 55);

        //---- RememberCheck ----
        RememberCheck.setText("Remember me");
        RememberCheck.setSelected(true);
        contentPane.add(RememberCheck);
        RememberCheck.setBounds(10, 75, 110, 21);

        //---- RegButton ----
        RegButton.setText("REGISTER");
        contentPane.add(RegButton);
        RegButton.setBounds(240, 75, 150, 23);

        {
            // compute preferred size
            Dimension preferredSize = new Dimension();
            for(int i = 0; i < contentPane.getComponentCount(); i++) {
                Rectangle bounds = contentPane.getComponent(i).getBounds();
                preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
            }
            Insets insets = contentPane.getInsets();
            preferredSize.width += insets.right;
            preferredSize.height += insets.bottom;
            contentPane.setMinimumSize(preferredSize);
            contentPane.setPreferredSize(preferredSize);
        }
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public static void main(String[] args) {

        com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme.setup();
        login frame = new login();
        frame.pack();
        frame.setSize(415, 150);
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
    // Generated using JFormDesigner Evaluation license - Minjae
    private JLabel label1;
    private JLabel label2;
    private JPasswordField PWtext;
    private JTextField IDtext;
    private JButton LoginButton;
    private JCheckBox RememberCheck;
    private JButton RegButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
