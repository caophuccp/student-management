package hibernate.java;

public class StudentListOS implements java.io.Serializable{
    String studentID;
    String classID;
    String subjectID;

    public StudentListOS() {
        this.studentID = null;
        this.classID = null;
        this.subjectID = null;
    }

    public StudentListOS(String studentID, String classID, String subjectID) {
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
    public String toString() {
        return "SLOSubject{" +
                "studentID='" + studentID + '\'' +
                ", classID='" + classID + '\'' +
                ", subjectID='" + subjectID + '\'' +
                '}';
    }
}
