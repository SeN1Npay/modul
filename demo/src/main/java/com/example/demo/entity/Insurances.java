package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "insurances")
public class Insurances {

    @Id
    @Column(name = "idinsurance", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idinsurance;

    @JsonIgnore
    @JoinColumn(name = "idsaleitem", referencedColumnName = "idsaleitem")
    @ManyToOne(optional = false)
    private Saleitems idsaleitem;

    @Column(name = "policynumber", nullable = false)
    private String policynumber;

    @Column(name = "startdate", nullable = false)
    private Date startdate;

    @Column(name = "enddate", nullable = false)
    private Date enddate;

    @Column(name = "cost", nullable = false)
    private Integer cost;

    public void setIdinsurance(Integer idinsurance) {
        this.idinsurance = idinsurance;
    }

    public Integer getIdinsurance() {
        return idinsurance;
    }

    public void setIdsaleitem(Saleitems idsaleitem) {
        this.idsaleitem = idsaleitem;
    }

    public Saleitems getIdsaleitem() {
        return idsaleitem;
    }

    public void setPolicynumber(String policynumber) {
        this.policynumber = policynumber;
    }

    public String getPolicynumber() {
        return policynumber;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Insurances{" +
                "idinsurance='" + idinsurance + '\'' + ", " +
                "idsaleitem='" + idsaleitem + '\'' + ", " +
                "policynumber='" + policynumber + '\'' + ", " +
                "startdate='" + startdate + '\'' + ", " +
                "enddate='" + enddate + '\'' + ", " +
                "cost='" + cost + '\'' +
                '}';
    }
}
