package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "saleitems")
public class Saleitems {

    @Id
    @Column(name = "idsaleitem", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idsaleitem;

    @JsonIgnore
    @JoinColumn(name = "idsale", referencedColumnName = "idsale")
    @ManyToOne(optional = false)
    private Sales idsale;

    @JsonIgnore
    @JoinColumn(name = "idproduct", referencedColumnName = "idproduct")
    @ManyToOne(optional = false)
    private Products idproduct;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public void setIdsaleitem(Integer idsaleitem) {
        this.idsaleitem = idsaleitem;
    }

    public Integer getIdsaleitem() {
        return idsaleitem;
    }

    public void setIdsale(Sales idsale) {
        this.idsale = idsale;
    }

    public Sales getIdsale() {
        return idsale;
    }

    public void setIdproduct(Products idproduct) {
        this.idproduct = idproduct;
    }

    public Products getIdproduct() {
        return idproduct;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Saleitems{" +
                "idsaleitem='" + idsaleitem + '\'' + ", " +
                "idsale='" + idsale + '\'' + ", " +
                "idproduct='" + idproduct + '\'' + ", " +
                "quantity='" + quantity + '\'' +
                '}';
    }
}
