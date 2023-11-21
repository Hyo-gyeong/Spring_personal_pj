package com.personal.kakaopj.groupchat.repository;

import com.personal.kakaopj.groupchat.domain.GroupChatRoom;
import com.personal.kakaopj.groupchat.domain.GroupChatting;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupChatRoomRepo extends JpaRepository<GroupChatRoom, Long> {

    GroupChatRoom findGroupChatRoomById(long groupChatroomId);

}
