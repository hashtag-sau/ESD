package com.saurabh.myrestaurant.repo;

import com.saurabh.myrestaurant.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * FROM product WHERE price BETWEEN 15 AND 30 ORDER BY price DESC LIMIT 2", nativeQuery = true)
    List<Product> getTop2ProductsWithinPriceRange();

    List<Product> findByNameContaining(String name);

}