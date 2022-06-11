package com.femio.cart;

import com.femio.cart.VO.FindByCodeResponse;
import com.femio.cart.VO.Product;
import com.femio.cart.VO.ResponseTemplateCartByCode;
import com.femio.cart.VO.ResponseTemplateCartById;
import com.femio.cart.exceptions.CartNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CartService {

    private final CartRepository cartRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public CartService(CartRepository cartRepository, RestTemplate restTemplate) {
        this.cartRepository = cartRepository;
        this.restTemplate = restTemplate;
    }

    public CartEntity addCart(CartRequest cartRequest) {
        CartEntity cartEntity = CartEntity.builder()
                .product_id(cartRequest.getProduct_id())
                .quantity(cartRequest.getQuantity())
                .code(cartRequest.getCode())
                .build();

        return cartRepository.save(cartEntity);
    }

    public CartEntity updateCart(Long id, CartRequest cartRequest){
        CartEntity cartEntity = cartRepository.findById(id).get();
        cartEntity.setProduct_id(cartRequest.getProduct_id());
        cartEntity.setQuantity(cartRequest.getQuantity());
        cartEntity.setCode(cartRequest.getCode());

        return cartRepository.save(cartEntity);
    }

    public List<CartEntity> findAllCarts() {
        return cartRepository.findAll();
    }

    public ResponseTemplateCartById findCartById(Long id){

        ResponseTemplateCartById responseTemplateVO = new ResponseTemplateCartById();
        CartEntity cart = cartRepository.findCartById(id)
                .orElseThrow(() -> new CartNotFoundException("Cart by id: "+id+" was not found"));

        Product product = restTemplate.getForObject("http://PRODUCT-SERVICE/api/v1/product/find/{id}"
                ,Product.class,
                cart.getProduct_id());

        responseTemplateVO.setCart(cart);
        responseTemplateVO.setProduct(product);

        return responseTemplateVO;

    }

    public void deleteCartById(Long id){
        cartRepository.deleteCartById(id);
    }

    public void deleteCartByCode(String code){
        cartRepository.deleteCartByCode(code);
    }

    public ResponseTemplateCartByCode findCartByCode(String code){

        ResponseTemplateCartByCode responseTemplateCartByCode = new ResponseTemplateCartByCode();

        List<CartEntity> cartEntities = cartRepository.findCartByCode(code)
                .orElseThrow(() -> new CartNotFoundException("Cart by code: " + code + " was not found"));

        responseTemplateCartByCode.setCode(code);

        List<FindByCodeResponse> findByCodeResponse = new ArrayList<>() ;

        for(CartEntity cartEntity:cartEntities){
            Product product = restTemplate.getForObject(
                    "http://PRODUCT-SERVICE/api/v1/product/find/{id}",
                    Product.class,
                    cartEntity.getProduct_id());
            findByCodeResponse.add(new FindByCodeResponse(product, cartEntity.getQuantity()));
        }

        responseTemplateCartByCode.setProducts(findByCodeResponse);

        return responseTemplateCartByCode;

    }

}