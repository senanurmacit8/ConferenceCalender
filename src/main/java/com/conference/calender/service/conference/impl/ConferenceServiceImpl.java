package com.conference.calender.service.conference.impl;

import com.conference.calender.entity.ConferenceEntity;
import com.conference.calender.entity.UserEntity;
import com.conference.calender.model.Conference;
import com.conference.calender.repository.conference.ConferenceRepository;
import com.conference.calender.repository.user.UserRepository;
import com.conference.calender.service.conference.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@Service
public class ConferenceServiceImpl implements ConferenceService {

    @Autowired
    ConferenceRepository repository;

    @Autowired
    UserRepository userRepository;

    public void createConference(Conference conference, Optional<UserEntity> userEntity){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(conference.getConferenceDateTime(), formatter);

        ConferenceEntity entity = new ConferenceEntity();

        entity.setConferenceName(conference.getConferenceName());
        entity.setConferenceDateTime(dateTime);
        entity.setDuration(conference.getDuration());
        entity.setUser(userEntity.get());

        repository.saveAndFlush(entity);

    }


}
