package com.bloominghealth.restapiproject.controller;

import com.bloominghealth.restapiproject.model.Product;
import com.bloominghealth.restapiproject.response.ResponseHandler;
import com.bloominghealth.restapiproject.service.ProductService;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController
{
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Read Specific Product Details from DB
    @GetMapping("/{productId}")
    @ApiOperation(value ="Product id", notes="Provide product details",
                    response = ResponseEntity.class)
    public ResponseEntity<Object> getCloudVendorDetails(@PathVariable("productId") String productId)
    {
       return ResponseHandler.responseBuilder("Requested Product Details are given here",
                HttpStatus.OK, productService.getProduct(productId));
    }

    // Read All Product Details from DB
    @GetMapping("/")
    public List<Product> getAllProductDetails()
    {
        return productService.getAllProducts();
    }

    @PostMapping("/")
    public String createProductDetails(@RequestBody Product product)
    {
    	productService.createProduct(product);
        return "Product Created Successfully";
    }

    @PutMapping("/")
    public String updateProductDetails(@RequestBody Product product)
    {
        productService.updateProduct(product);
        return "Product Updated Successfully";
    }

    @DeleteMapping("/{productId}")
    public String deleteProductDetails(@PathVariable("productId") String productId)
    {
        productService.deleteProduct(productId);
        return "Product Deleted Successfully";
    }
}
