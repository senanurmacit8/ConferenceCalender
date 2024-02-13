package com.conference.calender.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_CONFERENCE")
public class ConferenceEntity {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String conferenceName;
    private LocalDateTime conferenceDateTime;
    private String duration;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private UserEntity user;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public LocalDateTime getConferenceDateTime() {
        return conferenceDateTime;
    }

    public void setConferenceDateTime(LocalDateTime conferenceDateTime) {
        this.conferenceDateTime = conferenceDateTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String time) {
        this.duration = time;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
