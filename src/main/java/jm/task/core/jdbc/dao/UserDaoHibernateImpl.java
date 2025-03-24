package jm.task.core.jdbc.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (EntityManagerFactory emf = Util.createEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createNativeQuery("""
                            CREATE TABLE IF NOT EXISTS `db`.`users` (
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `name` VARCHAR(100) NOT NULL,
                            `lastName` VARCHAR(100) NOT NULL,
                            `age` INT NOT NULL,
                            PRIMARY KEY (`id`));""")
                    .executeUpdate();
            em.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (EntityManagerFactory emf = Util.createEntityManagerFactory();
             EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createNativeQuery("DROP TABLE IF EXISTS `db`.`users`;")
                    .executeUpdate();
            em.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
