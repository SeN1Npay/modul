package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "sales")
public class Sales {

    @Id
    @Column(name = "idsale", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idsale;

    @JsonIgnore
    @JoinColumn(name = "idcustomer", referencedColumnName = "idcustomer")
    @ManyToOne(optional = false)
    private Customers idcustomer;

    @JsonIgnore
    @JoinColumn(name = "idseller", referencedColumnName = "idseller")
    @ManyToOne(optional = false)
    private Sellers idseller;

    @Column(name = "saledate", nullable = false)
    private Date saledate;

    public void setIdsale(Integer idsale) {
        this.idsale = idsale;
    }

    public Integer getIdsale() {
        return idsale;
    }

    public void setIdcustomer(Customers idcustomer) {
        this.idcustomer = idcustomer;
    }

    public Customers getIdcustomer() {
        return idcustomer;
    }

    public void setIdseller(Sellers idseller) {
        this.idseller = idseller;
    }

    public Sellers getIdseller() {
        return idseller;
    }

    public void setSaledate(Date saledate) {
        this.saledate = saledate;
    }

    public Date getSaledate() {
        return saledate;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "idsale='" + idsale + '\'' + ", " +
                "idcustomer='" + idcustomer + '\'' + ", " +
                "idseller='" + idseller + '\'' + ", " +
                "saledate='" + saledate + '\'' +
                '}';
    }
}