package com.conference.calender.repository.conference;

import com.conference.calender.entity.ConferenceEntity;
import com.conference.calender.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ConferenceRepository extends JpaRepository<ConferenceEntity, Long> {
    List<ConferenceEntity>  findByConferenceTopic(String conferenceTopic);

    List<ConferenceEntity> findAllByStatus(Integer status);

    List<ConferenceEntity> findByConferenceDurationAndStatus(Integer duration, Integer status);

}
