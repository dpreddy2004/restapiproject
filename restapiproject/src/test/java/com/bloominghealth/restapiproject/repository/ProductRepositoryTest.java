package com.bloominghealth.restapiproject.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bloominghealth.restapiproject.model.Product;
import com.bloominghealth.restapiproject.repository.ProductRepository;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

  //  given - when - then

    @Autowired
    private ProductRepository productRepository;
    Product product;

    @BeforeEach
    void setUp() {
        product = new Product("1","Amazon",
                "USA", "xxxxx");
        productRepository.save(product);
    }

    @AfterEach
    void tearDown() {
        product = null;
        productRepository.deleteAll();
    }

    // Test case SUCCESS

    @Test
    void testFindByVendorName_Found()
    {
        List<Product> productList = productRepository.findByProductVendorName("Amazon");
        assertThat(productList.get(0).getProductVendorId()).isEqualTo(product.getProductVendorId());
        assertThat(productList.get(0).getProductVendorAddress())
                .isEqualTo(product.getProductVendorAddress());
    }

    // Test case FAILURE
    @Test
    void testFindByVendorName_NotFound()
    {
        List<Product> productList = productRepository.findByProductVendorName("GCP");
        assertThat(productList.isEmpty()).isTrue();
    }
}
