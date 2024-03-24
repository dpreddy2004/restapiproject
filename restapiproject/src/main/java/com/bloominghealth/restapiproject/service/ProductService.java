package com.bloominghealth.restapiproject.service;

import java.util.List;

import com.bloominghealth.restapiproject.model.Product;

public interface ProductService {
    public String createProduct(Product product);
    public String updateProduct(Product product);
    public String deleteProduct(String productId);
    public Product getProduct(String productId);
    public List<Product> getAllProducts();
    public List<Product> getByProductVendorName(String productVendorName);
}
