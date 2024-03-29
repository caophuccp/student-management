package hibernate.java;

public class Student implements java.io.Serializable {
    String studentID;
    String name;
    String gender;
    String idCardNo;
    String classID;

    public Student(String studentID, String name, String gender, String idCardNo, String classID) {
        this.studentID = studentID;
        this.name = name;
        this.gender = gender;
        this.idCardNo = idCardNo;
        this.classID = classID;
    }

    public Student() {
        this.studentID = null;
        this.name = null;
        this.gender = null;
        this.idCardNo = null;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", idCardNo='" + idCardNo + '\'' +
                '}';
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }
}
