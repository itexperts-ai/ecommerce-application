package com.product.controller;
import java.util.List;

import com.product.entity.Product;
import com.product.entity.UserLoginRequest;
import com.product.service.UserLoginRequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.product.dto.ProductRequest;
import com.product.helper.ProductApiPath;
import com.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(ProductApiPath.V1)
@Slf4j
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private UserLoginRequestService userLoginRequestService;

	@PostMapping(ProductApiPath.CREATE)
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductRequest product) {
		log.debug("Received request to create product  ! ");

		if (product == null) {
			log.warn("Product request body is null");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Product request body is missing");
		}

		ProductRequest savedProduct = productService.createProduct(product);
		if (savedProduct == null) {
			log.error("Failed to save product");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Server Failed");
		}

		log.info("Product created successfully: ");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(savedProduct);
	}

	@GetMapping(ProductApiPath.PRODUCT_BY_ID)
	public ResponseEntity<?> getByProductId(@PathVariable Long productId) {
		log.debug("Fetching product by ID: {}", productId);

		if (productId == null) {
			log.warn("Product ID is null");
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("productId can not be null : " + productId);
		}

		ProductRequest product = productService.getById(productId);
		if (product == null) {
			log.warn("Product with ID {} not found", productId);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sorry your product is inActive!");
		}

		log.info("Product found: {}", product);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(product);
	}

	@PutMapping(ProductApiPath.UPDATE_PRODUCT)
	public ResponseEntity<?> updateProduct(@PathVariable Long productId,@RequestBody ProductRequest product) {
		log.debug("Updating product ID {}: {}", productId, product);

		if (product == null || productId == null) {
			log.warn("Update request missing product data or ID");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product Request Body is Missing");
		}

		ProductRequest updatedProduct = productService.updateProduct(productId, product);
//		if (updatedProduct == null) {
//			log.error("Failed to update product with ID {}", productId);
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
//		}

		log.info("Product updated successfully: {}", updatedProduct);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(updatedProduct);
	}

	@GetMapping(ProductApiPath.PRODUCT_BY_CATEGORY)
	public ResponseEntity<?> getByProductCategoryType(@PathVariable String productCategoryType) {
		log.debug("Fetching products by category: {}", productCategoryType);

		if (productCategoryType == null) {
			log.warn("Category type is null");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product Category request is bad");
		}

		List<ProductRequest> products = productService.getAllByProductCategoryType(productCategoryType);
		if (products.isEmpty()) {
			log.warn("No products found for category: {}", productCategoryType);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Your Products are not available");
		}

		log.info("Products found for category {}: {}", productCategoryType, products.size());
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(products);
	}
	@GetMapping(ProductApiPath.PRODUCT_BY_CATEGORY_AND_STATUS)
	public ResponseEntity<?> getProductCategryTypeAndStatus(@PathVariable String productCategoryType,@RequestParam boolean isActive){
		log.debug("Fetching products by category: {}", productCategoryType);
		   if(productCategoryType==null){
			   return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Your Products are not available");
		   }
		   List<ProductRequest> product=productService.getAllProductsByCategoryTypeIsActive(productCategoryType,isActive);
		   return ResponseEntity.status(HttpStatus.FOUND).body(product);
	}
	@GetMapping(ProductApiPath.GET_ALL_PRODUCTS_PAGINATED_STATUS)
	public ResponseEntity<?> getAllProduct(
			@RequestParam(defaultValue = "5",required = false) Integer pageSize,
			@RequestParam(defaultValue = "1",required = false) Integer pageNumber,
			@RequestParam Boolean isActive
	       ){
		log.info("==========This is Controller Layer========= ");
		log.debug("Page number is {} and Pagesize is {} and Product status is {}",pageNumber,pageSize,isActive);
	    List<ProductRequest> product=productService.getAllProduct(pageNumber,pageSize,isActive);
		log.debug("====Your products are Retrived {}==== " ,product);
		return ResponseEntity.status(HttpStatus.FOUND).body(product);
	}
	@GetMapping(ProductApiPath.GET_ALL_PRODUCTS_PAGINATED)
	public ResponseEntity<?> getAllProduct(
			@RequestParam(defaultValue = "5",required = false) Integer pageSize,
			@RequestParam(defaultValue = "1",required = false) Integer pageNumber
	){
		log.info("========This is Controller Layer========= ");
		log.debug("Page number is {} and Pagesize is {}",pageNumber,pageSize);
		List<ProductRequest> product=productService.getAllProduct(pageNumber,pageSize);
		log.debug("Your products are Retrived {}",product);
		return ResponseEntity.status(HttpStatus.FOUND).body(product);
	}

	@GetMapping(ProductApiPath.GET_ALL_PRODUCTS)
	public ResponseEntity<?> getAllProduct(){
		log.debug("product receiving");
		List<ProductRequest>product= productService.getAllProduct();
		log.info("Product is {}",product);
		return ResponseEntity.status(HttpStatus.FOUND).body(product);
	}


	@GetMapping(ProductApiPath.GET_ALL_PRODUCT)
		public ResponseEntity<?> getALLProduct(@RequestParam Boolean isActive){
			log.info("Retriving Active Product");

		List<ProductRequest> product=	  productService.getAllProduct(isActive);
        return ResponseEntity.status(HttpStatus.FOUND).body(product);
	}

    @DeleteMapping(ProductApiPath.PRODUCT_BY_ID)
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
		log.debug("Deleting product with ID: {}", productId);

		if (productId == null) {
			log.warn("Product ID for deletion is null");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product Id cannot be null");
		}

		productService.deleteProduct(productId);

		log.info("Product deleted successfully with ID: {}", productId);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Product Deleted");
	}

		// this is for  User microservice
		@PostMapping("/login")
		public ResponseEntity<?>  loginUser(@RequestBody UserLoginRequest request) {
			try {
				UserLoginRequest ans = userLoginRequestService.getByUserNameAndPassword(request.getEmail(), request.getPassword());

				return ResponseEntity.status(HttpStatus.ACCEPTED).body(ans);
			} catch (Exception e) {
				log.error("Your id and Password are wrong{}{}", request.getEmail(), request.getPassword());
				return ResponseEntity.status(HttpStatus.ACCEPTED).body("Something went wrong !.....");
			}

		}

}
