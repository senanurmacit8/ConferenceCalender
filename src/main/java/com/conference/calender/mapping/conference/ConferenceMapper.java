package com.conference.calender.mapping.conference;

import com.conference.calender.entity.ConferenceEntity;
import com.conference.calender.model.Conference;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConferenceMapper {

    public ConferenceEntity mapFromConferenceToConferenceEntity (Conference conference){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(conference.getConferenceDateTime(), formatter);

        ConferenceEntity entity = new ConferenceEntity();

        entity.setConferenceName(conference.getConferenceName());
        entity.setConferenceDateTime(dateTime);
        entity.setConferenceDuration(conference.getDuration());

        return entity;
    }


}
