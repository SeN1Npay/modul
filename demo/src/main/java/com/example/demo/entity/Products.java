package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Products {

    @Id
    @Column(name = "idproduct", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idproduct;

    @JsonIgnore
    @JoinColumn(name = "idlocation", referencedColumnName = "idlocation")
    @ManyToOne(optional = false)
    private Locations idlocation;

    @JsonIgnore
    @JoinColumn(name = "idinterface", referencedColumnName = "idinterface")
    @ManyToOne(optional = false)
    private Interfaces idinterface;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "peripheral", nullable = false)
    private Integer peripheral;

    @Column(name = "photolink", nullable = false)
    private String photolink;

    public void setIdproduct(Integer idproduct) {
        this.idproduct = idproduct;
    }

    public Integer getIdproduct() {
        return idproduct;
    }

    public void setIdlocation(Locations idlocation) {
        this.idlocation = idlocation;
    }

    public Locations getIdlocation() {
        return idlocation;
    }

    public void setIdinterface(Interfaces idinterface) {
        this.idinterface = idinterface;
    }

    public Interfaces getIdinterface() {
        return idinterface;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setPeripheral(Integer peripheral) {
        this.peripheral = peripheral;
    }

    public Integer getPeripheral() {
        return peripheral;
    }

    public void setPhotolink(String photolink) {
        this.photolink = photolink;
    }

    public String getPhotolink() {
        return photolink;
    }

    @Override
    public String toString() {
        return "Products{" +
                "idproduct='" + idproduct + '\'' + ", " +
                "idlocation='" + idlocation + '\'' + ", " +
                "idinterface='" + idinterface + '\'' + ", " +
                "name='" + name + '\'' + ", " +
                "price='" + price + '\'' + ", " +
                "quantity='" + quantity + '\'' + ", " +
                "peripheral='" + peripheral + '\'' + ", " +
                "photolink='" + photolink + '\'' +
                '}';
    }
}