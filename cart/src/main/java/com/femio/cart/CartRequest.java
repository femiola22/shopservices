package com.femio.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {

    private Long id;
    private String code;
    private Long product_id;
    private Integer quantity;

}
