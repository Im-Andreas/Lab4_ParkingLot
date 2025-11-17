package org.parkinglot.common;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import org.parkinglot.entities.Car;

import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private Long id;

    private String username;

    private String email;

    private String password;

    private List<CarDto> cars = new ArrayList<>();

    public UserDto(Long id, String username, String email, String password, List<CarDto> cars) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.cars = cars;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<CarDto> getCars() {
        return cars;
    }
}
