package screens;


import hibernate.dao.HibernateDAO;
import hibernate.java.Account;

import javax.swing.*;
import java.awt.*;

public class AccSettingsScreen extends Screen {
    private Account currentUser;
    public AccSettingsScreen(JFrame parent, Account account) {
        super(parent);
        this.currentUser = account;
        initComponents();
//        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {

        home = new JPanel();
        topBar = new JPanel();
        backBtn = new JButton();
        separator = new JSeparator();
        body = new JPanel();
        oldPassPanel = new JPanel();
        oldPassLbl = new JLabel();
        oldPassTxf = new JPasswordField();
        newPassPanel = new JPanel();
        newPassLbl = new JLabel();
        newPassTxf = new JPasswordField();
        verPassPanel = new JPanel();
        verPassLbl = new JLabel();
        verPassTxf = new JPasswordField();
        submitPanel = new JPanel();
        submitBtn = new JButton();

//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout());

        home.setPreferredSize(new Dimension(300, 300));

        topBar.setLayout(new FlowLayout(FlowLayout.TRAILING));

        backBtn.setText("Huỷ");
        backBtn.setPreferredSize(new Dimension(60,30));
        backBtn.addActionListener(e -> back());
        topBar.add(backBtn);

        body.setLayout(new GridLayout(4, 0));

        oldPassLbl.setPreferredSize(new Dimension(100,30));
        oldPassLbl.setText("Mật Khẩu cũ");
        oldPassPanel.add(oldPassLbl);

        oldPassTxf.setPreferredSize(new Dimension(150, 30));
        oldPassPanel.add(oldPassTxf);

        body.add(oldPassPanel);

        newPassLbl.setPreferredSize(new Dimension(100,30));
        newPassLbl.setText("Mật khẩu mới");
        newPassPanel.add(newPassLbl);

        newPassTxf.setPreferredSize(new Dimension(150, 30));
        newPassPanel.add(newPassTxf);

        body.add(newPassPanel);

        verPassLbl.setPreferredSize(new Dimension(100,30));
        verPassLbl.setText("Mật khẩu mới");
        verPassPanel.add(verPassLbl);

        verPassTxf.setPreferredSize(new Dimension(150, 30));
        verPassPanel.add(verPassTxf);

        body.add(verPassPanel);

        submitBtn.setText("Thay Đổi");
        submitBtn.setPreferredSize(new Dimension(100, 30));
        submitBtn.addActionListener(e -> changePassword());
        submitPanel.add(submitBtn);

        body.add(submitPanel);

        GroupLayout jPanel1Layout = new GroupLayout(home);
        home.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(body, GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(topBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(separator))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(topBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(body, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                                .addContainerGap())
        );

        getContentPane().add(home);

        pack();
    }

    private Container getContentPane(){
        return this;
    }

    private void pack(){
        parent.pack();
    }

    private void back(){
        if (currentUser.getCategory() == 1) {
            changeScreen(new TCHomeScreen(parent, currentUser));
        } else {
            changeScreen(new SVHomeScreen(parent, currentUser));
        }
    }

    private void changePassword(){
        String op = String.valueOf(oldPassTxf.getPassword());
        if (!currentUser.getPassword().equals(op)) {
            JOptionPane.showMessageDialog(this,"Mật khẩu không chính xác",
                    "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String p1 = String.valueOf(newPassTxf.getPassword());
        String p2 = String.valueOf(verPassTxf.getPassword());
        if (!p1.equals(p2)) {
            JOptionPane.showMessageDialog(this,"Mật khẩu không khớp",
                    "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!validate(p1)) {
            JOptionPane.showMessageDialog(this,"Mật khẩu trên 8 ký tự, ít nhất 1 ký tự viết hoa,ít nhất 1 ký tự số",
                    "", JOptionPane.ERROR_MESSAGE);
            return;
        }
        currentUser.setPassword(p1);
        if (HibernateDAO.update(currentUser)) {
            JOptionPane.showMessageDialog(this,"Thay đổi thành công",
                    "", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        currentUser.setPassword(op);
        JOptionPane.showMessageDialog(this,"Thay đổi thất bại",
                "", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean validate(String password) {
        return password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    private JButton backBtn;
    private JPanel body;
    private JPanel home;
    private JSeparator separator;
    private JLabel newPassLbl;
    private JPanel newPassPanel;
    private JPasswordField newPassTxf;
    private JLabel oldPassLbl;
    private JPanel oldPassPanel;
    private JPasswordField oldPassTxf;
    private JButton submitBtn;
    private JPanel submitPanel;
    private JPanel topBar;
    private JLabel verPassLbl;
    private JPanel verPassPanel;
    private JPasswordField verPassTxf;
}
