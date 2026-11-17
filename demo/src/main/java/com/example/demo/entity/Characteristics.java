package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "characteristics")
public class Characteristics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcharacteristic", nullable = false)
    private Integer idcharacteristic;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "unit", nullable = false)
    private String unit;

    public void setIdcharacteristic(Integer idcharacteristic) {
        this.idcharacteristic = idcharacteristic;
    }

    public Integer getIdcharacteristic() {
        return idcharacteristic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    @Override
    public String toString() {
        return "Characteristics{" +
                "idcharacteristic='" + idcharacteristic + '\'' + ", " +
                "name='" + name + '\'' + ", " +
                "unit='" + unit + '\'' +
                '}';
    }
}