package com.example.springGraph.entity;

import jakarta.persistence.*;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String city;
    private String district;
    private String subdistrict;
    private String X;
    private String Y;

    public String getDistrict() {
        return district;
    }

    public String getSubdistrict() {
        return subdistrict;
    }

    public String getCity() {
        return city;
    }

    public int getId() {
        return id;
    }

    public String getX() {
        return X;
    }

    public String getY() {
        return Y;
    }

    public void setY(String y) {
        Y = y;
    }

    public void setX(String x) {
        X = x;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setSubdistrict(String subdistrict) {
        this.subdistrict = subdistrict;
    }
}