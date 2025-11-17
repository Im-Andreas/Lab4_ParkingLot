package org.parkinglot.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.parkinglot.common.CarDto;
import org.parkinglot.entities.Car;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;


@Stateless
public class CarsBean {
    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

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
        List<CarDto> carDtos = cars.stream().map(car -> new CarDto(
                car.getId(),
                car.getLicensePlate(),
                car.getParkingSpot(),
                car.getOwner().getUsername()
        ))
        .sorted(Comparator.comparing(CarDto::getLicensePlate, String.CASE_INSENSITIVE_ORDER))
        .toList();
        return carDtos;
    }
}
