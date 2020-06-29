package hibernate.java;

import java.io.Serializable;

public class Subject implements Serializable {
    String id;
    String name;

    public Subject() {
        this.id = null;
        this.name = null;
    }

    public Subject(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
