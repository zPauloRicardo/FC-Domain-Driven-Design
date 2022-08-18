package me.paulojr.ddd.infrastructure.database.model;


import me.paulojr.ddd.domain.entity.costumer.Address;
import me.paulojr.ddd.domain.entity.costumer.Costumer;
import me.paulojr.ddd.domain.entity.product.Product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FC_COSTUMER")
public class CostumerModel {

    @Id
    @Column(name = "ID_COSTUMER")
    private String id;
    private String name;
    private String street;
    private Integer number;
    private String zip;
    private String city;
    private boolean active = false;
    private Float rewardPoints = 0f;


    public CostumerModel() {
    }

    public CostumerModel(Costumer costumer) {
        this.id = costumer.getId();
        this.name = costumer.getName();
        this.active = costumer.isActive();
        this.rewardPoints = costumer.getRewardPoints();
        if(costumer.getAddress() != null) {
            this.city = costumer.getAddress().getCity();
            this.street = costumer.getAddress().getStreet();
            this.number = costumer.getAddress().getNumber();
            this.zip = costumer.getAddress().getZip();
        }

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
