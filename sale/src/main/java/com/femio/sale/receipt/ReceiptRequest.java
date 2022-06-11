package com.femio.sale.receipt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptRequest {

    private Long id;
    private String code;
    private Long productId;
    private Double productPrice;
    private Integer quantity;
    private String soldAt;
}
