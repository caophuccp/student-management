package hibernate.java;

import java.io.Serializable;

public class ClassSchedule implements Serializable {
    String subjectID;
    String classID;
    String classroom;

    public ClassSchedule() {
        this.subjectID = null;
        this.classID = null;
        this.classroom = null;
    }

    public ClassSchedule(String subjectID, String classID, String classroom) {
        this.subjectID = subjectID;
        this.classID = classID;
        this.classroom = classroom;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}
