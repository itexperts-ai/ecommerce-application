package com.product.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ProductRequest implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Min(value=0,message ="Id can not be less than 0")
	private Long productId;

	//@NotBlank(message = "Product Name is required !!")
//	@Size(max = 5,message = "Min 2 and Max 20 Characters are allowed !!")
	private String productName;

//	@NotBlank(message="Product description is required")
//	@Size(min = 10, max = 100,message = "Minimum give me atleast 10 character !!")
	private String productDescription;

//	@Min(value=1,message = "Product price must be greater than 0 !")
//	@Max(value=1000000,message = "product price cannot exceed 10,00,000. ")
	private int productPrice;


	//   inventory
//	@Min(value=0,message = "Product quantity cannot be negative ")
	private long productQuantity;
//	@Min(value = 10,message="Minimum stock level should be 10")
	private long minStockProduct=10;


	//category
//	@NotBlank(message = "Product category type is required.")
//	@Size(min = 3, max = 30, message = "Category type must be between 3 and 30 characters.")
	private String productCategoryType;

//	@Min(value = 1, message = "Product category ID must be greater than 0.")
	private int productCategoryId;
//	@NotNull(message = "Active status must not be null")
	private boolean isActive;
	public ProductRequest() {
		super();

	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public long getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(long productQuantity) {
		this.productQuantity = productQuantity;
	}

	public long getMinStockProduct() {
		return minStockProduct;
	}

	public void setMinStockProduct(long minStockProduct) {
		this.minStockProduct = minStockProduct;
	}

	public String getProductCategoryType() {
		return productCategoryType;
	}

	public void setProductCategoryType(String productCategoryType) {
		this.productCategoryType = productCategoryType;
	}

	public int getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	@Override
	public String toString() {
		return "ProductRequest{" +
				"productId=" + productId +
				", productName='" + productName + '\'' +
				", productDescription='" + productDescription + '\'' +
				", productPrice=" + productPrice +
				", productQuantity=" + productQuantity +
				", minStockProduct=" + minStockProduct +
				", productCategoryType='" + productCategoryType + '\'' +
				", productCategoryId=" + productCategoryId +
				", isActive=" + isActive +
				'}';
	}
}
