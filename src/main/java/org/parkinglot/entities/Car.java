package org.parkinglot.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "licensePlate")
    private String licensePlate;

    @Column(name = "parkingSpot")
    private String parkingSpot;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    private CarPhoto photo;

    @OneToOne(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public CarPhoto getPhoto() {
        return photo;
    }

    public void setPhoto(CarPhoto photo) {
        this.photo = photo;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getLicensePlate() { return licensePlate; }

    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    public String getParkingSpot() { return parkingSpot; }

    public void setParkingSpot(String parkingSpot) { this.parkingSpot = parkingSpot; }

    public Long getId() { return id; }

    public void setId(Long id) {
        this.id = id;
    }

}