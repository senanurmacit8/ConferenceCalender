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
    private String time;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private UserEntity user;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
