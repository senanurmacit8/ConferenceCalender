package com.conference.calender.model;


import lombok.Data;

@Data
public class Conference {

    private String conferenceName;
    private String conferenceDateTime;
    private Integer duration;
    private String userName;

}
