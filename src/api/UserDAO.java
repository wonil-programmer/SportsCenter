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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 로그인 함수
    public int login(String userID, String userPassword) {
        System.out.println("userPassword = " + userPassword);
        String SQL = "SELECT password FROM user WHERE u_id = ?";
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
        String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, user.getID());
            pstmt.setString(2, user.getUserID());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getName());
            pstmt.setString(5, user.getType());
            pstmt.setString(6, user.getPhoneNumber());
            pstmt.setString(7, user.getGender());
            pstmt.setBoolean(8, user.getLocker());
            pstmt.setString(9, user.getCreateTime());
            return pstmt.executeUpdate(); // 회원가입 성공
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류
    }

    // 헬스장이나 PT 등록을 DB에 반영하는 함수
    public int ptReg(int _i, int _p, int _m) {

        int idNum = _i;
        int ifPT = _p;
        int monthIdx = _m;

        String SQL = "UPDATE register SET ifPT = ?, monthIdx = ? WHERE idNum=?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, ifPT);
            pstmt.setInt(2, monthIdx);
            pstmt.setInt(3, idNum);
            pstmt.executeUpdate();
            System.out.println("헬스/PT 등록 성공");
        } catch(Exception e) {
            System.out.println("헬스/PT 등록 실패 > " + e.toString());
        }
        return -1;
    }

    // 입장 함수
//    public int enter(int id) {
//        String SQL = "INSERT INTO ENTER_EXIT (user_id) VALUES(?)";
//        try {
//            pstmt = conn.prepareStatement(SQL);
//            pstmt.setInt(1, id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return -1; // 데이터베이스 오류
//    }
//
//    // 퇴장 함수
//    public int exit(int id) {
//        String SQL = "UPDATE ENTRY_EXIT SET EXIT_TIME = CURRENT_TIMESTAMP WHERE USER_ID = ?";
//        try {
//            pstmt = conn.prepareStatement(SQL);
//            pstmt.setInt(1, id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return -1; // 데이터베이스 오류
//    }
//
//    // 사용시간 계산 함수
//    public String calcUseTime() {
//        String SQL = "SELECT ENTRY_TIME, EXIT_TIME FROM ENTRY_EXIT";
//        int[] timeArr = new int[24];
//        try {
//            pstmt = conn.prepareStatement(SQL);
//            rs = pstmt.executeQuery();
//            if (rs.next()) {
//                if (rs.getString(1).equals(userPassword))
//                    return 1; // 로그인 성공
//                else
//                    return 0; // 비밀번호 불일치
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return -1; // 데이터베이스 오류
//    }
}