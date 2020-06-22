package screens;

import CSV.CSVReader;
import hibernate.java.Account;
import hibernate.java.Student;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;


public class ImportScoreTableScreen extends Screen {
    Account currentUser;
    public ImportScoreTableScreen(Account currentUser) {
        this.currentUser = currentUser;
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);

        model.addColumn("STT");
        model.addColumn("MSSV");
        model.addColumn("Họ Tên");
        model.addColumn("Điểm GK");
        model.addColumn("Điểm CK");
        model.addColumn("Điểm Khác");
        model.addColumn("Điểm Tổng");

        classModel.addElement("18_1");
        classModel.addElement("18_2");
        classModel.addElement("18_3");
        classModel.addElement("18_4");

        subModel.addElement("----");
        subModel.addElement("CT01");
        subModel.addElement("CT02");
        subModel.addElement("CT03");
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
        table = new JTable(model);

        subModel = new DefaultComboBoxModel<>();
        subPanel = new JPanel();
        subLbl = new JLabel();
        subComboBox = new JComboBox(subModel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        backBtn.setText("Huỷ");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backBtnActionPerformed(e);
            }
        });

        appBarPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));

        submitBtn.setText("Thêm");
        submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                submitBtnActionPerformed(evt);
            }
        });
        appBarPanel.add(submitBtn);

        backBtn.setText("Huỷ");
        appBarPanel.add(backBtn);


        classIDLbl.setText("Mã Lớp");
        classIDLbl.setPreferredSize(new java.awt.Dimension(120, 30));
        classIDPanel.add(classIDLbl);

        classIDCb.setPreferredSize(new java.awt.Dimension(90, 30));
        classIDPanel.add(classIDCb);

        subLbl.setText("Môn Học");
        subLbl.setPreferredSize(new java.awt.Dimension(60, 30));
        subPanel.add(subLbl);

        subComboBox.setPreferredSize(new java.awt.Dimension(120, 30));
        subPanel.add(subComboBox);

        fileChooserPanel.setLayout(new java.awt.GridLayout(2, 0));

        fileChooserBtn.setText("Chọn File");
        fileChooserBtn.setPreferredSize(new java.awt.Dimension(120, 30));
        fileChooserBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fileChooserBtnActionPerformed(evt);
            }
        });
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

    }

    private void backBtnActionPerformed(ActionEvent evt) {
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
            fileLbl.setText(file.getAbsolutePath());
            buildTableItem(CSVReader.getList(file));
        }
    }

    void buildTableItem(List<Student> studentList) {
        model.setRowCount(0);
        for (int i = 0; i < studentList.size(); i++) {
            Student s = studentList.get(i);
            model.addRow(new Object[]{"" + i, s.getStudentID(), s.getName(), s.getGender(), s.getIdCardNo()});
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
    private DefaultTableModel model = new DefaultTableModel();
    private DefaultComboBoxModel<String> classModel = new DefaultComboBoxModel<>();
    private JButton submitBtn = new JButton();

    private JPanel subPanel;
    private JLabel subLbl;
    private JComboBox subComboBox;
    private DefaultComboBoxModel<String> subModel;
}
