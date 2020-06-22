package screens;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import hibernate.dao.AccountDAO;
import hibernate.java.Account;

import javax.swing.*;
import java.awt.*;

/**
 * @author caophuc
 */
public class LoginScreen extends Screen {
    public LoginScreen() {
        initComponents();
        setLocationRelativeTo(null);
//        setVisible(true);
        usernameTxf.setText("giaovu");
        passwordTxf.setText("giaovu");
    }

    private void initComponents() {

        body = new javax.swing.JPanel();
        usnPanel = new javax.swing.JPanel();
        usernameLbl = new javax.swing.JLabel();
        usernameTxf = new javax.swing.JTextField();
        pwPanel = new javax.swing.JPanel();
        passwordLbl = new javax.swing.JLabel();
        passwordTxf = new javax.swing.JPasswordField();
        loginBtnPanel = new javax.swing.JPanel();
        loginBtn = new javax.swing.JButton();
        remCkb = new JCheckBox();
        remLbl = new JLabel();
        remPanel = new JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(300, 250));
        getContentPane().setLayout(new java.awt.FlowLayout());

        body.setLayout(new java.awt.GridLayout(4, 0));

        usernameLbl.setText("Tên Đăng Nhập");
        usernameLbl.setPreferredSize(new java.awt.Dimension(100, 30));
        usnPanel.add(usernameLbl);

        usernameTxf.setPreferredSize(new java.awt.Dimension(200, 30));
        usnPanel.add(usernameTxf);

        body.add(usnPanel);

        passwordLbl.setText("Mật Khẩu");
        passwordLbl.setPreferredSize(new java.awt.Dimension(100, 30));
        pwPanel.add(passwordLbl);

        passwordTxf.setPreferredSize(new java.awt.Dimension(200, 30));
        pwPanel.add(passwordTxf);

        body.add(pwPanel);

        remPanel.setLayout(new FlowLayout(FlowLayout.LEADING));
        JPanel padding = new JPanel();
        padding.setPreferredSize(new java.awt.Dimension(100, 30));
        remPanel.add(padding);
        remPanel.add(remCkb);
        remLbl.setText("Nhớ Mật Khẩu");
        remPanel.add(remLbl);
        body.add(remPanel);

        loginBtn.setForeground(new java.awt.Color(255, 51, 51));
        loginBtn.setText("Đăng Nhập");
        loginBtn.setPreferredSize(new java.awt.Dimension(100, 30));
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        loginBtnPanel.add(loginBtn);

        body.add(loginBtnPanel);

        getContentPane().add(body);

        pack();
    }

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String username = usernameTxf.getText();
        String password = String.valueOf(passwordTxf.getPassword());
        Account account = AccountDAO.getAccount(username);
        if (account != null) {
            if (account.getCategory() == 1) {
                changeScreen(new TCHomeScreen(account));
            } else {
                changeScreen(new SVHomeScreen(account));
            }
        } else {
            JOptionPane.showMessageDialog(new JFrame(),"Sai Tên Đăng Nhập Hoặc Mật Khẩu","Message",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JPanel body;
    private javax.swing.JButton loginBtn;
    private javax.swing.JPanel loginBtnPanel;
    private javax.swing.JLabel passwordLbl;
    private javax.swing.JPasswordField passwordTxf;
    private javax.swing.JPanel pwPanel;
    private javax.swing.JLabel usernameLbl;
    private javax.swing.JTextField usernameTxf;
    private javax.swing.JPanel usnPanel;
    private JPanel remPanel;
    private JCheckBox remCkb;
    private JLabel remLbl;
    // End of variables declaration
}
