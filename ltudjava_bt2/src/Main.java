import hibernate.java.HibernateUtil;
import screens.Application;

public class Main {
    public static void main(String[] args) {
        HibernateUtil.getSessionFactory();
        Application app = new Application();
        app.start();
    }
}
