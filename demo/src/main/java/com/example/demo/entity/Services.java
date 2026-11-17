package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "services")
public class Services {

    @Id
    @Column(name = "idservice", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idservice;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cost", nullable = false)
    private Integer cost;

    public void setIdservice(Integer idservice) {
        this.idservice = idservice;
    }

    public Integer getIdservice() {
        return idservice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Services{" +
                "idservice='" + idservice + '\'' + ", " +
                "name='" + name + '\'' + ", " +
                "cost='" + cost + '\'' +
                '}';
    }
}