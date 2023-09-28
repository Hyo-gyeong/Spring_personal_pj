package com.personal.kakaopj.user.repository;

import com.personal.kakaopj.user.domain.User;
import com.personal.kakaopj.user.dto.UserSettingDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findUserById(long userId);
    ArrayList<User> findAll();

    // update User set kakao_id = , phone_number = '', update_date_time = '', is_birthday_hidden =  where user_id = ;
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update User " +
            "set kakao_id = :#{#dto.kakaoId}, phone_number = :#{#dto.phoneNumber}, update_date_time = :#{#dto.updateDateTime}, is_birthday_hidden = :#{#dto.isBirthdayHidden} " +
            "where user_id = :#{#dto.id}", nativeQuery = true)
    void updateUser(@Param("dto") UserSettingDto dto);

}
