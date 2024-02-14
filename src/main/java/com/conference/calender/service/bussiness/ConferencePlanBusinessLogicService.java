package com.conference.calender.service.bussiness;

import com.conference.calender.Enum.PresentationStatus;
import com.conference.calender.entity.ConferenceEntity;
import com.conference.calender.exception.NotFoundException;
import com.conference.calender.model.Conference;
import com.conference.calender.repository.conference.ConferenceRepository;
import com.conference.calender.utility.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConferencePlanBusinessLogicService {

    private static final String AGENDA_TIME_LINE_MORNING = "MORNING";
    private static final String AGENDA_TIME_LINE_AFTERNOON = "AFTERNOON";

    @Autowired
    ModelMapper modelMapper;

    //Todo Projede dikkat ettigimiz kısımlar;
    //MVC, kodların kalitesi ve temizliği, validation, unit testler v.b. gibi genel kodlama pratikleridir.

    //Todo Bonuslar:
    //Dokümantasyon, unit test'lerde farklı edge case'lerin denenmesi, uygulama için farklı algoritmaların denenmesi.


    //Todo Endpoint'leri test edebilmek için Postman vb. ortamlara import edilebilir konfigürasyon dosyası sağlanmalıdır.


    //create network events start on 16 or 17 but if this start on 16 ten presentations must done in 16

    @Autowired
    ConferenceRepository conferenceRepository;

    private boolean morningTrackCompleted = false;
    private boolean afternoonTrackCompleted = false;

    public Map<String,List<ConferenceEntity>> presentationListAlgorithm() throws NotFoundException {

        Map<String, List<ConferenceEntity>> agendaMap = new HashMap<>();

        List<ConferenceEntity> conferenceEntityList = conferenceRepository.findAllByStatus(PresentationStatus.ACTIVATED.getStatusId());

        if(CollectionUtils.isEmpty(conferenceEntityList)){
         throw new NotFoundException("There is no any Conference record for Generate agende!..");
        }

        List<ConferenceEntity> track1 = createTrack(conferenceEntityList,AGENDA_TIME_LINE_MORNING);
        List<ConferenceEntity> sortedTrack1 = track1.stream().sorted(Comparator.comparingInt(ConferenceEntity::getConferenceDuration).reversed()).collect(Collectors.toList());
        agendaMap.put(AGENDA_TIME_LINE_MORNING, sortedTrack1);
        this.morningTrackCompleted=false;

        conferenceEntityList.removeAll(track1);
        List<ConferenceEntity> track2 = createTrack(conferenceEntityList,AGENDA_TIME_LINE_AFTERNOON);
        List<ConferenceEntity> sortedTrack2 = track2.stream().sorted(Comparator.comparingInt(ConferenceEntity::getConferenceDuration).reversed()).collect(Collectors.toList());
        agendaMap.put(AGENDA_TIME_LINE_AFTERNOON, sortedTrack2);

        return agendaMap;
    }

    public List<Conference> getAgendaList() throws NotFoundException {
        Map< String , List<ConferenceEntity>> agendaMap = presentationListAlgorithm();

        List<ConferenceEntity> agendaList = new ArrayList<>();

        //Todo create new saloons and full them
        LocalDateTime localDateTime = LocalDateTime.of(2024,02,14,9,00);

        Collection<List<ConferenceEntity>> agendaConferenceEntityListCollection = agendaMap.values();

         for (List<ConferenceEntity> agendaConferenceEntityList : agendaConferenceEntityListCollection){
             for (ConferenceEntity conferenceEntity : agendaConferenceEntityList){

                 conferenceEntity.setConferenceDateTime(localDateTime);
                 localDateTime=localDateTime.plusMinutes(Long.valueOf(conferenceEntity.getConferenceDuration()));

                 boolean isNonBlocked = Utility.checkDateRange(localDateTime);
                 if(isNonBlocked){
                     agendaList.add(conferenceEntity);
                     if(localDateTime.equals(LocalDateTime.parse("2024-02-14T12:00")))
                         localDateTime =  LocalDateTime.of(2024,02,14,13,00);
                 }else {
                     localDateTime =  LocalDateTime.of(2024,02,14,13,00);
                 }
             }
         }

         updateConferencesStatus(agendaList);

         List<Conference> agendaConferenceList = agendaList
                 .stream()
                 .map(agenda-> this.modelMapper.map(agenda,Conference.class))
                 .collect(Collectors.toList());


        return agendaConferenceList;
    }

    private void updateConferencesStatus(List<ConferenceEntity> conferenceEntityList) {
        for (ConferenceEntity conferenceEntity : conferenceEntityList){
            conferenceEntity.setStatus(PresentationStatus.DEACTIVATED.getStatusId());
            conferenceRepository.saveAndFlush(conferenceEntity);
        }
    }

    public List<ConferenceEntity> createTrack(List<ConferenceEntity> conferenceEntityList, String trackTimeLine){
        List<ConferenceEntity> track = new ArrayList<>();

        for (ConferenceEntity conferenceEntity: conferenceEntityList){
            track.add(conferenceEntity);
            track = checkTrackTime(track,trackTimeLine);
            //conferenceEntity.setStatus(PresentationStatus.SUSPENDED.getStatusId());
           // conferenceRepository.save(conferenceEntity);

            if(this.morningTrackCompleted || this.afternoonTrackCompleted)
                break;
        }

        return track;
    }

    public List<ConferenceEntity> checkTrackTime(List<ConferenceEntity> track, String trackTimeLine){
        int trackMax = AGENDA_TIME_LINE_MORNING.equals(trackTimeLine) ? 180 : 240;
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

        if(trackTimeLine.equals(AGENDA_TIME_LINE_MORNING) && trackTime==180){
            this.morningTrackCompleted=true;
        }else if(trackTimeLine.equals(AGENDA_TIME_LINE_AFTERNOON) && trackTime==240){
            this.afternoonTrackCompleted=true;
        }

        return track;
    }

    /*


Örnek Veri:

Architecting Your Codebase 60min
Overdoing it in Python 45min
Flavors of Concurrency in Java 30min
Ruby Errors from Mismatched Gem Versions 45min
JUnit 5 - Shaping the Future of Testing on the JVM 45min
Cloud Native Java lightning
Communicating Over Distance 60min
AWS Technical Essentials 45min
Continuous Delivery 30min
Monitoring Reactive Applications 30min
Pair Programming vs Noise 45min
Rails Magic 60min
Microservices "Just Right" 60min
Clojure Ate Scala (on my project) 45min
Perfect Scalability 30min
Apache Spark 30min
Async Testing on JVM 60min
A World Without HackerNews 30min
User Interface CSS in Apps 30min

Örnek Çıktı:

Track 1:
09:00AM Architecting Your Codebase 60min
10:00AM Overdoing it in Python 45min
10:45AM Flavors of Concurrency in Java 30min
11:15AM Ruby Errors from Mismatched Gem Versions 45min
12:00PM Lunch
01:00PM Microservices "Just Right" 60min
02:00PM JUnit 5 - Shaping the Future of Testing on the JVM 45min
02:45PM Pair Programming vs Noise 45min
03:30PM Perfect Scalability 30min
04:00PM Apache Spark 30min
04:30PM User Interface CSS in Apps 30min
05:00PM Networking Event

Track 2:
09:00AM Communicating Over Distance 60min
10:00AM Rails Magic 60min
11:00AM Continuous Delivery 30min
11:30AM Monitoring Reactive Applications 30min
12:00PM Lunch
01:00PM AWS Technical Essentials 45min
01:45PM Clojure Ate Scala (on my project) 45min
02:30PM A World Without HackerNews 30min
03:00PM Async Testing on JVM 60min
04:00PM Cloud Native Java lightning
05:00PM Networking Event
     */
}
