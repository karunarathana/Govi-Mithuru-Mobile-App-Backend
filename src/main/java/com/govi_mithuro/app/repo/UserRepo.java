package com.govi_mithuro.app.repo;

import com.govi_mithuro.app.model.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity,Integer> {
    @Query(value = "SELECT * FROM t_user WHERE email = :userEmail", nativeQuery = true)
    Optional<UserEntity> findByUserEmail(String userEmail);

    @Query(value = "SELECT * FROM t_user WHERE user_name = :userName", nativeQuery = true)
    Optional<UserEntity> findByUserName(String userName);

    @Modifying
    @Transactional
    @Query(value = "UPDATE t_user SET password = :password WHERE email = :userEmail", nativeQuery = true)
    void updateUserPassword(String userEmail,String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE t_user SET otpcode = :otpCode WHERE email = :userEmail", nativeQuery = true)
    void updateOTPCode(String userEmail,String otpCode);

}
