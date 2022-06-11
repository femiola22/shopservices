package com.femio.sale;

import com.femio.sale.exceptions.SaleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class SaleService {
    
    private final SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public SaleEntity addSale(SaleRequest saleRequest) {
        SaleEntity saleEntity = SaleEntity.builder()
                .receiptCode(saleRequest.getReceiptCode())
                .userId(saleRequest.getUserId())
                .amountPaid(saleRequest.getAmountPaid())
                .createdAt(LocalDate.now().toString())
                .build();

        return saleRepository.save(saleEntity);
    }

    public SaleEntity updateSale(Long id, SaleRequest saleRequest){
        SaleEntity saleEntity = saleRepository.findById(id).get();
        saleEntity.setReceiptCode(saleRequest.getReceiptCode());
        saleEntity.setUserId(saleRequest.getUserId());
        saleEntity.setAmountPaid(saleRequest.getAmountPaid());
        saleEntity.setCreatedAt(LocalDate.now().toString());

        return saleRepository.save(saleEntity);
    }

    public List<SaleEntity> findAllSales() {
        return saleRepository.findAll();
    }

    public SaleEntity findSaleById(Long id){
        return saleRepository.findSaleEntityById(id)
                .orElseThrow(() -> new SaleNotFoundException("Sale by id: "+id+" was not found"));
    }

    public void deleteSale(Long id){
        saleRepository.deleteSaleEntityById(id);
    }

    public List<SaleEntity> findSaleEntitiesByUserIdAndCreatedAt(Long userId, String date1) {
        return saleRepository.findSaleEntitiesByUserIdAndCreatedAt(userId, date1)
                .orElseThrow(() -> new SaleNotFoundException("Sale by user: "+userId+" was not found"));
    }

    public List<SaleEntity> findSaleEntitiesByCreatedAtBetween(String date1, String date2) {
        return saleRepository.findSaleEntitiesByCreatedAtBetween(date1, date2)
                .orElseThrow(() -> new SaleNotFoundException("No sale record found"));
    }

}
