package com.example.ims.controller;

import com.example.ims.dto.ProductGetDTO;
import com.example.ims.dto.ProductPostDTO;
import com.example.ims.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    @ResponseBody
    public ResponseEntity<Object> getAllProducts() {
        try {
            List<ProductGetDTO> products = productService.getAllProducts();
            if (products.isEmpty()) {
                return new ResponseEntity<>(products, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error while getting products");
        }
    }
    @PostMapping("/product")
    @ResponseBody
    public ResponseEntity<Object> createProduct(@Valid @RequestBody ProductPostDTO productPostDto) {
        try {
            ProductPostDTO createdProduct = productService.createProduct(productPostDto);
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error while creating product");
        }
    }

}
