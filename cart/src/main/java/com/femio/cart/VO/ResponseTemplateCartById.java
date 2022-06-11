package com.femio.cart.VO;

import com.femio.cart.CartEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateCartById {

    private CartEntity cart;
    private Product product;
}
