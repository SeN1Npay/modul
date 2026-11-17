package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customers {

    @Id
    @Column(name = "idcustomer", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idcustomer;

    @JsonIgnore
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private Users iduser;

    @Column(name = "phone", nullable = false)
    private String phone;

    public void setIdcustomer(Integer idcustomer) {
        this.idcustomer = idcustomer;
    }

    public Integer getIdcustomer() {
        return idcustomer;
    }

    public void setIduser(Users iduser) {
        this.iduser = iduser;
    }

    public Users getIduser() {
        return iduser;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "idcustomer='" + idcustomer + '\'' + ", " +
                "iduser='" + iduser + '\'' + ", " +
                "phone='" + phone + '\'' +
                '}';
    }
}
