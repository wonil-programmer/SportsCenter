package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.lang.Integer;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;

public class UserDAO {

    LocalDate now = LocalDate.now();

    private Connection conn; // Connection 객체 생성
    private PreparedStatement pstmt; // prepareStatement 객체 생성    
    private ResultSet rs;

    // 생성자
    public UserDAO() {
        // DB에 접속하는 로직
        try {
            String dbURL = "jdbc:mysql://localhost:3306/sportscenter";
            String dbID = "root";
            String dbPassword = "mysqlrhtn8580!";
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

    // 입장 함수
    public int enter(int id) {
        String SQL = "INSERT INTO ENTER_EXIT (user_id) VALUES(?)";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류
    }

    // 퇴장 함수
    public int exit(int id) {
        String SQL = "UPDATE ENTER_EXIT SET EXIT_TIME = CURRENT_TIMESTAMP WHERE USER_ID = ?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류
    }

    



    public boolean lockerPayment(String _u, int _n, int _p) {
        boolean flag = false;

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

    public int[] lockerColor() {
        boolean flag = false;

        int num;
        int[] locker_state = new int[20];

        try {

            for (num = 0; num < 20; num++) {
                String SQL = "SELECT lockerState FROM locker WHERE lockerNum=" + (num + 1);
                pstmt = conn.prepareStatement(SQL);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    locker_state[num] = rs.getInt("lockerState");
                }

            }
            flag = true;
            System.out.println(Arrays.toString(locker_state));
            System.out.println("락커 색깔 가져오기 성공");

        } catch (Exception e) {
            flag = false;
            System.out.println("락커 색깔 가져오기 실패 > " + e.toString());
        }

        return locker_state;
    }

//        // 색깔 할당 부분
//        int[] locker_color = new int[20];
//        for(int j=0; j<locker_state.length; j++) {
//            if (locker_state[j]==0) {   // empty
//                locker_color[j] = 0x6699ff; // BLUE
//            }
//            else if (locker_state[j]==1) {  // using
//                locker_color[j] = 0xcccccc; // WHITE
//            }
//            else if (locker_state[j]==2) {  // broken
//                locker_color[j] = 0xff9999; // RED
//            }
//        }
//        return locker_color;
//    }

}
