package peaksoft.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import peaksoft.config.HibernateConfig;
import peaksoft.dao.UserDao;
import peaksoft.entities.Car;
import peaksoft.entities.User;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.entityManagerFactory();

    @Override
    public void saveUser(User user) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User getUserById(long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, id);
            entityManager.getTransaction().commit();
            return user;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            List<User> users = entityManager.createQuery("select u from User u", User.class).getResultList();
            entityManager.getTransaction().commit();
            return users;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            User user = entityManager.find(User.class, id);
            entityManager.remove(user);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateUser(User user, Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            User updetingUser = entityManager.find(User.class, id);
            updetingUser.setFistName(user.getFistName());
            updetingUser.setLastName(user.getLastName());
            updetingUser.setEmail(user.getEmail());
            entityManager.merge(updetingUser);
            entityManager.getTransaction().commit();
        }catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateCarAndUser( Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            User updatingUser = entityManager.find(User.class, id);
            for (Car car : updatingUser.getCars()) {
                car.setYear("22.22.2025");
            }
            entityManager.merge(updatingUser);
            entityManager.getTransaction().commit();
        }catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
}
