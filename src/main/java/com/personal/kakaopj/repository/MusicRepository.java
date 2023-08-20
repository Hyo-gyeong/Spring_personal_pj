package com.personal.kakaopj.repository;

import com.personal.kakaopj.domain.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicRepository extends JpaRepository<Music, Long> {
    Music findMusicById(long musicId);
}
