package com.femio.sale.receipt;

import com.femio.sale.exceptions.ReceiptNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ReceiptService {

    private final ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public ReceiptEntity addReceipt(ReceiptRequest receiptRequest) {
        ReceiptEntity receiptEntity = ReceiptEntity.builder()
                .code(receiptRequest.getCode())
                .productId(receiptRequest.getProductId())
                .productPrice(receiptRequest.getProductPrice())
                .quantity(receiptRequest.getQuantity())
                .soldAt(LocalDateTime.now().toString())
                .build();

        return receiptRepository.save(receiptEntity);
    }

    public ReceiptEntity updateReceipt(Long id, ReceiptRequest receiptRequest){
        ReceiptEntity receiptEntity = receiptRepository.findById(id).get();
        receiptEntity.setCode(receiptRequest.getCode());
        receiptEntity.setProductId(receiptRequest.getProductId());
        receiptEntity.setProductPrice(receiptRequest.getProductPrice());
        receiptEntity.setQuantity(receiptRequest.getQuantity());
        receiptEntity.setSoldAt(LocalDateTime.now().toString());

        return receiptRepository.save(receiptEntity);
    }

    public List<ReceiptEntity> findAllReceipts() {
        return receiptRepository.findAll();
    }

    public ReceiptEntity findReceiptById(Long id){
        return receiptRepository.findReceiptEntityById(id)
                .orElseThrow(() -> new ReceiptNotFoundException("Receipt by id: "+id+" was not found"));
    }

    public void deleteReceipt(Long id){
        receiptRepository.deleteReceiptEntityById(id);
    }

    public void deleteReceiptEntitiesByCode(String code){
        receiptRepository.deleteReceiptEntitiesByCode(code);
    }

    public List<ReceiptEntity> findReceiptEntitiesByCode(String code) {
        return receiptRepository.findReceiptEntitiesByCode(code)
                .orElseThrow(() -> new ReceiptNotFoundException("Receipt not found"));
    }

}
