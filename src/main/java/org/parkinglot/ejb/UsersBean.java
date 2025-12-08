package org.parkinglot.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.parkinglot.common.CarDto;
import org.parkinglot.common.UserDto;
import org.parkinglot.entities.User;
import org.parkinglot.entities.UserGroup;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UsersBean {

    private static final Logger LOG = Logger.getLogger(UsersBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    PasswordBean passwordBean;

    public void createUser(String username, String email, String password, Collection<String> groups) {
        LOG.info("createUser");
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordBean.convertToSha256(password));
        entityManager.persist(newUser);
        assignGroupsToUser(username, groups);
    }

    private void assignGroupsToUser(String username, Collection<String> groups) {
        LOG.info("assignGroupsToUser");
        for (String group : groups) {
            UserGroup userGroup = new UserGroup();
            userGroup.setUsername(username);
            userGroup.setUserGroup(group);
            entityManager.persist(userGroup);
        }
    }

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
        return users.stream().map(user -> new UserDto(
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
    }

}
