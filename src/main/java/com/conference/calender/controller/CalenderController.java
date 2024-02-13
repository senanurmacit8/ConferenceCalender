package com.conference.calender.controller;

import com.conference.calender.entity.UserEntity;
import com.conference.calender.model.Conference;
import com.conference.calender.model.User;
import com.conference.calender.repository.user.UserRepository;
import com.conference.calender.service.conference.impl.ConferenceServiceImpl;
import com.conference.calender.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CalenderController {

    @Autowired
    ConferenceServiceImpl service;

    @Autowired
    UserServiceImpl userService;

    @PostMapping( "/addConference")
    public ResponseEntity<String> addConference(@RequestBody Conference conference){

        Optional<UserEntity> userEntity = userService.getUserByName(conference.getUserName());

        if (userEntity.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "The userName is not exist."
            );
        }

        service.createConference(conference,userEntity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(2/*result.getId()*/)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PostMapping(value = "/deleteConference")
    public void deleteConference(@RequestBody Conference conference){

    }

    @GetMapping(value = "/listConferences")
    public void listConferences(){

    }

    @GetMapping(value = "/listConferencesByTopic")
    public void listConferencesByTopic(){

    }



}
