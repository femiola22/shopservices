package com.femio.productbarcode.product;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ProductEntity addProduct(@RequestBody ProductRequest newProductRequest) {
        log.info("new product registration {}", newProductRequest );
        return productService.addProduct(newProductRequest);
    }

    @GetMapping("/all")
    public List<ProductEntity> getAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable("id") Long id) {
        ProductEntity productEntity = productService.findProductById(id);
        return new ResponseEntity<>(productEntity, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable Long id,
                                                       @RequestBody ProductRequest productRequest) {
        ProductEntity updatedProduct = productService.updateProduct(id, productRequest);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product with id: " + id + " deleted successfully", HttpStatus.OK);
    }

    @GetMapping("/show/{maxPrice}")
    public ResponseEntity<List<ProductEntity>> getProductWithPriceLessThanOneThousand(@PathVariable("maxPrice") double maxPrice) {
        List<ProductEntity> productEntities = productService.getProductWithSqlCommand(maxPrice);
        return new ResponseEntity<>(productEntities, HttpStatus.OK);
    }
}
