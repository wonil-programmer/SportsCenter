package api.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

    private Connection conn; // Connection 객체 생성
    private PreparedStatement pstmt; // prepareStatement 객체 생성    
    private ResultSet rs;

    public UserDAO() {
        // DB에 접속하는 로직
        try {
            String dbURL = "jdbc:mysql://localhost:3306/sportscenter";
            String dbID = "root";
            String dbPassword = "mysqlrhtn8580!";
            Class.forName("com.mysql.jdbc.Driver");
            // getConnection 메소드로 DB에 연결
            conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int login(String userID, String userPassword) {
        String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
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
}
