package com.product.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="product_details")
public class Product {
	  @Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private Long productId;
	  @NotBlank(message = "Product Name is required !!")
	  @Size(min = 2,max = 20,message = "Min 2 and Max 20 Characters are allowed !!")
	  private String productName;

	  @NotBlank(message="Product description is required")
	  @Size(min = 10, max = 100,message = "Minimum give me atleast 10 character !!")
	  private String productDescription;

	  @Min(value=1,message = "Product price must be greater than 0 !")
	  @Max(value=1000000,message = "product price cannot exceed 10,00,000. ")
	  private int productPrice;


	//   inventory
	    @Min(value=0,message = "Product quantity cannot be negative ")
	    private long productQuantity;
	    @Min(value = 10,message="Minimum stock level should be 10")
	    private long minStockProduct=10;


	  //category
	  @NotBlank(message = "Product category type is required.")
	  @Size(min = 3, max = 30, message = "Category type must be between 3 and 30 characters.")
	  private String productCategoryType;

	  @Min(value = 1, message = "Product category ID must be greater than 0.")
	  private int productCategoryId;

	@NotNull(message = "Active status must not be null")
	private boolean isActive;

	public Product() {
		super();

	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public @NotBlank(message = "Product Name is required !!") @Size(min = 2, max = 20, message = "Min 2 and Max 20 Characters are allowed !!") String getProductName() {
		return productName;
	}

	public void setProductName(@NotBlank(message = "Product Name is required !!") @Size(min = 2, max = 20, message = "Min 2 and Max 20 Characters are allowed !!") String productName) {
		this.productName = productName;
	}

	public @NotBlank(message = "Product description is required") @Size(min = 10, max = 100, message = "Minimum give me atleast 10 character !!") String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(@NotBlank(message = "Product description is required") @Size(min = 10, max = 100, message = "Minimum give me atleast 10 character !!") String productDescription) {
		this.productDescription = productDescription;
	}

	public @Min(value = 1, message = "Product price must be greater than 0 !") @Max(value = 1000000, message = "product price cannot exceed 10,00,000. ") int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(@Min(value = 1, message = "Product price must be greater than 0 !") @Max(value = 1000000, message = "product price cannot exceed 10,00,000. ") int productPrice) {
		this.productPrice = productPrice;
	}

	public @Min(value = 0, message = "Product quantity cannot be negative ") long getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(@Min(value = 0, message = "Product quantity cannot be negative ") long productQuantity) {
		this.productQuantity = productQuantity;
	}

	public @Min(value = 10, message = "Minimum stock level should be 10") long getMinStockProduct() {
		return minStockProduct;
	}

	public void setMinStockProduct(@Min(value = 10, message = "Minimum stock level should be 10") long minStockProduct) {
		this.minStockProduct = minStockProduct;
	}

	public @NotBlank(message = "Product category type is required.") @Size(min = 3, max = 30, message = "Category type must be between 3 and 30 characters.") String getProductCategoryType() {
		return productCategoryType;
	}

	public void setProductCategoryType(@NotBlank(message = "Product category type is required.") @Size(min = 3, max = 30, message = "Category type must be between 3 and 30 characters.") String productCategoryType) {
		this.productCategoryType = productCategoryType;
	}

	public @Min(value = 1, message = "Product category ID must be greater than 0.") int getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(@Min(value = 1, message = "Product category ID must be greater than 0.") int productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public @NotBlank(message = "Product category type required") @Size(min = 3, max = 20) boolean isActive() {
		return isActive;
	}

	public void setActive(@NotBlank(message = "Product category type required") @Size(min = 3, max = 20) boolean active) {
		isActive = active;
	}

	@Override
	public String toString() {
		return "Product{" +
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



	 

	


