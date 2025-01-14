package peaksoft.dao;

import peaksoft.entities.Car;

import java.util.List;

public interface CarDao {
    void saveCar(Car car);
    Car getCarById(int id);
    List<Car> getAllCars();
    void deleteCar(Long id);
    void updateCar(Car car,Long id);

}
