package com.personal.kakaopj.profile.repository;

import com.personal.kakaopj.profile.domain.Profile;
import com.personal.kakaopj.profile.dto.ProfileDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Long> {

    // select profile_id, user_id, name, status_message, is_multi_profile, update_date_time, create_date_time from Profile where user_id =  and is_multi_profile =  order by profile_id desc
    @Query(value = "select profile_id, user_id, name, status_message, is_multi_profile, update_date_time, create_date_time " +
                    "from Profile " +
                    "where user_id = :userId and is_multi_profile = 0 " +
                    "order by profile_id desc", nativeQuery = true)
    Profile getMyMainProfile(@Param("userId") long userId);

    // select profile_id, user_id, name, status_message, is_multi_profile, update_date_time, create_date_time from Profile where user_id =  and is_multi_profile =  order by profile_id desc
    @Query(value = "select profile_id, user_id, name, status_message, is_multi_profile, update_date_time, create_date_time " +
                    "from Profile " +
                    "where user_id = :userId and is_multi_profile = 1 " +
                    "order by profile_id desc", nativeQuery = true)
    ArrayList<Profile> getMyMultiProfile(@Param("userId") long userId);

    Profile findProfileById(long profileId);

    // update Profile set name = , status_message =  where profile_id =
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update Profile set name = :#{#dto.name}, status_message = :#{#dto.statusMessage} " +
                    "where profile_id = :#{#dto.id}", nativeQuery = true)
    void updateUserProfile(@Param("dto") ProfileDto dto);

}
