package screens;

import hibernate.dao.ClassScheduleDAO;
import hibernate.dao.IClassDAO;
import hibernate.dao.StudentDAO;
import hibernate.dao.StudentLOSDAO;
import hibernate.java.Account;
import hibernate.java.IClass;
import hibernate.java.Student;
import hibernate.java.StudentLOS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class AddStudentScreen extends Screen {
    Account currentUser;

    public AddStudentScreen(Account currentUser) {
        this.currentUser = currentUser;
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);

        tableModel.addColumn("STT");
        tableModel.addColumn("MSSV");
        tableModel.addColumn("Họ Tên");
        tableModel.addColumn("Giới Tính");
        tableModel.addColumn("CMND");
        tableModel.addColumn("Trạng Thái");
        List<IClass> il = IClassDAO.getList();
        if (il.isEmpty()) {
            classModel.addElement("----");
        }
        for (IClass iClass : il) {
            classModel.addElement(iClass.getClassID());
        }

        reloadSubjectModel();
    }

    private void reloadSubjectModel() {
        subjectModel.removeAllElements();
        subjectModel.addElement("----");
        String classID = (String) classIDComboBox.getSelectedItem();
        ClassScheduleDAO.getList().stream()
                .filter((cs) -> cs.getClassID().equals(classID))
                .collect(Collectors.toList()).forEach((cs) -> subjectModel.addElement(cs.getSubjectID()));
    }

    private void initComponents() {

        classModel = new DefaultComboBoxModel<String>();
        subjectModel = new DefaultComboBoxModel<String>();

        btnPanel = new JPanel();
        addStudentBtn = new JButton();
        submitBtn = new JButton();

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
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return !(column == 0 || column == 5);
            }
        };
        table = new javax.swing.JTable(tableModel);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        appBarPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));

        backBtn.setText("Huỷ");
        backBtn.addActionListener(this::backBtnActionPerformed);
        appBarPanel.add(backBtn);

        classIDLbl.setText("Mã Lớp");
        classIDLbl.setPreferredSize(new java.awt.Dimension(60, 30));
        classIDPanel.add(classIDLbl);

        classIDComboBox.setModel(classModel);
        classIDComboBox.addActionListener(this::classIDComboBoxActionPerformed);
        classIDPanel.add(classIDComboBox);

        optPanel.add(classIDPanel);

        subLbl.setText("Mon Hoc");
        subLbl.setPreferredSize(new java.awt.Dimension(60, 30));

        subPanel.add(subLbl);

        subComboBox.setModel(subjectModel);
        subPanel.add(subComboBox);

        optPanel.add(subPanel);

        addStudentBtn.setText("Thêm Sinh Viên");
        addStudentBtn.setPreferredSize(new java.awt.Dimension(140, 40));
        addStudentBtn.addActionListener(this::addBtnActionPerformed);
        btnPanel.add(addStudentBtn);

        submitBtn.setText("Lưu Thay Đổi");
        submitBtn.setPreferredSize(new java.awt.Dimension(140, 40));
        submitBtn.addActionListener(this::submitBtnActionPerformed);
        btnPanel.add(submitBtn);


        tablePanel.setLayout(new javax.swing.BoxLayout(tablePanel, javax.swing.BoxLayout.LINE_AXIS));
        tableScrollPanel.setViewportView(table);

        tablePanel.add(tableScrollPanel);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
                bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyPanelLayout.createSequentialGroup()
                                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(bodyPanelLayout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(bodyPanelLayout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addComponent(btnPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addComponent(optPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
                                        .addGroup(bodyPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(tablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        bodyPanelLayout.setVerticalGroup(
                bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(bodyPanelLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(optPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
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
        reloadSubjectModel();
    }

    private void addBtnActionPerformed(ActionEvent e) {
        tableModel.addRow(new Object[]{"" + tableModel.getRowCount() + 1, null, null, null, null, "-"});
    }

    private void submitBtnActionPerformed(ActionEvent e) {
        if (tableModel.getRowCount() == 0) {
            return;
        }
        int n = tableModel.getRowCount();
        String id;
        String name;
        String gender;
        String idcard;
        String classID = (String) classIDComboBox.getSelectedItem();
        String subjectID = (String) subjectModel.getSelectedItem();
        boolean bs = true;
        if (subjectID == null || "----".equals(subjectID)) bs = false;

        boolean error = false;
        for (int i = 0; i < n; i++) {
            id = (String) tableModel.getValueAt(i, 1);
            name = (String) tableModel.getValueAt(i, 2);
            gender = (String) tableModel.getValueAt(i, 3);
            idcard = (String) tableModel.getValueAt(i, 4);
            Student s = new Student(id, name, gender, idcard, classID);
            if (!StudentDAO.addStudent(s)) {
                error = true;
                tableModel.setValueAt("F", i, 5);
            } else {
                tableModel.setValueAt("T", i, 5);
            }

            if (bs) {
                StudentLOS los = new StudentLOS(id, classID, subjectID);
                StudentLOSDAO.add(los);
            }
        }

        if (!error) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Thêm Thành Công", "Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Thêm Thất Bại", "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void backBtnActionPerformed(ActionEvent evt) {
        if (currentUser.getCategory() == 1) {
            changeScreen(new TCHomeScreen(currentUser));
        } else {
            changeScreen(new SVHomeScreen(currentUser));
        }
    }

    private JPanel btnPanel;
    private JButton addStudentBtn;
    private JButton submitBtn;

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