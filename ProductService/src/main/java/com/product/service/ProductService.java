package com.product.service;

import com.product.dto.ProductRequest;
import com.product.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

	ProductRequest createProduct(ProductRequest product);
//
   ProductRequest updateProduct(Long productId, ProductRequest product);


     void deleteProduct(Long productId);

     ProductRequest getById(Long productId);

     ProductRequest getByIdActiveProduct(Long id);

   // List<ProductRequest> getAllActiveProduct();

   List<ProductRequest> getAllProduct(Boolean isActive);
    List<ProductRequest> getAllProduct();
    List<ProductRequest> getAllProduct(int pageNumber,int pageSize);
    List<ProductRequest> getAllProduct(int pageNumber,int pageSize,boolean isActive);

    List<ProductRequest> getAllByProductCategoryType(String productCategoryType);
    List<ProductRequest> getAllProductsByCategoryTypeIsActive(String productCategoryType,boolean isActive);




}
