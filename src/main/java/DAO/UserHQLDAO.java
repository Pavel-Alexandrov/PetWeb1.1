package DAO;

import exception.StatementException;
import model.User;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import util.DBHelper;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserHQLDAO implements UserDao {

    private SessionFactory sessionFactory;
    private static UserHQLDAO userHQLDAO;

    static UserHQLDAO getInstance() {
        if (userHQLDAO == null) {
            userHQLDAO = new UserHQLDAO(DBHelper.getSessionFactory());
        }
        return userHQLDAO;
    }

    private UserHQLDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> getAllUsers() throws StatementException {
        try {
            Session session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(User.class);
            List<User> userList = criteria.list();
            session.close();
            return userList;
        } catch (HibernateException he) {
            throw new StatementException(he);
        }
    }

    @Override
    public void addUser(String name, String login, String password) throws StatementException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            User user = new User(name, login, password);
            session.save(user);
            transaction.commit();
            session.close();
        } catch (HibernateException he) {
            throw new StatementException(he);
        }
    }

    @Override
    public void updateUser(String name, String login, String password) throws StatementException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE User u SET u.name = :name, u.password = :password where u.login = :login");
            query.setParameter("name", name);
            query.setParameter("password", password);
            query.setParameter("login", login);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException he) {
            throw new StatementException(he);
        }
    }

    @Override
    public void deleteUser(Integer id) throws StatementException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            User user = (User) criteria.add(Restrictions.eq("id", id))
                    .uniqueResult();
            session.delete(user);
            transaction.commit();
            session.close();
        } catch (HibernateException he) {
            throw new StatementException(he);
        }
    }

    @Override
    public boolean checkUser(String login) throws StatementException {
        try {
            Session session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria(User.class);
            User user = (User) criteria.add(Restrictions.eq("login", login))
                    .uniqueResult();
            session.close();
            if (user == null) {
                return false;
            }
            return true;
        } catch (HibernateException he) {
            throw new StatementException(he);
        }
    }

    @Override
    public void dropTable() throws StatementException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("delete from User");
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException he) {
            throw new StatementException(he);
        }
    }
}
