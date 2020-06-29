package hibernate.dao;

import hibernate.java.ClassSchedule;
import hibernate.java.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClassScheduleDAO {
    public static ClassSchedule getStudent(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ClassSchedule schedule = null;
        try {
            schedule = session.get(ClassSchedule.class, id);
        } finally {
            session.close();
        }
        return schedule;
    }

    public static boolean add(ClassSchedule o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(o);
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

    public static boolean remove(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ClassSchedule schedule = null;
        try {
            schedule = session.get(ClassSchedule.class, id);
            session.delete(schedule);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            session.close();
        }
        return true;
    }

    public static List<ClassSchedule> getList(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<ClassSchedule> list = null;
        try {
            list = session.createQuery("from hibernate.java.ClassSchedule").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return list;
    }
}
