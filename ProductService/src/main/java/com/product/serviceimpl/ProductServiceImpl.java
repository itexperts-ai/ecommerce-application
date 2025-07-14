package com.product.serviceimpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.product.entity.UserLoginRequest;
import com.product.exceptions.ResourceNotFoundException;
import com.product.service.UserLoginRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.product.dto.ProductRequest;
import com.product.entity.Product;
import com.product.helper.ProductToDtoAndDtoToProduct;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import static java.util.Arrays.stream;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService , UserLoginRequestService {

     @Autowired
     ProductRepository productRepository;
     @Autowired
 	ProductToDtoAndDtoToProduct productToDtoAndDtoToProduct;
	 @Autowired
	RestTemplate restTemplate;
	@Override
	@Transactional
	public ProductRequest createProduct(ProductRequest productRequest) {
		log.debug("Service Method Started ! ");
		log.info(productRequest.toString());

//    	 ProductToDtoAndDtoToProduct productToDtoAndDtoToProduct=new ProductToDtoAndDtoToProduct();
		if (productRequest.getMinStockProduct() == 0 ||
			    productRequest.getProductCategoryId() == 0 ||
			    (productRequest.getProductCategoryType() == null || productRequest.getProductCategoryType().isEmpty()) ||
			    (productRequest.getProductDescription() == null || productRequest.getProductDescription().isEmpty()) ||
			    (productRequest.getProductName() == null || productRequest.getProductName().isEmpty()) ||
			    productRequest.getProductPrice() == 0 ||
			    productRequest.getProductQuantity() == 0) {

			log.warn("Product validation failed due to missing or invalid fields.");
			    return null;
			}
		
		else {
		  Product answer=      productToDtoAndDtoToProduct.productRequestToProduct(productRequest);
			log.info("Product validation passed !");
			log.info(answer.toString());
		  Product  product=  productRepository.save(answer);
			log.info("Product saved successfully !");
		 ProductRequest  pr= productToDtoAndDtoToProduct.productToProductRequest(product);
			log.info("Returning final Product object !");
		   return pr;
	      }
	}
	@Override
	public ProductRequest updateProduct(Long productId, ProductRequest product) {

		if (
			    product.getProductCategoryType() == null || product.getProductCategoryType().isEmpty()||
			    product.getProductDescription() == null || product.getProductDescription().isEmpty()||
			    product.getProductName() == null ||
			    product.getProductPrice() == 0 ||
			    product.getProductQuantity() == 0 ||
			    product.getMinStockProduct() == 0 ||
			    product.getProductCategoryId() == 0
				) {
			log.warn("============Product update failed: One or more required fields are empty or invalid. Product: {}=====", product);
			return null;

			}


		log.info("==========Fetching product with ID: {}==========", productId);
	    Product pp = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product not Found with ","productId :",productId));

		Product ppp=this.productToDtoAndDtoToProduct.productRequestToProduct(product);

	    if (ppp!=null) {
			log.info("=============Updating product fields for ID: {}=======", productId);

			pp.setProductName(ppp.getProductName());
	    pp.setProductDescription(ppp.getProductDescription());
	    pp.setProductPrice(ppp.getProductPrice());
	    pp.setProductQuantity(ppp.getProductQuantity());
	    pp.setMinStockProduct(ppp.getMinStockProduct());
	    pp.setProductCategoryType(ppp.getProductCategoryType());
	    pp.setProductCategoryId(ppp.getProductCategoryId());
		pp.setActive(ppp.isActive());

			Product answer=productRepository.save(pp);
			log.info("============Product with ID {} updated successfully==========", answer.getProductId());

	    return  this.productToDtoAndDtoToProduct.productToProductRequest(answer);
	    }
	    return null;
	}


//	@Override
//	public List<Product> getByProductCategoryId(int productCategoryId) {
//		
//		List<Product> pp=productRepository.findByProductCategoryId(productCategoryId);
//		if (pp.isEmpty()) {
//			   return null;
//		}
//		return pp;
//	}
//		
	@Override
	public ProductRequest getById(Long productId) {
		log.info("=========Fetching product with ID: {}===========", productId);

		Product product = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product","productId",productId));

		log.info("==========Product found: {}======", product);

			  return this.productToDtoAndDtoToProduct.productToProductRequest(product);

}
@Override
public ProductRequest getByIdActiveProduct(Long id){
	log.info("product is in service layar {}",id);
	Product product=productRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product","ProductId",id));
	log.info("===========Product found {}===========",product.getProductName());
    return productToDtoAndDtoToProduct.productToProductRequest(product);
}


	@Override
	public List<ProductRequest> getAllProduct() {
		log.info("======== Getting All product in Service Layer ============");
		List<Product> product= productRepository.findAll();
		log.info("============= Your product is {}========",product);
		return productToDtoAndDtoToProduct.productToProductRequest(product);
	}

	@Override
	public List<ProductRequest> getAllActiveProduct() {
		log.info("========= Getting All Active Product in service layer ==========");
	//List<Product> product=productRepository.findByIsActive(true);
		List<Product> product=productRepository.findAll();
	List<Product> list=	product.stream().filter((e)-> e.isActive()==true).collect(Collectors.toList());
	log.info("==========found  the active product in service layer {} ",list);
	return productToDtoAndDtoToProduct.productToProductRequest(list);
	}

	@Override
	@Transactional
	public void  deleteProduct(Long productId) {
		log.info("=======Attempting to delete product with ID: {}====", productId);


		Product product  = productRepository.findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product Not Found with ","productId",productId));


	        productRepository.deleteById(productId);
		log.info("=============Product with ID: {} has been successfully deleted.=============", productId);

	}

	@Override
	public List<ProductRequest> getByProductCategoryType(String productCategoryType) {
		log.info("=========Fetching products with category type: {}=========", productCategoryType);

		List<Product> pp = productRepository.findByProductCategoryType(productCategoryType);
		      if (pp.isEmpty() ){
				    return null;
			  }
		log.info("==============Found  products for category type: {}============", productCategoryType);

		return   this.productToDtoAndDtoToProduct.productToProductRequest(pp);
	}
    // Calling user API
	@Override
	public UserLoginRequest getByUserNameAndPassword(String email, String password) {
		final String serviceBUrl = "http://192.168.1.9:8080/api/v1/user/login";
		//StringBuilder sb=new StringBuilder();
		log.info("======Attempting login for email: {}======", email);

		Map<String, String> request = new HashMap<>();
		request.put("email", email);
		request.put("password", password);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Map<String, String>> entity = new HttpEntity<>(request, headers);

		ResponseEntity<UserLoginRequest> response = restTemplate.postForEntity(
				serviceBUrl,
				entity,
				UserLoginRequest.class
		);
		log.info("==========Login response received for email: {} with status: {}==========", email, response.getStatusCode());

		return response.getBody();

	}

//    @Override  
//	public long sumProductQuantityByCategory(long productQuantity, String productCategory) {
//		long answer=0;
//		     if (productQuantity<0 || productCategory==null ) {
//				return answer-1;
//			}
//		     answer=productRepository.sumProductQuantityByCategoryType(productQuantity,productCategory);
//		     if (answer>0) {
//				return answer;
//			}
//		
//		return answer;
//	}


	 
}
