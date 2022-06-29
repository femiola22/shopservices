package com.femio.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    void deleteUserById(Long id);
    UserEntity findUserById(Long id);

    @Query(value = "SELECT * FROM users WHERE username = ?1 AND password = ?2", nativeQuery = true)
    Optional<UserEntity> loginUser(String username, String password);
}
