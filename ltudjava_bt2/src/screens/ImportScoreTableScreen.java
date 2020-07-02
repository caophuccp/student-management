package screens;

import helpers.CSVReader;
import helpers.Helper;
import hibernate.dao.HibernateDAO;
import hibernate.java.Account;
import hibernate.java.ClassSchedule;
import hibernate.java.IClass;
import hibernate.java.Score;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;


public class ImportScoreTableScreen extends Screen {
    Account currentUser;

    public ImportScoreTableScreen(JFrame parent, Account currentUser) {
        super(parent);
        this.currentUser = currentUser;
        initComponents();
//        setLocationRelativeTo(null);
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

        backBtn.addActionListener(this::backBtnActionPerformed);
        submitBtn.addActionListener(this::submitBtnActionPerformed);
        classIDCb.addActionListener(this::classIDCbActionPerformed);
        subComboBox.addActionListener(this::subComboBoxActionPerformed);
        fileChooserBtn.addActionListener(this::fileChooserBtnActionPerformed);
    }

    private void reloadSubjectModel() {
        subjectModel.removeAllElements();
        subjectModel.addElement("----");
        String classID = (String) classIDCb.getSelectedItem();
        String query = "from hibernate.java.ClassSchedule CS where CS.classID = '" + classID + "'";
        List<ClassSchedule> cl = HibernateDAO.getList(query);
        cl.forEach((cs) -> subjectModel.addElement(cs.getSubjectID()));
    }

    private void reloadData() {

    }
    private Container getContentPane(){
        return this;
    }

    private void pack(){
        parent.pack();
    }
    private void initComponents() {

        appBarPanel = new JPanel();
        backBtn = new JButton();
        separator = new JSeparator();
        bodyPanel = new JPanel();
        optPanel = new JPanel();
        classIDPanel = new JPanel();
        classIDLbl = new JLabel();
        classIDCb = new JComboBox<>(classModel);
        fileChooserPanel = new JPanel();
        btnPanel = new JPanel();
        fileChooserBtn = new JButton();
        fileLbl = new JLabel();
        tablePanel = new JPanel();
        tableScrollPanel = new JScrollPane();
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return !(column == 0 || column == 7);
            }
        };
        table = new JTable(tableModel);

        subjectModel = new DefaultComboBoxModel<>();
        subPanel = new JPanel();
        subLbl = new JLabel();
        subComboBox = new JComboBox<>(subjectModel);

//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        appBarPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));

        submitBtn.setText("Thêm");
        appBarPanel.add(submitBtn);

        backBtn.setText("Huỷ");
        appBarPanel.add(backBtn);

        classIDLbl.setText("Mã Lớp");
        classIDLbl.setPreferredSize(new Dimension(120, 30));
        classIDPanel.add(classIDLbl);

        classIDCb.setPreferredSize(new Dimension(120, 30));
        classIDPanel.add(classIDCb);

        subLbl.setText("Môn Học");
        subLbl.setPreferredSize(new Dimension(60, 30));
        subPanel.add(subLbl);

        subComboBox.setPreferredSize(new Dimension(120, 30));
        subPanel.add(subComboBox);

        fileChooserPanel.setLayout(new GridLayout(2, 0));

        fileChooserBtn.setText("Chọn File");
        fileChooserBtn.setPreferredSize(new Dimension(120, 30));
        btnPanel.add(fileChooserBtn);

        fileChooserPanel.add(btnPanel);

        fileLbl.setHorizontalAlignment(SwingConstants.CENTER);
        fileChooserPanel.add(fileLbl);

        GroupLayout optPanelLayout = new GroupLayout(optPanel);
        optPanel.setLayout(optPanelLayout);
        optPanelLayout.setHorizontalGroup(
                optPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(optPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(classIDPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fileChooserPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        optPanelLayout.setVerticalGroup(
                optPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(optPanelLayout.createSequentialGroup()
                                .addGroup(optPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(fileChooserPanel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(optPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(optPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(classIDPanel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                                        .addComponent(subPanel, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))
        );
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
                                .addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
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

            if (!HibernateDAO.add(s)) {
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

    private void subComboBoxActionPerformed(ActionEvent e) {
        reloadData();
    }

    private void classIDCbActionPerformed(ActionEvent e) {
        reloadSubjectModel();
        reloadData();
    }

    private void backBtnActionPerformed(ActionEvent evt) {
        if (currentUser.getCategory() == 1) {
            changeScreen(new TCHomeScreen(parent, currentUser));
        } else {
            changeScreen(new SVHomeScreen(parent, currentUser));
        }
    }

    private void fileChooserBtnActionPerformed(ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter f = new FileNameExtensionFilter("csv", "csv");
        fileChooser.setFileFilter(f);
        fileChooser.setMultiSelectionEnabled(false);
        int r = fileChooser.showDialog(this, "Chọn file");
        if (r == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            fileLbl.setText(Helper.getPath(file.getAbsolutePath()));
            buildTableItem(CSVReader.getScoreList(file));
        }
    }

    void buildTableItem(List<Score> studentList) {
        tableModel.setRowCount(0);
        for (int i = 0; i < studentList.size(); i++) {
            Score s = studentList.get(i);
            tableModel.addRow(new Object[]{String.valueOf(i + 1), s.getStudentID(), s.getStudentName(),
                    String.valueOf(s.getGk()), String.valueOf(s.getCk()),
                    String.valueOf(s.getKhac()), String.valueOf(s.getTong()), "-"});
        }
    }


    private JPanel appBarPanel;
    private JButton backBtn;
    private JPanel bodyPanel;
    private JPanel btnPanel;
    private JLabel classIDLbl;
    private JPanel classIDPanel;
    private JComboBox classIDCb;
    private JButton fileChooserBtn;
    private JPanel fileChooserPanel;
    private JLabel fileLbl;
    private JPanel optPanel;
    private JSeparator separator;
    private JTable table;
    private JPanel tablePanel;
    private JScrollPane tableScrollPanel;
    private DefaultTableModel tableModel;
    private DefaultComboBoxModel<String> classModel = new DefaultComboBoxModel<>();
    private JButton submitBtn = new JButton();

    private JPanel subPanel;
    private JLabel subLbl;
    private JComboBox subComboBox;
    private DefaultComboBoxModel<String> subjectModel;
}
