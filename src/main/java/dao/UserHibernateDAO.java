package dao;

import exeption.DBException;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import java.util.List;

public class UserHibernateDAO implements UserDAO {

    Configuration configuration;

    SessionFactory sessionFactory;

    public UserHibernateDAO(Configuration configuration){
        this.configuration = configuration;
        sessionFactory = createSessionFactory(configuration);
    }

    private SessionFactory createSessionFactory(Configuration configuration){
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public List<User> getAllUser() throws DBException {
        List<User> users = null;

        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("SELECT user FROM User user");
            users = query.list();
        } catch (HibernateException e) {
          throw new DBException(e);
        }
        return users;
    }

    @Override
    public User getUserById(long id) throws DBException {
        User user = null;

        try(Session session = sessionFactory.openSession()){
            user = (User) session.get(User.class, id);
        }catch (HibernateException e){
            throw new DBException(e);
        }
        return user;
    }

    @Override
    public boolean validateUser(long id, String name, String password) throws DBException{
        User user = null;
        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(
                    "SELECT u FROM User u WHERE u.name =:nameUser " +
                            "AND u.password =:passwordUser " +
                            "AND u.id =:idUser")
                    .setParameter("nameUser", name)
                    .setParameter("passwordUser", password)
                    .setParameter("idUser", id)
                    .setMaxResults(1);
            user = (User) query.uniqueResult();
        }catch (HibernateException e){
            throw new DBException(e);
        }

        if (user == null){
            return false;
        }
        return true;
    }

    @Override
    public long getCountUserThisCity(String city) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("SELECT COUNT (user) FROM User user WHERE user.city =:parameter")
                .setParameter("parameter", city);
        long result = (Long)query.uniqueResult();
        return result;
    }

    @Override
    public void updateUserById(long id, User newParameterUser) throws DBException {
        try( Session session = sessionFactory.openSession()){
            User user = (User)session.get(User.class, id);
            user.setName(newParameterUser.getName());
            user.setAge(newParameterUser.getAge());
            user.setPassword(newParameterUser.getPassword());
            user.setCity(newParameterUser.getCity());
        }catch (HibernateException e){
            throw new DBException(e);
        }
    }

    @Override
    public void addUser(User user) throws DBException{
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void deleteUserById(long id) throws DBException {
        try(Session session = sessionFactory.openSession()){
        Query query = session.createQuery("DELETE FROM User WHERE id =:param");
        query.setParameter("param", id);
        query.executeUpdate();
        }catch (HibernateException e){
            throw new DBException(e);
        }
    }

}

