package screens;

import hibernate.dao.HibernateDAO;
import hibernate.java.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
public class LoginScreen extends Screen {
    public LoginScreen(JFrame parent) {
        super(parent);
        initComponents();
    }

    private void initComponents() {

        body = new JPanel();
        usnPanel = new JPanel();
        usernameLbl = new JLabel();
        usernameTxf = new JTextField();
        pwPanel = new JPanel();
        passwordLbl = new JLabel();
        passwordTxf = new JPasswordField();
        loginBtnPanel = new JPanel();
        loginBtn = new JButton();

        setPreferredSize(new Dimension(300, 250));
        this.setLayout(new FlowLayout());

        body.setLayout(new GridLayout(4, 0));

        usernameLbl.setText("Tên Đăng Nhập");
        usernameLbl.setPreferredSize(new Dimension(100, 30));
        usnPanel.add(usernameLbl);

        usernameTxf.setPreferredSize(new Dimension(200, 30));
        usnPanel.add(usernameTxf);

        body.add(usnPanel);

        passwordLbl.setText("Mật Khẩu");
        passwordLbl.setPreferredSize(new Dimension(100, 30));
        pwPanel.add(passwordLbl);

        passwordTxf.setPreferredSize(new Dimension(200, 30));
        pwPanel.add(passwordTxf);

        body.add(pwPanel);

        loginBtn.setForeground(new Color(255, 51, 51));
        loginBtn.setText("Đăng Nhập");
        loginBtn.setPreferredSize(new Dimension(100, 30));
        loginBtn.addActionListener(this::loginBtnActionPerformed);
        loginBtnPanel.add(loginBtn);

        body.add(loginBtnPanel);

        this.add(body);

    }

    private void loginBtnActionPerformed(ActionEvent evt) {
        String username = usernameTxf.getText();
        String password = String.valueOf(passwordTxf.getPassword());
        Account account = HibernateDAO.get(Account.class, username);
        if (account.getPassword().equals(password)) {
            if (account.getCategory() == 1) {
                changeScreen(new TCHomeScreen(parent, account));
            } else {
                changeScreen(new SVHomeScreen(parent, account));
            }
        } else {
            JOptionPane.showMessageDialog(new JFrame(),"Sai Tên Đăng Nhập Hoặc Mật Khẩu","Message",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private JPanel body;
    private JButton loginBtn;
    private JPanel loginBtnPanel;
    private JLabel passwordLbl;
    private JPasswordField passwordTxf;
    private JPanel pwPanel;
    private JLabel usernameLbl;
    private JTextField usernameTxf;
    private JPanel usnPanel;
}
