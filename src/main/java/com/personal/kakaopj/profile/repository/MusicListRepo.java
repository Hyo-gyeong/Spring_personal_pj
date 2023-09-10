package com.personal.kakaopj.profile.repository;

import com.personal.kakaopj.music.domain.MusicList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MusicListRepo extends JpaRepository<MusicList, Long> {

    // select music_list_id, profile_play_list_id, music_id, is_like, add_date_time from MusicList where profile_play_list_id =  order by music_list_id desc limit 1;
    @Query(value = "select music_list_id, profile_play_list_id, music_id, is_like, add_date_time " +
                    "from MusicList " +
                    "where profile_play_list_id = :profilePlayListId " +
                    "order by music_list_id desc " +
                    "limit 1", nativeQuery = true)
    MusicList getMyMainMusicList(@Param("profilePlayListId") long profilePlayListId);

    // select music_list_id, profile_play_list_id, music_id, is_like, add_date_time from MusicList where profile_play_list_id =  order by music_list_id desc;
    @Query(value = "select music_list_id, profile_play_list_id, music_id, is_like, add_date_time " +
                    "from MusicList " +
                    "where profile_play_list_id = :profilePlayListId " +
                    "order by music_list_id desc", nativeQuery = true)
    ArrayList<MusicList> getMyAllMusicList(@Param("profilePlayListId") long profilePlayListId);
}
