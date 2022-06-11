package com.femio.cart.usercart;

import com.femio.cart.CartEntity;
import com.femio.cart.exceptions.CartNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserCartService {

    private final UserCartRepository userCartRepository;

    @Autowired
    public UserCartService(UserCartRepository userCartRepository) {
        this.userCartRepository = userCartRepository;
    }

    public UserCartEntity addUserCart(UserCartRequest userCartRequest) {
        UserCartEntity userCartEntity = UserCartEntity.builder()
                .cartCode(UUID.randomUUID().toString())
                .userId(userCartRequest.getUserId())
                .status(userCartRequest.getStatus())
                .build();
        return userCartRepository.save(userCartEntity);
    }

    public UserCartEntity updateUserCart(Long id, UserCartRequest userCartRequest) {
        UserCartEntity userCartEntity = userCartRepository.findById(id).get();
        userCartEntity.setUserId(userCartRequest.getUserId());
        userCartEntity.setCartCode(userCartRequest.getCartCode());
        userCartEntity.setStatus(userCartRequest.getStatus());

        return userCartRepository.save(userCartEntity);
    }

    public List<UserCartEntity> findAllUserCartsByUserID(Long id) {
        return userCartRepository.findUserCartEntitiesByUserId(id);
    }

    public UserCartEntity findUserCartEntityByCartCode(String code){
        return userCartRepository.findUserCartEntityByCartCode(code)
                .orElseThrow(() -> new CartNotFoundException("Cart by id: "+code+" was not found"));
    }

    public void deleteUserCartEntityByCartCode(String code){
        userCartRepository.deleteByCartCode(code);
    }

    public void deleteUserCartEntityById(Long id){
        userCartRepository.deleteUserCartEntityById(id);
    }

}
