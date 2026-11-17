package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "managers")
public class Managers {

    @Id
    @Column(name = "idmanager", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idmanager;

    @JsonIgnore
    @JoinColumn(name = "iduser", referencedColumnName = "iduser")
    @ManyToOne(optional = false)
    private Users iduser;

    public void setIdmanager(Integer idmanager) {
        this.idmanager = idmanager;
    }

    public Integer getIdmanager() {
        return idmanager;
    }

    public void setIduser(Users iduser) {
        this.iduser = iduser;
    }

    public Users getIduser() {
        return iduser;
    }

    @Override
    public String toString() {
        return "Managers{" +
                "idmanager='" + idmanager + '\'' + ", " +
                "iduser='" + iduser + '\'' +
                '}';
    }
}