package com.conference.calender.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "T_USER")


public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastname;
    private String job;
    private String country;

    public UserEntity(Long id, String name, String lastname, String job, String country) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.job = job;
        this.country = country;
    }

    public UserEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        job = job;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
