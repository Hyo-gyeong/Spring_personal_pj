package com.personal.kakaopj.repository;

import com.personal.kakaopj.domain.ProfileImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProfileImgRepository extends JpaRepository<ProfileImg, Long> {
    ArrayList<ProfileImg> findProfileImgById(long profileId);

    @Query(value = "select * from ProfileImg where profile_id = :id", nativeQuery = true)
    ArrayList<ProfileImg> getProfileImgByProfileId(@Param("id") long profileId);
}
