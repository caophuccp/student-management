package hibernate.java;

import java.util.Objects;

public class StudentLOS implements java.io.Serializable{
    String studentID;
    String classID;
    String subjectID;

    public StudentLOS() {
        this.studentID = null;
        this.classID = null;
        this.subjectID = null;
    }

    public StudentLOS(String studentID, String classID, String subjectID) {
        this.studentID = studentID;
        this.classID = classID;
        this.subjectID = subjectID;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentLOS los = (StudentLOS) o;
        return Objects.equals(studentID, los.studentID) &&
                Objects.equals(classID, los.classID) &&
                Objects.equals(subjectID, los.subjectID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, classID, subjectID);
    }

    @Override
    public String toString() {
        return "SLOSubject{" +
                "studentID='" + studentID + '\'' +
                ", classID='" + classID + '\'' +
                ", subjectID='" + subjectID + '\'' +
                '}';
    }
}
