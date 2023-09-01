package com.personal.kakaopj.repository;

import com.personal.kakaopj.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Long> {
    @Query(value = "select * from Profile where user_id = :userId and is_multi_profile = 1", nativeQuery = true)
    ArrayList<Profile> getMultiProfileByUserId(long userId);

    @Query(value = "select * from Profile where user_id = :userId and is_multi_profile = 0", nativeQuery = true)
    Profile getMyMainProfile(@Param("userId") long userId);

    @Query(value = "select * from Profile where user_id = :userId and is_multi_profile = 1", nativeQuery = true)
    ArrayList<Profile> getMyMultiProfile(@Param("userId") long userId);

    Profile findProfileById(long profileId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Profile set name = :name, status_message = :msg where profile_id = :pfId", nativeQuery = true)
    void updateUserProfile(@Param("name") String name, @Param("msg") String message, @Param("pfId") long profileId);
}
