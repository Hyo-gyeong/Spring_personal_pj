package com.personal.kakaopj.repository;

import com.personal.kakaopj.domain.ProfileBgImg;
import com.personal.kakaopj.domain.ProfileImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProfileBgImgRepository extends JpaRepository<ProfileBgImg, Long> {
    @Query(value = "select * from ProfileBgImg where profile_id = :id", nativeQuery = true)
    ArrayList<ProfileBgImg> getProfileImgByProfileId(@Param("id") long profileId);
}
