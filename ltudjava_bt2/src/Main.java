import hibernate.java.HibernateUtil;
import screens.Application;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        System.setProperty("apple.awt.application.name", "FAPP");
        HibernateUtil.getSessionFactory();
        Application app = new Application();
        app.start();
    }
}
