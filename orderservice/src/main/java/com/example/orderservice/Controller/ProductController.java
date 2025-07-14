package com.example.orderservice.Controller;


import com.example.orderservice.Service.ProductService;
import com.example.orderservice.entity.Product;
import com.example.orderservice.util.ApiProductPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(ApiProductPath.v1)
@CrossOrigin(origins = "*")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(ApiProductPath.CREATE_PRODUCT)
    public Product createProduct(@RequestBody Product product){
        log.debug("Request Received for Order !!");
        return  productService.CreateProduct(product);
    }

    @GetMapping("/product/{id}")
    public  Product getProductById(@PathVariable Long id){
        return productService.getProductById(id);
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }


}
