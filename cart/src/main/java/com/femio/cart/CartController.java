package com.femio.cart;

import com.femio.cart.VO.ResponseTemplateCartByCode;
import com.femio.cart.VO.ResponseTemplateCartById;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public CartEntity addCart(@RequestBody CartRequest newCartRequest) {
        log.info("new cart registration {}", newCartRequest);
        return cartService.addCart(newCartRequest);
    }

    @GetMapping("/all")
    public List<CartEntity> getAllCarts() {
        return cartService.findAllCarts();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ResponseTemplateCartById> getCartById(@PathVariable("id") Long id) {
        ResponseTemplateCartById cartEntity = cartService.findCartById(id);
        return new ResponseEntity<>(cartEntity, HttpStatus.OK);
    }

    @GetMapping("/find/code/{code}")
    public ResponseTemplateCartByCode getCartById(@PathVariable("code") String code) {
        return cartService.findCartByCode(code);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CartEntity> updateCart(@PathVariable Long id,
                                                    @RequestBody CartRequest cartRequest) {
        CartEntity updatedCart = cartService.updateCart(id, cartRequest);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @DeleteMapping("/delete/code/{code}")
    public ResponseEntity<?> deleteEntityByCode(@PathVariable("code") String code) {
        cartService.deleteCartByCode(code);
        return new ResponseEntity<>("Cart item(s) with code: " + code + " deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable("id") Long id) {
        cartService.deleteCartById(id);
        return new ResponseEntity<>("Cart with id: " + id + " deleted successfully", HttpStatus.OK);
    }

}
