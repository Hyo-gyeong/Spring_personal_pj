package com.personal.kakaopj.repository;

import com.personal.kakaopj.domain.ProfileBgImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

@Repository
public interface ProfileBgImgRepo extends JpaRepository<ProfileBgImg, Long> {
    @Query(value = "select * from ProfileBgImg where profile_id = :id", nativeQuery = true)
    ArrayList<ProfileBgImg> getProfileBgImgByProfileId(@Param("id") long profileId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update ProfileBgImg set is_main = 0 where profile_id = :id", nativeQuery = true)
    void changeAllBgImgNotMain(@Param("id") long profileId);
}
