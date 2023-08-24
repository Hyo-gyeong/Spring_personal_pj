package com.personal.kakaopj.repository;

import com.personal.kakaopj.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepo extends JpaRepository<Friend, Long> {
}
