package hibernate.dao;

import hibernate.java.HibernateUtil;
import hibernate.java.Subject;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SubjectDAO {
    public static Subject getSubject(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Subject s = null;
        try {
            s = session.get(Subject.class, id);
        } finally {
            session.close();
        }
        return s;
    }

    public static boolean addSubject(Subject s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(s);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            return false;
        }
        finally {
            session.close();
        }
        return true;
    }

    public static boolean removeSubject(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Subject s = null;
        try {
            s = session.get(Subject.class, id);
            session.delete(s);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            session.close();
        }
        return true;
    }

    public static List<Subject> getList(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Subject> list = null;
        try {
            list = session.createQuery("from hibernate.java.Subject").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return list;
    }
}
