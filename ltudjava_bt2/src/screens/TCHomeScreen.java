package screens;


import hibernate.java.Account;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TCHomeScreen extends Screen {
    private Account currentUser;
    public TCHomeScreen(Account account) {
        this.currentUser = account;
        initComponents();
        setLocationRelativeTo(null);
        btn1.setText("Thêm Danh Sách Lớp");
        btn2.setText("Xem Danh Sách Lớp");
        btn3.setText("Thêm Sinh Viên");
        btn4.setText("Nhập Thời Khoá Biểu");
        btn5.setText("Xem Thời Khoá Biểu");
        btn6.setText("Nhập Bảng Điểm");
        btn7.setText("Xem Bảng Điểm");
        btn8.setText("Sửa Điểm");
        btn9.setText("jButton1");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importStudentList();
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dsClass();
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importClassSchedule();
            }
        });
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dsClassSchedule();
            }
        });
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                importATranscript();
            }
        });
        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                academicTranscript();
            }
        });
        btn8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fixAcademicTranscript();
            }
        });
    }
    private void initComponents() {

        topBar = new javax.swing.JPanel();
        logoutBtn = new javax.swing.JButton();
        accBtn = new javax.swing.JButton();
        separator = new javax.swing.JSeparator();
        bodyPanel = new javax.swing.JPanel();
        btnPanel1 = new javax.swing.JPanel();
        btn1 = new javax.swing.JButton();
        btnPanel2 = new javax.swing.JPanel();
        btn2 = new javax.swing.JButton();
        btnPanel3 = new javax.swing.JPanel();
        btn3 = new javax.swing.JButton();
        btnPanel4 = new javax.swing.JPanel();
        btn4 = new javax.swing.JButton();
        btnPanel5 = new javax.swing.JPanel();
        btn5 = new javax.swing.JButton();
        btnPanel6 = new javax.swing.JPanel();
        btn6 = new javax.swing.JButton();
        btnPanel7 = new javax.swing.JPanel();
        btn7 = new javax.swing.JButton();
        btnPanel8 = new javax.swing.JPanel();
        btn8 = new javax.swing.JButton();
        btnPanel9 = new javax.swing.JPanel();
        btn9 = new javax.swing.JButton();

        Dimension btnSize = new Dimension(200, 40);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING);
        flowLayout1.setAlignOnBaseline(true);
        topBar.setLayout(flowLayout1);

        accBtn.setText("Đổi Mật Khẩu");
        accBtn.setPreferredSize(new Dimension(120,40));
        accBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePassword();
            }
        });
        topBar.add(accBtn);

        logoutBtn.setText("Đăng Xuất");
        logoutBtn.setPreferredSize(new Dimension(120,40));
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        topBar.add(logoutBtn);

        bodyPanel.setLayout(new java.awt.GridLayout(3, 3));

        btn1.setPreferredSize(btnSize);
        btnPanel1.add(btn1);

        bodyPanel.add(btnPanel1);

        btn2.setPreferredSize(btnSize);
        btnPanel2.add(btn2);

        bodyPanel.add(btnPanel2);

        btn3.setPreferredSize(btnSize);
        btnPanel3.add(btn3);

        bodyPanel.add(btnPanel3);

        btn4.setPreferredSize(btnSize);
        btnPanel4.add(btn4);

        bodyPanel.add(btnPanel4);

        btn5.setPreferredSize(btnSize);
        btnPanel5.add(btn5);

        bodyPanel.add(btnPanel5);

        btn6.setPreferredSize(btnSize);
        btnPanel6.add(btn6);

        bodyPanel.add(btnPanel6);

        btn7.setPreferredSize(btnSize);
        btnPanel7.add(btn7);

        bodyPanel.add(btnPanel7);

        btn8.setPreferredSize(btnSize);
        btnPanel8.add(btn8);

        bodyPanel.add(btnPanel8);

        btn9.setPreferredSize(btnSize);
        btnPanel9.add(btn9);

        bodyPanel.add(btnPanel9);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(separator)
                                        .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(topBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(topBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }

    private void fixAcademicTranscript(){
        changeScreen(new FixScoreScreen(currentUser));
    }

    private void academicTranscript(){
        changeScreen(new ScoreTableScreen(currentUser));
    }
    private void importATranscript(){
        changeScreen(new ImportScoreTableScreen(currentUser));
    }
    private void dsClassSchedule(){
        changeScreen(new ClassScheduleScreen(currentUser));
    }
    private void importStudentList(){
        changeScreen(new ImportClassScreen(currentUser));
    }
    private void dsClass(){
        changeScreen(new StudentListScreen(currentUser));
    }
    private void addStudent(){
        changeScreen(new AddStudentScreen(currentUser));
    }
    private void importClassSchedule(){
        changeScreen(new ImportClassScheduleScreen(currentUser));
    }

    private void changePassword(){
        changeScreen(new AccSettingsScreen(currentUser));
    }

    private void logout(){
        changeScreen(new LoginScreen());
    }

    private javax.swing.JButton logoutBtn;
    private javax.swing.JButton accBtn;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JPanel btnPanel1;
    private javax.swing.JPanel btnPanel2;
    private javax.swing.JPanel btnPanel3;
    private javax.swing.JPanel btnPanel4;
    private javax.swing.JPanel btnPanel5;
    private javax.swing.JPanel btnPanel6;
    private javax.swing.JPanel btnPanel7;
    private javax.swing.JPanel btnPanel8;
    private javax.swing.JPanel btnPanel9;
    private javax.swing.JSeparator separator;
    private javax.swing.JPanel topBar;
}
