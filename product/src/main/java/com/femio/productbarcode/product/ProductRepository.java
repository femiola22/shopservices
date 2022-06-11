package com.femio.productbarcode.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    void deleteProductById(Long id);
    Optional<ProductEntity> findProductById(Long id);

    @Query(value = "SELECT * FROM product WHERE price <= ?1", nativeQuery = true)
    public List<ProductEntity> getProductWithPriceLessThanOneThousand(double maxPrice);
}
