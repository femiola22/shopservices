package com.femio.sale;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/sale")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping("/add")
    public SaleEntity addSale(@RequestBody SaleRequest saleRequest) {
        log.info("new sale registration {}", saleRequest );
        return saleService.addSale(saleRequest);
    }

    @GetMapping("/all")
    public List<SaleEntity> getAllSales() {
        return saleService.findAllSales();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<SaleEntity> getSaleById(@PathVariable("id") Long id) {
        SaleEntity saleEntity = saleService.findSaleById(id);
        return new ResponseEntity<>(saleEntity, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SaleEntity> updateSale(@PathVariable Long id,
                                                 @RequestBody SaleRequest saleRequest) {
        SaleEntity updatedSale = saleService.updateSale(id, saleRequest);
        return new ResponseEntity<>(updatedSale, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSale(@PathVariable("id") Long id) {
        saleService.deleteSale(id);
        return new ResponseEntity<>("Sale with id: " + id + " deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/find/user/date/{userId}/{date1}")
    public List<SaleEntity> findSaleEntitiesByUserIdAndDateTime(@PathVariable("userId") Long userId, @PathVariable("date1") String date1) {
        return saleService.findSaleEntitiesByUserIdAndCreatedAt(userId, date1);
    }

    @GetMapping("/find/dates/{date1}/{date2}")
    public List<SaleEntity> findSaleEntitiesByDateTimeBetween(@PathVariable("date1") String date1, @PathVariable("date2") String date2) {
        return saleService.findSaleEntitiesByCreatedAtBetween(date1, date2);
    }
}
