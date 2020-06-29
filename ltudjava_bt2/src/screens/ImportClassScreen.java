package screens;

import helpers.CSVReader;
import helpers.Helper;
import hibernate.dao.IClassDAO;
import hibernate.dao.StudentDAO;
import hibernate.java.Account;
import hibernate.java.IClass;
import hibernate.java.Student;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;


public class ImportClassScreen extends Screen {
    Account currentUser;

    public ImportClassScreen(Account currentUser) {
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

    }

    private void initComponents() {

        appBarPanel = new javax.swing.JPanel();
        backBtn = new javax.swing.JButton();
        separator = new javax.swing.JSeparator();
        bodyPanel = new javax.swing.JPanel();
        optPanel = new javax.swing.JPanel();
        classIDPanel = new javax.swing.JPanel();
        classIDLbl = new javax.swing.JLabel();
        classIDTxf = new javax.swing.JTextField();
        fileChooserPanel = new javax.swing.JPanel();
        btnPanel = new javax.swing.JPanel();
        fileChooserBtn = new javax.swing.JButton();
        fileLbl = new javax.swing.JLabel();
        tablePanel = new javax.swing.JPanel();
        tableScrollPanel = new javax.swing.JScrollPane();
        tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return !(column == 0 || column == 5);
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


        classIDLbl.setText("Ma Lop");
        classIDLbl.setPreferredSize(new java.awt.Dimension(60, 30));
        classIDPanel.add(classIDLbl);

        classIDTxf.setPreferredSize(new java.awt.Dimension(90, 30));
        classIDPanel.add(classIDTxf);

        fileChooserPanel.setLayout(new java.awt.GridLayout(2, 0));

        fileChooserBtn.setText("Chon File");
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

    private void submitBtnActionPerformed(ActionEvent evt) {
        if (classIDTxf.getText().isEmpty() || tableModel.getRowCount() == 0) {
            return;
        }
        int n = tableModel.getRowCount();
        String id;
        String name;
        String gender;
        String idcard;
        String classID = classIDTxf.getText();
        IClassDAO.add(new IClass(classID));
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
        }

        if (!error) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Thêm Thành Công","Message",JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(new JFrame(),
                    "Thêm Thất Bại","Message",JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void backBtnActionPerformed(ActionEvent evt) {
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
            buildTableItem(CSVReader.getStudentList(file));
        }
    }

    void buildTableItem(List<Student> studentList) {
        tableModel.setRowCount(0);

        for (int i = 0; i < studentList.size(); i++) {
            Student s = studentList.get(i);
            tableModel.addRow(new Object[]{"" + (i + 1), s.getStudentID(), s.getName(), s.getGender(), s.getIdCardNo(),"-"});
        }
    }


    private javax.swing.JPanel appBarPanel;
    private javax.swing.JButton backBtn;
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JPanel btnPanel;
    private javax.swing.JLabel classIDLbl;
    private javax.swing.JPanel classIDPanel;
    private javax.swing.JTextField classIDTxf;
    private javax.swing.JButton fileChooserBtn;
    private javax.swing.JPanel fileChooserPanel;
    private javax.swing.JLabel fileLbl;
    private javax.swing.JPanel optPanel;
    private javax.swing.JSeparator separator;
    private javax.swing.JTable table;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JScrollPane tableScrollPanel;
    private DefaultTableModel tableModel;
//    private DefaultTableCellRenderer tableCellRenderer;
    private JButton submitBtn = new JButton();

}
