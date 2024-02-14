package com.conference.calender.controller;

import com.conference.calender.exception.NotFoundException;
import com.conference.calender.model.Conference;
import com.conference.calender.service.bussiness.impl.ConferencePlanBusinessLogicServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
class AgendaControllerTest {

    @InjectMocks
    AgendaController controller;

    private ConferencePlanBusinessLogicServiceImpl service;

    @BeforeEach
    void setUp() throws NotFoundException {
        service = mock(ConferencePlanBusinessLogicServiceImpl.class);

    }

    @Test
    void showAgenda() throws NotFoundException {
        Mockito.when(service.getAgendaList()).thenReturn(Arrays.asList(dummyConferenceList()));

        List<List<Conference>> conferenceList = service.getAgendaList();

        Assert.assertEquals(dummyConferenceList().get(0).getUserName(),conferenceList.get(0).get(0).getUserName());
    }

    @Test()
    void showAgenda_withNotFoundException() throws NotFoundException {
        Mockito.when(service.getAgendaList()).thenThrow(new NotFoundException(""));

        assertThatThrownBy(() -> service.getAgendaList())
                .isInstanceOf(NotFoundException.class);
    }

    private List<Conference> dummyConferenceList(){
        List<Conference> conferenceList = new ArrayList<>();
        Conference conference = new Conference();
        conference.setUserName("sena");
        conference.setConferenceDateTime(LocalDateTime.now().toString());
        conference.setDuration(30);
        conferenceList.add(conference);

        return conferenceList;
    }
}