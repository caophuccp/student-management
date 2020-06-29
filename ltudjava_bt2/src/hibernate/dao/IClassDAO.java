package hibernate.dao;

import hibernate.java.HibernateUtil;
import hibernate.java.IClass;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class IClassDAO {
    public static IClass get(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        IClass ic = null;
        try {
            ic = session.get(IClass.class, id);
        } finally {
            session.close();
        }
        return ic;
    }

    public static boolean add(IClass s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(s);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return false;
        }
        finally {
            session.close();
        }
        return true;
    }

    public static boolean remove(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        IClass ic = null;
        try {
            ic = session.get(IClass.class, id);
            session.delete(ic);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            session.close();
        }
        return true;
    }

    public static List<IClass> getList(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<IClass> list = null;
        try {
            list = session.createQuery("from hibernate.java.IClass").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return list;
    }
}
