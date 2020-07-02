package screens;

import hibernate.dao.*;
import hibernate.java.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreTableScreen extends Screen{
    Account currentUser;
    public ScoreTableScreen(JFrame parent, Account currentUser) {
        super(parent);
        this.currentUser = currentUser;
        initComponents();
//        setLocationRelativeTo(null);
        setVisible(true);

        table.setEnabled(false);

        tableModel.addColumn("STT");
        tableModel.addColumn("MSSV");
        tableModel.addColumn("Họ Tên");
        tableModel.addColumn("Điểm Giữa Kỳ");
        tableModel.addColumn("Điểm Cuối Kỳ");
        tableModel.addColumn("Điểm Khác");
        tableModel.addColumn("Điểm Tổng");
        tableModel.addColumn("Kết Quả");

        tkTabelModel.addColumn("Tổng");
        tkTabelModel.addColumn("Số Đậu");
        tkTabelModel.addColumn("Phần Trăm Đậu");
        tkTabelModel.addColumn("Số Rớt");
        tkTabelModel.addColumn("Phần Trăm Rớt");
        tkTabelModel.addRow(new Object[]{0,0,0,0,0});

        String query = "from hibernate.java.IClass";
        List<IClass> il = HibernateDAO.getList(query);
        if (il.isEmpty()) {
            classModel.addElement("----");
        }
        for (IClass iClass : il) {
            classModel.addElement(iClass.getClassID());
        }

        reloadSubjectModel();

        backBtn.addActionListener(this::backBtnActionPerformed);
        classIDComboBox.addActionListener(this::classIDComboBoxActionPerformed);
        subComboBox.addActionListener(this::subComboBoxActionPerformed);
    }
    private void reloadSubjectModel(){
        subjectModel.removeAllElements();
        subjectModel.addElement("----");
        String classID = (String)classIDComboBox.getSelectedItem();
        String query = "from hibernate.java.ClassSchedule CS where CS.classID = '" + classID + "'";
        List<ClassSchedule> cl = HibernateDAO.getList(query);
        cl.forEach((cs) -> subjectModel.addElement(cs.getSubjectID()));
    }

    void reloadData(){
        String classID = (String) classIDComboBox.getSelectedItem();
        String subjectID = (String) subComboBox.getSelectedItem();
        String query = "from hibernate.java.Score S where S.classID = '" + classID + "' and S.subjectID = '" + subjectID + "'";
        List<Score> l = HibernateDAO.getList(query);
        for (Score score : l) {
            Student s = HibernateDAO.get(Student.class, score.getStudentID());
            score.setStudentName(s.getName());
        }
        tableModel.setRowCount(0);
        int d = 0;
        for (int i = 0; i < l.size(); i++) {
            Score cs = l.get(i);
            String rs;
            if (cs.getTong() < 5) {
                rs = "Đậu";
                d += 1;
            } else {
                rs = "Rớt";
            }
            tableModel.addRow(new Object[]{"" + (i + 1), cs.getStudentID(), cs.getStudentName(), cs.getGk(),
            cs.getCk(), cs.getKhac(), cs.getTong(), rs});
        }

        int percentPass = Math.round(100*(float)d/(float)l.size());
        tkTabelModel.setRowCount(0);
        tkTabelModel.addRow(new Object[]{l.size(), d, percentPass, l.size() - d, 100 - percentPass});
    }

    private void classIDComboBoxActionPerformed(ActionEvent e) {
        reloadSubjectModel();
        reloadData();
    }

    private void subComboBoxActionPerformed(ActionEvent e) {
        reloadData();
    }

    private Container getContentPane(){
        return this;
    }

    private void pack(){
        parent.pack();
    }

    private void initComponents() {

        classModel = new DefaultComboBoxModel<>();
        subjectModel = new DefaultComboBoxModel<>();

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

        tkTabelModel = new DefaultTableModel();
        tkPanel = new JPanel();
        tkScrollPanel = new JScrollPane();
        tkTable = new JTable(tkTabelModel);

//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        appBarPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));

        backBtn.setText("Huỷ");
        appBarPanel.add(backBtn);

        classIDLbl.setText("Mã Lớp");
        classIDLbl.setPreferredSize(new Dimension(60, 30));
        classIDPanel.add(classIDLbl);

        classIDComboBox.setModel(classModel);
        classIDPanel.add(classIDComboBox);

        optPanel.add(classIDPanel);

        subLbl.setText("Môn Học");
        subLbl.setPreferredSize(new Dimension(60, 30));
        subPanel.add(subLbl);

        subComboBox.setModel(subjectModel);
        subPanel.add(subComboBox);

        optPanel.add(subPanel);

        tkPanel.setLayout(new BoxLayout(tkPanel, BoxLayout.LINE_AXIS));
        tkScrollPanel.setViewportView(tkTable);
        tkPanel.add(tkScrollPanel);

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
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(tkPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE))
                                .addContainerGap())
        );
        bodyPanelLayout.setVerticalGroup(
                bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(bodyPanelLayout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(optPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tkPanel, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
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
            changeScreen(new TCHomeScreen(parent, currentUser));
        } else {
            changeScreen(new SVHomeScreen(parent, currentUser));
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
    private javax.swing.JPanel tkPanel;
    private javax.swing.JScrollPane tkScrollPanel;
    private javax.swing.JTable tkTable;
    private DefaultTableModel tkTabelModel;
}
