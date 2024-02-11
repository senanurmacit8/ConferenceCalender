package com.conference.calender.controller;

import com.conference.calender.model.Conference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalenderController {

    @PostMapping( "/addConference")
    public void addConference(@RequestBody Conference conference){


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
