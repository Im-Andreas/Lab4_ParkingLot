package org.parkinglot.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.parkinglot.common.CarDto;
import org.parkinglot.entities.Car;
import org.parkinglot.entities.User;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;


@Stateless
public class CarsBean {
    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public void createCar(CarDto carDto, Long userId){
        LOG.info("createCar");
        try {
            Car car = new Car();
            car.setLicensePlate(carDto.getLicensePlate());
            car.setParkingSpot(carDto.getParkingSpot());

            User existingUser = entityManager.find(User.class, userId);
            existingUser.getCars().add(car);
            car.setOwner(existingUser);

            entityManager.persist(car);
        }catch (Exception e){
            throw new EJBException(e);
        }
    }

    public void updateCar(CarDto carDto, Long userId) {
        LOG.info("updateCar");
        try {
            Car car = entityManager.find(Car.class, carDto.getId());

            car.setLicensePlate(carDto.getLicensePlate());
            car.setParkingSpot(carDto.getParkingSpot());

            User oldUser = car.getOwner();
            oldUser.getCars().remove(car);

            User newUser = entityManager.find(User.class, userId);
            newUser.getCars().add(car);
            car.setOwner(newUser);
        }catch (Exception e){
            throw new EJBException(e);
        }
    }

    public CarDto findById(Long id) {
        LOG.info("findById");
        try {
            Car car = entityManager.find(Car.class, id);

            return new CarDto(
                    car.getId(),
                    car.getLicensePlate(),
                    car.getParkingSpot(),
                    car.getOwner().getUsername()
            );
        }catch (Exception e){
            throw new EJBException(e);
        }
    }

    public void deleteCarsByIds(Collection<Long> carIds) {
        LOG.info("deleteCarsByIds");
        try {
            for (Long carId : carIds) {
                Car car = entityManager.find(Car.class, carId);
                entityManager.remove(car);
            }
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public List<CarDto> listAllCars() {
        LOG.info("listAllCars");
        try{
            TypedQuery<Car> typedQuery =
                    entityManager.createQuery("SELECT c FROM Car c", Car.class);
            List<Car> cars = typedQuery.getResultList();
            return copyCarsToDto(cars);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public List<CarDto> copyCarsToDto(List<Car> cars) {
        return cars.stream().map(car -> new CarDto(
                car.getId(),
                car.getLicensePlate(),
                car.getParkingSpot(),
                car.getOwner().getUsername()
        ))
        .sorted(Comparator.comparing(CarDto::getLicensePlate, String.CASE_INSENSITIVE_ORDER))
        .toList();
    }
}
