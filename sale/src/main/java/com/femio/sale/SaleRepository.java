package com.femio.sale;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

    void deleteSaleEntityById(Long id);

    Optional<SaleEntity> findSaleEntityById(Long id);

    Optional<List<SaleEntity>> findSaleEntitiesByUserIdAndCreatedAt(Long userId, String date);

    Optional<List<SaleEntity>> findSaleEntitiesByCreatedAtBetween(String date1, String date2);

}
