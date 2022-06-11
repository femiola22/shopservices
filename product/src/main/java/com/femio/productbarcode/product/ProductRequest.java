package com.femio.productbarcode.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private Long id;
    private String name;
    private String section;
    private int quantity;
    private double price;
    private String expiryDate;
    private int lowStockLevel;
    private String imageLink;
    private String code;
}
