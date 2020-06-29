package CSV;

import hibernate.java.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static List<Student> getList(File file) {
        List<Student> list = new ArrayList<>();
        try {
//            STT,MSSV,Họ và Tên,Giới tính,CMND
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = in.readLine();
            String[] tps = line.split(",");
            if (tps.length != 5) return  list;
            line = in.readLine();
            while (line != null) {
                tps = line.split(",");
                Student s = new Student(tps[1], tps[2], tps[3], tps[4], "");
                list.add(s);
                line = in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
