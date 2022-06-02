package entity;

public class Address {

    private String street;
    private int postal;
    private String city;


    public Address(String street, int postal, String city) {
        this.street = street;
        this.postal = postal;
        this.city = city;
    }


    public Address() {
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPostal() {
        return postal;
    }

    public void setPostal(int postal) {
        this.postal = postal;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
