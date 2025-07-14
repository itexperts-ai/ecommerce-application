package com.product.helper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.product.dto.ProductRequest;
import com.product.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductToDtoAndDtoToProduct {
	
	@Autowired
	ModelMapper modelMapper;
	
	public Product productRequestToProduct(ProductRequest productRequest) {
		
		     return this.modelMapper.map(productRequest, Product.class);
	}

	public ProductRequest productToProductRequest(Product product) {
		
		return this.modelMapper.map(product, ProductRequest.class);
	}

	public List
			<ProductRequest> productToProductRequest(List<Product> products) {
		return products.stream()
				.map(this::productToProductRequest)
				.collect(Collectors.toList());
	}


}
