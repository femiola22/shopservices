package com.femio.cart.usercart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserCartRepository  extends JpaRepository<UserCartEntity, Long> {

    void deleteUserCartEntityById(Long id);

    void deleteByCartCode(String code);


    @Query(value = "SELECT * from user_cart where user_id = ?1",
            nativeQuery = true)
    List<UserCartEntity> findUserCartEntitiesByUserId(Long userId);

    Optional <UserCartEntity> findUserCartEntityByCartCode(String code);

}
