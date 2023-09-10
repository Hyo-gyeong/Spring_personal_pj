package com.personal.kakaopj.profile.repository;

import com.personal.kakaopj.profile.domain.ProfileBgImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Repository
public interface ProfileBgImgRepo extends JpaRepository<ProfileBgImg, Long> {

    // select profile_bg_img_id, profile_id, img_address, is_main, is_private, create_date_time, update_date_time from ProfileBgImg where profile_id =  order by profile_bg_img_id desc;
    @Query(value = "select profile_bg_img_id, profile_id, img_address, is_main, is_private, create_date_time, update_date_time " +
                    "from ProfileBgImg " +
                    "where profile_id = :profileId " +
                    "order by profile_bg_img_id desc", nativeQuery = true)
    ArrayList<ProfileBgImg> getProfileBgImgByProfileId(@Param("profileId") long profileId);

    // update ProfileBgImg set is_main = 0 where profile_id = ;
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update ProfileBgImg set is_main = 0 where profile_id = :id", nativeQuery = true)
    void changeAllBgImgNotMain(@Param("id") long profileId);

    void deleteById(Long bgImgId);

    // select profile_bg_img_id, profile_id, img_address, is_main, is_private, create_date_time, update_date_time from ProfileBgImg where profile_bg_img_id =  order by profile_bg_img_id desc;
    @Query(value = "select profile_bg_img_id, profile_id, img_address, is_main, is_private, create_date_time, update_date_time " +
                    "from ProfileBgImg " +
                    "where profile_bg_img_id = :bgImgId " +
                    "order by profile_bg_img_id desc", nativeQuery = true)
    ProfileBgImg ifProfileBgImgExist(@Param("bgImgId") long bgImgId);

//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query(value = "insert into ProfileBgImg (img_address, is_main, is_private, create_date_time, update_date_time)\n" +
//            " values (:imgAdd, 1, 0, :now, :now);", nativeQuery = true)
//    void addProfileBgImg(@Param("imgAdd") String imgAdd, @Param("now") LocalDateTime now);

}
