package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.lang.Integer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

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


    // 헬스 회원권 등록 함수
    public int regHealth(int id, int period) {

        String preSQL = "SELECT EXISTS(SELECT * FROM health_members WHERE user_id = ?) AS regFlag";
        String SQL = "INSERT INTO health_members (user_id, start_date, end_date) VALUES (?, ?, ?)";
        String reRegSQL = "UPDATE health_members SET end_date = ? WHERE user_id = ?";

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = df.format(cal.getTime()); // 시작날짜를 현재날짜로 지정

        try {
            pstmt = conn.prepareStatement(preSQL);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            rs.next();
            int regFlag = rs.getInt(1); // 기존 등록 회원인지 체크하는 플래그 (1이면 기존등록회원)
            System.out.println("regFlag= " + regFlag);
            // -2 반환시 "이미 등록한 회원입니다. 회원님의 남은기간은 ?일입니다. 재등록하시겠습니까?"
            // 메시지 출력후, 확인 버튼 누르면 재등록 창 팝업
            if (regFlag == 1) return -2;
            else {
                pstmt = conn.prepareStatement(SQL);
                pstmt.setInt(1, id);
                pstmt.setString(2, startDate);
                cal.add(Calendar.MONTH, period); // 사용기간만큼 날짜 더함
                String endDate = df.format(cal.getTime());
                pstmt.setString(3, endDate);
                return pstmt.executeUpdate();
            }
        } catch(Exception e) {
            System.out.println("회원권 등록 실패 > " + e.toString());
        }
        return -1; // 데이터베이스 오류
    }


    // PT 등록을 DB에 반영하는 함수
    public int regPT(int id, int trainerId, int useCnt) { // id : 회원ID(FK), trainerId : 트레이너ID, period : 등록횟수

        String SQL = "INSERT INTO personal_trainings (user_id, trainer_id, use_count) VALUES (?, ?, ?)";

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


    // 락커 구매 함수
    public int buyLocker(int id, int lockerNum, int period) {

        String SQL = "INSERT INTO lockers (number, user_id, state, start_date, end_date) VALUES (?, ?, ?, ?, ?)";

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = df.format(cal.getTime()); // 시작날짜를 현재날짜로 지정

        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, lockerNum);
            pstmt.setInt(2, id);
            pstmt.setString(3, "사용중"); // state 사용중으로 변경
            pstmt.setString(4, startDate);
            cal.add(Calendar.MONTH, period); // 사용기간만큼 날짜 더함
            String endDate = df.format(cal.getTime());
            pstmt.setString(5, endDate);
            return pstmt.executeUpdate();
        } catch(Exception e) {
            System.out.println("락커 등록 실패 > " + e.toString());
        }
        return -1; // 데이터베이스 오류
    }

//    // 락커 상태에 따른 색상 변경 함수
//    public int[] lockerColor() {
//        boolean flag = false;
//
//        int num;
//        String[] lockerState = new String[20];
//
//        try {
//
//            for (num = 0; num < 20; num++) {
//                String SQL = "SELECT state FROM lockers WHERE number=" + (num + 1);
//                pstmt = conn.prepareStatement(SQL);
//                rs = pstmt.executeQuery();
//                while (rs.next()) {
//                    lockerState[num] = rs.getString(1);
//                }
//
//            }
//            flag = true;
//            System.out.println(Arrays.toString(lockerState));
//            System.out.println("락커 색깔 가져오기 성공");
//
//        } catch (Exception e) {
//            flag = false;
//            System.out.println("락커 색깔 가져오기 실패 > " + e.toString());
//        }
//
//        // 색깔 할당 부분
//        int[] lockerColor = new int[20];
//        for(int j=0; j<lockerState.length; j++) {
//            if (lockerState[j]=='사용가능') {   // empty
//                lockerColor[j] = 0x6699ff; // BLUE
//            }
//            else if (lockerState[j]==) {  // using
//                lockerColor[j] = 0xcccccc; // WHITE
//            }
//            else if (lockerState[j]==2) {  // broken
//                lockerColor[j] = 0xff9999; // RED
//            }
//        }
//        return lockerColor;
//    }


    public String[] showUserInfo ( int id){
        String[] userInfoList = new String[10];
        /*
         0 이름
         1 PT 트레이너 정보 (id)
         2 PT 남은 횟수
         3 회원권 남은 날짜 (일단위)
         4 회원권 시작날짜
         5 회원권 끝나는날짜
         6 개인 락커 번호
         7 개인 락커 남은 날짜 (일단위)
         8 개인 락커 시작 날짜
         9 개인 락커 종료 날짜
         */

        try {

            /* 이용자 정보 관련 */
            // 이용자 이름
            String SQL_user_name = "SELECT name FROM user WHERE id=" + id;
            pstmt = conn.prepareStatement(SQL_user_name);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                userInfoList[0] = rs.getString("name");   // 이름
            }


            /* pt 관련 */
            // pt트레이너 정보, 남은 횟수 (등록 안한 경우는 default: 'pt등록안함')
            String preSQL_pt = "SELECT EXISTS(SELECT * FROM personal_trainings WHERE user_id = ?) AS ptFlag";
            String SQL_pt = "SELECT trainer_id, use_count FROM personal_trainings WHERE user_id=" + id;

            // pt 여부 확인
            pstmt = conn.prepareStatement(preSQL_pt);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            rs.next();
            int ptFlag = rs.getInt(1); // pt 등록 회원인지 체크하는 플래그 (1이면 pt 등록회원)
            if (ptFlag == 1) { // pt 등록된 경우
                pstmt = conn.prepareStatement(SQL_pt);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    userInfoList[1] = rs.getString(1); // pt 트레이너 정보
                    userInfoList[2] = rs.getString(2); // pt 남은 횟수
                }
            } else {
                userInfoList[1] = "pt 등록안함";
                userInfoList[2] = "-";
            }

            /* 회원권 관련 */
            String preSQL_mem = "SELECT EXISTS(SELECT * FROM health_members WHERE user_id = ?) AS memFlag";
            String SQL_mem = "SELECT timestampdiff(day, start_date, end_date), start_date, end_date FROM health_members WHERE user_id=" + id;

            // pt 여부 확인
            pstmt = conn.prepareStatement(preSQL_mem);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            rs.next();
            int memFlag = rs.getInt(1); // pt 등록 회원인지 체크하는 플래그 (1이면 회원권 등록회원)
            if (memFlag == 1) { // pt 등록된 경우
                pstmt = conn.prepareStatement(SQL_mem);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    userInfoList[3] = rs.getString(1); // 회원권 남은 날짜
                    userInfoList[4] = rs.getString(2); // 회원권 시작 날짜
                    userInfoList[5] = rs.getString(3); // 회원권 종료 날짜
                }
            } else {
                userInfoList[3] = "회원권 등록안함";
                userInfoList[4] = "-";
            }


            /* 개인 락커 관련 */
            String preSQL_locker = "SELECT EXISTS(SELECT * FROM lockers WHERE user_id = ?) AS lockerFlag";
            String SQL_locker = "SELECT number, timestampdiff(day, start_date, end_date), start_date, end_date FROM lockers WHERE user_id=" + id;

            // 개인랔커 사용 여부 확인
            pstmt = conn.prepareStatement(preSQL_locker);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            rs.next();
            int lockerFlag = rs.getInt(1); // 개인락커 사용하고 있는 회원인지 체크하는 플래그 (1이면 구매한 회원)
            if (lockerFlag == 1) { // 개인락커 구매한 경우
                pstmt = conn.prepareStatement(SQL_mem);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    userInfoList[6] = rs.getString(1); // 개인락커 번호
                    userInfoList[7] = rs.getString(2); // 개인락커 남은 날짜
                    userInfoList[8] = rs.getString(3); // 개인락커 시작 날짜
                    userInfoList[9] = rs.getString(4); // 개인락커 종료 날짜
                }
            } else {
                userInfoList[6] = "개인락커 없음";
                userInfoList[7] = "-";
                userInfoList[8] = "-";
                userInfoList[9] = "-";
            }
            return userInfoList; // 회원정보를 리스트에 담아 반환

        } catch (Exception e) {
            System.out.println("회원 정보 불러오기 실패 > " + e.toString());
        }
        return null; // 데이터베이스 오류
    }


}

