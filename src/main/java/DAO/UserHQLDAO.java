package DAO;

import model.User;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserHQLDAO implements UserDao {

    private SessionFactory sessionFactory;
    private Configuration configuration;

    public UserHQLDAO(Configuration configuration) {
        this.configuration = configuration;
        sessionFactory = createSessionFactory(configuration);
    }

    private SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public List<User> getAllUsers() {
        try {
            Session session = sessionFactory.openSession();
            Query<User> query = session.createQuery("FROM User u", User.class);
            List<User> userList = query.getResultList();
            session.close();
            return userList;
        } catch (HibernateException he) {
            he.printStackTrace();
            throw new HibernateException(he);
        }
    }

    @Override
    public void addUser(String name, String login, String password) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            User user = new User(name, login, password);
            session.save(user);
            transaction.commit();
            session.close();
        } catch (HibernateException he) {
            he.printStackTrace();
            throw new HibernateException(he);
        }
    }

    @Override
    public void updateUser(String name, String login, String password) {
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
            he.printStackTrace();
            throw new HibernateException(he);
        }
    }

    @Override
    public void deleteUser(Integer id) {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE User u WHERE u.id=:id");
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException he) {
            he.printStackTrace();
            throw new HibernateException(he);
        }
    }

    //проверяет наличие юзера в базе по логину
    @Override
    public boolean checkUser(String login) {
        try {
            Session session = sessionFactory.openSession();
            Query<User> query = session.createQuery("FROM User u WHERE u.login=:login");
            query.setParameter("login", login);
            User user = query.uniqueResult();
            session.close();
            return user != null;
        } catch (HibernateException he) {
            he.printStackTrace();
            throw new HibernateException(he);
        }
    }

    @Override
    public void dropTable() {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("delete from User");
            query.executeUpdate();
            transaction.commit();
            session.close();
        } catch (HibernateException he) {
            he.printStackTrace();
            throw new HibernateException(he);
        }
    }
}
