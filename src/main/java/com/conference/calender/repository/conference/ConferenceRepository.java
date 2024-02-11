package com.conference.calender.repository.conference;

import com.conference.calender.entity.ConferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConferenceRepository extends JpaRepository<ConferenceEntity, Long> {


}
