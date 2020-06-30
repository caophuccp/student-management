package screens;

import hibernate.dao.ClassScheduleDAO;
import hibernate.dao.IClassDAO;
import hibernate.dao.StudentLOSDAO;
import hibernate.java.Account;
import hibernate.java.ClassSchedule;
import hibernate.java.IClass;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class ClassScheduleScreen extends Screen{
    Account currentUser;
    public ClassScheduleScreen(Account currentUser) {
        this.currentUser = currentUser;
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);

        table.setEnabled(false);
        tableModel.addColumn("STT");
        tableModel.addColumn("Mã Môn");
        tableModel.addColumn("Tên Môn");
        tableModel.addColumn("Phòng Học");

        List<IClass> il = IClassDAO.getList();
        if (il.isEmpty()) {
            classModel.addElement("----");
        }
        for (IClass iClass : il) {
            classModel.addElement(iClass.getClassID());
        }

        reloadSubjectModel();
    }

    private void reloadSubjectModel(){
        subjectModel.removeAllElements();
        subjectModel.addElement("----");
        String classID = (String)classIDComboBox.getSelectedItem();
        ClassScheduleDAO.getList().stream()
                .filter((cs)->cs.getClassID().equals(classID))
                .collect(Collectors.toList()).forEach((cs)->subjectModel.addElement(cs.getSubjectID()));

    }

    private void initComponents() {

        classModel = new DefaultComboBoxModel<String>();
        subjectModel = new DefaultComboBoxModel<String>();

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
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        appBarPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));

        backBtn.setText("Huỷ");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backBtnActionPerformed(e);
            }
        });
        appBarPanel.add(backBtn);
//        GroupLayout appBarPanelLayout = new GroupLayout(appBarPanel);
//        appBarPanel.setLayout(appBarPanelLayout);
//        appBarPanelLayout.setHorizontalGroup(
//                appBarPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                        .addGroup(GroupLayout.Alignment.TRAILING, appBarPanelLayout.createSequentialGroup()
//                                .addGap(0, 0, Short.MAX_VALUE)
//                                .addComponent(backBtn))
//        );
//        appBarPanelLayout.setVerticalGroup(
//                appBarPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                        .addGroup(GroupLayout.Alignment.TRAILING, appBarPanelLayout.createSequentialGroup()
//                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                                .addComponent(backBtn)
//                                .addContainerGap())
//        );

        classIDLbl.setText("Mã Lớp");
        classIDLbl.setPreferredSize(new java.awt.Dimension(60, 30));
        classIDPanel.add(classIDLbl);

        classIDComboBox.setModel(classModel);
        classIDComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reloadSubjectModel();
                reloadData();
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
                reloadData();
            }
        });
        subPanel.add(subComboBox);

        optPanel.add(subPanel);

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
                        .addGroup(bodyPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tablePanel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addContainerGap())
        );
        bodyPanelLayout.setVerticalGroup(
                bodyPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(bodyPanelLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(optPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
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

    private void backBtnActionPerformed(ActionEvent evt) {
        if (currentUser.getCategory() == 1) {
            changeScreen(new TCHomeScreen(currentUser));
        } else {
            changeScreen(new SVHomeScreen(currentUser));
        }
    }

    void reloadData(){
        String classID = (String) classIDComboBox.getSelectedItem();
        String subjectID = (String) subComboBox.getSelectedItem();
//        List<ClassSchedule> l = ClassScheduleDAO.getList().stream()
//                .filter((cs)-> cs.getClassID().equals(classID) && ("----".equals(subjectID)
//                        || cs.getSubjectID().equals(subjectID))).collect(Collectors.toList());
        String query = "from hibernate.java.ClassSchedule CS where CS.classID = '" + classID + "'";
        if (subjectID != null && !"----".equals(subjectID)) query += "and CS.subjectID = '" + subjectID + "'";
        List<ClassSchedule> l = ClassScheduleDAO.getList(query);
        tableModel.setRowCount(0);
        for (int i = 0; i < l.size(); i++) {
            ClassSchedule cs = l.get(i);
            tableModel.addRow(new Object[]{"" + (i + 1), cs.getSubjectID(), cs.getClassID(), cs.getClassroom()});
        }

    }

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
