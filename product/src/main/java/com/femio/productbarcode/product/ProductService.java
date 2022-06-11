package com.femio.productbarcode.product;

import com.femio.productbarcode.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductEntity addProduct(ProductRequest newProductRequest) {
        ProductEntity productEntity = ProductEntity.builder()
                .name(newProductRequest.getName())
                .section(newProductRequest.getSection())
                .quantity(newProductRequest.getQuantity())
                .price(newProductRequest.getPrice())
                .expiryDate(newProductRequest.getExpiryDate())
                .lowStockLevel(newProductRequest.getLowStockLevel())
                .imageLink(newProductRequest.getImageLink())
                .code(UUID.randomUUID().toString())
                .build();

        return productRepository.save(productEntity);
    }

    public ProductEntity updateProduct(Long id, ProductRequest productRequest){
        ProductEntity productEntity = productRepository.findById(id).get();
        productEntity.setName(productRequest.getName());
        productEntity.setSection(productRequest.getSection());
        productEntity.setQuantity(productRequest.getQuantity());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setExpiryDate(productRequest.getExpiryDate());
        productEntity.setLowStockLevel(productRequest.getLowStockLevel());
        productEntity.setImageLink(productRequest.getImageLink());
        productEntity.setCode(productRequest.getCode());

        return productRepository.save(productEntity);
    }

    public List<ProductEntity> findAllProducts() {
        return productRepository.findAll();
    }

    public ProductEntity findProductById(Long id){
        return productRepository.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product by id: "+id+" was not found"));
    }

    public void deleteProduct(Long id){
        productRepository.deleteProductById(id);
    }

    public List<ProductEntity> getProductWithSqlCommand(double maxPrice) {
        return productRepository.getProductWithPriceLessThanOneThousand(maxPrice);
    }

}