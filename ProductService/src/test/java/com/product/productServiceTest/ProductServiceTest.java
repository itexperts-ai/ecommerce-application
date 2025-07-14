package com.product.productServiceTest;

import com.product.dto.ProductRequest;
import com.product.entity.Product;
import com.product.exceptions.ResourceNotFoundException;
import com.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@SpringBootTest
@Configuration
public class ProductServiceTest {
     @Autowired
     ProductService productService;
     @BeforeEach
     public void init(){
         log.info("..................Testing is Starting......... .........!");

     }
     @AfterEach
     public void close(){
         log.info(".......................Testing successfully Completed....................!");
     }
    @Test
    public void createProductTest(){
        ProductRequest pr=new ProductRequest(703L,"Phohh","Latest Android smartphone with 8GB RAM", 25000, 50, 10, "Electronics",101,false);
        ProductRequest productRequest= this.productService.createProduct(pr);
        Assertions.assertThat(!pr.getProductName().equals("Phohh"));
        Assertions.assertThat(pr.getProductName()).isEqualTo("Phohh");
    }

    @Test
    public void findByIdProductTest(){
        ProductRequest product= this.productService.getById(302L);
        Assertions.assertThat(product.getProductCategoryId()).isNotNull();
        log.info("....................Product id is {}...................",product.getProductId());
          Assertions.assertThat(product.getProductId()==302);

    }
    @Test
    public void findByIdActiveProduct(){
         ProductRequest product=this.productService.getByIdActiveProduct(302L);
         Assertions.assertThat(!product.getProductName().isEmpty());
         log.info("======== Product id is {}===",product.getProductId());
         Assertions.assertThat(product.getProductId()==302);
    }
    @Test
    public void findAllActiveProduct(){
        List<ProductRequest> product=this.productService.getAllActiveProduct();
        Assertions.assertThat(product!=null);
        log.info("========== Products are {}",product);
    }


    @Test
    public void deleteProductTest(){
             this.productService.deleteProduct(102L);
             log.info("....................Deleted......................");

    }

    @Test
    public void updateProductTest(){
        ProductRequest pr=new ProductRequest(702L,"Phohh","Latest Android smartphone with 8GB RAM", 25000, 50, 10, "Electronics",101,true);
        ProductRequest productRequest= this.productService.updateProduct(pr.getProductId(),pr);
        Assertions.assertThat(pr.getProductCategoryId()==702);
        Assertions.assertThat(pr.getProductName()).isEqualTo("Phohh");
    }
}
