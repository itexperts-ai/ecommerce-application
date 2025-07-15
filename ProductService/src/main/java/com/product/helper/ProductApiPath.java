package com.product.helper;

public interface ProductApiPath {
	  String API="/product";
	  String V1=API+"/v1";
	  
	  String  CREATE = "/create-product";
	  String  PRODUCT_BY_ID="/{productId}";
	String PRODUCT_BY_CATEGORY = "/category/{productCategoryType}";
	String PRODUCT_BY_CATEGORY_AND_STATUS = "/category_status/{productCategoryType}";

	String UPDATE_PRODUCT="/update-product/{productId}";
	  String GET_ALL_PRODUCT="/getAllProduct";
	  String GET_ALL_PRODUCTS="/getAllProducts";
	  String GET_ALL_PRODUCTS_PAGINATED = "/pagable_products";
	String GET_ALL_PRODUCTS_PAGINATED_STATUS = "/pagable_products_status";

}
