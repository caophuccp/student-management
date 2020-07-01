package screens;

import helpers.Helper;
import hibernate.dao.HibernateDAO;
import hibernate.java.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

public class FixScoreScreen extends Screen {
    Account currentUser;
    List<Score> scoreList;

    public FixScoreScreen(Account currentUser) {
        this.currentUser = currentUser;
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);

        tableModel.addColumn("STT");
        tableModel.addColumn("MSSV");
        tableModel.addColumn("Họ Tên");
        tableModel.addColumn("Điểm GK");
        tableModel.addColumn("Điểm CK");
        tableModel.addColumn("Điểm Khác");
        tableModel.addColumn("Điểm Tổng");
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
        String classID = (String) classIDCb.getSelectedItem();
        String query = "from hibernate.java.ClassSchedule CS where CS.classID = '" + classID + "'";
        List<ClassSchedule> cl = HibernateDAO.getList(query);
        cl.forEach((cs) -> subjectModel.addElement(cs.getSubjectID()));
    }

    void reloadData() {
        String classID = (String) classIDCb.getSelectedItem();
        String subjectID = (String) subComboBox.getSelectedItem();
        String query = "from hibernate.java.Score S where S.classID = '" + classID + "' and S.subjectID = '" + subjectID + "'";
        scoreList = HibernateDAO.getList(query);
        for (Score score : scoreList) {
            Student s = HibernateDAO.get(Student.class ,score.getStudentID());
            score.setStudentName(s.getName());
        }
        displayData(scoreList);
    }

    private void displayData(List<Score> l) {
        tableModel.setRowCount(0);
        for (int i = 0; i < l.size(); i++) {
            Score cs = l.get(i);

            tableModel.addRow(new Object[]{"" + (i + 1), cs.getStudentID(), cs.getStudentName()
                    , Helper.toString(cs.getGk()), Helper.toString(cs.getCk()), Helper.toString(cs.getKhac())
                    , Helper.toString(cs.getTong())});
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
            displayData(scoreList);
            return;
        }
        String key = keyTxf.getText();
        displayData(scoreList.stream().filter((s) -> s.getStudentID().contains(key)).collect(Collectors.toList()));
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
        tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return !(column == 0 || column == 7);
            }
        };
        table = new JTable(tableModel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        appBarPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));

        submitBtn.setText("Lưu");
        submitBtn.addActionListener(this::submitBtnActionPerformed);
        appBarPanel.add(submitBtn);

        backBtn.setText("Huỷ");
        backBtn.addActionListener(this::backBtnActionPerformed);
        appBarPanel.add(backBtn);

        classIDLbl.setText("Mã Lớp");
        classIDLbl.setPreferredSize(new Dimension(60, 30));
        classIDPanel.add(classIDLbl);

        classIDCb.setPreferredSize(new Dimension(120, 30));
        classIDCb.addActionListener(this::classIDComboBoxActionPerformed);
        classIDPanel.add(classIDCb);

        optPanel.add(classIDPanel);

        subLbl.setText("Môn Học");
        subLbl.setPreferredSize(new Dimension(60, 30));
        subPanel.add(subLbl);

        subComboBox.setPreferredSize(new Dimension(120, 30));
        subComboBox.addActionListener(this::subComboBoxActionPerformed);
        subPanel.add(subComboBox);

        optPanel.add(subPanel);

        searchLbl.setText("MSSV");
        searchLbl.setPreferredSize(new Dimension(50, 30));
        searchPanel.add(searchLbl);

        keyTxf.setPreferredSize(new Dimension(120, 30));
        keyTxf.addActionListener(this::keyTxtActionPerformed);
        searchPanel.add(keyTxf);

        searchBtn.setText("Tìm Kiếm");
        searchBtn.setPreferredSize(new Dimension(90, 30));
        searchBtn.addActionListener(this::keyTxtActionPerformed);
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

    private void submitBtnActionPerformed(ActionEvent evt) {
        if (tableModel.getRowCount() == 0) {
            return;
        }
        int n = tableModel.getRowCount();
        String id;
        String name;
        String gk;
        String ck;
        String khac;
        String tong;
        String classID = (String) classIDCb.getSelectedItem();
        String subjectID = (String) subComboBox.getSelectedItem();
        boolean error = false;
        for (int i = 0; i < n; i++) {
            id = (String) tableModel.getValueAt(i, 1);
            name = (String) tableModel.getValueAt(i, 2);
            gk = (String) tableModel.getValueAt(i, 3);
            ck = (String) tableModel.getValueAt(i, 4);
            khac = (String) tableModel.getValueAt(i, 5);
            tong = (String) tableModel.getValueAt(i, 6);
            Score s = new Score(id, name, classID, subjectID,
                    Helper.parseFloat(gk), Helper.parseFloat(ck), Helper.parseFloat(khac), Helper.parseFloat(tong));

            if (!HibernateDAO.update(s)) {
                error = true;
                tableModel.setValueAt("F", i, 7);
            } else {
                tableModel.setValueAt("T", i, 7);
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
