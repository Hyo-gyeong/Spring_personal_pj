package com.personal.kakaopj.repository;

import com.personal.kakaopj.domain.ProfilePlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilePlayListRepo extends JpaRepository<ProfilePlayList, Long> {
    ProfilePlayList findProfilePlayListById(long profileId);
}
