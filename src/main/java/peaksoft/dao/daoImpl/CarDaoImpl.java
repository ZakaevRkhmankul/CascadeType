package peaksoft.dao.daoImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.HibernateException;
import peaksoft.config.HibernateConfig;
import peaksoft.dao.CarDao;
import peaksoft.entities.Car;

import java.util.List;

public class CarDaoImpl implements CarDao {
    private final EntityManagerFactory entityManagerFactory = HibernateConfig.entityManagerFactory();

    @Override
    public void saveCar(Car car) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.persist(car);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Car getCarById(int id) {
        Car car = null;
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            car = entityManager.find(Car.class, id);
            entityManager.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return car;
    }

    @Override
    public List<Car> getAllCars() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            List<Car> cars = entityManager.createQuery("SELECT c FROM Car c", Car.class).getResultList();
            entityManager.getTransaction().commit();
            return cars;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void deleteCar(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.find(Car.class, id));
            entityManager.getTransaction().commit();

        }catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateCar(Car car, Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            entityManager.getTransaction().begin();
            Car updatingCar = entityManager.find(Car.class, id);
            updatingCar.setBrand(car.getBrand());
            updatingCar.setModel(car.getModel());
            updatingCar.setYear(car.getYear());

            entityManager.merge(updatingCar);
            entityManager.getTransaction().commit();
        }catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

}
