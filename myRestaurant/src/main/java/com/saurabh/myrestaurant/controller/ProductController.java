package com.saurabh.myrestaurant.controller;


import com.saurabh.myrestaurant.dto.ProductRequest;
import com.saurabh.myrestaurant.entity.Product;
import com.saurabh.myrestaurant.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;


    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productCreateRequest) {
        return ResponseEntity.ok(productService.createProduct(productCreateRequest));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    // get Product by Name with parameter query
    @GetMapping("/by-name")
    public ResponseEntity<List<Product>> getProductByName(@RequestParam("name") String name) {
        List<Product> product = productService.getProductByName(name);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id , @RequestBody @Valid ProductRequest productUpdateRequest) {
        Product product = productService.updateProduct(id, productUpdateRequest);
        if(product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        Boolean deleted = productService.deleteProduct(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Product Deleted Successfully!");
    }

    @GetMapping("/top-2-products")
    public ResponseEntity<List<Product>> getTopProducts() {
        return ResponseEntity.ok(productService.getTop2ProductsWithinPriceRange());
    }
}