package screens;


import hibernate.java.Account;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccSettingsScreen extends Screen {
    private Screen preScene;
    private Account currentUser;
    public AccSettingsScreen(Account account) {
        this.currentUser = account;
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {

        home = new javax.swing.JPanel();
        topBar = new javax.swing.JPanel();
        backBtn = new javax.swing.JButton();
        separator = new javax.swing.JSeparator();
        body = new javax.swing.JPanel();
        oldPassPanel = new javax.swing.JPanel();
        oldPassLbl = new javax.swing.JLabel();
        oldPassTxf = new javax.swing.JPasswordField();
        newPassPanel = new javax.swing.JPanel();
        newPassLbl = new javax.swing.JLabel();
        newPassTxf = new javax.swing.JPasswordField();
        verPassPanel = new javax.swing.JPanel();
        verPassLbl = new javax.swing.JLabel();
        verPassTxf = new javax.swing.JPasswordField();
        submitPanel = new javax.swing.JPanel();
        submitBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.FlowLayout());

        home.setPreferredSize(new java.awt.Dimension(300, 300));

        topBar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));

        backBtn.setText("Huỷ");
        backBtn.setPreferredSize(new Dimension(60,30));
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                back();
            }
        });
        topBar.add(backBtn);

        body.setLayout(new java.awt.GridLayout(4, 0));

        oldPassLbl.setPreferredSize(new Dimension(100,30));
        oldPassLbl.setText("Mật Khẩu cũ");
        oldPassPanel.add(oldPassLbl);

        oldPassTxf.setPreferredSize(new java.awt.Dimension(150, 30));
        oldPassPanel.add(oldPassTxf);

        body.add(oldPassPanel);

        newPassLbl.setPreferredSize(new Dimension(100,30));
        newPassLbl.setText("Mật khẩu mới");
        newPassPanel.add(newPassLbl);

        newPassTxf.setPreferredSize(new java.awt.Dimension(150, 30));
        newPassPanel.add(newPassTxf);

        body.add(newPassPanel);

        verPassLbl.setPreferredSize(new Dimension(100,30));
        verPassLbl.setText("Mật khẩu mới");
        verPassPanel.add(verPassLbl);

        verPassTxf.setPreferredSize(new java.awt.Dimension(150, 30));
        verPassPanel.add(verPassTxf);

        body.add(verPassPanel);

        submitBtn.setText("Thay Đổi");
        submitBtn.setPreferredSize(new java.awt.Dimension(100, 30));
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePassword();
            }
        });
        submitPanel.add(submitBtn);

        body.add(submitPanel);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(home);
        home.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(topBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(separator))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(topBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                .addContainerGap())
        );

        getContentPane().add(home);

        pack();
    }

    private void back(){
        if (currentUser.getCategory() == 1) {
            changeScreen(new TCHomeScreen(currentUser));
        } else {
            changeScreen(new SVHomeScreen(currentUser));
        }
    }

    private void changePassword(){

    }

    private javax.swing.JButton backBtn;
    private javax.swing.JPanel body;
    private javax.swing.JPanel home;
    private javax.swing.JSeparator separator;
    private javax.swing.JLabel newPassLbl;
    private javax.swing.JPanel newPassPanel;
    private javax.swing.JPasswordField newPassTxf;
    private javax.swing.JLabel oldPassLbl;
    private javax.swing.JPanel oldPassPanel;
    private javax.swing.JPasswordField oldPassTxf;
    private javax.swing.JButton submitBtn;
    private javax.swing.JPanel submitPanel;
    private javax.swing.JPanel topBar;
    private javax.swing.JLabel verPassLbl;
    private javax.swing.JPanel verPassPanel;
    private javax.swing.JPasswordField verPassTxf;
    // End of variables declaration
}
