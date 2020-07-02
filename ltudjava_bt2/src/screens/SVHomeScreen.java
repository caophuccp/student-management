package screens;

import hibernate.java.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SVHomeScreen extends Screen {
    private final Account currentUser;
    public SVHomeScreen(JFrame parent, Account account) {
        super(parent);
        this.currentUser = account;
        initComponents();
        btn1.addActionListener(this::btn1ActionPerformed);
    }
    
    private void initComponents() {

        topBar = new JPanel();
        logoutBtn = new JButton();
        accBtn = new JButton();
        separator = new JSeparator();
        bodyPanel = new JPanel();
        btnPanel1 = new JPanel();
        btn1 = new JButton();

        FlowLayout flowLayout1 = new FlowLayout(FlowLayout.TRAILING);
        flowLayout1.setAlignOnBaseline(true);
        topBar.setLayout(flowLayout1);

        accBtn.setText("Đổi Mật Khẩu");
        accBtn.setPreferredSize(new Dimension(100,40));
        accBtn.addActionListener(e -> changePassword());
        topBar.add(accBtn);

        logoutBtn.setText("Đăng Xuất");
        logoutBtn.setPreferredSize(new Dimension(100,40));
        logoutBtn.addActionListener(e -> logout());
        topBar.add(logoutBtn);

        bodyPanel.setLayout(new GridLayout(1, 1));

        btn1.setText("Xem Điểm");
        btn1.setPreferredSize(new Dimension(100,40));
        btnPanel1.add(btn1);

        bodyPanel.add(btnPanel1);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(separator)
                                        .addComponent(bodyPanel, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
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
                                .addComponent(bodyPanel, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                                .addContainerGap())
        );
    }

    private void btn1ActionPerformed(ActionEvent e){
        changeScreen(new StudentScoreScreen(parent, currentUser));
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
    private JPanel btnPanel1;
    private JSeparator separator;
    private JPanel topBar;
}

