package hibernate.java;

import java.io.Serializable;

public class IClass implements Serializable {
    String classID;

    public IClass() {
        this.classID = null;
    }

    public IClass(String classID) {
        this.classID = classID;
    }

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
    }
}
