package com.personal.kakaopj.music.repository;

import com.personal.kakaopj.music.domain.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepo extends JpaRepository<Music, Long> {
    Music findMusicById(long musicId);
}
