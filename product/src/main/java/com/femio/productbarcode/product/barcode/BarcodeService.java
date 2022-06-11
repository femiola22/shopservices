package com.femio.productbarcode.product.barcode;

import com.femio.productbarcode.exceptions.BarcodeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BarcodeService {

    private final BarcodeRepository barcodeRepository;

    @Autowired
    public BarcodeService(BarcodeRepository barcodeRepository) {
        this.barcodeRepository = barcodeRepository;
    }

    public BarcodeEntity findProductByBarcode(String barcode) {
        BarcodeEntity barcodeEntity = barcodeRepository.findBarcodeEntityByBarcode(barcode)
                .orElseThrow(() -> new BarcodeNotFoundException("Product by barcode: " + barcode + " was not found"));

        return barcodeEntity;
    }

    public void deleteBarcode(String barcode){
        barcodeRepository.deleteBarcodeEntityByBarcode(barcode);
    }

    public BarcodeEntity addProductBarcode(BarcodeRequest barcodeRequest){

        BarcodeEntity barcodeEntity = BarcodeEntity.builder()
                .productId(barcodeRequest.getProductId())
                .barcode(barcodeRequest.getBarcode())
                .build();
        return barcodeRepository.save(barcodeEntity);
    }
}
