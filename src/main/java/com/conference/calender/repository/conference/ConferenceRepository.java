package com.conference.calender.repository.conference;

import com.conference.calender.entity.ConferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
public interface ConferenceRepository extends JpaRepository<ConferenceEntity, Long> {
    List<ConferenceEntity>  findByConferenceTopic(String conferenceTopic);

    List<ConferenceEntity> findAllByStatus(Integer status);

   @Modifying
   @Query("update ConferenceEntity u set u.status = 1 ")
    void updateAll();

}
