package com.example.orderservice.Service;

import com.example.orderservice.entity.Product;

import java.util.List;

public interface ProductService {
    Product CreateProduct(Product product);
    Product getProductById(Long id);
    List<Product> getAllProduct();
    List<Product> getAllProducts();
}
