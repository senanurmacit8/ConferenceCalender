package com.conference.calender.controller;


import com.conference.calender.exception.NotFoundException;
import com.conference.calender.model.Conference;
import com.conference.calender.repository.conference.ConferenceRepository;
import com.conference.calender.service.bussiness.impl.ConferencePlanBusinessLogicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AgendaController {

    @Autowired
    private ConferencePlanBusinessLogicServiceImpl conferencePlanBusinessLogicServiceImpl;

    @Autowired
    ConferenceRepository repository;

    @GetMapping( "/getAgendaList")
    public ResponseEntity<List<List<Conference>>> showAgenda()  {
        List<List<Conference>> conferenceList ;
            try {
                conferenceList = conferencePlanBusinessLogicServiceImpl.getAgendaList();

            }catch (NotFoundException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
            }
        return ResponseEntity.status(HttpStatus.OK).body(conferenceList);
    }
   @GetMapping( "/fixDummyData")
    public ResponseEntity<String> fixDummyData()  {
       repository.updateAll();
       return ResponseEntity.status(HttpStatus.OK).body("The status of the conference records are updated... ");

    }

}
