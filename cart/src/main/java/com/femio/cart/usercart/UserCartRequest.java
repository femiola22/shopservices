package com.femio.cart.usercart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCartRequest {

    private Long id;
    private Long userId;
    private String cartCode;
    private String status;

}
