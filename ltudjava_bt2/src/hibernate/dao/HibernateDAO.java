package hibernate.dao;

import hibernate.java.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public class HibernateDAO {
    public static<T> T get(Class<T> c, Serializable s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        T object = null;
        try {
            object = session.get(c, s);
        } finally {
            session.close();
        }
        return object;
    }

    public static<T> boolean add(Object o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(o);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean remove(Object o) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.delete(o);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean update(Object s) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(s);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return true;
    }

    public static List getList(String query) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List list = null;
        try {
            list = session.createQuery(query).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }
}
