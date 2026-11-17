package com.example.demo.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "users")
public class Users {

    @Id
    @Column(name = "iduser", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer iduser;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "middlename", nullable = false)
    private String middlename;

    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

    @Column(name = "employmentdate", nullable = false)
    private Date employmentdate;

    @Column(name = "passport", nullable = false)
    private String passport;

    @Column(name = "snils", nullable = false)
    private String snils;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "photolink", nullable = false)
    private String photolink;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "passwordhash", nullable = false)
    private String passwordhash;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "registrationcode", nullable = false)
    private String registrationcode;

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setEmploymentdate(Date employmentdate) {
        this.employmentdate = employmentdate;
    }

    public Date getEmploymentdate() {
        return employmentdate;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getPassport() {
        return passport;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public String getSnils() {
        return snils;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhotolink(String photolink) {
        this.photolink = photolink;
    }

    public String getPhotolink() {
        return photolink;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setRegistrationcode(String registrationcode) {
        this.registrationcode = registrationcode;
    }

    public String getRegistrationcode() {
        return registrationcode;
    }

    @Override
    public String toString() {
        return "Users{" +
                "iduser='" + iduser + '\'' + ", " +
                "lastname='" + lastname + '\'' + ", " +
                "firstname='" + firstname + '\'' + ", " +
                "middlename='" + middlename + '\'' + ", " +
                "birthdate='" + birthdate + '\'' + ", " +
                "employmentdate='" + employmentdate + '\'' + ", " +
                "passport='" + passport + '\'' + ", " +
                "snils='" + snils + '\'' + ", " +
                "email='" + email + '\'' + ", " +
                "photolink='" + photolink + '\'' + ", " +
                "login='" + login + '\'' + ", " +
                "passwordhash='" + passwordhash + '\'' + ", " +
                "status='" + status + '\'' + ", " +
                "registrationcode='" + registrationcode + '\'' +
                '}';
    }
}