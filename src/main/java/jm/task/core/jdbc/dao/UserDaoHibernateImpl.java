package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            session.createSQLQuery("""
                    CREATE TABLE IF NOT EXISTS users (
                        id INT NOT NULL AUTO_INCREMENT,
                        name VARCHAR(100) NOT NULL,
                        lastName VARCHAR(100) NOT NULL,
                        age INT NOT NULL,
                        PRIMARY KEY (id)
                    )""").executeUpdate();

            session.getTransaction().commit();
            System.out.println("Table 'users' created successfully.");
        } catch (Exception e) {
            System.out.println("Failed to create table: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();

            session.createSQLQuery("DROP TABLE IF EXISTS users")
                    .executeUpdate();

            session.getTransaction().commit();
            System.out.println("Table 'users' dropped successfully.");
        } catch (Exception e) {
            System.out.println("Failed to drop table: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = getSessionFactory().openSession()) {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            System.out.println("User с именем " + name + " добавлен в базу данных.");
        } catch (Exception e) {
            System.out.println("Something went wrong: "
                    + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                System.out.println("User removed.");
            } else {
                System.out.println("User not found.");
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Something went wrong: "
                    + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        } catch (Exception e) {
            System.out.println("Something went wrong: "
                    + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Table 'users' cleaned.");
        } catch (Exception e) {
            System.out.println("Something went wrong: "
                    + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}