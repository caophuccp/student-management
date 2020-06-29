package hibernate.dao;

import hibernate.java.HibernateUtil;
import hibernate.java.Score;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ScoreDAO {
    public static Score get(Score s) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Score score = null;
        try {
            score = session.get(Score.class, s);
        } finally {
            session.close();
        }
        return score;
    }

    public static boolean add(Score s) {
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

    public static boolean removeScore(Score score) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Score s = null;
            s = session.get(Score.class, score);
            session.delete(s);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean update(Score s) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Score tmp = new Score(s.getStudentID(), null, s.getClassID(),
                s.getSubjectID(), null, null, null, null);
        if (get(tmp) == null) {
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

    public static List<Score> getList() {
        return getList("from hibernate.java.Score");
    }

    public static List<Score> getList(String query) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Score> list = null;
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
