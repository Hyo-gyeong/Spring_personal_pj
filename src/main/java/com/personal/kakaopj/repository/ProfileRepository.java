package com.personal.kakaopj.repository;

import com.personal.kakaopj.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    ArrayList<Profile> findProfileByUserId(long userId);

    @Query(value = "select * from Profile where user_id = :userId and is_multi_profile = 0", nativeQuery = true)
    Profile getMainProfile(@Param("userId") long userId);
}
