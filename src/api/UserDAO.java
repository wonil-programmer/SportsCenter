package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    private Connection conn; // Connection 객체 생성
    private PreparedStatement pstmt; // prepareStatement 객체 생성    
    private ResultSet rs;

    // 생성자
    public UserDAO() {
        // DB에 접속하는 로직
        try {
            String dbURL = "jdbc:mysql://localhost:3306/sportscenter";
            String dbID = "root";
            String dbPassword = "0201";
            Class.forName("com.mysql.cj.jdbc.Driver");
            // getConnection 메소드로 DB에 연결
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
            System.out.println("MySQL 서버 연동 성공");
        } catch (Exception e) {
            System.out.println("MySQL 서버 연동 실패 > " + e.toString());
        }
    }

    // 로그인 함수
    public int login(String userID, String userPassword) {
        System.out.println("userPassword = " + userPassword);
        String SQL = "SELECT user_password FROM user WHERE user_id = ?";
        try {
            // Statement 클래스를 이용하여 prepareStatement 객체 생성
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, userID);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getString(1).equals(userPassword))
                    return 1; // 로그인 성공
                else
                    return 0; // 비밀번호 불일치
            }
            return -1; // 아이디 없음
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -2; // 데이터베이스 오류
    }

    // 회원가입 함수
    public int signUp(User user) {
        String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, user.getUserID());
            pstmt.setString(2, user.getUserPassword());
            pstmt.setString(3, user.getUserName());
            pstmt.setString(4, user.getUserBelong());
            pstmt.setString(5, user.getUserPhoneNumber());
            pstmt.setString(6, user.getUserGender());
            pstmt.setBoolean(7, user.getUserLocker());
            pstmt.setString(8, user.getCreateTime());
            return pstmt.executeUpdate(); // 회원가입 성공
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류
    }

    boolean lockerPayment(String _u, int _n, int _p) {
        boolean flag = false;

        _u = "test";    // 수정

        String UserID = _u;
        int lockerPeriod = _p;
        int lockerNum = _n;

        try {
            String SQL = "UPDATE locker SET userID = ?, lockerPeriod = ?, lockerState=? WHERE lockerNum=?";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, UserID);
            pstmt.setInt(2, lockerPeriod);
            pstmt.setInt(3, 1); // lockerState 사용중으로 변경
            pstmt.setInt(4, lockerNum);
            pstmt.executeUpdate();
            flag = true;
            System.out.println("락커 등록 성공");
        } catch(Exception e) {
            flag = false;
            System.out.println("락커 등록 실패 > " + e.toString());
        }

        return flag;
    }
}
