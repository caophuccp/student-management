package hibernate.dao;

import hibernate.java.HibernateUtil;
import hibernate.java.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StudentDAO {
    public static Student getStudent(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Student student = null;
        try {
            student = session.get(Student.class, id);
        } finally {
            session.close();
        }
        return student;
    }

    public static boolean addStudent(Student s) {
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

    public static boolean removeStudent(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Student student = null;
        try {
            student = session.get(Student.class, id);
            session.delete(student);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        finally {
            session.close();
        }
        return true;
    }

    public static List<Student> getList(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> list = null;
        try {
            list = session.createQuery("from hibernate.java.Student").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return list;
    }
}