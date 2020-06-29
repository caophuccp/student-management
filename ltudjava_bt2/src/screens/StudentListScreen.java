package screens;

import hibernate.dao.IClassDAO;
import hibernate.dao.StudentDAO;
import hibernate.java.Account;
import hibernate.java.IClass;
import hibernate.java.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StudentListScreen extends Screen{
    Account currentUser;
    public StudentListScreen(Account currentUser) {
        this.currentUser = currentUser;
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);

        tableModel.addColumn("STT");
        tableModel.addColumn("MSSV");
        tableModel.addColumn("Họ Tên");
        tableModel.addColumn("Giới Tính");
        tableModel.addColumn("CMND");

        List<IClass> il = IClassDAO.getList();
        if (il.isEmpty()) {
            classModel.addElement("----");
        }
        for (IClass iClass : il) {
            classModel.addElement(iClass.getClassID());
        }
        subjectModel.addElement("----");
    }

    private void initComponents() {

        classModel = new DefaultComboBoxModel<String>();
        subjectModel = new DefaultComboBoxModel<String>();

        appBarPanel = new javax.swing.JPanel();
        backBtn = new javax.swing.JButton();
        separator = new javax.swing.JSeparator();
        bodyPanel = new javax.swing.JPanel();
        optPanel = new javax.swing.JPanel();
        classIDPanel = new javax.swing.JPanel();
        classIDLbl = new javax.swing.JLabel();
        classIDComboBox = new javax.swing.JComboBox<>();
        subPanel = new javax.swing.JPanel();
        subLbl = new javax.swing.JLabel();
        subComboBox = new javax.swing.JComboBox<>();
        tablePanel = new javax.swing.JPanel();
        tableScrollPanel = new javax.swing.JScrollPane();
        tableModel = new DefaultTableModel();
        table = new javax.swing.JTable(tableModel);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        backBtn.setText("Huỷ");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backBtnActionPerformed(e);
            }
        });

        javax.swing.GroupLayout appBarPanelLayout = new javax.swing.GroupLayout(appBarPanel);
        appBarPanel.setLayout(appBarPanelLayout);
        appBarPanelLayout.setHorizontalGroup(
                appBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, appBarPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(backBtn))
        );
        appBarPanelLayout.setVerticalGroup(
                appBarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, appBarPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(backBtn)
                                .addContainerGap())
        );

        classIDLbl.setText("Mã Lớp");
        classIDLbl.setPreferredSize(new java.awt.Dimension(60, 30));
        classIDPanel.add(classIDLbl);

        classIDComboBox.setModel(classModel);
        classIDComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                classIDComboBoxActionPerformed(e);
            }
        });
        classIDPanel.add(classIDComboBox);

        optPanel.add(classIDPanel);

        subLbl.setText("Môn Học");
        subLbl.setPreferredSize(new java.awt.Dimension(60, 30));
        subPanel.add(subLbl);

        subComboBox.setModel(subjectModel);
        subComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subComboBoxActionPerformed(e);
            }
        });
        subPanel.add(subComboBox);

        optPanel.add(subPanel);

        tablePanel.setLayout(new javax.swing.BoxLayout(tablePanel, javax.swing.BoxLayout.LINE_AXIS));
        tableScrollPanel.setViewportView(table);

        tablePanel.add(tableScrollPanel);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
                bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(bodyPanelLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(optPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(bodyPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addContainerGap())
        );
        bodyPanelLayout.setVerticalGroup(
                bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(bodyPanelLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(optPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(appBarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(separator)
                        .addComponent(bodyPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(appBarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
    private void classIDComboBoxActionPerformed(ActionEvent evt) {
        String ci = (String)classIDComboBox.getSelectedItem();
        List<Student> studentList = StudentDAO.getList().stream()
                .filter((s)->s.getClassID().equals(ci)).collect(Collectors.toList());
        tableModel.setRowCount(0);
        for (int i = 0; i < studentList.size(); i++) {
            Student s = studentList.get(i);
            tableModel.addRow(new Object[]{"" + i, s.getClassID(), s.getName(), s.getGender(), s.getIdCardNo()});
        }
    }

    private void subComboBoxActionPerformed(ActionEvent evt) {
    }

    private void backBtnActionPerformed(ActionEvent evt) {
        if (currentUser.getCategory() == 1) {
            changeScreen(new TCHomeScreen(currentUser));
        } else {
            changeScreen(new SVHomeScreen(currentUser));
        }
    }

    private javax.swing.JPanel appBarPanel;
    private javax.swing.JButton backBtn;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JComboBox<String> classIDComboBox;
    private javax.swing.JLabel classIDLbl;
    private javax.swing.JPanel classIDPanel;
    private javax.swing.JPanel optPanel;
    private javax.swing.JSeparator separator;
    private javax.swing.JComboBox<String> subComboBox;
    private javax.swing.JLabel subLbl;
    private javax.swing.JPanel subPanel;
    private javax.swing.JTable table;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JScrollPane tableScrollPanel;
    private DefaultTableModel tableModel;
    private DefaultComboBoxModel<String> classModel;
    private DefaultComboBoxModel<String> subjectModel;
}
