package com.conference.calender.controller;

import com.conference.calender.entity.UserEntity;
import com.conference.calender.model.Conference;
import com.conference.calender.service.conference.impl.ConferenceServiceImpl;
import com.conference.calender.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ConferenceController {

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
    public ResponseEntity<String> deleteConference(@RequestBody Conference conference){

        Optional<UserEntity> userEntity = userService.getUserByName(conference.getUserName());

        if (!userEntity.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    "The userName not found."
            );
        }
        service.deleteConference(conference,userEntity);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("deleted");
    }

    @GetMapping(value = "/listConferences")
    public void listConferences(){

        service.listConferences();

    }

    @GetMapping(value = "/listConferencesByTopic")
    public void listConferencesByTopic(@RequestBody String conferenceTopic) {

        service.listConferencesByTopic(conferenceTopic);

    }

    @PutMapping(value = "/updateConference")
    public void updateConference(){



    }



}
