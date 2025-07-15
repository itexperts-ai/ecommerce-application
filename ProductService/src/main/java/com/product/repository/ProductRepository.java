package com.product.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.product.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	//Product findById(Long id);
	List<Product> findByProductCategoryId(int productCategoryId);
	List<Product> findByProductCategoryType(String productCategoryId);
	List<Product> findByProductCategoryTypeAndIsActive(String productCategoryId,Boolean isActive);

	List<Product> findByIsActive(boolean isActive);
	Page<Product> findByIsActive(boolean isActive, Pageable pageable);

	@Query(value = "SELECT SUM(productQuantity) FROM product WHERE productCategoryType = :productCategoryType", nativeQuery = true)
	long sumProductQuantityByCategoryType(@Param("productCategoryType") String productCategoryType);

}
