package com.personal.kakaopj.repository;

import com.personal.kakaopj.domain.ProfileImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
public interface ProfileImgRepo extends JpaRepository<ProfileImg, Long> {
    ArrayList<ProfileImg> findProfileImgById(long profileId);

    @Query(value = "select * from ProfileImg where profile_id = :id", nativeQuery = true)
    ArrayList<ProfileImg> getProfileImgByProfileId(@Param("id") long profileId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update ProfileImg set is_main = 0 where profile_id = :id", nativeQuery = true)
    void changeAllImgNotMain(@Param("id") long profileId);

    void deleteById(Long imgId);

    @Query(value = "select * from ProfileImg where profile_img_id = :id", nativeQuery = true)
    ProfileImg ifProfileImgExist(@Param("id") long imgId);
}
