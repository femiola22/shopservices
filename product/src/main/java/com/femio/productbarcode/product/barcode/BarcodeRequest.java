package com.femio.productbarcode.product.barcode;

import com.femio.productbarcode.product.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarcodeRequest {

    private Long id;
    private Long productId;
    private String barcode;
}
