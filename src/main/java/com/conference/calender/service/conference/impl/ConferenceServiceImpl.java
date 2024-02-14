package com.conference.calender.service.conference.impl;

import com.conference.calender.entity.ConferenceEntity;
import com.conference.calender.entity.UserEntity;
import com.conference.calender.exception.NotFoundException;
import com.conference.calender.model.Conference;
import com.conference.calender.repository.conference.ConferenceRepository;
import com.conference.calender.repository.user.UserRepository;
import com.conference.calender.service.conference.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ConferenceServiceImpl implements ConferenceService {

    @Autowired
    ConferenceRepository repository;

    @Autowired
    UserRepository userRepository;

    public void createConference(Conference conference, Optional<UserEntity> userEntity){

        /*
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(conference.getConferenceDateTime(), formatter);
         */

        ConferenceEntity entity = new ConferenceEntity();

        entity.setConferenceName(conference.getConferenceName());
        //entity.setConferenceDateTime(dateTime);
        entity.setConferenceDuration(conference.getDuration());
        entity.setUser(userEntity.get());

        repository.saveAndFlush(entity);

    }

    public void deleteConference(Conference conference,Optional<UserEntity> userEntity){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(conference.getConferenceDateTime(), formatter);


        ConferenceEntity entity = new ConferenceEntity();
        entity.setConferenceName(conference.getConferenceName());
        entity.setConferenceDateTime(dateTime);
        entity.setConferenceDuration(conference.getDuration());
        entity.setUser(userEntity.get());

        repository.delete(entity);
    }

    public List<Conference> listConferences(){

        List<Conference> conferenceList = new ArrayList<>();

        List<ConferenceEntity> conferenceEntityList = repository.findAll();

        for(ConferenceEntity conferenceEntity: conferenceEntityList){

            String conferenceDateTime = conferenceEntity.getConferenceDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toString();

            Conference conference = new Conference();
            conference.setConferenceName(conferenceEntity.getConferenceName());
            conference.setConferenceDateTime(conferenceDateTime);
            conference.setDuration(conferenceEntity.getConferenceDuration());
            conference.setUserName(conferenceEntity.getUser().getName());

            conferenceList.add(conference);
        }
        return conferenceList;
    }

    public Conference getConferenceById(Long conferenceId) throws NotFoundException {
        Conference conference = new Conference();

        Optional<ConferenceEntity> conferenceEntityOptional = repository.findById(conferenceId);

        if(conferenceEntityOptional.isPresent()) {
            ConferenceEntity conferenceEntity = conferenceEntityOptional.get();

            String conferenceDateTime = conferenceEntity.getConferenceDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toString();

            conference.setConferenceName(conferenceEntity.getConferenceName());
            conference.setConferenceDateTime(conferenceDateTime);
            conference.setDuration(conferenceEntity.getConferenceDuration());
            conference.setUserName(conferenceEntity.getUser().getName());

        }else {
        throw new NotFoundException("The confrence is not exist. Conference Id :" + conferenceId);
        }

        return conference;
    }
    public List<Conference> listConferencesByTopic(String conferenceTopic) {

        List<Conference> conferenceList = new ArrayList<>();

        List<ConferenceEntity> conferenceEntityList = repository.findByConferenceTopic(conferenceTopic);

        if(!CollectionUtils.isEmpty(conferenceEntityList)) {

            for (ConferenceEntity conferenceEntity: conferenceEntityList ){
                Conference conference = new Conference();
                String conferenceDateTime = conferenceEntity.getConferenceDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).toString();

                conference.setConferenceName(conferenceEntity.getConferenceName());
                conference.setConferenceDateTime(conferenceDateTime);
                conference.setDuration(conferenceEntity.getConferenceDuration());
                conference.setUserName(conferenceEntity.getUser().getName());
                conferenceList.add(conference);
            }
        }

        return conferenceList;
    }


}
