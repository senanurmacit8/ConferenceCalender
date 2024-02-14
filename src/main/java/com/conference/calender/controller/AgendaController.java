package com.conference.calender.controller;


import com.conference.calender.exception.NotFoundException;
import com.conference.calender.model.Conference;
import com.conference.calender.service.bussiness.ConferencePlanBusinessLogicService;
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
    private ConferencePlanBusinessLogicService conferencePlanBusinessLogicService;

    @GetMapping( "/getAgendaList")
    public ResponseEntity<List<Conference>> showAgenda()  {
        List<Conference> conferenceList ;
            try {
                conferenceList = conferencePlanBusinessLogicService.getAgendaList();
            }catch (NotFoundException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());
            }
        return ResponseEntity.status(HttpStatus.OK).body(conferenceList);
    }

}
