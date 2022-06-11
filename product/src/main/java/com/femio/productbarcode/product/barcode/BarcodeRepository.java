package com.femio.productbarcode.product.barcode;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BarcodeRepository extends JpaRepository<BarcodeEntity,Long> {

    void deleteBarcodeEntityByBarcode(String barcode);

    Optional<BarcodeEntity> findBarcodeEntityByBarcode(String barcode);

}
