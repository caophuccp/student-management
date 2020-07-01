package screens;

import helpers.CSVReader;
import helpers.Helper;
import hibernate.dao.*;
import hibernate.java.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


public class ImportClassScheduleScreen extends Screen {
    Account currentUser;
    List<Subject> subjectList;
    List<ClassSchedule> csList;
    public ImportClassScheduleScreen(Account currentUser) {
        this.currentUser = currentUser;
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);

        tableModel.addColumn("STT");
        tableModel.addColumn("Mã Môn");
        tableModel.addColumn("Tên Môn");
        tableModel.addColumn("Phòng Học");
        tableModel.addColumn("Trạng Thái");

        List<IClass> il = IClassDAO.getList();
        if (il.isEmpty()) {
            classModel.addElement("----");
        }
        for (IClass iClass : il) {
            classModel.addElement(iClass.getClassID());
        }
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
        tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return !(column == 0 || column == 4);
            }
        };
        table = new JTable(tableModel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        backBtn.setText("huy");
        backBtn.addActionListener(this::backBtnActionPerformed);

        appBarPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));

        submitBtn.setText("Thêm");
        submitBtn.addActionListener(this::submitBtnActionPerformed);
        appBarPanel.add(submitBtn);

        backBtn.setText("Huỷ");
        appBarPanel.add(backBtn);


        classIDLbl.setText("Mã Lớp");
        classIDLbl.setPreferredSize(new Dimension(60, 30));
        classIDPanel.add(classIDLbl);

        classIDCb.setPreferredSize(new Dimension(120, 30));
        classIDPanel.add(classIDCb);

        fileChooserPanel.setLayout(new GridLayout(2, 0));

        fileChooserBtn.setText("Chọn File");
        fileChooserBtn.setPreferredSize(new Dimension(120, 30));
        fileChooserBtn.addActionListener(this::fileChooserBtnActionPerformed);
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
                                .addComponent(fileChooserPanel, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                .addContainerGap())
        );
        optPanelLayout.setVerticalGroup(
                optPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(optPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(optPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(fileChooserPanel, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(classIDPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void submitBtnActionPerformed(ActionEvent e) {
        if (tableModel.getRowCount() == 0) {
            return;
        }
        int n = subjectList.size();
        String classID = (String) classIDCb.getSelectedItem();
        boolean error = false;
        for (int i = 0; i < n; i++) {
            Subject sb = subjectList.get(i);
            ClassSchedule cs = csList.get(i);
            cs.setClassID(classID);
            if (!SubjectDAO.addSubject(sb) || !ClassScheduleDAO.add(cs)) {
                error = true;
                tableModel.setValueAt("F", i, 4);
            } else {
                tableModel.setValueAt("T", i, 4);
            }
            StudentDAO.getList().stream()
                    .filter((s)->s.getClassID().equals(classID)).collect(Collectors.toList())
                    .forEach((s)-> StudentLOSDAO.add(new StudentLOS(s.getStudentID(),classID,sb.getId())));
        }

        if (!error) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Thêm Thành Công","Message",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Thêm Thất Bại","Message",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void backBtnActionPerformed(ActionEvent e) {
        if (currentUser.getCategory() == 1) {
            changeScreen(new TCHomeScreen(currentUser));
        } else {
            changeScreen(new SVHomeScreen(currentUser));
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
            buildTableItem(csList = CSVReader.getClassSchList(file),subjectList = CSVReader.getSubjectList(file));
        }
    }

    void buildTableItem(List<ClassSchedule> studentList, List<Subject> subjectList) {
        tableModel.setRowCount(0);
        for (int i = 0; i < studentList.size(); i++) {
            ClassSchedule cs = studentList.get(i);
            Subject s = subjectList.get(i);
            tableModel.addRow(new Object[]{"" + i, s.getId(), s.getName(), cs.getClassroom(), "-"});
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
}
