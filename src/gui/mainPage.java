/*
 * Created by JFormDesigner on Fri Nov 18 20:23:17 KST 2022
 */
package gui;
import javax.swing.event.*;
import api.User;
import api.UserDAO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import gui.loginPage;
/**
 * @author Minjae
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;

class CalendarDataManager{ //6*7배열에 나타낼 달력 값을 구하는 클래스
    static final int CAL_WIDTH = 7;
    final static int CAL_HEIGHT = 6;
    int calDates[][] = new int[CAL_HEIGHT][CAL_WIDTH];
    int calYear;
    int calMonth;
    int calDayOfMon;
    final int calLastDateOfMonth[]={31,28,31,30,31,30,31,31,30,31,30,31};
    int calLastDate;
    Calendar today = Calendar.getInstance();
    Calendar cal;

    public CalendarDataManager()
    {
        setToday();
    }
    public void setToday(){
        calYear = today.get(Calendar.YEAR);
        calMonth = today.get(Calendar.MONTH);
        calDayOfMon = today.get(Calendar.DAY_OF_MONTH);
        makeCalData(today);

    }
    private void makeCalData(Calendar cal){ //달력의 값을 채워넣기 위한 메소드
        //1일의 위치와 마지막 날짜를 구함

        int calStartingPos = (cal.get(Calendar.DAY_OF_WEEK)+7-(cal.get(Calendar.DAY_OF_MONTH))%7)%7;
        if(calMonth == 1) calLastDate = calLastDateOfMonth[calMonth] + leapCheck(calYear);
        else calLastDate = calLastDateOfMonth[calMonth];
        // 달력 배열 초기화
        for(int i = 0 ; i<CAL_HEIGHT ; i++){
            for(int j = 0 ; j<CAL_WIDTH ; j++){
                calDates[i][j] = 0;
            }
        }
        // 달력배열에 값 채워넣기
        for(int i = 0, num = 1, k = 0 ; i<CAL_HEIGHT ; i++){
            if(i == 0) k = calStartingPos;
            else k = 0;
            for(int j = k ; j<CAL_WIDTH ; j++){
                if(num <= calLastDate) calDates[i][j]=num++;
            }
        }
    }
    private int leapCheck(int year){
        if(year%4 == 0 && year%100 != 0 || year%400 == 0) return 1;
        else return 0;
    }
    public void moveMonth(int mon){ // 현재 달로부터 전, 후로 이동할 수 있는 메소드
        calMonth += mon;
        if(calMonth>11) while(calMonth>11){
            calYear++;
            calMonth -= 12;
        } else if (calMonth<0) while(calMonth<0){
            calYear--;
            calMonth += 12;
        }
        cal = new GregorianCalendar(calYear,calMonth,calDayOfMon);
        makeCalData(cal);
    }
}



public class mainPage extends JFrame {
    CalendarDataManager CDM = new CalendarDataManager();

    public mainPage() {
        initComponents();
        MainForm.setVisible(true);
        MainForm.setSize(585,330);
    }

    public static void main(String[] args) {
        com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme.setup();
        mainPage frame = new mainPage();
        frame.pack();


    }
    public void curuser()
    {
        UserDAO userDAO = new UserDAO();
        String currentuser = Integer.toString(userDAO.countCurUser());
        CurUser.setText(currentuser);
    }
    public void weekstatistic(int num)
    {
        UserDAO userDAO = new UserDAO();
        String dateList[] = new String[6];
        int[] timeArr = new int[15];
        dateList = userDAO.calcPastWeekDates(num+1);
        timeArr = userDAO.alignByTime(dateList);
        for(int i=0;i<15;i++)
        {
            table1.setValueAt("            "+ timeArr[i],i,1);
        }
    }
    public void drawgraph(int num)
    {
        UserDAO userDAO = new UserDAO();
        float[] dayArr = new float[6]; // 월~토
        String dateList[] = new String[6];
        dateList = userDAO.calcPastWeekDates(num+1);
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
            MonGraph.setValue(100);
            TueGraph.setValue((int)(dayArr[1]/dayArr[0]*100));
            WenGraph.setValue((int)(dayArr[2]/dayArr[0]*100));
            ThuGraph.setValue((int)(dayArr[3]/dayArr[0]*100));
            FriGraph.setValue((int)(dayArr[4]/dayArr[0]*100));
            SatGraph.setValue((int)(dayArr[5]/dayArr[0]*100));
        }
        else if(max_index == 1)
        {
            TueGraph.setValue(100);
            MonGraph.setValue((int)(dayArr[0]/dayArr[1]*100));
            WenGraph.setValue((int)(dayArr[2]/dayArr[1]*100));
            ThuGraph.setValue((int)(dayArr[3]/dayArr[1]*100));
            FriGraph.setValue((int)(dayArr[4]/dayArr[1]*100));
            SatGraph.setValue((int)(dayArr[5]/dayArr[1]*100));
        }
        else if(max_index == 2)
        {
            WenGraph.setValue(100);
            TueGraph.setValue((int)(dayArr[1]/dayArr[2]*100));
            MonGraph.setValue((int)(dayArr[0]/dayArr[2]*100));
            ThuGraph.setValue((int)(dayArr[3]/dayArr[2]*100));
            FriGraph.setValue((int)(dayArr[4]/dayArr[2]*100));
            SatGraph.setValue((int)(dayArr[5]/dayArr[2]*100));
        }
        else if(max_index == 3)
        {
            ThuGraph.setValue(100);
            MonGraph.setValue((int)(dayArr[0]/dayArr[3]*100));
            WenGraph.setValue((int)(dayArr[2]/dayArr[3]*100));
            TueGraph.setValue((int)(dayArr[1]/dayArr[3]*100));
            FriGraph.setValue((int)(dayArr[4]/dayArr[3]*100));
            SatGraph.setValue((int)(dayArr[5]/dayArr[3]*100));
        }
        else if(max_index == 4)
        {
            FriGraph.setValue(100);
            TueGraph.setValue((int)(dayArr[1]/dayArr[4]*100));
            WenGraph.setValue((int)(dayArr[2]/dayArr[4]*100));
            ThuGraph.setValue((int)(dayArr[3]/dayArr[4]*100));
            MonGraph.setValue((int)(dayArr[0]/dayArr[4]*100));
            SatGraph.setValue((int)(dayArr[5]/dayArr[4]*100));
        }
        else if(max_index == 5)
        {
            SatGraph.setValue(100);
            MonGraph.setValue((int)(dayArr[0]/dayArr[5]*100));
            WenGraph.setValue((int)(dayArr[2]/dayArr[5]*100));
            ThuGraph.setValue((int)(dayArr[3]/dayArr[5]*100));
            FriGraph.setValue((int)(dayArr[4]/dayArr[5]*100));
            TueGraph.setValue((int)(dayArr[1]/dayArr[5]*100));
        }
    }
    private void LockerBuy(ActionEvent e) { // 락커 결제 수정 필요, 결제 이미 되어있는지 아닌지 확인 절차 필요
        // TODO add your code here
        UserDAO userDAO = new UserDAO();
        int period = LockerDate.getSelectedIndex();
        int lockernum = Integer.parseInt(LockerNum.getText());
        userDAO.buyLocker(1234,lockernum,LockerDate.getSelectedIndex()+1);  // 수정
        LockerBuyForm.setVisible(false);
        JOptionPane.showMessageDialog(null, "결제에 성공하였습니다");
    }
    private int LockerState(int number) {   // 락커 상태를 int로 가져옴. 결제 여부에 사용. 색깔반영에도 사용(?) (0:사용중,1:사용가능,2:고장,-1:오류)
        UserDAO userDAO = new UserDAO();
        String res = userDAO.checkLockerUse(number);
        int ret=-1; // 반환값
        // System.out.println(userDAO.checkLockerUse(number));  //
        if(res.equals("사용중")) {
            ret = 0;
        } else if (res.equals("사용가능")) {
            ret = 1;
        } else if (res.equals("고장")) {
            ret = 2;
        }
        return ret;
    }

    private void Locker1(ActionEvent e) {   // 임시
        // TODO add your code here
        int num=1;  // 락커 번호 (이 부분만 변경해서 복사해 쓰면 됨)
        int lockerstate=LockerState(num);
        if (lockerstate==0) {
            JOptionPane.showMessageDialog(null, "이미 사용 중인 락커입니다.");
        } else if (lockerstate==1) {
            LockerBuyForm.setVisible(true);
            LockerBuyForm.setSize(145, 180);
            LockerNum.setText( Integer.toString(num) );
        } else if (lockerstate==2) {
            JOptionPane.showMessageDialog(null, "고장난 락커입니다.");
        }
    }

    private void Locker2(ActionEvent e) {
        // TODO add your code here
        LockerBuyForm.setVisible(true);
        LockerBuyForm.setSize(145,180);
        LockerNum.setText("2");
    }

    private void Locker3(ActionEvent e) {
        LockerBuyForm.setVisible(true);
        LockerBuyForm.setSize(145,180);
        LockerNum.setText("3");
    }

    private void Locker4(ActionEvent e) {
        LockerBuyForm.setVisible(true);
        LockerBuyForm.setSize(145,180);
        LockerNum.setText("4");
    }

    private void Locker5(ActionEvent e) {
        // TODO add your code here
        LockerBuyForm.setVisible(true);
        LockerBuyForm.setSize(145,180);
        LockerNum.setText("5");
    }

    private void Locker6(ActionEvent e) { // 일단 6번 락커까지만,, 최적화할 수 있는 방법을 찾아보자,,,,
        LockerBuyForm.setVisible(true);
        LockerBuyForm.setSize(145,180);
        LockerNum.setText("6");
    }

    private void Locker7(ActionEvent e) {
        // TODO add your code here
        LockerBuyForm.setVisible(true);
        LockerBuyForm.setSize(145,180);
        LockerNum.setText("7");
    }

    private void Locker8(ActionEvent e) {
        // TODO add your code here
        LockerBuyForm.setVisible(true);
        LockerBuyForm.setSize(145,180);
        LockerNum.setText("8");
    }

    private void Locker9(ActionEvent e) {
        // TODO add your code here
        LockerBuyForm.setVisible(true);
        LockerBuyForm.setSize(145,180);
        LockerNum.setText("9");
    }


    private void Locker10(ActionEvent e) {
        // TODO add your code here
        LockerBuyForm.setVisible(true);
        LockerBuyForm.setSize(145,180);
        LockerNum.setText("10");
    }

    private void Locker11(ActionEvent e) {
        LockerBuyForm.setVisible(true);
        LockerBuyForm.setSize(145,180);
        LockerNum.setText("11");
    }

    private void Locker12(ActionEvent e) {
        // TODO add your code here
        LockerBuyForm.setVisible(true);
        LockerBuyForm.setSize(145,180);
        LockerNum.setText("12");
    }

    private void Locker13(ActionEvent e) {
        // TODO add your code here
        LockerBuyForm.setVisible(true);
        LockerBuyForm.setSize(145,180);
        LockerNum.setText("13");
    }

    private void Locker14(ActionEvent e) {
        // TODO add your code here
        LockerBuyForm.setVisible(true);
        LockerBuyForm.setSize(145,180);
        LockerNum.setText("14");
    }

    private void Locker15(ActionEvent e) {
        // TODO add your code here
        LockerBuyForm.setVisible(true);
        LockerBuyForm.setSize(145,180);
        LockerNum.setText("15");
    }

    private void Locker16(ActionEvent e) {
        // TODO add your code here
        LockerBuyForm.setVisible(true);
        LockerBuyForm.setSize(145,180);
        LockerNum.setText("16");
    }

    private void Locker17(ActionEvent e) {
        // TODO add your code here
        LockerBuyForm.setVisible(true);
        LockerBuyForm.setSize(145,180);
        LockerNum.setText("17");
    }

    private void Locker18(ActionEvent e) {
        // TODO add your code here
        LockerBuyForm.setVisible(true);
        LockerBuyForm.setSize(145,180);
        LockerNum.setText("18");
    }

    private void Locker19(ActionEvent e) {
        // TODO add your code here
        LockerBuyForm.setVisible(true);
        LockerBuyForm.setSize(145,180);
        LockerNum.setText("19");
    }

    private void Locker20(ActionEvent e) {
        // TODO add your code here
        LockerBuyForm.setVisible(true);
        LockerBuyForm.setSize(145,180);
        LockerNum.setText("20");
    }


    private void LockerDateItemStateChanged(ItemEvent e) {
        // TODO add your code here
        int index = LockerDate.getSelectedIndex();

        if (index==0) {
            LockerPrice.setText("5000원");
        }
        else if (index==1) {
            LockerPrice.setText("10000원");
        }
        else if (index==2) {
            LockerPrice.setText("15000원");
        }
    }

    private void Enter(ActionEvent e) {
        // TODO add your code here
        UserDAO userDAO = new UserDAO();
        userDAO.enter(1234);
    }

    private void Exit(ActionEvent e) {
        // TODO add your code here
        UserDAO userDAO = new UserDAO();
        userDAO.exit(1234);
    }



    private void TrainerComboItemStateChanged(ItemEvent e) {
        // TODO add your code here
        int index = TrainerCombo.getSelectedIndex();
        if (index==0) {
            PTday.setText("월,수,금");
        }
        else if (index==1) {
            PTday.setText("화,목,일");
        }
        else if (index==2) {
            PTday.setText("월,목,토");
        }
        else if (index==3) {
            PTday.setText("수,금,일");
        }

    }

    private void PTnumComboItemStateChanged(ItemEvent e) {
        // TODO add your code here
        int index = PTnumCombo.getSelectedIndex();
        if (index==0) {
            PTprice.setText("240000 원");
        }
        else if (index==1) {
            PTprice.setText("400000 원");
        }
    }

    private void tabbedPane1StateChanged(ChangeEvent e) {
        // TODO add your code here
        UserDAO userDAO = new UserDAO();
        curuser();
        weekstatistic(WeekCombo.getSelectedIndex());
        drawgraph(WeekCombo.getSelectedIndex());
    }

    private void WeekComboItemStateChanged(ItemEvent e) {
        // TODO add your code here
        UserDAO userDAO = new UserDAO();
        curuser();
        weekstatistic(WeekCombo.getSelectedIndex());
        drawgraph(WeekCombo.getSelectedIndex());
    }

    private void PTbuy(ActionEvent e) {
        UserDAO userDAO = new UserDAO();
        int ptnum;
        if(PTnumCombo.getSelectedIndex()==0)
        {
            ptnum = 10;
        }
        else {
            ptnum = 20;
        }
        userDAO.regPT(1234, TrainerCombo.getSelectedIndex(),ptnum);
        // TODO add your code
    }





    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        MainForm = new JFrame();
        tabbedPane1 = new JTabbedPane();
        panel5 = new JPanel();
        label13 = new JLabel();
        CurUser = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        WeekCombo = new JComboBox<>();
        MonGraph = new JProgressBar();
        TueGraph = new JProgressBar();
        WenGraph = new JProgressBar();
        ThuGraph = new JProgressBar();
        FriGraph = new JProgressBar();
        SatGraph = new JProgressBar();
        label21 = new JLabel();
        label5 = new JLabel();
        panel2 = new JPanel();
        panel9 = new JPanel();
        scrollPane3 = new JScrollPane();
        panel10 = new JPanel();
        panel1 = new JPanel();
        panel7 = new JPanel();
        scrollPane2 = new JScrollPane();
        table2 = new JTable();
        panel8 = new JPanel();
        label2 = new JLabel();
        TrainerCombo = new JComboBox<>();
        label4 = new JLabel();
        PTprice = new JLabel();
        label6 = new JLabel();
        PTnumCombo = new JComboBox<>();
        label7 = new JLabel();
        label8 = new JLabel();
        PTday = new JLabel();
        PTbuyButton = new JButton();
        panel4 = new JPanel();
        Locker2 = new JButton();
        Locker3 = new JButton();
        Locker4 = new JButton();
        Locker5 = new JButton();
        Locker6 = new JButton();
        Locker7 = new JButton();
        Locker11 = new JButton();
        Locker12 = new JButton();
        Locker13 = new JButton();
        Locker14 = new JButton();
        Locker15 = new JButton();
        Locker16 = new JButton();
        Locker17 = new JButton();
        Locker8 = new JButton();
        Locker9 = new JButton();
        Locker10 = new JButton();
        Locker18 = new JButton();
        Locker19 = new JButton();
        Locker20 = new JButton();
        label15 = new JLabel();
        label16 = new JLabel();
        label17 = new JLabel();
        label18 = new JLabel();
        label19 = new JLabel();
        label20 = new JLabel();
        Locker1 = new JButton();
        panel3 = new JPanel();
        panel6 = new JPanel();
        label9 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        label22 = new JLabel();
        label23 = new JLabel();
        label24 = new JLabel();
        label25 = new JLabel();
        EnterButton = new JButton();
        ExitButton = new JButton();
        LockerBuyForm = new JDialog();
        LockerBuy = new JButton();
        label1 = new JLabel();
        LockerNum = new JLabel();
        LockerDate = new JComboBox<>();
        label3 = new JLabel();
        LockerPrice = new JLabel();

        //======== MainForm ========
        {
            MainForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            MainForm.setResizable(false);
            MainForm.setTitle("OPEN SW");
            var MainFormContentPane = MainForm.getContentPane();
            MainFormContentPane.setLayout(null);

            //======== tabbedPane1 ========
            {
                tabbedPane1.setTabPlacement(SwingConstants.LEFT);
                tabbedPane1.addChangeListener(e -> tabbedPane1StateChanged(e));

                //======== panel5 ========
                {
                    panel5.setLayout(null);

                    //---- label13 ----
                    label13.setText("\ud604\uc7ac \uc774\uc6a9\uc790\uc218 : ");
                    panel5.add(label13);
                    label13.setBounds(30, 10, 95, 17);

                    //---- CurUser ----
                    CurUser.setText("32");
                    CurUser.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.BOLD, 12));
                    panel5.add(CurUser);
                    CurUser.setBounds(130, 10, 26, 17);

                    //======== scrollPane1 ========
                    {

                        //---- table1 ----
                        table1.setModel(new DefaultTableModel(
                            new Object[][] {
                                {"   6 : 00 ~ 7 : 00", null},
                                {"   7 : 00 ~ 8 : 00", null},
                                {"   8 : 00 ~ 9 : 00", null},
                                {"   9 : 00 ~ 10 : 00", null},
                                {"  10 : 00 ~ 11 : 00", null},
                                {"  11 : 00 ~ 12 : 00", null},
                                {"  12 : 00 ~ 13 : 00", null},
                                {"  13 : 00 ~ 14 : 00", null},
                                {"  14 : 00 ~ 15 : 00", null},
                                {"  15 : 00 ~ 16 : 00", null},
                                {"  16 : 00 ~ 17 : 00", null},
                                {"  17 : 00 ~ 18 : 00", null},
                                {"  18 : 00 ~ 19 : 00", null},
                                {"  19 : 00 ~ 20 : 00", null},
                                {"  20 : 00 ~ 21 : 00", null},
                            },
                            new String[] {
                                "\uc2dc\uac04", "\uc778\uc6d0"
                            }
                        ) {
                            boolean[] columnEditable = new boolean[] {
                                false, false
                            };
                            @Override
                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return columnEditable[columnIndex];
                            }
                        });
                        {
                            TableColumnModel cm = table1.getColumnModel();
                            cm.getColumn(0).setResizable(false);
                            cm.getColumn(1).setResizable(false);
                        }
                        table1.setShowVerticalLines(true);
                        table1.setShowHorizontalLines(true);
                        table1.setFocusable(false);
                        scrollPane1.setViewportView(table1);
                    }
                    panel5.add(scrollPane1);
                    scrollPane1.setBounds(30, 75, 235, 197);

                    //---- WeekCombo ----
                    WeekCombo.setModel(new DefaultComboBoxModel<>(new String[] {
                        "\uc9c0\ub09c\uc8fc",
                        "2\uc8fc \uc804"
                    }));
                    WeekCombo.addItemListener(e -> WeekComboItemStateChanged(e));
                    panel5.add(WeekCombo);
                    WeekCombo.setBounds(30, 40, 72, 23);

                    //---- MonGraph ----
                    MonGraph.setOrientation(SwingConstants.VERTICAL);
                    MonGraph.setValue(40);
                    MonGraph.setBackground(new Color(0xf2f2f2));
                    panel5.add(MonGraph);
                    MonGraph.setBounds(300, 100, 10, 150);

                    //---- TueGraph ----
                    TueGraph.setOrientation(SwingConstants.VERTICAL);
                    TueGraph.setValue(60);
                    TueGraph.setBackground(new Color(0xf2f2f2));
                    panel5.add(TueGraph);
                    TueGraph.setBounds(325, 100, 10, 150);

                    //---- WenGraph ----
                    WenGraph.setOrientation(SwingConstants.VERTICAL);
                    WenGraph.setValue(55);
                    WenGraph.setBackground(new Color(0xf2f2f2));
                    panel5.add(WenGraph);
                    WenGraph.setBounds(350, 100, 10, 150);

                    //---- ThuGraph ----
                    ThuGraph.setOrientation(SwingConstants.VERTICAL);
                    ThuGraph.setValue(75);
                    ThuGraph.setBackground(new Color(0xf2f2f2));
                    panel5.add(ThuGraph);
                    ThuGraph.setBounds(375, 100, 10, 150);

                    //---- FriGraph ----
                    FriGraph.setOrientation(SwingConstants.VERTICAL);
                    FriGraph.setValue(60);
                    FriGraph.setBackground(new Color(0xf2f2f2));
                    panel5.add(FriGraph);
                    FriGraph.setBounds(400, 100, 10, 150);

                    //---- SatGraph ----
                    SatGraph.setOrientation(SwingConstants.VERTICAL);
                    SatGraph.setValue(80);
                    SatGraph.setBackground(new Color(0xf2f2f2));
                    panel5.add(SatGraph);
                    SatGraph.setBounds(425, 100, 10, 150);

                    //---- label21 ----
                    label21.setText(" \uc6d4   \ud654    \uc218   \ubaa9   \uae08   \ud1a0    ");
                    panel5.add(label21);
                    label21.setBounds(295, 255, 168, 17);

                    //---- label5 ----
                    label5.setText("\uba85");
                    label5.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.BOLD, 12));
                    panel5.add(label5);
                    label5.setBounds(150, 10, 20, label5.getPreferredSize().height);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel5.getComponentCount(); i++) {
                            Rectangle bounds = panel5.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel5.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel5.setMinimumSize(preferredSize);
                        panel5.setPreferredSize(preferredSize);
                    }
                }
                tabbedPane1.addTab("\ud68c\uc6d0 \uc218 \ud655\uc778", panel5);

                //======== panel2 ========
                {
                    panel2.setLayout(null);

                    //======== panel9 ========
                    {
                        panel9.setBorder(new TitledBorder("\uac00\uaca9"));
                        panel9.setLayout(null);
                        panel9.add(scrollPane3);
                        scrollPane3.setBounds(15, 30, 405, 105);
                    }
                    panel2.add(panel9);
                    panel9.setBounds(20, 15, 435, 150);

                    //======== panel10 ========
                    {
                        panel10.setBorder(new TitledBorder("\uacb0\uc81c"));
                        panel10.setLayout(null);
                    }
                    panel2.add(panel10);
                    panel10.setBounds(20, 175, 435, 100);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel2.getComponentCount(); i++) {
                            Rectangle bounds = panel2.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel2.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel2.setMinimumSize(preferredSize);
                        panel2.setPreferredSize(preferredSize);
                    }
                }
                tabbedPane1.addTab("\ud68c\uc6d0\uad8c \uad6c\ub9e4", panel2);

                //======== panel1 ========
                {
                    panel1.setLayout(null);

                    //======== panel7 ========
                    {
                        panel7.setBorder(new TitledBorder("\uac15\uc0ac\ubaa9\ub85d"));
                        panel7.setLayout(null);

                        //======== scrollPane2 ========
                        {

                            //---- table2 ----
                            table2.setModel(new DefaultTableModel(
                                new Object[][] {
                                    {"                    \uc720OO", "                  \uc6d4,\uc218,\uae08"},
                                    {"                    \uae40OO", "                  \ud654,\ubaa9,\uc77c"},
                                    {"                    \ud55cOO", "                  \uc6d4,\ubaa9,\ud1a0"},
                                    {"                    \ubc15OO", "                  \uc218,\uae08,\uc77c"},
                                },
                                new String[] {
                                    "\ud2b8\ub808\uc774\ub108", "PT \uc2dc\uac04"
                                }
                            ) {
                                boolean[] columnEditable = new boolean[] {
                                    false, false
                                };
                                @Override
                                public boolean isCellEditable(int rowIndex, int columnIndex) {
                                    return columnEditable[columnIndex];
                                }
                            });
                            {
                                TableColumnModel cm = table2.getColumnModel();
                                cm.getColumn(0).setResizable(false);
                                cm.getColumn(1).setResizable(false);
                            }
                            table2.setShowHorizontalLines(true);
                            table2.setShowVerticalLines(true);
                            scrollPane2.setViewportView(table2);
                        }
                        panel7.add(scrollPane2);
                        scrollPane2.setBounds(15, 30, 405, 105);
                    }
                    panel1.add(panel7);
                    panel7.setBounds(20, 15, 435, 150);

                    //======== panel8 ========
                    {
                        panel8.setBorder(new TitledBorder("\uacb0\uc81c"));
                        panel8.setLayout(null);

                        //---- label2 ----
                        label2.setText("\ud2b8\ub808\uc774\ub108");
                        panel8.add(label2);
                        label2.setBounds(new Rectangle(new Point(30, 35), label2.getPreferredSize()));

                        //---- TrainerCombo ----
                        TrainerCombo.setModel(new DefaultComboBoxModel<>(new String[] {
                            "\uc720OO",
                            "\uae40OO",
                            "\ud55cOO",
                            "\ubc15OO"
                        }));
                        TrainerCombo.addItemListener(e -> TrainerComboItemStateChanged(e));
                        panel8.add(TrainerCombo);
                        TrainerCombo.setBounds(85, 30, 85, TrainerCombo.getPreferredSize().height);

                        //---- label4 ----
                        label4.setText("\uc694\uae08 : ");
                        panel8.add(label4);
                        label4.setBounds(new Rectangle(new Point(190, 65), label4.getPreferredSize()));

                        //---- PTprice ----
                        PTprice.setText("300000 \uc6d0");
                        panel8.add(PTprice);
                        PTprice.setBounds(new Rectangle(new Point(235, 65), PTprice.getPreferredSize()));

                        //---- label6 ----
                        label6.setText("\ud69f\uc218");
                        panel8.add(label6);
                        label6.setBounds(new Rectangle(new Point(40, 65), label6.getPreferredSize()));

                        //---- PTnumCombo ----
                        PTnumCombo.setModel(new DefaultComboBoxModel<>(new String[] {
                            "10\ud68c",
                            "20\ud68c"
                        }));
                        PTnumCombo.setSelectedIndex(0);
                        PTnumCombo.addItemListener(e -> PTnumComboItemStateChanged(e));
                        panel8.add(PTnumCombo);
                        PTnumCombo.setBounds(85, 60, 85, PTnumCombo.getPreferredSize().height);
                        panel8.add(label7);
                        label7.setBounds(new Rectangle(new Point(185, 35), label7.getPreferredSize()));

                        //---- label8 ----
                        label8.setText("PT \uc2dc\uac04 : ");
                        panel8.add(label8);
                        label8.setBounds(190, 35, label8.getPreferredSize().width, 17);

                        //---- PTday ----
                        PTday.setText("\uc6d4,\uc218,\uae08");
                        panel8.add(PTday);
                        PTday.setBounds(new Rectangle(new Point(250, 35), PTday.getPreferredSize()));

                        //---- PTbuyButton ----
                        PTbuyButton.setText("\uacb0\uc81c");
                        PTbuyButton.addActionListener(e -> PTbuy(e));
                        panel8.add(PTbuyButton);
                        PTbuyButton.setBounds(315, 35, 105, 45);
                    }
                    panel1.add(panel8);
                    panel8.setBounds(20, 175, 435, 100);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel1.getComponentCount(); i++) {
                            Rectangle bounds = panel1.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel1.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel1.setMinimumSize(preferredSize);
                        panel1.setPreferredSize(preferredSize);
                    }
                }
                tabbedPane1.addTab("PT\uad8c \uad6c\ub9e4", panel1);

                //======== panel4 ========
                {
                    panel4.setLayout(null);

                    //---- Locker2 ----
                    Locker2.setText("2.");
                    Locker2.setBackground(new Color(0x6699ff));
                    Locker2.setFocusable(false);
                    Locker2.addActionListener(e -> Locker2(e));
                    panel4.add(Locker2);
                    Locker2.setBounds(110, 25, 75, 35);

                    //---- Locker3 ----
                    Locker3.setText("3.");
                    Locker3.setBackground(new Color(0xcccccc));
                    Locker3.setFocusable(false);
                    Locker3.addActionListener(e -> Locker3(e));
                    panel4.add(Locker3);
                    Locker3.setBounds(200, 25, 75, 35);

                    //---- Locker4 ----
                    Locker4.setText("4.");
                    Locker4.setBackground(new Color(0x6699ff));
                    Locker4.setFocusable(false);
                    Locker4.setMnemonic('4');
                    Locker4.addActionListener(e -> Locker4(e));
                    panel4.add(Locker4);
                    Locker4.setBounds(285, 25, 75, 35);

                    //---- Locker5 ----
                    Locker5.setText("5.");
                    Locker5.setBackground(new Color(0x6699ff));
                    Locker5.setFocusable(false);
                    Locker5.addActionListener(e -> Locker5(e));
                    panel4.add(Locker5);
                    Locker5.setBounds(370, 25, 75, 35);

                    //---- Locker6 ----
                    Locker6.setText("6.");
                    Locker6.setBackground(new Color(0xcccccc));
                    Locker6.setFocusable(false);
                    Locker6.addActionListener(e -> Locker6(e));
                    panel4.add(Locker6);
                    Locker6.setBounds(20, 75, 75, 35);

                    //---- Locker7 ----
                    Locker7.setText("7.");
                    Locker7.setBackground(new Color(0xcccccc));
                    Locker7.setFocusable(false);
                    Locker7.addActionListener(e -> Locker7(e));
                    panel4.add(Locker7);
                    Locker7.setBounds(110, 75, 75, 35);

                    //---- Locker11 ----
                    Locker11.setText("11.");
                    Locker11.setBackground(new Color(0xcccccc));
                    Locker11.setFocusable(false);
                    Locker11.addActionListener(e -> Locker11(e));
                    panel4.add(Locker11);
                    Locker11.setBounds(20, 125, 75, 35);

                    //---- Locker12 ----
                    Locker12.setText("12.");
                    Locker12.setBackground(new Color(0xcccccc));
                    Locker12.setFocusable(false);
                    Locker12.addActionListener(e -> Locker12(e));
                    panel4.add(Locker12);
                    Locker12.setBounds(110, 125, 75, 35);

                    //---- Locker13 ----
                    Locker13.setText("13.");
                    Locker13.setBackground(new Color(0x6699ff));
                    Locker13.setFocusable(false);
                    Locker13.addActionListener(e -> Locker13(e));
                    panel4.add(Locker13);
                    Locker13.setBounds(200, 125, 75, 35);

                    //---- Locker14 ----
                    Locker14.setText("14.");
                    Locker14.setBackground(new Color(0x6699ff));
                    Locker14.setFocusable(false);
                    Locker14.addActionListener(e -> Locker14(e));
                    panel4.add(Locker14);
                    Locker14.setBounds(285, 125, 75, 35);

                    //---- Locker15 ----
                    Locker15.setText("15.");
                    Locker15.setBackground(new Color(0xff9999));
                    Locker15.setFocusable(false);
                    Locker15.addActionListener(e -> Locker15(e));
                    panel4.add(Locker15);
                    Locker15.setBounds(370, 125, 75, 35);

                    //---- Locker16 ----
                    Locker16.setText("16.");
                    Locker16.setBackground(new Color(0x6699ff));
                    Locker16.setFocusable(false);
                    Locker16.addActionListener(e -> Locker16(e));
                    panel4.add(Locker16);
                    Locker16.setBounds(20, 175, 75, 35);

                    //---- Locker17 ----
                    Locker17.setText("17.");
                    Locker17.setBackground(new Color(0x6699ff));
                    Locker17.setFocusable(false);
                    Locker17.addActionListener(e -> Locker17(e));
                    panel4.add(Locker17);
                    Locker17.setBounds(110, 175, 75, 35);

                    //---- Locker8 ----
                    Locker8.setText("8.");
                    Locker8.setBackground(new Color(0xcccccc));
                    Locker8.setFocusable(false);
                    Locker8.addActionListener(e -> Locker8(e));
                    panel4.add(Locker8);
                    Locker8.setBounds(200, 75, 75, 35);

                    //---- Locker9 ----
                    Locker9.setText("9.");
                    Locker9.setBackground(new Color(0x6699ff));
                    Locker9.setFocusable(false);
                    Locker9.addActionListener(e -> Locker9(e));
                    panel4.add(Locker9);
                    Locker9.setBounds(285, 75, 75, 35);

                    //---- Locker10 ----
                    Locker10.setText("10.");
                    Locker10.setBackground(new Color(0xcccccc));
                    Locker10.setFocusable(false);
                    Locker10.addActionListener(e -> Locker10(e));
                    panel4.add(Locker10);
                    Locker10.setBounds(370, 75, 75, 35);

                    //---- Locker18 ----
                    Locker18.setText("18.");
                    Locker18.setBackground(new Color(0xcccccc));
                    Locker18.setFocusable(false);
                    Locker18.addActionListener(e -> Locker18(e));
                    panel4.add(Locker18);
                    Locker18.setBounds(200, 175, 75, 35);

                    //---- Locker19 ----
                    Locker19.setText("19.");
                    Locker19.setBackground(new Color(0xcccccc));
                    Locker19.setFocusable(false);
                    Locker19.addActionListener(e -> Locker19(e));
                    panel4.add(Locker19);
                    Locker19.setBounds(285, 175, 75, 35);

                    //---- Locker20 ----
                    Locker20.setText("20.");
                    Locker20.setBackground(new Color(0xcccccc));
                    Locker20.setFocusable(false);
                    Locker20.addActionListener(e -> Locker20(e));
                    panel4.add(Locker20);
                    Locker20.setBounds(370, 175, 75, 35);

                    //---- label15 ----
                    label15.setText("   ");
                    label15.setBackground(new Color(0x6699ff));
                    label15.setOpaque(true);
                    panel4.add(label15);
                    label15.setBounds(225, 240, 15, 15);

                    //---- label16 ----
                    label16.setText("\uc0ac\uc6a9 \uac00\ub2a5");
                    panel4.add(label16);
                    label16.setBounds(new Rectangle(new Point(250, 240), label16.getPreferredSize()));

                    //---- label17 ----
                    label17.setText("   ");
                    label17.setBackground(new Color(0xcccccc));
                    label17.setOpaque(true);
                    panel4.add(label17);
                    label17.setBounds(310, 240, 15, 15);

                    //---- label18 ----
                    label18.setText("\uc774\uc6a9\uc911");
                    panel4.add(label18);
                    label18.setBounds(335, 240, 52, 17);

                    //---- label19 ----
                    label19.setText("   ");
                    label19.setBackground(new Color(0xff9999));
                    label19.setOpaque(true);
                    panel4.add(label19);
                    label19.setBounds(385, 240, 15, 15);

                    //---- label20 ----
                    label20.setText("\uace0\uc7a5");
                    panel4.add(label20);
                    label20.setBounds(410, 240, 52, 17);

                    //---- Locker1 ----
                    Locker1.setText("1.");
                    Locker1.setBackground(new Color(0x6699ff));
                    Locker1.addActionListener(e -> Locker1(e));
                    panel4.add(Locker1);
                    Locker1.setBounds(20, 25, 75, 35);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel4.getComponentCount(); i++) {
                            Rectangle bounds = panel4.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel4.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel4.setMinimumSize(preferredSize);
                        panel4.setPreferredSize(preferredSize);
                    }
                }
                tabbedPane1.addTab("\uac1c\uc778\ub77d\ucee4", panel4);

                //======== panel3 ========
                {
                    panel3.setLayout(null);

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel3.getComponentCount(); i++) {
                            Rectangle bounds = panel3.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel3.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel3.setMinimumSize(preferredSize);
                        panel3.setPreferredSize(preferredSize);
                    }
                }
                tabbedPane1.addTab("\uc2a4\ucf00\uc904 \uad00\ub9ac", panel3);

                //======== panel6 ========
                {
                    panel6.setLayout(null);

                    //---- label9 ----
                    label9.setText("\uc774\ub984 : ");
                    panel6.add(label9);
                    label9.setBounds(20, 25, 34, 17);

                    //---- label11 ----
                    label11.setText("\ud68c\uc6d0\uad8c : ");
                    panel6.add(label11);
                    label11.setBounds(20, 55, 46, 17);

                    //---- label12 ----
                    label12.setText("\ub0a8\uc740 \uae30\uac04 : ");
                    panel6.add(label12);
                    label12.setBounds(20, 115, 62, 17);

                    //---- label22 ----
                    label22.setText("\uac1c\uc778 \ub77d\ucee4 \ubc88\ud638 : ");
                    panel6.add(label22);
                    label22.setBounds(20, 145, 90, 17);

                    //---- label23 ----
                    label23.setText("PT \ud2b8\ub808\uc774\ub108 : ");
                    panel6.add(label23);
                    label23.setBounds(20, 175, 76, 17);

                    //---- label24 ----
                    label24.setText("PT \ub0a8\uc740 \ud69f\uc218 : ");
                    panel6.add(label24);
                    label24.setBounds(20, 205, 80, 17);

                    //---- label25 ----
                    label25.setText("\ud68c\uc6d0\uad8c \uac00\uaca9 : ");
                    panel6.add(label25);
                    label25.setBounds(new Rectangle(new Point(20, 85), label25.getPreferredSize()));

                    //---- EnterButton ----
                    EnterButton.setText("\uc785\uc7a5");
                    EnterButton.addActionListener(e -> Enter(e));
                    panel6.add(EnterButton);
                    EnterButton.setBounds(new Rectangle(new Point(225, 50), EnterButton.getPreferredSize()));

                    //---- ExitButton ----
                    ExitButton.setText("\ud1f4\uc7a5");
                    ExitButton.addActionListener(e -> Exit(e));
                    panel6.add(ExitButton);
                    ExitButton.setBounds(new Rectangle(new Point(310, 50), ExitButton.getPreferredSize()));

                    {
                        // compute preferred size
                        Dimension preferredSize = new Dimension();
                        for(int i = 0; i < panel6.getComponentCount(); i++) {
                            Rectangle bounds = panel6.getComponent(i).getBounds();
                            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                        }
                        Insets insets = panel6.getInsets();
                        preferredSize.width += insets.right;
                        preferredSize.height += insets.bottom;
                        panel6.setMinimumSize(preferredSize);
                        panel6.setPreferredSize(preferredSize);
                    }
                }
                tabbedPane1.addTab("\ud68c\uc6d0\uc815\ubcf4", panel6);
            }
            MainFormContentPane.add(tabbedPane1);
            tabbedPane1.setBounds(0, 0, 560, 285);

            MainFormContentPane.setPreferredSize(new Dimension(565, 315));
            MainForm.pack();
            MainForm.setLocationRelativeTo(MainForm.getOwner());
        }

        //======== LockerBuyForm ========
        {
            var LockerBuyFormContentPane = LockerBuyForm.getContentPane();
            LockerBuyFormContentPane.setLayout(null);

            //---- LockerBuy ----
            LockerBuy.setText("\uacb0\uc81c");
            LockerBuy.addActionListener(e -> LockerBuy(e));
            LockerBuyFormContentPane.add(LockerBuy);
            LockerBuy.setBounds(30, 100, 90, 35);

            //---- label1 ----
            label1.setText("\ubc88 \uac1c\uc778\ub77d\ucee4");
            LockerBuyFormContentPane.add(label1);
            label1.setBounds(50, 15, 90, label1.getPreferredSize().height);

            //---- LockerNum ----
            LockerNum.setText("1");
            LockerBuyFormContentPane.add(LockerNum);
            LockerNum.setBounds(35, 15, 20, LockerNum.getPreferredSize().height);

            //---- LockerDate ----
            LockerDate.setModel(new DefaultComboBoxModel<>(new String[] {
                "1\uac1c\uc6d4",
                "2\uac1c\uc6d4",
                "3\uac1c\uc6d4"
            }));
            LockerDate.addItemListener(e -> LockerDateItemStateChanged(e));
            LockerBuyFormContentPane.add(LockerDate);
            LockerDate.setBounds(35, 70, 80, LockerDate.getPreferredSize().height);

            //---- label3 ----
            label3.setText("\uc694\uae08 : ");
            LockerBuyFormContentPane.add(label3);
            label3.setBounds(new Rectangle(new Point(30, 40), label3.getPreferredSize()));

            //---- LockerPrice ----
            LockerPrice.setText("5000 \uc6d0  ");
            LockerBuyFormContentPane.add(LockerPrice);
            LockerPrice.setBounds(70, 40, 50, LockerPrice.getPreferredSize().height);

            LockerBuyFormContentPane.setPreferredSize(new Dimension(145, 175));
            LockerBuyForm.pack();
            LockerBuyForm.setLocationRelativeTo(LockerBuyForm.getOwner());
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JFrame MainForm;
    private JTabbedPane tabbedPane1;
    private JPanel panel5;
    private JLabel label13;
    public JLabel CurUser;
    private JScrollPane scrollPane1;
    public JTable table1;
    private JComboBox<String> WeekCombo;
    public JProgressBar MonGraph;
    public JProgressBar TueGraph;
    public JProgressBar WenGraph;
    public JProgressBar ThuGraph;
    public JProgressBar FriGraph;
    public JProgressBar SatGraph;
    private JLabel label21;
    private JLabel label5;
    private JPanel panel2;
    private JPanel panel9;
    private JScrollPane scrollPane3;
    private JPanel panel10;
    private JPanel panel1;
    private JPanel panel7;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JPanel panel8;
    private JLabel label2;
    private JComboBox<String> TrainerCombo;
    private JLabel label4;
    private JLabel PTprice;
    private JLabel label6;
    private JComboBox<String> PTnumCombo;
    private JLabel label7;
    private JLabel label8;
    private JLabel PTday;
    private JButton PTbuyButton;
    private JPanel panel4;
    public JButton Locker2;
    public JButton Locker3;
    public JButton Locker4;
    public JButton Locker5;
    public JButton Locker6;
    public JButton Locker7;
    public JButton Locker11;
    public JButton Locker12;
    public JButton Locker13;
    public JButton Locker14;
    public JButton Locker15;
    public JButton Locker16;
    public JButton Locker17;
    public JButton Locker8;
    public JButton Locker9;
    public JButton Locker10;
    public JButton Locker18;
    public JButton Locker19;
    public JButton Locker20;
    private JLabel label15;
    private JLabel label16;
    private JLabel label17;
    private JLabel label18;
    private JLabel label19;
    private JLabel label20;
    public JButton Locker1;
    private JPanel panel3;
    private JPanel panel6;
    private JLabel label9;
    private JLabel label11;
    private JLabel label12;
    private JLabel label22;
    private JLabel label23;
    private JLabel label24;
    private JLabel label25;
    private JButton EnterButton;
    private JButton ExitButton;
    private JDialog LockerBuyForm;
    private JButton LockerBuy;
    private JLabel label1;
    private JLabel LockerNum;
    private JComboBox<String> LockerDate;
    private JLabel label3;
    private JLabel LockerPrice;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}