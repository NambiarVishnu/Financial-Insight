package com.NetWorth.Transaction.repository;

import com.NetWorth.Transaction.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepo extends JpaRepository<UserInfo,Long> {

    Optional<UserInfo> findByUsername(String username);
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
