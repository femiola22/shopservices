package com.femio.cart.usercart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/user/cart")
public class UserCartController {
    
    private final UserCartService userCartService;

    public UserCartController(UserCartService userCartService) {
        this.userCartService = userCartService;
    }


    @PostMapping("/add")
    public UserCartEntity addUserCart(@RequestBody UserCartRequest newUserCartRequest) {
        log.info("new userCart registration {}", newUserCartRequest);
        return userCartService.addUserCart(newUserCartRequest);
    }

    @GetMapping("/find/code/{code}")
    public ResponseEntity<UserCartEntity> getUserCartById(@PathVariable("code") String code) {
        UserCartEntity userCartEntity = userCartService.findUserCartEntityByCartCode(code);
        return new ResponseEntity<>(userCartEntity, HttpStatus.OK);
    }

    @GetMapping("/find/user/{userId}")
    public List<UserCartEntity> findAllUserCartsByUserID(@PathVariable("userId") Long id) {
        return userCartService.findAllUserCartsByUserID(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserCartEntity> updateUserCart(@PathVariable Long id,
                                                 @RequestBody UserCartRequest userCartRequest) {
        UserCartEntity updatedUserCart = userCartService.updateUserCart(id, userCartRequest);
        return new ResponseEntity<>(updatedUserCart, HttpStatus.OK);
    }

    @DeleteMapping("/delete/code/{code}")
    public void deleteEntityByCode(@PathVariable("code") String code) {
        userCartService.deleteUserCartEntityByCartCode(code);
    }

    @DeleteMapping("/delete/id/{id}")
    public void deleteUserCart(@PathVariable("id") Long id) {
        userCartService.deleteUserCartEntityById(id);
    }

}
