package com.product.service;

import com.product.dto.ProductRequest;
import com.product.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

	ProductRequest createProduct(ProductRequest product);
//
   ProductRequest updateProduct(Long productId, ProductRequest product);
//
     void deleteProduct(Long productId);
     ProductRequest getById(Long productId);
     ProductRequest getByIdActiveProduct(Long id);
     List<ProductRequest> getAllProduct();
     List<ProductRequest> getAllActiveProduct();

//    List<ProductRequest> getByProductCategoryId(int productCategoryId);
//
    List<ProductRequest> getByProductCategoryType(String productCategoryType);
//    
  // long sumProductQuantityByCategory(long productQuantity,String productCategory);

}
