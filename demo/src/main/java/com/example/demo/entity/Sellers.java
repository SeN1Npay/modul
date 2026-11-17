package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "sellers")
public class Sellers {

    @Id
    @Column(name = "idseller", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idseller;

    @JsonIgnore
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private Users iduser;

    public void setIdseller(Integer idseller) {
        this.idseller = idseller;
    }

    public Integer getIdseller() {
        return idseller;
    }

    public void setIduser(Users iduser) {
        this.iduser = iduser;
    }

    public Users getIduser() {
        return iduser;
    }

    @Override
    public String toString() {
        return "Sellers{" +
                "idseller='" + idseller + '\'' + ", " +
                "iduser='" + iduser + '\'' +
                '}';
    }
}