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
}
