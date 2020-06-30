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
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean removeStudent(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Student student = getStudent(id);
        if (student == null) return false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(student);
            transaction.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return true;
    }

    public static boolean update(Student s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        if (getStudent(s.getStudentID()) == null) {
            return false;
        }
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(s);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return true;
    }

    public static List<Student> getList(String query) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Student> list = null;
        try {
            list = session.createQuery(query).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public static List<Student> getList() {
        return getList("from hibernate.java.Student");
    }
}
