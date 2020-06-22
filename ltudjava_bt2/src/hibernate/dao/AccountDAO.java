package hibernate.dao;

import hibernate.java.Account;
import hibernate.java.HibernateUtil;
import hibernate.java.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AccountDAO {
    public static List<Account> getList(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Account> accounts = null;
        try {
            accounts = session.createQuery("from hibernate.java.Account").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return accounts;
    }

    public static Account getAccount(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Account acc = null;
        try {
            acc = session.get(Account.class, username);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return acc;

    }
}
