package me.paulojr.ddd.infrastructure.customer.jpa.model;



import me.paulojr.ddd.domain.customer.entity.Customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "FC_customer")
public class CustomerModel {

    @Id
    @Column(name = "ID_customer")
    private String id;
    private String name;
    private String street;
    private Integer number;
    private String zip;
    private String city;
    private boolean active = false;
    private Float rewardPoints = 0f;


    public CustomerModel() {
    }

    public CustomerModel(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.active = customer.isActive();
        this.rewardPoints = customer.getRewardPoints();
        if(customer.getAddress() != null) {
            this.city = customer.getAddress().getCity();
            this.street = customer.getAddress().getStreet();
            this.number = customer.getAddress().getNumber();
            this.zip = customer.getAddress().getZip();
        }

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRewardPoints(Float rewardPoints) {
        this.rewardPoints = rewardPoints;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumber() {
        return number;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public boolean isActive() {
        return active;
    }

    public Float getRewardPoints() {
        return rewardPoints;
    }
}
