package org.parkinglot.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.parkinglot.common.CarDto;
import org.parkinglot.common.UserDto;
import org.parkinglot.entities.User;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UsersBean {

    private static final Logger LOG = Logger.getLogger(UsersBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<UserDto> listAllUsers() {
        LOG.info("listAllUsers");
        try{
            TypedQuery<User> typedQuery =
                    entityManager.createQuery("SELECT u FROM User u", User.class);
            List<User> users = typedQuery.getResultList();
            return copyUsersToDto(users);
        } catch (Exception e) {
            throw new EJBException(e);
        }
    }

    public List<UserDto> copyUsersToDto(List<User> users) {
        List<UserDto> userDtos = users.stream().map(user -> new UserDto(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getCars().stream().map(car -> new CarDto(
                        car.getId(),
                        car.getLicensePlate(),
                        car.getParkingSpot(),
                        car.getOwner().getUsername()
                ))
                .sorted(Comparator.comparing(CarDto::getLicensePlate, String.CASE_INSENSITIVE_ORDER))
                .toList()
        )).sorted(Comparator.comparing(UserDto::getUsername, String.CASE_INSENSITIVE_ORDER)).toList();
        return userDtos;
    }

}
