package screens;

import helpers.CSVReader;
import helpers.Helper;
import hibernate.dao.*;
import hibernate.java.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        appBarPanel = new javax.swing.JPanel();
        backBtn = new javax.swing.JButton();
        separator = new javax.swing.JSeparator();
        bodyPanel = new javax.swing.JPanel();
        optPanel = new javax.swing.JPanel();
        classIDPanel = new javax.swing.JPanel();
        classIDLbl = new javax.swing.JLabel();
        classIDCb = new javax.swing.JComboBox<>(classModel);
        fileChooserPanel = new javax.swing.JPanel();
        btnPanel = new javax.swing.JPanel();
        fileChooserBtn = new javax.swing.JButton();
        fileLbl = new javax.swing.JLabel();
        tablePanel = new javax.swing.JPanel();
        tableScrollPanel = new javax.swing.JScrollPane();
        tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return !(column == 0 || column == 4);
            }
        };
        table = new javax.swing.JTable(tableModel);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        backBtn.setText("huy");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backBtnActionPerformed(e);
            }
        });

        appBarPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));

        submitBtn.setText("Thêm");
        submitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });
        appBarPanel.add(submitBtn);

        backBtn.setText("Huỷ");
        appBarPanel.add(backBtn);


        classIDLbl.setText("Mã Lớp");
        classIDLbl.setPreferredSize(new java.awt.Dimension(60, 30));
        classIDPanel.add(classIDLbl);

        classIDCb.setPreferredSize(new java.awt.Dimension(120, 30));
        classIDPanel.add(classIDCb);

        fileChooserPanel.setLayout(new java.awt.GridLayout(2, 0));

        fileChooserBtn.setText("Chọn File");
        fileChooserBtn.setPreferredSize(new java.awt.Dimension(120, 30));
        fileChooserBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fileChooserBtnActionPerformed(evt);
            }
        });
        btnPanel.add(fileChooserBtn);

        fileChooserPanel.add(btnPanel);

        fileLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fileChooserPanel.add(fileLbl);

        javax.swing.GroupLayout optPanelLayout = new javax.swing.GroupLayout(optPanel);
        optPanel.setLayout(optPanelLayout);
        optPanelLayout.setHorizontalGroup(
                optPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(optPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(classIDPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fileChooserPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                .addContainerGap())
        );
        optPanelLayout.setVerticalGroup(
                optPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(optPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(optPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(fileChooserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(classIDPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
                                .addComponent(tablePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
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
                    .forEach((s)->{
                        StudentLOSDAO.add(new StudentLOS(s.getStudentID(),classID,sb.getId()));
                    });
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

    private void fileChooserBtnActionPerformed(java.awt.event.ActionEvent evt) {
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


    private javax.swing.JPanel appBarPanel;
    private javax.swing.JButton backBtn;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JPanel btnPanel;
    private javax.swing.JLabel classIDLbl;
    private javax.swing.JPanel classIDPanel;
    private javax.swing.JComboBox classIDCb;
    private javax.swing.JButton fileChooserBtn;
    private javax.swing.JPanel fileChooserPanel;
    private javax.swing.JLabel fileLbl;
    private javax.swing.JPanel optPanel;
    private javax.swing.JSeparator separator;
    private javax.swing.JTable table;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JScrollPane tableScrollPanel;
    private DefaultTableModel tableModel;
    private DefaultComboBoxModel<String> classModel = new DefaultComboBoxModel<>();
    private JButton submitBtn = new JButton();
}
