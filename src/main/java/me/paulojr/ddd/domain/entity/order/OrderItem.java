package me.paulojr.ddd.domain.entity.order;

public class OrderItem {

    private String id;
    private String name;
    private Float price;
    private String productId;
    private Float quantity;


    public OrderItem(String id, String name, Float price, String productId, Float quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.productId = productId;
        this.quantity = quantity;
        this.validate();
    }

    private void validate() {
        if (this.id == null || this.id.isBlank() || this.id.isEmpty())
            throw new IllegalArgumentException("Id não pode ser nulo.");
        if (this.name == null || this.name.isBlank() || this.name.isEmpty())
            throw new IllegalArgumentException("Id de cliente não pode ser nulo.");
        if (this.productId == null || this.productId.isBlank() || this.productId.isEmpty())
            throw new IllegalArgumentException("Id de cliente não pode ser nulo.");
        if (this.price == null || this.price <= 0 || this.price.isInfinite() || this.price.isNaN())
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        if (this.quantity == null || this.quantity <= 0 || this.quantity.isInfinite() || this.quantity.isNaN())
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price * this.quantity;
    }

    public String getProductId() {
        return productId;
    }

    public Float getQuantity() {
        return quantity;
    }
}
