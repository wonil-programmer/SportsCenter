package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.lang.Integer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // 데이터베이스 오류
    }

    // 현재 이용자수 계산 함수
    public int countCurUser() {
        // 퇴장시각 NULL인 경우(입장버튼을 누른 후 퇴장버튼은 누르지 않은 경우)
        String SQL = "SELECT COUNT(*) AS '현재 이용자수' FROM sportscenter.enter_exit WHERE exit_time IS NOT NULL";

        try {
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery(); // SQL문 실행
            int curUser = rs.getInt(1); // 현재 이용자수 값 저장
            System.out.println("curUser = " + curUser);
            return curUser;
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

        // 색깔 할당 부분
        int[] locker_color = new int[20];
        for(int j=0; j<locker_state.length; j++) {
            if (locker_state[j]==0) {   // empty
                locker_color[j] = 0x6699ff; // BLUE
            }
            else if (locker_state[j]==1) {  // using
                locker_color[j] = 0xcccccc; // WHITE
            }
            else if (locker_state[j]==2) {  // broken
                locker_color[j] = 0xff9999; // RED
            }
        }
        return locker_color;
    }

    //
    public String[] userInformation (int id) {
        String[] stringList = new String[11];
        // String cur_id;
        /*
         * 이름 ㅇ
         * 회원 상태 추가
         * 회원권
         * 회원권 가격
         * 남은 기간
         * 개인 락커 번호
         * PT 트레이너
         * PT 남은 횟수ㄴ
         * PT 가격ㅇ
         * */
        /*
        * 0 이름
        * 1 PT유무
        * 2 PT횟수Idx
        * 3 PT가격
        * 4 PT트레이너Idx
        * 5 회원권유무
        * 6 회원권기간Idx
        * 7 회원권시작날짜
        * 8 회원권끝나는날짜
        * 9 락커 사용여부
        * 10 락커 번호
        *
        * */
        String cur_id = null;
        try {
//            // id 얻어오기
//            String SQL1 = "SELECT u_id FROM user WHERE id=" + id;
//            pstmt = conn.prepareStatement(SQL1);
//            rs = pstmt.executeQuery();
//            while (rs.next()) {
//                cur_id = rs.getString("id");
//            }

            //
            String SQL1 = "SELECT name FROM user WHERE id=" + id;
            pstmt = conn.prepareStatement(SQL1);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                stringList[0] = rs.getString("name");   // 이름
            }


            // PT유무

            String SQL2 = "SELECT ifPT FROM register WHERE id=" + id;
            pstmt = conn.prepareStatement(SQL2);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                stringList[1] = rs.getString("ifPT");   // PT유무
            }

            // pt횟수Idx
            if (stringList[1].equals("1")) {    // pt 할경우
                String SQL3 = "SELECT ptNumIdx FROM register WHERE id=" + id;
                pstmt = conn.prepareStatement(SQL3);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    stringList[2] = rs.getString("ptNumIdx");   // PT횟수Idx
                }
            } else {
                stringList[2] = "없음";
            }


            /* PT 가격
            * */
            if (stringList[1].equals("1")) {    // pt 할경우
                if (stringList[2].equals("0")) {    // 월 8회
                    stringList[3] = "24만원";
                } else if (stringList[2].equals("1")) {
                    stringList[3] = "30만원";
                }
            } else {
                stringList[3] ="없음";
            }


            // PT트레이너Idx
            if (stringList[1].equals("1")) {    // pt 할경우
                String SQL4 = "SELECT ptTrainerIdx FROM register WHERE id=" + id;
                pstmt = conn.prepareStatement(SQL4);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    stringList[4] = rs.getString("ptTrainerIdx");   // PT트레이너Idx
                }
            } else {
                stringList[4] = "없음";
            }


            ////////회원권
            // 회원권 유무
            String SQL5 = "SELECT ifMembership FROM register WHERE id=" + id;
            pstmt = conn.prepareStatement(SQL5);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                stringList[5] = rs.getString("ifMembership");   // 회원권유무
            }
            // 멤버십기간Idx
            if (stringList[5].equals("1")) {    // 헬스장할경우
                String SQL6 = "SELECT membershipIdx FROM register WHERE id=" + id;
                pstmt = conn.prepareStatement(SQL6);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    stringList[6] = rs.getString("membershipIdx");   // 멤버십Idx
                }
            } else {
                stringList[6] = "없음";
            }
            // 멤버십시작날짜
            if (stringList[5].equals("1")) {    // 헬스장할경우
                String SQL7 = "SELECT membershipDate FROM register WHERE id=" + id;
                pstmt = conn.prepareStatement(SQL7);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    stringList[7] = rs.getString("membershipDate");   // 멤버십날짜
                }
            } else {
                stringList[7] = "없음";
            }

            // 멤버십끝나는날짜
            if (stringList[5].equals("1")) {
                String[] Date = stringList[7].split("-");
                int[] startDate = {Integer.parseInt(Date[0]), Integer.parseInt(Date[1]), Integer.parseInt(Date[2])};

                // 회원권기간 처리 부분
                if(stringList[6].equals("0") == false && stringList[6].equals("1")==false) {
                    stringList[8] = "오류";
                } else {
                    int memMonth=0;
                    if(stringList[6].equals("0")) { // 헬스장Idx
                        memMonth = 1;
                    } else if (stringList[6].equals("1")) {
                        memMonth = 3;}

                    // 날짜계산 알고리즘
                    startDate[1]+=memMonth;
                    if(startDate[1]>12) {
                        startDate[0]+=1;
                        startDate[1]%=12;
                    }
                    // 최종 날짜
                    stringList[8]=startDate[0]+"-"+startDate[1]+"-"+startDate[2];
                }

                // ** 하나의 id가 여러 개를 누를 경우 오류 가능성 -> 마지막 락커번호만 가져옴
                // 락커 사용 여부
                String SQL8 = "SELECT lockerState FROM locker WHERE id=" + id;
                pstmt = conn.prepareStatement(SQL8);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    stringList[9] = rs.getString("lockerState");   //
                }

                // 락커 번호
                if (stringList[9].equals("1")) {
                    String SQL9 = "SELECT lockerNum FROM locker WHERE id=" + id;
                    pstmt = conn.prepareStatement(SQL9);
                    rs = pstmt.executeQuery();
                    while (rs.next()) {
                        stringList[10] = rs.getString("lockerNum");   //
                    }
                } else {
                    stringList[10] = "사용하지 않음";
                }

            }   // try


        } catch (Exception e) {
            System.out.println("회원 정보 실패 > " + e.toString());
        }

        return stringList;
    }


    // PT 등록을 DB에 반영하는 함수
    public int ptReg(int _i, int _t, int _m) {

        int idNum = _i; // 회원 id (Int)
        int trainer = _t;   // 트레이너 Index
        int monthIdx = _m;  // pt 등록 달 수 Index

        String SQL = "UPDATE register SET ifPT = ?, ptNumIdx = ?, ptTrainerIdx = ? WHERE id=?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, 1);
            pstmt.setInt(2, monthIdx);
            pstmt.setInt(3, trainer);
            pstmt.setInt(4, idNum);
            pstmt.executeUpdate();
            System.out.println("PT 등록 성공");
        } catch(Exception e) {
            System.out.println("PT 등록 실패 > " + e.toString());
        }
        return -1;
    }


    // 헬스장 등록을 DB에 반영하는 함수
    public int ptReg(int _i, int _m) {

        int idNum = _i; // 회원 id (Int)
        int monthIdx = _m;

        // 현재 날짜 구하기
        LocalDate now = LocalDate.now();
        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 포맷 적용
        String formatedNow = now.format(formatter);


        String SQL = "UPDATE register SET ifMembership = ?, membershipIdx = ?, membershipDate = ? WHERE id=?";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setInt(1, 1);
            pstmt.setInt(2, monthIdx);
            pstmt.setString(3, formatedNow);    // 다시 확인
            pstmt.setInt(4, idNum);
            pstmt.executeUpdate();
            System.out.println("헬스장 등록 성공");
        } catch(Exception e) {
            System.out.println("헬스장 등록 실패 > " + e.toString());
        }
        return -1;
    }

}


