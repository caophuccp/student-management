package hibernate.dao;

import hibernate.java.Account;
import hibernate.java.HibernateUtil;
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
    public static Account get(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Account a = null;
        try {
            a = session.get(Account.class, id);
        } finally {
            session.close();
        }
        return a;
    }

    public static boolean update(Account a) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (get(a.getUsername()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(a);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return true;
    }
}
