package screens;

import hibernate.dao.HibernateDAO;
import hibernate.java.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

public class AddStudentScreen extends Screen {
    Account currentUser;

    public AddStudentScreen(JFrame parent, Account currentUser) {
        super(parent);
        this.currentUser = currentUser;
        initComponents();
//        setLocationRelativeTo(null);
        setVisible(true);

        tableModel.addColumn("STT");
        tableModel.addColumn("MSSV");
        tableModel.addColumn("Họ Tên");
        tableModel.addColumn("Giới Tính");
        tableModel.addColumn("CMND");
        tableModel.addColumn("Trạng Thái");

        String query = "from hibernate.java.IClass";
        List<IClass> il = HibernateDAO.getList(query);
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
        String query = "from hibernate.java.ClassSchedule CS where CS.classID = '" + classID + "'";
        List<ClassSchedule> cl = HibernateDAO.getList(query);
        cl.forEach((cs) -> subjectModel.addElement(cs.getSubjectID()));
    }

    private void initComponents() {

        classModel = new DefaultComboBoxModel<>();
        subjectModel = new DefaultComboBoxModel<>();

        btnPanel = new JPanel();
        addStudentBtn = new JButton();
        submitBtn = new JButton();

        appBarPanel = new JPanel();
        backBtn = new JButton();
        separator = new JSeparator();
        bodyPanel = new JPanel();
        optPanel = new JPanel();
        classIDPanel = new JPanel();
        classIDLbl = new JLabel();
        classIDComboBox = new JComboBox<>();
        subPanel = new JPanel();
        subLbl = new JLabel();
        subComboBox = new JComboBox<>();
        tablePanel = new JPanel();
        tableScrollPanel = new JScrollPane();
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return !(column == 0 || column == 5);
            }
        };
        table = new JTable(tableModel);

//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        appBarPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));

        backBtn.setText("Huỷ");
        backBtn.addActionListener(this::backBtnActionPerformed);
        appBarPanel.add(backBtn);

        classIDLbl.setText("Mã Lớp");
        classIDLbl.setPreferredSize(new Dimension(60, 30));
        classIDPanel.add(classIDLbl);

        classIDComboBox.setModel(classModel);
        classIDComboBox.addActionListener(this::classIDComboBoxActionPerformed);
        classIDPanel.add(classIDComboBox);

        optPanel.add(classIDPanel);

        subLbl.setText("Mon Hoc");
        subLbl.setPreferredSize(new Dimension(60, 30));

        subPanel.add(subLbl);

        subComboBox.setModel(subjectModel);
        subPanel.add(subComboBox);

        optPanel.add(subPanel);

        addStudentBtn.setText("Thêm Sinh Viên");
        addStudentBtn.setPreferredSize(new Dimension(140, 40));
        addStudentBtn.addActionListener(this::addBtnActionPerformed);
        btnPanel.add(addStudentBtn);

        submitBtn.setText("Lưu Thay Đổi");
        submitBtn.setPreferredSize(new Dimension(140, 40));
        submitBtn.addActionListener(this::submitBtnActionPerformed);
        btnPanel.add(submitBtn);


        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.LINE_AXIS));
        tableScrollPanel.setViewportView(table);

        tablePanel.add(tableScrollPanel);

        GroupLayout bodyPanelLayout = new GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
                bodyPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, bodyPanelLayout.createSequentialGroup()
                                .addGroup(bodyPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(bodyPanelLayout.createSequentialGroup()
                                                .addGap(3, 3, 3)
                                                .addGroup(bodyPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(bodyPanelLayout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addComponent(btnPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addComponent(optPanel, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)))
                                        .addGroup(bodyPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(tablePanel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        bodyPanelLayout.setVerticalGroup(
                bodyPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(bodyPanelLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(optPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(appBarPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(separator)
                        .addComponent(bodyPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(appBarPanel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(separator, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bodyPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private Container getContentPane(){
        return this;
    }

    private void pack(){
        parent.pack();
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
            if (!HibernateDAO.add(s)) {
                error = true;
                tableModel.setValueAt("F", i, 5);
            } else {
                tableModel.setValueAt("T", i, 5);
            }

            if (bs) {
                StudentLOS los = new StudentLOS(id, classID, subjectID);
                HibernateDAO.add(los);
//
//                String query = "from hibernate.java.ClassSchedule CS where CS.classID = '" + classID + "'";
//                List<ClassSchedule> cl = HibernateDAO.getList(query);
//                cl.forEach();
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
            changeScreen(new TCHomeScreen(parent, currentUser));
        } else {
            changeScreen(new SVHomeScreen(parent, currentUser));
        }
    }

    private JPanel btnPanel;
    private JButton addStudentBtn;
    private JButton submitBtn;

    private JPanel appBarPanel;
    private JButton backBtn;
    private JPanel bodyPanel;
    private JComboBox<String> classIDComboBox;
    private JLabel classIDLbl;
    private JPanel classIDPanel;
    private JPanel optPanel;
    private JSeparator separator;
    private JComboBox<String> subComboBox;
    private JLabel subLbl;
    private JPanel subPanel;
    private JTable table;
    private JPanel tablePanel;
    private JScrollPane tableScrollPanel;
    private DefaultTableModel tableModel;
    private DefaultComboBoxModel<String> classModel;
    private DefaultComboBoxModel<String> subjectModel;
}