package com.bloominghealth.restapiproject.service.impl;

import com.bloominghealth.restapiproject.exception.ProductNotFoundException;
import com.bloominghealth.restapiproject.model.Product;
import com.bloominghealth.restapiproject.repository.ProductRepository;
import com.bloominghealth.restapiproject.service.ProductService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String createProduct(Product product) {
        // More Business Logic
        productRepository.save(product);
        return "Success";
    }

    @Override
    public String updateProduct(Product product) {
        // More Business Logic
        productRepository.save(product);
        return "Success";
    }

    @Override
    public String deleteProduct(String productId) {
        // More Business Logic
        productRepository.deleteById(productId);
        return "Success";
    }

    @Override
    public Product getProduct(String productId) {
        // More Business Logic
        if(productRepository.findById(productId).isEmpty())
            throw new ProductNotFoundException("Requested Product does not exist");
        return productRepository.findById(productId).get();
    }

    @Override
    public List<Product> getAllProducts() {
        // More Business Logic
        return productRepository.findAll();
    }

    @Override
    public List<Product> getByProductVendorName(String productVendorName)
    {
        return productRepository.findByProductVendorName(productVendorName);
    }
}
