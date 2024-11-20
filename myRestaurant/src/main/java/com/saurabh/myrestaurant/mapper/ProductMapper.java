package com.saurabh.myrestaurant.mapper;

import com.saurabh.myrestaurant.dto.ProductRequest;
import com.saurabh.myrestaurant.entity.Product;
import org.springframework.stereotype.Service;



@Service
public class ProductMapper {
    //product dto to entity (for adding products)
    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .build();
    }
}

