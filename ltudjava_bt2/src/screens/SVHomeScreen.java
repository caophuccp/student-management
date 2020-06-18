package screens;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SVHomeScreen extends Screen {
    public SVHomeScreen() {
        initComponents();
        setLocationRelativeTo(null);
//        setVisible(true);
    }
    private void initComponents() {

        topBar = new javax.swing.JPanel();
        logoutBtn = new javax.swing.JButton();
        accBtn = new javax.swing.JButton();
        separator = new javax.swing.JSeparator();
        bodyPanel = new javax.swing.JPanel();
        btnPanel1 = new javax.swing.JPanel();
        btn1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING);
        flowLayout1.setAlignOnBaseline(true);
        topBar.setLayout(flowLayout1);

        accBtn.setText("Đổi Mật Khẩu");
        accBtn.setPreferredSize(new Dimension(100,40));
        accBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePassword();
            }
        });
        topBar.add(accBtn);

        logoutBtn.setText("Đăng Xuất");
        logoutBtn.setPreferredSize(new Dimension(100,40));
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        topBar.add(logoutBtn);

        bodyPanel.setLayout(new java.awt.GridLayout(1, 1));

        btn1.setText("Xem Điểm");
        btn1.setPreferredSize(new Dimension(100,40));
        btnPanel1.add(btn1);

        bodyPanel.add(btnPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(separator)
                                        .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
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
                                .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pack();
    }

    private void changePassword(){
        changeScreen(new AccSettingsScreen(this));
    }

    private void logout(){
        changeScreen(new LoginScreen());
    }

    private javax.swing.JButton logoutBtn;
    private javax.swing.JButton accBtn;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JButton btn1;
    private javax.swing.JPanel btnPanel1;
    private javax.swing.JSeparator separator;
    private javax.swing.JPanel topBar;
    // End of variables declaration
}

