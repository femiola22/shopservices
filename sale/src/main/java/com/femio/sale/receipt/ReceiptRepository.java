package com.femio.sale.receipt;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReceiptRepository extends JpaRepository<ReceiptEntity, Long> {

    void deleteReceiptEntitiesByCode(String code);

    void deleteReceiptEntityById(Long id);

    Optional <ReceiptEntity> findReceiptEntityById(Long id);

    Optional <List<ReceiptEntity>> findReceiptEntitiesByCode(String code);
}
