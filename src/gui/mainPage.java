/*
 * Created by JFormDesigner on Fri Nov 18 20:23:17 KST 2022
 */

package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import gui.loginPage;
/**
 * @author Minjae
 */
public class mainPage extends JFrame {
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
    private void LockerBuy(ActionEvent e) {
        // TODO add your code here

        LockerBuyForm.setVisible(false);

    }
    private void Locker1(ActionEvent e) {
        // TODO add your code here
        LockerBuyForm.setVisible(true);
        LockerBuyForm.setSize(145,180);
        LockerNum.setText("1");
        
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

    private void LockerDateItemStateChanged(ItemEvent e) {
        // TODO add your code here
        int index = LockerDate.getSelectedIndex();

        if (index==0) {
            LockerPrice.setText("5000원");
        }
        else if (index==1) {
            LockerPrice.setText("8000원");
        }
        else if (index==2) {
            LockerPrice.setText("10000원");
        }
    }

    
    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Minjae
        MainForm = new JFrame();
        tabbedPane1 = new JTabbedPane();
        panel5 = new JPanel();
        label13 = new JLabel();
        NowPerson = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        WeekCombo = new JComboBox<>();
        DayCombo = new JComboBox<>();
        MonGraph = new JProgressBar();
        TueGraph = new JProgressBar();
        WenGraph = new JProgressBar();
        ThuGraph = new JProgressBar();
        FriGraph = new JProgressBar();
        SatGraph = new JProgressBar();
        SunGraph = new JProgressBar();
        label21 = new JLabel();
        panel2 = new JPanel();
        panel1 = new JPanel();
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
        LockerBuyForm = new JDialog();
        LockerBuy = new JButton();
        label1 = new JLabel();
        LockerNum = new JLabel();
        LockerDate = new JComboBox<>();
        label3 = new JLabel();
        LockerPrice = new JLabel();

        //======== MainForm ========
        {
            MainForm.setTitle("OPEN SW");
            MainForm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            MainForm.setResizable(false);
            var MainFormContentPane = MainForm.getContentPane();
            MainFormContentPane.setLayout(null);

            //======== tabbedPane1 ========
            {
                tabbedPane1.setTabPlacement(SwingConstants.LEFT);

                //======== panel5 ========
                {
                    panel5.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing
                    . border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder
                    . CENTER, javax. swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .
                    awt .Font .BOLD ,12 ), java. awt. Color. red) ,panel5. getBorder( )) )
                    ; panel5. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e
                    ) {if ("\u0062order" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} )
                    ;
                    panel5.setLayout(null);

                    //---- label13 ----
                    label13.setText("\ud604\uc7ac \uc774\uc6a9\uc790\uc218 : ");
                    panel5.add(label13);
                    label13.setBounds(20, 10, 86, 17);

                    //---- NowPerson ----
                    NowPerson.setText("32\uba85");
                    NowPerson.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.BOLD, 12));
                    panel5.add(NowPerson);
                    NowPerson.setBounds(110, 10, 26, 17);

                    //======== scrollPane1 ========
                    {

                        //---- table1 ----
                        table1.setModel(new DefaultTableModel(
                            new Object[][] {
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
                            },
                            new String[] {
                                "\uc2dc\uac04", "\ud3c9\uade0 \uc778\uc6d0"
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
                    scrollPane1.setBounds(20, 75, 235, 197);

                    //---- WeekCombo ----
                    WeekCombo.setModel(new DefaultComboBoxModel<>(new String[] {
                        "\uc9c0\ub09c\uc8fc",
                        "2\uc8fc \uc804"
                    }));
                    panel5.add(WeekCombo);
                    WeekCombo.setBounds(20, 40, 72, 23);

                    //---- DayCombo ----
                    DayCombo.setModel(new DefaultComboBoxModel<>(new String[] {
                        "\uc6d4",
                        "\ud654",
                        "\uc218",
                        "\ubaa9",
                        "\uae08",
                        "\ud1a0",
                        "\uc77c"
                    }));
                    panel5.add(DayCombo);
                    DayCombo.setBounds(100, 40, 72, 23);

                    //---- MonGraph ----
                    MonGraph.setOrientation(SwingConstants.VERTICAL);
                    MonGraph.setValue(40);
                    MonGraph.setBackground(new Color(0xf2f2f2));
                    panel5.add(MonGraph);
                    MonGraph.setBounds(285, 115, 10, 135);

                    //---- TueGraph ----
                    TueGraph.setOrientation(SwingConstants.VERTICAL);
                    TueGraph.setValue(60);
                    TueGraph.setBackground(new Color(0xf2f2f2));
                    panel5.add(TueGraph);
                    TueGraph.setBounds(310, 115, 10, 135);

                    //---- WenGraph ----
                    WenGraph.setOrientation(SwingConstants.VERTICAL);
                    WenGraph.setValue(55);
                    WenGraph.setBackground(new Color(0xf2f2f2));
                    panel5.add(WenGraph);
                    WenGraph.setBounds(335, 115, 10, 135);

                    //---- ThuGraph ----
                    ThuGraph.setOrientation(SwingConstants.VERTICAL);
                    ThuGraph.setValue(75);
                    ThuGraph.setBackground(new Color(0xf2f2f2));
                    panel5.add(ThuGraph);
                    ThuGraph.setBounds(360, 115, 10, 135);

                    //---- FriGraph ----
                    FriGraph.setOrientation(SwingConstants.VERTICAL);
                    FriGraph.setValue(60);
                    FriGraph.setBackground(new Color(0xf2f2f2));
                    panel5.add(FriGraph);
                    FriGraph.setBounds(385, 115, 10, 135);

                    //---- SatGraph ----
                    SatGraph.setOrientation(SwingConstants.VERTICAL);
                    SatGraph.setValue(80);
                    SatGraph.setBackground(new Color(0xf2f2f2));
                    panel5.add(SatGraph);
                    SatGraph.setBounds(410, 115, 10, 135);

                    //---- SunGraph ----
                    SunGraph.setOrientation(SwingConstants.VERTICAL);
                    SunGraph.setValue(100);
                    SunGraph.setBackground(new Color(0xf2f2f2));
                    panel5.add(SunGraph);
                    SunGraph.setBounds(435, 115, 10, 135);

                    //---- label21 ----
                    label21.setText(" \uc6d4   \ud654    \uc218   \ubaa9   \uae08   \ud1a0   \uc77c ");
                    panel5.add(label21);
                    label21.setBounds(280, 255, 168, 17);

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
                    panel4.add(Locker7);
                    Locker7.setBounds(110, 75, 75, 35);

                    //---- Locker11 ----
                    Locker11.setText("11.");
                    Locker11.setBackground(new Color(0xcccccc));
                    Locker11.setFocusable(false);
                    panel4.add(Locker11);
                    Locker11.setBounds(20, 125, 75, 35);

                    //---- Locker12 ----
                    Locker12.setText("12.");
                    Locker12.setBackground(new Color(0xcccccc));
                    Locker12.setFocusable(false);
                    panel4.add(Locker12);
                    Locker12.setBounds(110, 125, 75, 35);

                    //---- Locker13 ----
                    Locker13.setText("13.");
                    Locker13.setBackground(new Color(0x6699ff));
                    Locker13.setFocusable(false);
                    panel4.add(Locker13);
                    Locker13.setBounds(200, 125, 75, 35);

                    //---- Locker14 ----
                    Locker14.setText("14.");
                    Locker14.setBackground(new Color(0x6699ff));
                    Locker14.setFocusable(false);
                    panel4.add(Locker14);
                    Locker14.setBounds(285, 125, 75, 35);

                    //---- Locker15 ----
                    Locker15.setText("15.");
                    Locker15.setBackground(new Color(0xff9999));
                    Locker15.setFocusable(false);
                    panel4.add(Locker15);
                    Locker15.setBounds(370, 125, 75, 35);

                    //---- Locker16 ----
                    Locker16.setText("16.");
                    Locker16.setBackground(new Color(0x6699ff));
                    Locker16.setFocusable(false);
                    panel4.add(Locker16);
                    Locker16.setBounds(20, 175, 75, 35);

                    //---- Locker17 ----
                    Locker17.setText("17.");
                    Locker17.setBackground(new Color(0x6699ff));
                    Locker17.setFocusable(false);
                    panel4.add(Locker17);
                    Locker17.setBounds(110, 175, 75, 35);

                    //---- Locker8 ----
                    Locker8.setText("8.");
                    Locker8.setBackground(new Color(0xcccccc));
                    Locker8.setFocusable(false);
                    panel4.add(Locker8);
                    Locker8.setBounds(200, 75, 75, 35);

                    //---- Locker9 ----
                    Locker9.setText("9.");
                    Locker9.setBackground(new Color(0x6699ff));
                    Locker9.setFocusable(false);
                    panel4.add(Locker9);
                    Locker9.setBounds(285, 75, 75, 35);

                    //---- Locker10 ----
                    Locker10.setText("10.");
                    Locker10.setBackground(new Color(0xcccccc));
                    Locker10.setFocusable(false);
                    panel4.add(Locker10);
                    Locker10.setBounds(370, 75, 75, 35);

                    //---- Locker18 ----
                    Locker18.setText("18.");
                    Locker18.setBackground(new Color(0xcccccc));
                    Locker18.setFocusable(false);
                    panel4.add(Locker18);
                    Locker18.setBounds(200, 175, 75, 35);

                    //---- Locker19 ----
                    Locker19.setText("19.");
                    Locker19.setBackground(new Color(0xcccccc));
                    Locker19.setFocusable(false);
                    panel4.add(Locker19);
                    Locker19.setBounds(285, 175, 75, 35);

                    //---- Locker20 ----
                    Locker20.setText("20.");
                    Locker20.setBackground(new Color(0xcccccc));
                    Locker20.setFocusable(false);
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
            tabbedPane1.setBounds(0, -5, 560, 285);

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
            LockerNum.setBounds(new Rectangle(new Point(35, 15), LockerNum.getPreferredSize()));

            //---- LockerDate ----
            LockerDate.setModel(new DefaultComboBoxModel<>(new String[] {
                "1\uac1c\uc6d4",
                "6\uac1c\uc6d4",
                "1\ub144"
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
    // Generated using JFormDesigner Evaluation license - Minjae
    private JFrame MainForm;
    private JTabbedPane tabbedPane1;
    private JPanel panel5;
    private JLabel label13;
    private JLabel NowPerson;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JComboBox<String> WeekCombo;
    private JComboBox<String> DayCombo;
    private JProgressBar MonGraph;
    private JProgressBar TueGraph;
    private JProgressBar WenGraph;
    private JProgressBar ThuGraph;
    private JProgressBar FriGraph;
    private JProgressBar SatGraph;
    private JProgressBar SunGraph;
    private JLabel label21;
    private JPanel panel2;
    private JPanel panel1;
    private JPanel panel4;
    private JButton Locker2;
    private JButton Locker3;
    private JButton Locker4;
    private JButton Locker5;
    private JButton Locker6;
    private JButton Locker7;
    private JButton Locker11;
    private JButton Locker12;
    private JButton Locker13;
    private JButton Locker14;
    private JButton Locker15;
    private JButton Locker16;
    private JButton Locker17;
    private JButton Locker8;
    private JButton Locker9;
    private JButton Locker10;
    private JButton Locker18;
    private JButton Locker19;
    private JButton Locker20;
    private JLabel label15;
    private JLabel label16;
    private JLabel label17;
    private JLabel label18;
    private JLabel label19;
    private JLabel label20;
    private JButton Locker1;
    private JPanel panel3;
    private JPanel panel6;
    private JLabel label9;
    private JLabel label11;
    private JLabel label12;
    private JLabel label22;
    private JLabel label23;
    private JLabel label24;
    private JLabel label25;
    private JDialog LockerBuyForm;
    private JButton LockerBuy;
    private JLabel label1;
    private JLabel LockerNum;
    private JComboBox<String> LockerDate;
    private JLabel label3;
    private JLabel LockerPrice;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
