package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "interfaces")
public class Interfaces {

    @Id
    @Column(name = "idinterface", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idinterface;

    @Column(name = "name", nullable = false)
    private String name;

    public void setIdinterface(Integer idinterface) {
        this.idinterface = idinterface;
    }

    public Integer getIdinterface() {
        return idinterface;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Interfaces{" +
                "idinterface='" + idinterface + '\'' + ", " +
                "name='" + name + '\'' +
                '}';
    }
}
