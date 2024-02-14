package com.conference.calender.controller;

import com.conference.calender.entity.UserEntity;
import com.conference.calender.model.Conference;
import com.conference.calender.service.conference.impl.ConferenceServiceImpl;
import com.conference.calender.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
class ConferenceControllerTest {

    @InjectMocks
    ConferenceController controller;

    private UserServiceImpl userServiceImpl;
    private ConferenceServiceImpl conferenceServiceImpl;

    @BeforeEach
    void setUp() {
        userServiceImpl= mock(UserServiceImpl.class);
        conferenceServiceImpl= mock(ConferenceServiceImpl.class);


    }

    @Test
    void addConference() {

        Mockito.when(userServiceImpl.getUserByName(Mockito.anyString())).thenReturn(dummyUserEntity());

    }

    @Test
    void deleteConference() {
    }

    @Test
    void listConferences() {
    }

    @Test
    void listConferencesByTopic() {
    }

    @Test
    void updateConference() {
    }

    private Optional<UserEntity> dummyUserEntity(){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("sena");
        userEntity.setJob("developer");
        userEntity.setLastname("imren");
        userEntity.setCountry("Turkey");
        return Optional.of(userEntity);
    }

    private Conference dummyConference(){
        Conference conference= new Conference();
        conference.setUserName("sena");
        conference.setConferenceDateTime(LocalDateTime.now().toString());
        conference.setDuration(30);
        return conference;
    }
}