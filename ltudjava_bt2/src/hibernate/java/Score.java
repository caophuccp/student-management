package hibernate.java;

import java.io.Serializable;
import java.util.Objects;

public class Score implements Serializable {
    String studentID;
    String classID;
    String subjectID;
    Float gk;
    Float ck;
    Float khac;
    Float tong;
    String studentName;

    public Score() {
        this.studentID = null;
        this.classID = null;
        this.gk = null;
        this.ck = null;
        this.khac = null;
        this.tong = null;
        this.studentName = null;
    }

    public Score(String studentID, String studentName, String classID, String subjectID, Float gk, Float ck, Float khac, Float tong) {
        this.studentID = studentID;
        this.classID = classID;
        this.subjectID = subjectID;
        this.gk = gk;
        this.ck = ck;
        this.khac = khac;
        this.tong = tong;
        this.studentName = studentName;
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

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return Objects.equals(studentID, score.studentID) &&
                Objects.equals(classID, score.classID) &&
                Objects.equals(subjectID, score.subjectID) &&
                Objects.equals(gk, score.gk) &&
                Objects.equals(ck, score.ck) &&
                Objects.equals(khac, score.khac) &&
                Objects.equals(tong, score.tong) &&
                Objects.equals(studentName, score.studentName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, classID, subjectID);
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
