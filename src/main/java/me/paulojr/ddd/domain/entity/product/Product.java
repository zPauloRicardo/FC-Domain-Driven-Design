package me.paulojr.ddd.domain.entity.product;

public class Product {

    private String id;
    private String name;
    private Float price;

    public Product(String id, String name, Float price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.validate();
    }

    private void validate() {
        if (this.id == null || this.id.isBlank() || this.id.isEmpty())
            throw new IllegalArgumentException("Id é obrigatorio.");
        if (this.name == null || this.name.isBlank() || this.name.isEmpty())
            throw new IllegalArgumentException("Nome é obrigatorio.");
        if (this.price == null || this.price.isNaN() || this.price.isInfinite())
            throw new IllegalArgumentException("Preço é obrigatorio.");
        if (this.price < 0)
            throw new IllegalArgumentException("Preço deve ser maior que 0.");
    }


    public void changeName(String name) {
        this.name = name;
        this.validate();
    }

    public void changePrice(Float price) {
        this.price = price;
        this.validate();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getPrice() {
        return price;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        return price != null ? price.equals(product.price) : product.price == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
