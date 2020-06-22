import hibernate.dao.AccountDAO;
import hibernate.dao.StudentDAO;
import hibernate.java.Account;
import hibernate.java.HibernateUtil;
import hibernate.java.Student;
import screens.Application;

public class Main {
    public static void main(String[] args) {
        HibernateUtil.getSessionFactory();
        Application app = new Application();
        app.start();
    }
}
