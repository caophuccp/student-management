package hibernate.java;

import java.io.Serializable;

public class Score implements Serializable {
    String studentID;
    String classID;
    String subjectID;
    Float gk;
    Float ck;
    Float khac;
    Float tong;

    public Score() {
        this.studentID = null;
        this.classID = null;
        this.gk = null;
        this.ck = null;
        this.khac = null;
        this.tong = null;
    }

    public Score(String studentID, String classID, String subjectID, Float gk, Float ck, Float khac, Float tong) {
        this.studentID = studentID;
        this.classID = classID;
        this.subjectID = subjectID;
        this.gk = gk;
        this.ck = ck;
        this.khac = khac;
        this.tong = tong;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public Float getGk() {
        return gk;
    }

    public void setGk(Float gk) {
        this.gk = gk;
    }

    public Float getCk() {
        return ck;
    }

    public void setCk(Float ck) {
        this.ck = ck;
    }

    public Float getKhac() {
        return khac;
    }

    public void setKhac(Float khac) {
        this.khac = khac;
    }

    public Float getTong() {
        return tong;
    }

    public void setTong(Float tong) {
        this.tong = tong;
    }

    @Override
    public String toString() {
        return "Score{" +
                "studentID='" + studentID + '\'' +
                ", classID='" + classID + '\'' +
                ", subjectID='" + subjectID + '\'' +
                ", gk=" + gk +
                ", ck=" + ck +
                ", khac=" + khac +
                ", tong=" + tong +
                '}';
    }
}
