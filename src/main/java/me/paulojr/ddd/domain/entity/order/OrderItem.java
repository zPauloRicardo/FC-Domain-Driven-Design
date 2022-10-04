package me.paulojr.ddd.domain.entity.order;

import java.util.Objects;

public class OrderItem {

    private final String id;
    private final String name;
    private final Float price;
    private final String productId;
    private final Float quantity;


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

    public Float getUnitPrice() {
        return this.price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (!Objects.equals(id, orderItem.id)) return false;
        if (!Objects.equals(name, orderItem.name)) return false;
        if (!Objects.equals(price, orderItem.price)) return false;
        if (!Objects.equals(productId, orderItem.productId)) return false;
        return Objects.equals(quantity, orderItem.quantity);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }
}
