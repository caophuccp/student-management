package screens;

import helpers.Helper;
import hibernate.dao.DAOUtils;
import hibernate.java.Account;
import hibernate.java.ClassSchedule;
import hibernate.java.IClass;
import hibernate.java.Score;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class StudentScoreScreen extends Screen{
    Account currentUser;
    public StudentScoreScreen(JFrame parent, Account currentUser) {
        super(parent);
        this.currentUser = currentUser;
        initComponents();

        table.setEnabled(false);
        tableModel.addColumn("STT");
        tableModel.addColumn("Mã Môn");
        tableModel.addColumn("Mã Lớp");
        tableModel.addColumn("Giữa Kỳ");
        tableModel.addColumn("Cuối Kỳ");
        tableModel.addColumn("Khác");
        tableModel.addColumn("Tổng");

        String query = "from hibernate.java.IClass";
        List<IClass> il = DAOUtils.getList(query);
        classModel.addElement("----");
        for (IClass iClass : il) {
            classModel.addElement(iClass.getClassID());
        }

        reloadSubjectModel();
    }

    private void reloadSubjectModel(){
        subjectModel.removeAllElements();
        subjectModel.addElement("----");
        String classID = (String)classIDComboBox.getSelectedItem();
        String query = "from hibernate.java.ClassSchedule CS where CS.classID = '" + classID + "'";
        List<ClassSchedule> cl = DAOUtils.getList(query);
        cl.forEach((cs) -> subjectModel.addElement(cs.getSubjectID()));
    }

    private void classIDCBActionPerformed(ActionEvent e){
        reloadSubjectModel();
        reloadData();
    }

    private void subCBActionPerformed(ActionEvent e){
        reloadData();
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

        appBarPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));

        backBtn.setText("Huỷ");
        backBtn.addActionListener(this::backBtnActionPerformed);
        appBarPanel.add(backBtn);

        classIDLbl.setText("Mã Lớp");
        classIDLbl.setPreferredSize(new Dimension(60, 30));
        classIDPanel.add(classIDLbl);

        classIDComboBox.setModel(classModel);
        classIDComboBox.addActionListener(this::classIDCBActionPerformed);
        classIDPanel.add(classIDComboBox);

        optPanel.add(classIDPanel);

        subLbl.setText("Môn Học");
        subLbl.setPreferredSize(new Dimension(60, 30));
        subPanel.add(subLbl);

        subComboBox.setModel(subjectModel);
        subComboBox.setModel(subjectModel);
        subComboBox.addActionListener(this::subCBActionPerformed);
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

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
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

    }

    private void backBtnActionPerformed(ActionEvent evt) {
        if (currentUser.getCategory() == 1) {
            changeScreen(new TCHomeScreen(parent, currentUser));
        } else {
            changeScreen(new SVHomeScreen(parent, currentUser));
        }
    }
    
    void reloadData(){
        String classID = (String) classIDComboBox.getSelectedItem();
        String subjectID = (String) subComboBox.getSelectedItem();
        String query = "from hibernate.java.Score CS where CS.studentID = '" + currentUser.getUsername() + "'";
        if (subjectID != null && !"----".equals(subjectID)) query += "and CS.subjectID = '" + subjectID + "'";
        if (classID != null && !"----".equals(classID)) query += "and CS.classID = '" + classID + "'";


        List<Score> l = DAOUtils.getList(query);
        tableModel.setRowCount(0);
        for (int i = 0; i < l.size(); i++) {
            Score s = l.get(i);
            tableModel.addRow(new Object[]{"" + (i + 1), s.getSubjectID(), s.getClassID()
                    , Helper.toString(s.getGk()), Helper.toString(s.getCk()),
                    Helper.toString(s.getKhac()), Helper.toString(s.getTong())
            });
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
