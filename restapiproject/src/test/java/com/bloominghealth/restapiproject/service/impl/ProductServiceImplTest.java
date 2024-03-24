package com.bloominghealth.restapiproject.service.impl;

import com.bloominghealth.restapiproject.model.Product;
import com.bloominghealth.restapiproject.repository.ProductRepository;
import com.bloominghealth.restapiproject.service.ProductService;
import com.bloominghealth.restapiproject.service.impl.ProductServiceImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    private ProductService productService;
    AutoCloseable autoCloseable;
    Product product;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository);
        product = new Product("1", "Amazon",
                "USA", "xxxxx");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateProduct() {
        mock(Product.class);
        mock(ProductRepository.class);

        when(productRepository.save(product)).thenReturn(product);
        assertThat(productService.createProduct(product)).isEqualTo("Success");

    }

    @Test
    void testUpdateProduct() {
        mock(Product.class);
        mock(ProductRepository.class);

        when(productRepository.save(product)).thenReturn(product);
        assertThat(productService.updateProduct(product)).isEqualTo("Success");
    }

    @Test
    void testDeleteProduct() {
        mock(Product.class);
        mock(ProductRepository.class, Mockito.CALLS_REAL_METHODS);

        doAnswer(Answers.CALLS_REAL_METHODS).when(productRepository)
                .deleteById(any());
        assertThat(productService.deleteProduct("1")).isEqualTo("Success");
    }

    @Test
    void testGetProduct() {
        mock(Product.class);
        mock(ProductRepository.class);

        when(productRepository.findById("1")).thenReturn(Optional.ofNullable(product));

        assertThat(productService.getProduct("1").getProductVendorName())
                .isEqualTo(product.getProductVendorName());
    }

    @Test
    void testGetByProductVendorName() {
        mock(Product.class);
        mock(ProductRepository.class);

        when(productRepository.findByProductVendorName("Amazon")).
                thenReturn(new ArrayList<Product>(Collections.singleton(product)));

        assertThat(productService.getByProductVendorName("Amazon").get(0).getProductVendorId()).
                isEqualTo(product.getProductVendorId());
    }

    @Test
    void testGetAllProducts() {
        mock(Product.class);
        mock(ProductRepository.class);

        when(productRepository.findAll()).thenReturn(new ArrayList<Product>(
                Collections.singleton(product)
        ));

        assertThat(productService.getAllProducts().get(0).getProductVendorPhoneNumber()).
                isEqualTo(product.getProductVendorPhoneNumber());

    }


}