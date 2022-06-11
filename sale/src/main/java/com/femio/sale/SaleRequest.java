package com.femio.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleRequest {

    private Long id;
    private String receiptCode;
    private Long userId;
    private Double amountPaid;
    private String createdAt;


}
