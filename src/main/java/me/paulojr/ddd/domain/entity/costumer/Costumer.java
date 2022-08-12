package me.paulojr.ddd.domain.entity.costumer;

public class Costumer {

    private String id;
    private String name;
    private Address address;
    private boolean active = false;
    private Float rewardPoints = 0f;

    public Costumer(String id, String name) {
        this.id = id;
        this.name = name;
        this.validate();
    }

    public String getId() {
        return id;
    }

    public void changeName(String name) {
        //regras de negocio
        this.name = name;
        this.validate();
    }

    public void activate() {
        if(this.address == null)
            throw new RuntimeException("Endereço é obrigatório para ativar o cliente.");
        this.active = true;
    }

    public void deactivate() {
        this.active = false;
    }

    private void validate() {
        if (this.name == null || this.name.isBlank() || this.name.isEmpty() || this.name.split(" ").length <= 1)
            throw new IllegalArgumentException("Nome deve ser completo.");

        if (this.id == null || this.id.isBlank() || this.id.isEmpty())
            throw new IllegalArgumentException("Id não pode ser nulo.");
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public void addReawardPoints(Float points) {
        this.rewardPoints += points;
    }

    public Float getRewardPoints() {
        return rewardPoints;
    }
}
