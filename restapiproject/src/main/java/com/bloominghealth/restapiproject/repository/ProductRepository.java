package com.bloominghealth.restapiproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bloominghealth.restapiproject.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByProductVendorName(String vendorName);
}
