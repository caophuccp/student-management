package hibernate.dao;

import hibernate.java.HibernateUtil;
import hibernate.java.StudentLOS;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentLOSDAO {
    public static StudentLOS getStudentListOS(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        StudentLOS s = null;
        try {
            s = session.get(StudentLOS.class, id);
        } finally {
            session.close();
        }
        return s;
    }

    public static boolean add(StudentLOS s) {
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

    public static boolean removeStudentListOS(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        StudentLOS s = null;
        try {
            s = session.get(StudentLOS.class, id);
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

    public static List<StudentLOS> getList(String query){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<StudentLOS> list = null;
        try {
            list = session.createQuery(query).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return list;
    }

    public static List<StudentLOS> getList(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<StudentLOS> list = null;
        try {
            list = session.createQuery("from hibernate.java.StudentLOS").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return list;
    }
}
