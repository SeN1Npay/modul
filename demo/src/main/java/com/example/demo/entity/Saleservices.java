package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "saleservices")
public class Saleservices {

    @Id
    @Column(name = "idsaleservice", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idsaleservice;

    @JsonIgnore
    @JoinColumn(name = "idsale", referencedColumnName = "idsale")
    @ManyToOne(optional = false)
    private Sales idsale;

    @JsonIgnore
    @JoinColumn(name = "idservice", referencedColumnName = "idservice")
    @ManyToOne(optional = false)
    private Services idservice;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    public void setIdsaleservice(Integer idsaleservice) {
        this.idsaleservice = idsaleservice;
    }

    public Integer getIdsaleservice() {
        return idsaleservice;
    }

    public void setIdsale(Sales idsale) {
        this.idsale = idsale;
    }

    public Sales getIdsale() {
        return idsale;
    }

    public void setIdservice(Services idservice) {
        this.idservice = idservice;
    }

    public Services getIdservice() {
        return idservice;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Saleservices{" +
                "idsaleservice='" + idsaleservice + '\'' + ", " +
                "idsale='" + idsale + '\'' + ", " +
                "idservice='" + idservice + '\'' + ", " +
                "quantity='" + quantity + '\'' +
                '}';
    }
}