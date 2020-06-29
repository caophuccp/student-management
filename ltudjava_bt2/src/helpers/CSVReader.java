package helpers;

import hibernate.java.ClassSchedule;
import hibernate.java.Score;
import hibernate.java.Student;
import hibernate.java.Subject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static List<Student> getStudentList(File file) {
        List<Student> list = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = in.readLine();
            String[] tps = line.split(",");
            if (tps.length != 5) return list;
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

    public static List<ClassSchedule> getClassSchList(File file) {
        List<ClassSchedule> list = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = in.readLine();
            String[] tps = line.split(",");
            if (tps.length != 4) return list;
            line = in.readLine();
            while (line != null) {
                tps = line.split(",");
                ClassSchedule cs = new ClassSchedule(tps[1], "", tps[3]);
                list.add(cs);
                line = in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Subject> getSubjectList(File file) {
        List<Subject> list = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = in.readLine();
            String[] tps = line.split(",");
            if (tps.length != 4) return list;
            line = in.readLine();
            while (line != null) {
                tps = line.split(",");
                Subject s = new Subject(tps[1], tps[2]);
                list.add(s);
                line = in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Score> getScoreList(File file) {

        List<Score> list = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = in.readLine();
            String[] tps = line.split(",");
            if (tps.length != 7) return list;
            line = in.readLine();
            while (line != null) {
                tps = line.split(",");
                Score s = new Score(tps[1], tps[2],"", "",
                        Helper.parseFloat(tps[3]), Helper.parseFloat(tps[4]), Helper.parseFloat(tps[5]),Helper.parseFloat(tps[6]));
                list.add(s);
                line = in.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
