package screens;

import helpers.Helper;
import hibernate.dao.*;
import hibernate.java.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveStudentScreen extends Screen {
    Account currentUser;
    List<Student> studentList = null;

    public RemoveStudentScreen(JFrame parent, Account currentUser) {
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

        String query = "from hibernate.java.IClass";
        List<IClass> il = HibernateDAO.getList(query);
        if (il.isEmpty()) {
            classModel.addElement("----");
        }
        for (IClass iClass : il) {
            classModel.addElement(iClass.getClassID());
        }

        reloadSubjectModel();

        submitBtn.addActionListener(this::submitBtnActionPerformed);
        backBtn.addActionListener(this::backBtnActionPerformed);
        classIDCb.addActionListener(this::classIDComboBoxActionPerformed);
        subComboBox.addActionListener(this::subComboBoxActionPerformed);
        keyTxf.addActionListener(this::keyTxtActionPerformed);
        searchBtn.addActionListener(this::keyTxtActionPerformed);
        reloadData();
    }

    private void initComponents() {
        appBarPanel = new JPanel();
        submitBtn = new JButton();
        backBtn = new JButton();
        separator = new JSeparator();
        bodyPanel = new JPanel();
        optPanel = new JPanel();
        classIDPanel = new JPanel();
        classIDLbl = new JLabel();
        classIDCb = new JComboBox<>(classModel);
        subPanel = new JPanel();
        subLbl = new JLabel();
        subComboBox = new JComboBox<>(subjectModel);
        searchPanel = new JPanel();
        searchLbl = new JLabel();
        searchBtn = new JButton();
        keyTxf = new JTextField();
        tablePanel = new JPanel();
        tableScrollPanel = new JScrollPane();
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);

//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        appBarPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));

        submitBtn.setText("Xoá");
        appBarPanel.add(submitBtn);

        backBtn.setText("Huỷ");
        appBarPanel.add(backBtn);

        classIDLbl.setText("Mã Lớp");
        classIDLbl.setPreferredSize(new Dimension(60, 30));
        classIDPanel.add(classIDLbl);

        classIDCb.setPreferredSize(new Dimension(120, 30));
        classIDPanel.add(classIDCb);

        optPanel.add(classIDPanel);

        subLbl.setText("Môn Học");
        subLbl.setPreferredSize(new Dimension(60, 30));
        subPanel.add(subLbl);

        subComboBox.setPreferredSize(new Dimension(120, 30));
        subPanel.add(subComboBox);

        optPanel.add(subPanel);

        searchLbl.setText("MSSV");
        searchLbl.setPreferredSize(new Dimension(50, 30));
        searchPanel.add(searchLbl);

        keyTxf.setPreferredSize(new Dimension(120, 30));
        searchPanel.add(keyTxf);

        searchBtn.setText("Tìm Kiếm");
        searchBtn.setPreferredSize(new Dimension(90, 30));
        searchPanel.add(searchBtn);

        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.LINE_AXIS));
        tableScrollPanel.setViewportView(table);

        tablePanel.add(tableScrollPanel);

        GroupLayout bodyPanelLayout = new GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
                bodyPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(bodyPanelLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(optPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, bodyPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(bodyPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(searchPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE))
                                .addContainerGap())
        );
        bodyPanelLayout.setVerticalGroup(
                bodyPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(bodyPanelLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(optPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchPanel, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
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
    private void reloadSubjectModel() {
        subjectModel.removeAllElements();
        subjectModel.addElement("----");
        String classID = (String) classIDCb.getSelectedItem();
        String query = "from hibernate.java.ClassSchedule CS where CS.classID = '" + classID + "'";
        List<ClassSchedule> cl = HibernateDAO.getList(query);
        cl.forEach((cs) -> subjectModel.addElement(cs.getSubjectID()));
    }

    private String getStudentSelectQuery() {
        String ci = (String) classIDCb.getSelectedItem();
        String si = (String) subComboBox.getSelectedItem();
        if ("----".equals(si)) return "from hibernate.java.Student S where S.classID = '" + ci + "'";
        String query = "(select SLOS.studentID from hibernate.java.StudentLOS SLOS where SLOS.classID = '" + ci + "'";
        if (si != null) query += "and SLOS.subjectID = '" + si + "')";
        else query += ")";

        query = "from hibernate.java.Student S where S.studentID in " + query;
        return query;
    }

    private void reloadData() {
        String query = getStudentSelectQuery();
        studentList = HibernateDAO.getList(query);
        displayData(studentList);
    }

    private void displayData(List<Student> l) {
        tableModel.setRowCount(0);
        for (int i = 0; i < l.size(); i++) {
            Student s = l.get(i);
            tableModel.addRow(new Object[]{"" + i, s.getStudentID(), s.getName(), s.getGender(), s.getIdCardNo()});
        }
    }

    private void classIDComboBoxActionPerformed(ActionEvent e) {
        reloadSubjectModel();
        reloadData();
    }

    private void subComboBoxActionPerformed(ActionEvent e) {
        reloadData();
    }

    private void keyTxtActionPerformed(ActionEvent e) {
        if (keyTxf.getText() == null || keyTxf.getText().isEmpty()) {
            displayData(studentList);
            return;
        }
        String key = keyTxf.getText();
        displayData(studentList.stream().filter((s) -> s.getStudentID().contains(key)).collect(Collectors.toList()));
    }

    private void submitBtnActionPerformed(ActionEvent evt) {
        if (tableModel.getRowCount() == 0) {
            return;
        }
        int selectedRow = table.getSelectedRow();
        String classID = (String) classIDCb.getSelectedItem();
        String subjectID = (String) subComboBox.getSelectedItem();
        String studentID = (String) tableModel.getValueAt(selectedRow, 1);
        Score s = new Score(studentID,null,classID, subjectID,null,null,null,null);
        StudentLOS los = new StudentLOS(studentID,classID,subjectID);

        HibernateDAO.remove(Score.class, s);
        boolean error = !HibernateDAO.remove(StudentLOS.class, los);

        if ("----".equals(subjectID)){
            List<StudentLOS> sl = HibernateDAO.getList("from hibernate.java.StudentLOS where studentID = '"
                    + studentID + "'");
            sl.forEach(HibernateDAO::remove);

            boolean e2 = !HibernateDAO.remove(Student.class, studentID);
            error = error & e2;
        }

        if (error) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Xoá Thất Bại", "Message", JOptionPane.INFORMATION_MESSAGE);
        } else {
            reloadData();
            JOptionPane.showMessageDialog(new JFrame(),
                    "Xoá Thành Công", "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void backBtnActionPerformed(ActionEvent evt) {
        if (currentUser.getCategory() == 1) {
            changeScreen(new TCHomeScreen(parent, currentUser));
        } else {
            changeScreen(new SVHomeScreen(parent, currentUser));
        }
    }

    private JPanel appBarPanel;
    private JButton backBtn;
    private JPanel bodyPanel;
    private JComboBox<String> classIDCb;
    private JLabel classIDLbl;
    private JPanel classIDPanel;
    private JTextField keyTxf;
    private JPanel optPanel;
    private JButton searchBtn;
    private JLabel searchLbl;
    private JPanel searchPanel;
    private JSeparator separator;
    private JComboBox<String> subComboBox;
    private JLabel subLbl;
    private JPanel subPanel;
    private JButton submitBtn;
    private JTable table;
    private JPanel tablePanel;
    private JScrollPane tableScrollPanel;

    private DefaultTableModel tableModel;
    private DefaultComboBoxModel<String> classModel = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> subjectModel = new DefaultComboBoxModel<>();
}
