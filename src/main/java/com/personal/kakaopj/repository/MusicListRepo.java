package com.personal.kakaopj.repository;

import com.personal.kakaopj.domain.MusicList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MusicListRepo extends JpaRepository<MusicList, Long> {
    @Query(value = "select * from MusicList where profile_play_list_id = :id order by music_list_id desc limit 1", nativeQuery = true)
    MusicList getMyMainMusicList(@Param("id") long profilePlayListId);

    @Query(value = "select * from MusicList where profile_play_list_id = :id order by music_list_id desc", nativeQuery = true)
    ArrayList<MusicList> getMyAllMusicList(@Param("id") long profilePlayListId);
}
