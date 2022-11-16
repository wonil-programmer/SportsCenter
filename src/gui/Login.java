package gui;

import javax.swing.*;
import api.user.UserDAO;


public class Login {

    private JButton logInButton;
    private JTextField textField1;
    private JPasswordField passwordField1;





    public static void main(String args[]) {

        JFrame frame = new JFrame("로그인");

        UserDAO userDAO = new UserDAO();
        // addeventlistner
        int result = userDAO.login(ID, Password);
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

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
