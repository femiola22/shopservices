package com.femio.cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    void deleteCartById(Long id);
    Optional<CartEntity> findCartById(Long id);

    Optional <List<CartEntity>> findCartByCode(String code);

    void deleteCartByCode(String code);

}
