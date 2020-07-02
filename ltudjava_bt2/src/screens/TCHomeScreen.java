package screens;


import hibernate.java.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TCHomeScreen extends Screen {
    private Account currentUser;
    public TCHomeScreen(JFrame parent, Account account) {
        super(parent);
        this.currentUser = account;
        initComponents();
        btn1.setText("Thêm Danh Sách Lớp");
        btn2.setText("Xem Danh Sách Lớp");
        btn3.setText("Thêm Sinh Viên");
        btn4.setText("Xoá Sinh Viên");
        btn5.setText("Nhập Thời Khoá Biểu");
        btn6.setText("Xem Thời Khoá Biểu");
        btn7.setText("Nhập Bảng Điểm");
        btn8.setText("Xem Bảng Điểm");
        btn9.setText("Sửa Điểm");

        btn1.addActionListener(e -> importStudentList());
        btn2.addActionListener(e -> dsClass());
        btn3.addActionListener(e -> addStudent());
        btn4.addActionListener(e -> removeStudent());
        btn5.addActionListener(e -> importClassSchedule());
        btn6.addActionListener(e -> dsClassSchedule());
        btn7.addActionListener(e -> importScore());
        btn8.addActionListener(e -> scoreTable());
        btn9.addActionListener(e -> fixScore());
    }
    private void initComponents() {

        topBar = new JPanel();
        logoutBtn = new JButton();
        accBtn = new JButton();
        separator = new JSeparator();
        bodyPanel = new JPanel();
        btnPanel1 = new JPanel();
        btn1 = new JButton();
        btnPanel2 = new JPanel();
        btn2 = new JButton();
        btnPanel3 = new JPanel();
        btn3 = new JButton();
        btnPanel4 = new JPanel();
        btn4 = new JButton();
        btnPanel5 = new JPanel();
        btn5 = new JButton();
        btnPanel6 = new JPanel();
        btn6 = new JButton();
        btnPanel7 = new JPanel();
        btn7 = new JButton();
        btnPanel8 = new JPanel();
        btn8 = new JButton();
        btnPanel9 = new JPanel();
        btn9 = new JButton();

        Dimension btnSize = new Dimension(200, 40);

        FlowLayout flowLayout1 = new FlowLayout(FlowLayout.TRAILING);
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

        bodyPanel.setLayout(new GridLayout(3, 3));

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

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(separator)
                                        .addComponent(bodyPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(topBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(topBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bodyPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }

    private void removeStudent(){
        changeScreen(new RemoveStudentScreen(parent, currentUser));
    }

    private void fixScore(){
        changeScreen(new FixScoreScreen(parent, currentUser));
    }

    private void scoreTable(){
        changeScreen(new ScoreTableScreen(parent, currentUser));
    }
    private void importScore(){
        changeScreen(new ImportScoreTableScreen(parent, currentUser));
    }
    private void dsClassSchedule(){
        changeScreen(new ClassScheduleScreen(parent, currentUser));
    }
    private void importStudentList(){
        changeScreen(new ImportClassScreen(parent, currentUser));
    }
    private void dsClass(){
        changeScreen(new StudentListScreen(parent, currentUser));
    }
    private void addStudent(){
        changeScreen(new AddStudentScreen(parent, currentUser));
    }
    private void importClassSchedule(){
        changeScreen(new ImportClassScheduleScreen(parent, currentUser));
    }

    private void changePassword(){
        changeScreen(new AccSettingsScreen(parent, currentUser));
    }

    private void logout(){
        changeScreen(new LoginScreen(parent));
    }

    private JButton logoutBtn;
    private JButton accBtn;
    private JPanel bodyPanel;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;
    private JPanel btnPanel1;
    private JPanel btnPanel2;
    private JPanel btnPanel3;
    private JPanel btnPanel4;
    private JPanel btnPanel5;
    private JPanel btnPanel6;
    private JPanel btnPanel7;
    private JPanel btnPanel8;
    private JPanel btnPanel9;
    private JSeparator separator;
    private JPanel topBar;
}
