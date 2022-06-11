package com.femio.cart.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindByCodeResponse {
    private Product product;
    private Integer quantity;
}
