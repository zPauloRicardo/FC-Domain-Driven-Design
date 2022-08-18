package me.paulojr.ddd.domain.entity.costumer;

public class Address {

    private String street;
    private Integer number;
    private String zip;
    private String city;

    public Address(String street, Integer number, String zip, String city) {
        this.street = street;
        this.number = number;
        this.zip = zip;
        this.city = city;
        this.validate();
    }

    private void validate() {
        if(this.street == null || this.street.isBlank() || this.street.isBlank())
            throw new IllegalArgumentException("Rua é obrigatorio.");
        if(this.zip == null || this.zip.isBlank() || this.zip.isBlank())
            throw new IllegalArgumentException("CEP é obrigatorio.");
        if(this.city == null || this.city.isBlank() || this.city.isBlank())
            throw new IllegalArgumentException("Cidade é obrigatorio.");
        if(this.number == null || this.number.intValue() == 0)
            throw new IllegalArgumentException("Numero deve ser maior que 0.");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (number != null ? !number.equals(address.number) : address.number != null) return false;
        if (zip != null ? !zip.equals(address.zip) : address.zip != null) return false;
        return city != null ? city.equals(address.city) : address.city == null;
    }

    @Override
    public int hashCode() {
        int result = street != null ? street.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (zip != null ? zip.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}
