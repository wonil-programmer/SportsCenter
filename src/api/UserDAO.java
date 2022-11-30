package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.lang.Integer;
import java.time.DayOfWeek;
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
            System.out.println("연동 성공 > ");
        } catch (Exception e) {
            System.out.println("연동 실패 > ");
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
            pstmt.setBoolean(8, user.getLockerFlag());
            pstmt.setString(9, user.getCreateDateTime());
            return pstmt.executeUpdate(); // 회원가입 성공
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류
    }

    // 과거 주에 해당하는 요일에 해당하는 날짜 계산함수 (weekAgo : 예시.1이면 1주전, 2이면 2주전)
    public String[] calcPastWeekDates(int weekAgo) {

        String dateList[] = new String[6]; // 월~토
        Calendar cal = Calendar.getInstance();

        int weeksAgo = weekAgo * (-7); // 입력값에 대해 며칠전인지 계산
        cal.add(Calendar.DATE, weeksAgo);
        int nWeek = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE, 1-nWeek); //요일에서 월요일 되도록 뺌

        // 과거 주에 해당하는 날짜 배열에 추가
        for (int i = 0; i < 6; i++) {
            int nMonth  = cal.get(Calendar.MONTH)+1;
            cal.add(Calendar.DATE, 1);
            String date = cal.get(Calendar.YEAR) +"-"+ (nMonth<10?"0"+nMonth:nMonth+"") +"-"+ (cal.get(Calendar.DATE)<10?"0"+cal.get(Calendar.DATE):cal.get(Calendar.DATE)+"");
            dateList[i] = date;
        }

        return dateList;
    }


    // 입장 함수
    public int enter(int id) {
        String SQL = "INSERT INTO visit_logs (user_id) VALUES(?)";
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
        String SQL = "UPDATE visit_logs SET exit_datetime = CURRENT_TIMESTAMP WHERE user_id = ?";
        try {

            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, id);

            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류
    }


    // 현재 이용자수 계산 함수
    public int countCurUser() {
        // 퇴장시각 NULL인 경우(입장버튼을 누른 후 퇴장버튼은 누르지 않은 경우)
        String SQL = "SELECT COUNT(*) AS '현재 이용자수' FROM visit_logs WHERE exit_datetime IS NULL";

        try {
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            if (rs.next()) {

                int curUser = rs.getInt(1); // 현재 이용자수 값 저장
                return curUser;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류
    }


    // 과거 특정 주간(월~토)의 사용자들을 시간대별로 정렬하는 함수. (최소사용시간 : 30분)
    public int[] alignByTime(String[] dateList) {

        int minUseHour = 30;
        String SQL = "SELECT DATE_FORMAT(enter_datetime, '%H'), DATE_FORMAT(exit_datetime, '%H') FROM visit_logs " +
                "WHERE DATE_FORMAT(enter_datetime, '%Y-%m-%d') BETWEEN ? AND date_add(?, INTERVAL 1 DAY) " +
                "AND (TIMESTAMPDIFF(MINUTE, enter_datetime, exit_datetime) > ?)";

        int[] timeArr = new int[15]; // 06~20시
        try {

            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, dateList[0]);
            pstmt.setString(2, dateList[5]);
            pstmt.setInt(3, minUseHour);
            rs = pstmt.executeQuery();

            // 시간대별 이용자수 통계내는 부분
            while (rs.next()) {
                int enterTime = Integer.parseInt(rs.getString(1));
                int exitTime = Integer.parseInt(rs.getString(2));
                for (int i = enterTime; i <= exitTime; i++) {
                    timeArr[i - 6]++;
                }
            }

            return timeArr;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // 데이터베이스 오류
    }

    // 요일별 인원 계산 함수
    public float[] alignByDay(String[] dateList) {

        int minUseHour = 30;
        String SQL = "SELECT COUNT(enter_datetime) FROM visit_logs " +
                "WHERE DATE_FORMAT(enter_datetime, '%Y-%m-%d') = ? " +
                "AND (TIMESTAMPDIFF(MINUTE, enter_datetime, exit_datetime) > ?)";

        float[] dayArr = new float[6]; // 월~토
        try {

            // 요일별 일이용자수 계산 후 배열에 대입
            for (int i = 0; i < 6; i++) {
                pstmt = conn.prepareStatement(SQL);
                pstmt.setString(1, dateList[i]);
                pstmt.setInt(2, minUseHour);
                rs = pstmt.executeQuery();
                rs.next();
                dayArr[i] = rs.getInt(1); // 해당 요일 이용자 수 저장
            }

            for (int i = 0; i < 6; i++) {
                System.out.println("dayArr["+i+"]" + "=" + dayArr[i]);
            }
            return dayArr;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // 데이터베이스 오류
    }

    // PT 등록을 DB에 반영하는 함수
    public int regPT(int id, int trainerId, int useCnt) { // id : 회원ID(FK), trainerId : 트레이너ID, period : 등록횟수

        String SQL = "UPDATE personal_trainings SET user_id = ?, trainer_id = ?, use_count = ?";

        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, id);
            pstmt.setInt(2, trainerId);
            pstmt.setInt(3, useCnt);
            return pstmt.executeUpdate();
        } catch(Exception e) {
            System.out.println("PT 등록 실패 > " + e.toString());
        }
        return -1;
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
//
//    public int[] lockerColor() {
//        boolean flag = false;
//
//        int num;
//        int[] locker_state = new int[20];
//
//        try {
//
//            for (num = 0; num < 20; num++) {
//                String SQL = "SELECT lockerState FROM locker WHERE lockerNum=" + (num + 1);
//                pstmt = conn.prepareStatement(SQL);
//                rs = pstmt.executeQuery();
//                while (rs.next()) {
//                    locker_state[num] = rs.getInt("lockerState");
//                }
//
//            }
//            flag = true;
//            System.out.println(Arrays.toString(locker_state));
//            System.out.println("락커 색깔 가져오기 성공");
//
//        } catch (Exception e) {
//            flag = false;
//            System.out.println("락커 색깔 가져오기 실패 > " + e.toString());
//        }
//
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


