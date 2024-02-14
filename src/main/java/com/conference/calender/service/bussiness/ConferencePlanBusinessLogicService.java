package com.conference.calender.service.bussiness;

import com.conference.calender.entity.ConferenceEntity;
import com.conference.calender.exception.NotFoundException;
import com.conference.calender.model.Conference;

import java.util.List;
import java.util.Map;

public interface ConferencePlanBusinessLogicService {

     Map<String, List<ConferenceEntity>> presentationListAlgorithm(List<ConferenceEntity> conferenceEntityList) throws NotFoundException ;
    public List<List<Conference>> getAgendaList() throws NotFoundException ;
     List<ConferenceEntity> createTrack(List<ConferenceEntity> conferenceEntityList, String trackTimeLine);

     List<ConferenceEntity> checkTrackTime(List<ConferenceEntity> track, String trackTimeLine);
}
