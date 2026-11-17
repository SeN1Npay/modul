package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "productcharacteristics")
public class Productcharacteristics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproductcharacteristic", nullable = false)
    private Integer idproductcharacteristic;

    @JsonIgnore
    @JoinColumn(name = "idproduct", referencedColumnName = "idproduct")
    @ManyToOne(optional = false)
    private Products idproduct;

    @JsonIgnore
    @JoinColumn(name = "idcharacteristic", referencedColumnName = "idcharacteristic")
    @ManyToOne(optional = false)
    private Characteristics idcharacteristic;

    @Column(name = "value", nullable = false)
    private String value;

    public void setIdproductcharacteristic(Integer idproductcharacteristic) {
        this.idproductcharacteristic = idproductcharacteristic;
    }

    public Integer getIdproductcharacteristic() {
        return idproductcharacteristic;
    }

    public void setIdproduct(Products idproduct) {
        this.idproduct = idproduct;
    }

    public Products getIdproduct() {
        return idproduct;
    }

    public void setIdcharacteristic(Characteristics idcharacteristic) {
        this.idcharacteristic = idcharacteristic;
    }

    public Characteristics getIdcharacteristic() {
        return idcharacteristic;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Productcharacteristics{" +
                "idproductcharacteristic='" + idproductcharacteristic + '\'' + ", " +
                "idproduct='" + idproduct + '\'' + ", " +
                "idcharacteristic='" + idcharacteristic + '\'' + ", " +
                "value='" + value + '\'' +
                '}';
    }
}