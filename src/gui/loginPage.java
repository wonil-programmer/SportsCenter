package gui;

import java.awt.event.*;
import javax.swing.table.*;
import java.awt.*;
import javax.swing.*;
//import com.intellij.ide.ui.laf.*;
/*
 * Created by JFormDesigner on Wed Nov 16 09:47:05 KST 2022
 */



/**
 * @author Minjae
 */
public class loginPage extends JFrame {
    public loginPage() {
        initComponents();
    }

    private void Reg(ActionEvent e) {
        // TODO add your code here
        this2.setVisible(true);
        this2.setSize(300,280);

    }

    private void Login(ActionEvent e) {
        // TODO add your code here
        MainForm.setVisible(true);
        MainForm.setSize(585,330);
        this.setVisible(false);
        
    }

    private void button1(ActionEvent e) {
        // TODO add your code here
        this2.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - Minjae
        label1 = new JLabel();
        label2 = new JLabel();
        PWtext = new JPasswordField();
        IDtext = new JTextField();
        LoginButton = new JButton();
        RememberCheck = new JCheckBox();
        RegButton = new JButton();
        this2 = new JFrame();
        label3 = new JLabel();
        label4 = new JLabel();
        textField1 = new JTextField();
        label5 = new JLabel();
        radioButton1 = new JRadioButton();
        radioButton2 = new JRadioButton();
        label6 = new JLabel();
        label7 = new JLabel();
        comboBox1 = new JComboBox<>();
        label8 = new JLabel();
        checkBox1 = new JCheckBox();
        button1 = new JButton();
        passwordField1 = new JPasswordField();
        passwordField2 = new JPasswordField();
        MainForm = new JFrame();
        tabbedPane1 = new JTabbedPane();
        panel5 = new JPanel();
        label13 = new JLabel();
        label14 = new JLabel();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        comboBox2 = new JComboBox<>();
        comboBox3 = new JComboBox<>();
        progressBar1 = new JProgressBar();
        progressBar2 = new JProgressBar();
        progressBar3 = new JProgressBar();
        progressBar4 = new JProgressBar();
        progressBar5 = new JProgressBar();
        progressBar6 = new JProgressBar();
        progressBar7 = new JProgressBar();
        label21 = new JLabel();
        panel2 = new JPanel();
        panel4 = new JPanel();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        button7 = new JButton();
        button8 = new JButton();
        button9 = new JButton();
        button10 = new JButton();
        button11 = new JButton();
        button12 = new JButton();
        button13 = new JButton();
        button14 = new JButton();
        button15 = new JButton();
        button16 = new JButton();
        button17 = new JButton();
        button18 = new JButton();
        button19 = new JButton();
        button20 = new JButton();
        button21 = new JButton();
        label15 = new JLabel();
        label16 = new JLabel();
        label17 = new JLabel();
        label18 = new JLabel();
        label19 = new JLabel();
        label20 = new JLabel();
        panel6 = new JPanel();
        label9 = new JLabel();
        label11 = new JLabel();
        label12 = new JLabel();
        label22 = new JLabel();
        label23 = new JLabel();
        label24 = new JLabel();
        label25 = new JLabel();

        //======== this ========
        setTitle("LOGIN");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        var contentPane = getContentPane();
        contentPane.setLayout(null);

        //---- label1 ----
        label1.setText("ID");
        contentPane.add(label1);
        label1.setBounds(15, 15, 20, 17);

        //---- label2 ----
        label2.setText("PW");
        contentPane.add(label2);
        label2.setBounds(10, 45, 18, 20);
        contentPane.add(PWtext);
        PWtext.setBounds(35, 45, 195, 23);
        contentPane.add(IDtext);
        IDtext.setBounds(35, 15, 195, 23);

        //---- LoginButton ----
        LoginButton.setText("LOGIN");
        LoginButton.addActionListener(e -> Login(e));
        contentPane.add(LoginButton);
        LoginButton.setBounds(240, 15, 150, 55);

        //---- RememberCheck ----
        RememberCheck.setText("Remember me");
        RememberCheck.setSelected(true);
        contentPane.add(RememberCheck);
        RememberCheck.setBounds(10, 75, 110, 21);

        //---- RegButton ----
        RegButton.setText("REGISTER");
        RegButton.addActionListener(e -> Reg(e));
        contentPane.add(RegButton);
        RegButton.setBounds(240, 75, 150, 23);

        contentPane.setPreferredSize(new Dimension(400, 135));
        pack();
        setLocationRelativeTo(getOwner());

        //======== this2 ========
        {
            this2.setTitle("REGISTER");
            this2.setResizable(false);
            var this2ContentPane = this2.getContentPane();
            this2ContentPane.setLayout(null);

            //---- label3 ----
            label3.setText("userID");
            this2ContentPane.add(label3);
            label3.setBounds(new Rectangle(new Point(30, 15), label3.getPreferredSize()));

            //---- label4 ----
            label4.setText("userPW");
            this2ContentPane.add(label4);
            label4.setBounds(new Rectangle(new Point(25, 50), label4.getPreferredSize()));
            this2ContentPane.add(textField1);
            textField1.setBounds(85, 10, 175, 25);

            //---- label5 ----
            label5.setText("gender");
            this2ContentPane.add(label5);
            label5.setBounds(25, 115, 50, label5.getPreferredSize().height);

            //---- radioButton1 ----
            radioButton1.setText("\ub0a8");
            radioButton1.setSelected(true);
            this2ContentPane.add(radioButton1);
            radioButton1.setBounds(new Rectangle(new Point(85, 115), radioButton1.getPreferredSize()));

            //---- radioButton2 ----
            radioButton2.setText("\uc5ec");
            this2ContentPane.add(radioButton2);
            radioButton2.setBounds(new Rectangle(new Point(125, 115), radioButton2.getPreferredSize()));

            //---- label6 ----
            label6.setText("PW Again");
            this2ContentPane.add(label6);
            label6.setBounds(15, 85, 60, 17);

            //---- label7 ----
            label7.setText("state");
            this2ContentPane.add(label7);
            label7.setBounds(new Rectangle(new Point(30, 145), label7.getPreferredSize()));

            //---- comboBox1 ----
            comboBox1.setModel(new DefaultComboBoxModel<>(new String[] {
                "\uc7ac\ud559\uc0dd",
                "\uc77c\ubc18\uc778",
                "\uad50\uc9c1\uc6d0"
            }));
            this2ContentPane.add(comboBox1);
            comboBox1.setBounds(85, 145, 75, comboBox1.getPreferredSize().height);

            //---- label8 ----
            label8.setText("locker usage");
            this2ContentPane.add(label8);
            label8.setBounds(10, 175, 70, label8.getPreferredSize().height);

            //---- checkBox1 ----
            checkBox1.setText("\uc0ac\uc6a9\ud568");
            checkBox1.setSelected(true);
            this2ContentPane.add(checkBox1);
            checkBox1.setBounds(new Rectangle(new Point(85, 175), checkBox1.getPreferredSize()));

            //---- button1 ----
            button1.setText("Create Account");
            button1.addActionListener(e -> button1(e));
            this2ContentPane.add(button1);
            button1.setBounds(15, 205, 260, 30);
            this2ContentPane.add(passwordField1);
            passwordField1.setBounds(85, 45, 175, passwordField1.getPreferredSize().height);
            this2ContentPane.add(passwordField2);
            passwordField2.setBounds(85, 80, 175, passwordField2.getPreferredSize().height);

            this2ContentPane.setPreferredSize(new Dimension(290, 275));
            this2.pack();
            this2.setLocationRelativeTo(this2.getOwner());
        }

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
                    panel5.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border
                    . EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax
                    . swing. border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,
                    12 ), java. awt. Color. red) ,panel5. getBorder( )) ); panel5. addPropertyChangeListener (new java. beans
                    . PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .
                    getPropertyName () )) throw new RuntimeException( ); }} );
                    panel5.setLayout(null);

                    //---- label13 ----
                    label13.setText("\ud604\uc7ac \uc774\uc6a9\uc790\uc218 : ");
                    panel5.add(label13);
                    label13.setBounds(20, 10, 86, 17);

                    //---- label14 ----
                    label14.setText("32\uba85");
                    label14.setFont(new Font("\ub9d1\uc740 \uace0\ub515", Font.BOLD, 12));
                    panel5.add(label14);
                    label14.setBounds(110, 10, 26, 17);

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

                    //---- comboBox2 ----
                    comboBox2.setModel(new DefaultComboBoxModel<>(new String[] {
                        "\uc9c0\ub09c\uc8fc",
                        "2\uc8fc \uc804"
                    }));
                    panel5.add(comboBox2);
                    comboBox2.setBounds(20, 40, 72, 23);

                    //---- comboBox3 ----
                    comboBox3.setModel(new DefaultComboBoxModel<>(new String[] {
                        "\uc6d4",
                        "\ud654",
                        "\uc218",
                        "\ubaa9",
                        "\uae08",
                        "\ud1a0",
                        "\uc77c"
                    }));
                    panel5.add(comboBox3);
                    comboBox3.setBounds(100, 40, 72, 23);

                    //---- progressBar1 ----
                    progressBar1.setOrientation(SwingConstants.VERTICAL);
                    progressBar1.setValue(40);
                    progressBar1.setBackground(new Color(0xf2f2f2));
                    panel5.add(progressBar1);
                    progressBar1.setBounds(285, 115, 10, 135);

                    //---- progressBar2 ----
                    progressBar2.setOrientation(SwingConstants.VERTICAL);
                    progressBar2.setValue(60);
                    progressBar2.setBackground(new Color(0xf2f2f2));
                    panel5.add(progressBar2);
                    progressBar2.setBounds(310, 115, 10, 135);

                    //---- progressBar3 ----
                    progressBar3.setOrientation(SwingConstants.VERTICAL);
                    progressBar3.setValue(55);
                    progressBar3.setBackground(new Color(0xf2f2f2));
                    panel5.add(progressBar3);
                    progressBar3.setBounds(335, 115, 10, 135);

                    //---- progressBar4 ----
                    progressBar4.setOrientation(SwingConstants.VERTICAL);
                    progressBar4.setValue(75);
                    progressBar4.setBackground(new Color(0xf2f2f2));
                    panel5.add(progressBar4);
                    progressBar4.setBounds(360, 115, 10, 135);

                    //---- progressBar5 ----
                    progressBar5.setOrientation(SwingConstants.VERTICAL);
                    progressBar5.setValue(60);
                    progressBar5.setBackground(new Color(0xf2f2f2));
                    panel5.add(progressBar5);
                    progressBar5.setBounds(385, 115, 10, 135);

                    //---- progressBar6 ----
                    progressBar6.setOrientation(SwingConstants.VERTICAL);
                    progressBar6.setValue(80);
                    progressBar6.setBackground(new Color(0xf2f2f2));
                    panel5.add(progressBar6);
                    progressBar6.setBounds(410, 115, 10, 135);

                    //---- progressBar7 ----
                    progressBar7.setOrientation(SwingConstants.VERTICAL);
                    progressBar7.setValue(100);
                    progressBar7.setBackground(new Color(0xf2f2f2));
                    panel5.add(progressBar7);
                    progressBar7.setBounds(435, 115, 10, 135);

                    //---- label21 ----
                    label21.setText("\uc6d4    \ud654    \uc218   \ubaa9   \uae08   \ud1a0   \uc77c ");
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

                //======== panel4 ========
                {
                    panel4.setLayout(null);

                    //---- button2 ----
                    button2.setText("1.");
                    button2.setBackground(new Color(0x6699ff));
                    button2.setFocusable(false);
                    panel4.add(button2);
                    button2.setBounds(20, 25, 75, 35);

                    //---- button3 ----
                    button3.setText("2.");
                    button3.setBackground(new Color(0x6699ff));
                    button3.setFocusable(false);
                    panel4.add(button3);
                    button3.setBounds(110, 25, 75, 35);

                    //---- button4 ----
                    button4.setText("3.");
                    button4.setBackground(new Color(0xcccccc));
                    button4.setFocusable(false);
                    panel4.add(button4);
                    button4.setBounds(200, 25, 75, 35);

                    //---- button5 ----
                    button5.setText("4.");
                    button5.setBackground(new Color(0x6699ff));
                    button5.setFocusable(false);
                    panel4.add(button5);
                    button5.setBounds(285, 25, 75, 35);

                    //---- button6 ----
                    button6.setText("5.");
                    button6.setBackground(new Color(0x6699ff));
                    button6.setFocusable(false);
                    panel4.add(button6);
                    button6.setBounds(370, 25, 75, 35);

                    //---- button7 ----
                    button7.setText("6.");
                    button7.setBackground(new Color(0xcccccc));
                    button7.setFocusable(false);
                    panel4.add(button7);
                    button7.setBounds(20, 75, 75, 35);

                    //---- button8 ----
                    button8.setText("7.");
                    button8.setBackground(new Color(0xcccccc));
                    button8.setFocusable(false);
                    panel4.add(button8);
                    button8.setBounds(110, 75, 75, 35);

                    //---- button9 ----
                    button9.setText("11.");
                    button9.setBackground(new Color(0xcccccc));
                    button9.setFocusable(false);
                    panel4.add(button9);
                    button9.setBounds(20, 125, 75, 35);

                    //---- button10 ----
                    button10.setText("12.");
                    button10.setBackground(new Color(0xcccccc));
                    button10.setFocusable(false);
                    panel4.add(button10);
                    button10.setBounds(110, 125, 75, 35);

                    //---- button11 ----
                    button11.setText("13.");
                    button11.setBackground(new Color(0x6699ff));
                    button11.setFocusable(false);
                    panel4.add(button11);
                    button11.setBounds(200, 125, 75, 35);

                    //---- button12 ----
                    button12.setText("14.");
                    button12.setBackground(new Color(0x6699ff));
                    button12.setFocusable(false);
                    panel4.add(button12);
                    button12.setBounds(285, 125, 75, 35);

                    //---- button13 ----
                    button13.setText("15.");
                    button13.setBackground(new Color(0xff9999));
                    button13.setFocusable(false);
                    panel4.add(button13);
                    button13.setBounds(370, 125, 75, 35);

                    //---- button14 ----
                    button14.setText("16.");
                    button14.setBackground(new Color(0x6699ff));
                    button14.setFocusable(false);
                    panel4.add(button14);
                    button14.setBounds(20, 175, 75, 35);

                    //---- button15 ----
                    button15.setText("17.");
                    button15.setBackground(new Color(0x6699ff));
                    button15.setFocusable(false);
                    panel4.add(button15);
                    button15.setBounds(110, 175, 75, 35);

                    //---- button16 ----
                    button16.setText("8.");
                    button16.setBackground(new Color(0xcccccc));
                    button16.setFocusable(false);
                    panel4.add(button16);
                    button16.setBounds(200, 75, 75, 35);

                    //---- button17 ----
                    button17.setText("9.");
                    button17.setBackground(new Color(0x6699ff));
                    button17.setFocusable(false);
                    panel4.add(button17);
                    button17.setBounds(285, 75, 75, 35);

                    //---- button18 ----
                    button18.setText("10.");
                    button18.setBackground(new Color(0xcccccc));
                    button18.setFocusable(false);
                    panel4.add(button18);
                    button18.setBounds(370, 75, 75, 35);

                    //---- button19 ----
                    button19.setText("18.");
                    button19.setBackground(new Color(0xcccccc));
                    button19.setFocusable(false);
                    panel4.add(button19);
                    button19.setBounds(200, 175, 75, 35);

                    //---- button20 ----
                    button20.setText("19.");
                    button20.setBackground(new Color(0xcccccc));
                    button20.setFocusable(false);
                    panel4.add(button20);
                    button20.setBounds(285, 175, 75, 35);

                    //---- button21 ----
                    button21.setText("20.");
                    button21.setBackground(new Color(0xcccccc));
                    button21.setFocusable(false);
                    panel4.add(button21);
                    button21.setBounds(370, 175, 75, 35);

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
            tabbedPane1.setBounds(0, 0, 560, 285);

            MainFormContentPane.setPreferredSize(new Dimension(565, 315));
            MainForm.pack();
            MainForm.setLocationRelativeTo(MainForm.getOwner());
        }

        //---- buttonGroup1 ----
        var buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(radioButton1);
        buttonGroup1.add(radioButton2);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    public static void main(String[] args) {

        com.formdev.flatlaf.intellijthemes.FlatArcOrangeIJTheme.setup();
        loginPage frame = new loginPage();
        frame.pack();
        frame.setSize(415, 145);
        frame.setVisible(true);
        //UserDAO userDAO = new UserDAO();
        // addeventlistner
        /*
        int result = userDAO.login(username, password);
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
    */
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - Minjae
    private JLabel label1;
    private JLabel label2;
    private JPasswordField PWtext;
    private JTextField IDtext;
    private JButton LoginButton;
    private JCheckBox RememberCheck;
    private JButton RegButton;
    private JFrame this2;
    private JLabel label3;
    private JLabel label4;
    private JTextField textField1;
    private JLabel label5;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JLabel label6;
    private JLabel label7;
    private JComboBox<String> comboBox1;
    private JLabel label8;
    private JCheckBox checkBox1;
    private JButton button1;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JFrame MainForm;
    private JTabbedPane tabbedPane1;
    private JPanel panel5;
    private JLabel label13;
    private JLabel label14;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JComboBox<String> comboBox2;
    private JComboBox<String> comboBox3;
    private JProgressBar progressBar1;
    private JProgressBar progressBar2;
    private JProgressBar progressBar3;
    private JProgressBar progressBar4;
    private JProgressBar progressBar5;
    private JProgressBar progressBar6;
    private JProgressBar progressBar7;
    private JLabel label21;
    private JPanel panel2;
    private JPanel panel4;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button14;
    private JButton button15;
    private JButton button16;
    private JButton button17;
    private JButton button18;
    private JButton button19;
    private JButton button20;
    private JButton button21;
    private JLabel label15;
    private JLabel label16;
    private JLabel label17;
    private JLabel label18;
    private JLabel label19;
    private JLabel label20;
    private JPanel panel6;
    private JLabel label9;
    private JLabel label11;
    private JLabel label12;
    private JLabel label22;
    private JLabel label23;
    private JLabel label24;
    private JLabel label25;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
