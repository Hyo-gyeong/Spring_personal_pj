package com.personal.kakaopj.groupchat.repository;

import com.personal.kakaopj.groupchat.domain.GroupChatting;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupChattingRepo extends JpaRepository<GroupChatting, Long> {

    // select group_chatting_id, group_chatroom_id, user_id, group_chatroom_name, is_alarm_on, is_favorite, is_pinned from GroupChatting where user_id =  order by group_chatting_id desc;
    @Query(value = "select group_chatting_id, group_chatroom_id, user_id, group_chatroom_name, is_alarm_on, is_favorite, is_pinned " +
            "from GroupChatting " +
            "where user_id = :userId " +
            "order by group_chatting_id desc", nativeQuery = true)
    ArrayList<GroupChatting> getMyGroupChattingList(@Param("userId") long userId);

    GroupChatting findGroupChattingById(long groupChattingId);
}