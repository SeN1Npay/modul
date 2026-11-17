package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "locations")
public class Locations {

    @Id
    @Column(name = "idlocation", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idlocation;

    @Column(name = "room", nullable = false)
    private String room;

    @Column(name = "rack", nullable = false)
    private String rack;

    @Column(name = "shelf", nullable = false)
    private String shelf;

    public void setIdlocation(Integer idlocation) {
        this.idlocation = idlocation;
    }

    public Integer getIdlocation() {
        return idlocation;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

    public void setRack(String rack) {
        this.rack = rack;
    }

    public String getRack() {
        return rack;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public String getShelf() {
        return shelf;
    }

    @Override
    public String toString() {
        return "Locations{" +
                "idlocation='" + idlocation + '\'' + ", " +
                "room='" + room + '\'' + ", " +
                "rack='" + rack + '\'' + ", " +
                "shelf='" + shelf + '\'' +
                '}';
    }
}
