package com.conference.calender.service.bussiness.impl;

import com.conference.calender.entity.ConferenceEntity;
import com.conference.calender.exception.NotFoundException;
import com.conference.calender.model.Conference;
import com.conference.calender.repository.conference.ConferenceRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
class ConferencePlanBusinessLogicServiceImplTest {

    private static final String AGENDA_TIME_LINE_MORNING = "MORNING";
    private static final String AGENDA_TIME_LINE_AFTERNOON = "AFTERNOON";
    private boolean morningTrackCompleted = false;
    private boolean afternoonTrackCompleted = false;
    private static final LocalDateTime conferenceAfternoonStartDate = LocalDateTime.of(2024,02,14,13,00);
    private static final LocalDateTime conferenceLunchDate = LocalDateTime.parse("2024-02-14T12:00");
    private static final Integer morningAvailableTime = 180;
    private static Integer afternoonAvailableTime = 240;
    private static final Integer afternoonAvailableTimeWithNetwokEvent = 180;


    @InjectMocks
    ConferencePlanBusinessLogicServiceImpl conferencePlanBusinessLogicService;

    private ConferenceRepository conferenceRepository;
    ModelMapper modelMapper;

    @BeforeEach
    void setUp() {

        conferencePlanBusinessLogicService= new ConferencePlanBusinessLogicServiceImpl();
        conferenceRepository= mock(ConferenceRepository.class);
        modelMapper=new ModelMapper();

        ReflectionTestUtils.setField(conferencePlanBusinessLogicService, "conferenceRepository", conferenceRepository);
        ReflectionTestUtils.setField(conferencePlanBusinessLogicService, "modelMapper", modelMapper);



    }

    @Test
    public void presentationListAlgorithm_Test() {
        List<ConferenceEntity> conferenceEntityList = dummyConferenceEntityList();
        Mockito.when(conferenceRepository.saveAndFlush(Mockito.any()))
                .thenReturn(conferenceEntityList.get(0));

        Map<String, List<ConferenceEntity>> actual = conferencePlanBusinessLogicService.presentationListAlgorithm(conferenceEntityList);

        Assert.assertEquals(actual.size(),2);
        Assert.assertNotNull(actual.get(AGENDA_TIME_LINE_MORNING));
        Assert.assertNotNull(actual.get(AGENDA_TIME_LINE_AFTERNOON));
    }

    @Test
    public void getAgendaList() throws NotFoundException {
        Mockito.when(conferenceRepository.findAllByStatus(Mockito.anyInt()))
                .thenReturn(dummyConferenceEntityList());

        List<List<Conference>> actual = conferencePlanBusinessLogicService.getAgendaList();

        int actualTotalTime = actual.get(0).parallelStream().map(Conference::getDuration).reduce(0, Integer::sum);

        Assert.assertEquals(180, actualTotalTime);
    }

    @Test
    public void createTrack_TestwithMorning(){
        List<ConferenceEntity> conferenceEntityList = dummyConferenceEntityList();
        String trackTimeLine = AGENDA_TIME_LINE_MORNING;

        List<ConferenceEntity> track= dummyConferenceEntityList()
                .stream().limit(3).collect(Collectors.toList());

        List<ConferenceEntity> actual = conferencePlanBusinessLogicService.createTrack(conferenceEntityList,trackTimeLine);
        Assert.assertTrue(actual.size()>1);
    }

    @Test
    public void createTrack_TestwithAfternoon(){
        List<ConferenceEntity> conferenceEntityList = dummyConferenceEntityList();
        String trackTimeLine = AGENDA_TIME_LINE_AFTERNOON;
        this.morningTrackCompleted =true;

        List<ConferenceEntity> track= dummyConferenceEntityList()
        .stream().limit(4).collect(Collectors.toList());

        List<ConferenceEntity> actual = conferencePlanBusinessLogicService.createTrack(conferenceEntityList,trackTimeLine);
        Assert.assertTrue(actual.size()>1);
    }

    @Test
    public void checkTrackTime_TestMorning(){
        List<ConferenceEntity> conferenceEntityList = dummyConferenceEntityList();
        String trackTimeLine = AGENDA_TIME_LINE_MORNING;

        List<ConferenceEntity> actual = conferencePlanBusinessLogicService.checkTrackTime(conferenceEntityList,trackTimeLine);

        Assert.assertEquals(3,actual.size());
    }

    @Test
    public void checkTrackTime_TestAfterNoon(){
        List<ConferenceEntity> conferenceEntityList = dummyConferenceEntityList();
        String trackTimeLine = AGENDA_TIME_LINE_AFTERNOON;

        List<ConferenceEntity> actual = conferencePlanBusinessLogicService.checkTrackTime(conferenceEntityList,trackTimeLine);

        Assert.assertEquals(5,actual.size());

    }


    private List<ConferenceEntity> dummyConferenceEntityList(){
        List<ConferenceEntity> conferenceEntityList = new ArrayList<>();
        ConferenceEntity conferenceEntity = new ConferenceEntity();
        conferenceEntity.setStatus(1);
        conferenceEntity.setConferenceDateTime(LocalDateTime.now());
        conferenceEntity.setConferenceName("DummyConference");
        conferenceEntity.setConferenceDuration(60);
        for (int i=0 ; i<=7 ; i++){
            conferenceEntity.setConferenceName("DummyConference" + i);
            conferenceEntityList.add(conferenceEntity);
        }
        return conferenceEntityList;
    }
}