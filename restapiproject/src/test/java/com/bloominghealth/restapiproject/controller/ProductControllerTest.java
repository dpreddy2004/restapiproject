package com.bloominghealth.restapiproject.controller;

import com.bloominghealth.restapiproject.controller.ProductController;
import com.bloominghealth.restapiproject.model.Product;
import com.bloominghealth.restapiproject.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    Product productOne;
    Product productTwo;
    List<Product> productList= new ArrayList<>();

    @BeforeEach
    void setUp() {
        productOne = new Product("1", "Amazon",
                "USA", "xxxxx");
        productTwo = new Product("2", "GCP",
                "UK", "yyyyy");
        productList.add(productOne);
        productList.add(productTwo);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getProductDetails() throws Exception {
        when(productService.getProduct("1")).thenReturn(productOne);
        this.mockMvc.perform(get("/product/" + "1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void getAllProductDetails() throws  Exception {
        when(productService.getAllProducts()).thenReturn(productList);
        this.mockMvc.perform(get("/product"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void createProductDetails() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(productOne);

        when(productService.createProduct(productOne)).thenReturn("Success");
        this.mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void updateProductDetails() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(productOne);

        when(productService.updateProduct(productOne))
                .thenReturn("Product Updated Successfully");
        this.mockMvc.perform(put("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void deleteProductDetails() throws Exception {
        when(productService.deleteProduct("1"))
                .thenReturn("Product Deleted Successfully");
        this.mockMvc.perform(delete("/product/" + "1"))
                .andDo(print()).andExpect(status().isOk());

    }
}
