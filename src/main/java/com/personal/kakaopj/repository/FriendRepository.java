package com.personal.kakaopj.repository;

import com.personal.kakaopj.domain.Friend;
import com.personal.kakaopj.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
}
