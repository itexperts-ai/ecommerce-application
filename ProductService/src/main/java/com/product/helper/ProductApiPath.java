package com.product.helper;

public interface ProductApiPath {
	  String API="/product";
	  String V1=API+"/v1";
	  
	  String  CREATE = "/create-product";
	  String  PRODUCT_BY_ID="/{productId}";
	  String PRODUCT_BY_CATEGORY="/category/{productCategoryType}";
	  String UPDATE_PRODUCT="/update-product/{productId}";
	  String GET_ALL_ACTIVE_PRODUCT="/getAllActive";
}
