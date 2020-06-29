package hibernate.dao;

import hibernate.java.HibernateUtil;
import hibernate.java.Score;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ScoreDAO {
    public static Score getScore(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Score s = null;
        try {
            s = session.get(Score.class, id);
        } finally {
            session.close();
        }
        return s;
    }

    public static boolean addScore(Score s) {
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

    public static boolean removeScore(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Score s = null;
        try {
            s = session.get(Score.class, id);
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

    public static List<Score> getList(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Score> list = null;
        try {
            list = session.createQuery("from hibernate.java.Score").list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return list;
    }
}
