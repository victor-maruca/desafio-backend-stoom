package com.example.desafio.Models;

import com.example.desafio.Helpers.IdHelper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "addresses")
public class Address {
    @Id
    private String id;
    private String streetName = null;
    private int number = -1;
    private String complement;
    private String neighbourhood = null;
    private String city = null;
    private String state = null;
    private String country = null;
    private int zipcode = -1;
    private Double latitude = null;
    private Double longitude = null;

    public Address() { this.id = IdHelper.getUUID(); }

    public Address (
            String streetName,
            int number,
            String complement,
            String neighbourhood,
            String city,
            String state,
            String country,
            int zipcode,
            Double latitude,
            Double longitude
    ) {
        this.id = IdHelper.getUUID();
        this.streetName = streetName;
        this.number = number;
        this.complement = complement;
        this.neighbourhood = neighbourhood;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Address(
        String streetName,
        int number,
        String complement,
        String neighbourhood,
        String city,
        String state,
        String country,
        int zipcode
    ) {
        this.id = IdHelper.getUUID();
        this.streetName = streetName;
        this.number = number;
        this.complement = complement;
        this.neighbourhood = neighbourhood;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.latitude = null;
        this.longitude = null;
    }

    public Address(
            String streetName,
            int number,
            String neighbourhood,
            String city,
            String state,
            String country,
            int zipcode
    ) {
        this.id = IdHelper.getUUID();
        this.streetName = streetName;
        this.number = number;
        this.neighbourhood = neighbourhood;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.latitude = null;
        this.longitude = null;
        this.complement = null;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
