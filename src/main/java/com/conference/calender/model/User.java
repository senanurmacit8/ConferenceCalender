package com.conference.calender.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

    private String name;
    private String lastname;
    private String job;
    private String country;

}
