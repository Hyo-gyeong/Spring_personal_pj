package com.personal.kakaopj.repository;

import com.personal.kakaopj.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findUserById(long userId);
    ArrayList<User> findAll();

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update User set kakao_id = :kakaoId, phone_number = :phone, update_date_time = :updateDT, " +
            "is_birthday_hidden = :isHidden where user_id = :id", nativeQuery = true)
    void updateUser(@Param("kakaoId") String kakaoId, @Param("phone") String phone, @Param("isHidden") boolean BDHidden,
                    @Param("updateDT") LocalDateTime now, @Param("id") long userId);

}
