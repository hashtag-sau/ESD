package com.saurabh.myrestaurant.service;

import com.saurabh.myrestaurant.dto.ProductRequest;
import com.saurabh.myrestaurant.entity.Product;
import com.saurabh.myrestaurant.mapper.ProductMapper;
import com.saurabh.myrestaurant.repo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper Productmapper;

    public Product createProduct(ProductRequest request) {
        Product product = Productmapper.toProduct(request);
        product = productRepository.save(product);
        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product updateProduct(Long id, ProductRequest request) {
        Product product = getProductById(id);
        if(product != null) {
            product.setName(request.name());
            product.setPrice(request.price());

            return productRepository.save(product);
        }
        return null;
    }

    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Product> getTop2ProductsWithinPriceRange() {
        return productRepository.getTop2ProductsWithinPriceRange();
    }

    // Read product by name
    public List<Product> getProductByName(String productName) {
        return productRepository.findByNameContaining(productName);
    }

}

