package com.personal.kakaopj.chat.repository;

import com.personal.kakaopj.chat.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepo extends JpaRepository<Chat, Long> {

}
