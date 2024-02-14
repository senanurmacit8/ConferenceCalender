package com.conference.calender.service.bussiness.impl;

import com.conference.calender.Enum.PresentationStatus;
import com.conference.calender.entity.ConferenceEntity;
import com.conference.calender.exception.NotFoundException;
import com.conference.calender.model.Conference;
import com.conference.calender.repository.conference.ConferenceRepository;
import com.conference.calender.service.bussiness.ConferencePlanBusinessLogicService;
import com.conference.calender.utility.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConferencePlanBusinessLogicServiceImpl implements ConferencePlanBusinessLogicService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ConferenceRepository conferenceRepository;

    private static final String AGENDA_TIME_LINE_MORNING = "MORNING";
    private static final String AGENDA_TIME_LINE_AFTERNOON = "AFTERNOON";
    private boolean morningTrackCompleted = false;
    private boolean afternoonTrackCompleted = false;
    private static final LocalDateTime conferenceAfternoonStartDate = LocalDateTime.of(2024,02,14,13,00);
    private static final LocalDateTime conferenceLunchDate = LocalDateTime.parse("2024-02-14T12:00");
    private static final Integer morningAvailableTime = 180;
    private static Integer afternoonAvailableTime = 240;
    private static final Integer afternoonAvailableTimeWithNetwokEvent = 180;

    @Override
    public Map<String,List<ConferenceEntity>> presentationListAlgorithm(List<ConferenceEntity> conferenceEntityList) {

        Map<String, List<ConferenceEntity>> agendaMap = new HashMap<>();

        List<ConferenceEntity> track1 = createTrack(conferenceEntityList,AGENDA_TIME_LINE_MORNING);
        List<ConferenceEntity> sortedTrack1 = track1.stream().sorted(Comparator.comparingInt(ConferenceEntity::getConferenceDuration).reversed()).collect(Collectors.toList());
        agendaMap.put(AGENDA_TIME_LINE_MORNING, sortedTrack1);
        updateConferencesStatus(sortedTrack1);
        this.morningTrackCompleted=false;

        conferenceEntityList.removeAll(track1);
        List<ConferenceEntity> track2 = createTrack(conferenceEntityList,AGENDA_TIME_LINE_AFTERNOON);
        List<ConferenceEntity> sortedTrack2 = track2.stream().sorted(Comparator.comparingInt(ConferenceEntity::getConferenceDuration).reversed()).collect(Collectors.toList());
        agendaMap.put(AGENDA_TIME_LINE_AFTERNOON, sortedTrack2);
        updateConferencesStatus(sortedTrack2);
        this.afternoonTrackCompleted=false;

        return agendaMap;
    }

    @Override
    public List<List<Conference>> getAgendaList() throws NotFoundException {
        List<List<Conference>> agendaConferenceSaloonlist = new ArrayList<>();

        List<Map< String , List<ConferenceEntity>>> agendaMapList = getConferenceSaloonsList(new ArrayList<>());

        agendaConferenceSaloonlist = createAgendasBySaloon(agendaMapList,agendaConferenceSaloonlist);

        return agendaConferenceSaloonlist;
    }

    private List<List<Conference>> createAgendasBySaloon(List<Map< String , List<ConferenceEntity>>> agendaMapList,List<List<Conference>> agendaConferenceSaloonlist){

        for (Map< String , List<ConferenceEntity>> agendaMap : agendaMapList){
            List<ConferenceEntity> agendaConferenceEntityList = new ArrayList<>();

            LocalDateTime conferencesStartDate = LocalDateTime.of(2024,02,14,9,00);
            for (List<ConferenceEntity> conferenceEntityList : agendaMap.values()){
                for (ConferenceEntity conferenceEntity : conferenceEntityList){

                    conferenceEntity.setConferenceDateTime(conferencesStartDate);
                    conferencesStartDate=conferencesStartDate.plusMinutes(Long.valueOf(conferenceEntity.getConferenceDuration()));

                    boolean isNonBlocked = Utility.checkDateRange(conferencesStartDate);
                    if(isNonBlocked){
                        agendaConferenceEntityList.add(conferenceEntity);
                        if(conferencesStartDate.equals(conferenceLunchDate))
                            conferencesStartDate = conferenceAfternoonStartDate;
                    }else {
                        conferencesStartDate = conferenceAfternoonStartDate;
                    }
                }
            }

            List<Conference> agendaConferenceList = agendaConferenceEntityList
                    .stream()
                    .map(agenda-> this.modelMapper.map(agenda,Conference.class))
                    .collect(Collectors.toList());

            agendaConferenceSaloonlist.add(agendaConferenceList);
        }

        return agendaConferenceSaloonlist;
    }

    private List<Map< String , List<ConferenceEntity>>> getConferenceSaloonsList(List<Map< String , List<ConferenceEntity>>> agendaMapList ) throws NotFoundException {
        List<ConferenceEntity> conferenceEntityList = conferenceRepository.findAllByStatus(PresentationStatus.ACTIVATED.getStatusId());

        if(!CollectionUtils.isEmpty(conferenceEntityList)){
            Map< String , List<ConferenceEntity>> agendaMap = presentationListAlgorithm(conferenceEntityList);
            agendaMapList.add(agendaMap);
            getConferenceSaloonsList(agendaMapList);
        }

        return agendaMapList;
    }

    private void updateConferencesStatus(List<ConferenceEntity> conferenceEntityList) {
        for (ConferenceEntity conferenceEntity : conferenceEntityList){
            if(!conferenceEntity.getConferenceName().equalsIgnoreCase("Networking Event")){
                conferenceEntity.setStatus(PresentationStatus.DEACTIVATED.getStatusId());
                conferenceRepository.saveAndFlush(conferenceEntity);
            }
        }
    }

    @Override
    public List<ConferenceEntity> createTrack(List<ConferenceEntity> conferenceEntityList, String trackTimeLine){
        List<ConferenceEntity> track = new ArrayList<>();

        for (ConferenceEntity conferenceEntity: conferenceEntityList){
            track.add(conferenceEntity);
            afternoonAvailableTime = new Random(0).nextInt(1)%2 == 0 ? afternoonAvailableTimeWithNetwokEvent : afternoonAvailableTime;
            track = checkTrackTime(track,trackTimeLine);

            if(this.morningTrackCompleted || this.afternoonTrackCompleted)
                break;
        }

        return track;
    }

    @Override
    public List<ConferenceEntity> checkTrackTime(List<ConferenceEntity> track, String trackTimeLine){
        int trackMax = AGENDA_TIME_LINE_MORNING.equals(trackTimeLine) ? morningAvailableTime : afternoonAvailableTime;
        int trackTime = track.parallelStream().map(ConferenceEntity::getConferenceDuration)
                .reduce(0, Integer::sum);

        if (trackTime>trackMax) {
            List<ConferenceEntity> sortedTrack = track
                    .stream()
                    .sorted(Comparator.comparingInt(ConferenceEntity::getConferenceDuration).reversed())
                    .limit(track.size() - 1)
                    .collect(Collectors.toList());

            track = this.checkTrackTime(sortedTrack,trackTimeLine);
        }

        if(trackTimeLine.equals(AGENDA_TIME_LINE_MORNING) && trackTime==morningAvailableTime){
            this.morningTrackCompleted=true;
        }else if(trackTimeLine.equals(AGENDA_TIME_LINE_AFTERNOON) && trackTime==afternoonAvailableTime){
            if(afternoonAvailableTime==afternoonAvailableTimeWithNetwokEvent){
                ConferenceEntity eventConferenceEntity = new ConferenceEntity();
                eventConferenceEntity.setConferenceName("Networking Event");
                eventConferenceEntity.setConferenceDateTime(LocalDateTime.of(conferenceLunchDate.toLocalDate(), LocalTime.of(16,00)));
                eventConferenceEntity.setConferenceDuration(60);
                track.add(eventConferenceEntity);
            }else {
                ConferenceEntity eventConferenceEntity = new ConferenceEntity();
                eventConferenceEntity.setConferenceName("Networking Event");
                eventConferenceEntity.setConferenceDateTime(LocalDateTime.of(conferenceLunchDate.toLocalDate(), LocalTime.of(17,00)));
                eventConferenceEntity.setConferenceDuration(60);
                track.add(eventConferenceEntity);
            }
            this.afternoonTrackCompleted=true;
        }

        return track;
    }
}
