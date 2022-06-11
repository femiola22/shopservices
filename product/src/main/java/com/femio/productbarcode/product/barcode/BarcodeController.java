package com.femio.productbarcode.product.barcode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/product/barcode")
public class
BarcodeController {

    private final BarcodeService barcodeService;

    public BarcodeController(BarcodeService barcodeService) {
        this.barcodeService = barcodeService;
    }

    @PostMapping("/add")
    public BarcodeEntity addProduct(@RequestBody BarcodeRequest newBarcodeRequest) {
        log.info("new barcode registration {}", newBarcodeRequest );
        return barcodeService.addProductBarcode(newBarcodeRequest);
    }

    @GetMapping("/find/{barcode}")
    public ResponseEntity<BarcodeEntity> getProductByBarcode(@PathVariable("barcode") String barcode) {
        BarcodeEntity barcodeEntity = barcodeService.findProductByBarcode(barcode);
        return new ResponseEntity<>(barcodeEntity, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{barcode}")
    public ResponseEntity<?> deleteProduct(@PathVariable("barcode") String barcode) {
        barcodeService.deleteBarcode(barcode);
        return new ResponseEntity<>("Product with id: " + barcode + " deleted successfully", HttpStatus.OK);
    }
}
