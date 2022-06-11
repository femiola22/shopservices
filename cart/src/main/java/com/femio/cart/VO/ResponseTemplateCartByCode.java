package com.femio.cart.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTemplateCartByCode {
    private String code;
    private List<FindByCodeResponse> products;

}
