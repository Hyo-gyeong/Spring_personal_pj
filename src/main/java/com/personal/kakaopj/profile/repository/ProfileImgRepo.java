package com.personal.kakaopj.profile.repository;

import com.personal.kakaopj.profile.domain.ProfileImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
public interface ProfileImgRepo extends JpaRepository<ProfileImg, Long> {
    ArrayList<ProfileImg> findAllById(long profileId);

    // select profile_img_id, profile_id, img_address, is_main, update_date_time, create_date_time, is_private from ProfileImg where profile_id =  order by profile_img_id desc;
    @Query(value = "select profile_img_id, profile_id, img_address, is_main, update_date_time, create_date_time, is_private " +
                    "from ProfileImg " +
                    "where profile_id = :profileId " +
                    "order by profile_img_id desc", nativeQuery = true)
    ArrayList<ProfileImg> getProfileImgByProfileId(@Param("profileId") long profileId);

    // update ProfileImg set is_main = 0 where profile_id =
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update ProfileImg set is_main = 0 where profile_id = :profileId", nativeQuery = true)
    void changeAllImgNotMain(@Param("profileId") long profileId);

    void deleteById(Long imgId);

    // select profile_img_id, profile_id, img_address, is_main, update_date_time, create_date_time, is_private from ProfileImg where profile_img_id =  order by profile_img_id desc;
    @Query(value = "select profile_img_id, profile_id, img_address, is_main, update_date_time, create_date_time, is_private " +
                    "from ProfileImg " +
                    "where profile_img_id = :imgId " +
                    "order by profile_img_id desc", nativeQuery = true)
    ProfileImg ifProfileImgExist(@Param("imgId") long imgId);

//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query(value = "insert into ProfileImg (img_address, is_main, update_date_time, create_date_time)\n" +
//            " values (:imgAdd, 1, :now, :now);", nativeQuery = true)
//    void addProfileImg(@Param("imgAdd") String imgAdd, @Param("now") LocalDateTime now);
}
