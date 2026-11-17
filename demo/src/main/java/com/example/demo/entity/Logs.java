package com.example.demo.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "logs")
public class Logs {

    @Id
    @Column(name = "idlog", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idlog;

    @Column(name = "method", nullable = false)
    private String method;

    @Column(name = "recordtype", nullable = false)
    private String recordtype;

    @Column(name = "recordtext", nullable = false)
    private String recordtext;

    @Column(name = "actiondate", nullable = false)
    private Date actiondate;

    @Column(name = "actiontime", nullable = false)
    private String actiontime;

    public void setIdlog(Integer idlog) {
        this.idlog = idlog;
    }

    public Integer getIdlog() {
        return idlog;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethod() {
        return method;
    }

    public void setRecordtype(String recordtype) {
        this.recordtype = recordtype;
    }

    public String getRecordtype() {
        return recordtype;
    }

    public void setRecordtext(String recordtext) {
        this.recordtext = recordtext;
    }

    public String getRecordtext() {
        return recordtext;
    }

    public void setActiondate(Date actiondate) {
        this.actiondate = actiondate;
    }

    public Date getActiondate() {
        return actiondate;
    }

    public void setActiontime(String actiontime) {
        this.actiontime = actiontime;
    }

    public String getActiontime() {
        return actiontime;
    }

    @Override
    public String toString() {
        return "Logs{" +
                "idlog='" + idlog + '\'' + ", " +
                "method='" + method + '\'' + ", " +
                "recordtype='" + recordtype + '\'' + ", " +
                "recordtext='" + recordtext + '\'' + ", " +
                "actiondate='" + actiondate + '\'' + ", " +
                "actiontime='" + actiontime + '\'' +
                '}';
    }
}
