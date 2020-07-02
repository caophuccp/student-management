package hibernate.java;

import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassSchedule schedule = (ClassSchedule) o;
        return Objects.equals(subjectID, schedule.subjectID) &&
                Objects.equals(classID, schedule.classID) &&
                Objects.equals(classroom, schedule.classroom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectID, classID, classroom);
    }
}
