package com.femio.sale.receipt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/receipt")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/add")
    public ReceiptEntity addReceipt(@RequestBody ReceiptRequest receiptRequest) {
        log.info("new receipt registration {}", receiptRequest );
        return receiptService.addReceipt(receiptRequest);
    }

    @GetMapping("/all")
    public List<ReceiptEntity> getAllReceipts() {
        return receiptService.findAllReceipts();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ReceiptEntity> getReceiptById(@PathVariable("id") Long id) {
        ReceiptEntity receiptEntity = receiptService.findReceiptById(id);
        return new ResponseEntity<>(receiptEntity, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReceiptEntity> updateReceipt(@PathVariable Long id,
                                                       @RequestBody ReceiptRequest receiptRequest) {
        ReceiptEntity updatedReceipt = receiptService.updateReceipt(id, receiptRequest);
        return new ResponseEntity<>(updatedReceipt, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReceipt(@PathVariable("id") Long id) {
        receiptService.deleteReceipt(id);
        return new ResponseEntity<>("Receipt with id: " + id + " deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping("/delete/code/{code}")
    public ResponseEntity<?> deleteReceiptEntitiesByCode(@PathVariable("code") String code) {
        receiptService.deleteReceiptEntitiesByCode(code);
        return new ResponseEntity<>("Receipt with code: " + code + " deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/find/code/{code}")
    public List<ReceiptEntity> findReceiptEntitiesByUserIdAndDateTime(@PathVariable("code") String code) {
        return receiptService.findReceiptEntitiesByCode(code);
    }

}
