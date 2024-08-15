package com.example.ims.service;

import com.example.ims.dao.ProductRepository;
import com.example.ims.dao.CompartimentRepository;
import com.example.ims.dao.CategoryRepository;

import com.example.ims.dto.ProductGetDTO;
import com.example.ims.dto.ProductPostDTO;
import com.example.ims.dto.ProductPutDTO;
import com.example.ims.model.Product;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CompartimentRepository compartimentRepository;
    private final CategoryRepository categoryRepository;
    private final ProductStatusService productStatusService;

    public ProductServiceImpl(ProductRepository productRepository,
                              CompartimentRepository compartimentRepository,
                              CategoryRepository categoryRepository,
    ProductStatusService productStatusService) {
        this.productRepository = productRepository;
        this.compartimentRepository = compartimentRepository;
        this.categoryRepository = categoryRepository;
        this.productStatusService = productStatusService;
    }

    @Override
    public List<ProductGetDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductGetDTO> productGetDTOS = new ArrayList<>();
        // map products to productGetDTOS
        for (Product product : products) {
            ProductGetDTO productGetDTO = new ProductGetDTO();
            productGetDTO.setId(product.getId());
            productGetDTO.setName(product.getName());
            productGetDTO.setCompartimentId(product.getCompartiment().getId());
            productGetDTO.setQuantity(product.getQuantity());
            productGetDTO.setStatus(product.getStatus());

            productGetDTO.setCategoryName(product.getCategory().getName());
            // handle null pointer exception
            try {
                productGetDTOS.add(productGetDTO);
            } catch (NullPointerException e) {
                productGetDTOS = List.of(productGetDTO);
            }
        }
        return productGetDTOS;
    }

    @Override
    public ProductPostDTO createProduct(@Valid ProductPostDTO productPostDto) {
        Product product = new Product();
        product.setName(productPostDto.getName());
        product.setQuantity(productPostDto.getQuantity());
        //product.setStatus(productPostDto.getStatus());
        product.setCompartiment(
                compartimentRepository.findById(productPostDto.getCompartmentId())
                        .orElseThrow(() -> new NoSuchElementException("Compartiment with id " + productPostDto.getCompartmentId() + " not found"))
        );
        product.setCategory(
                categoryRepository.findById(productPostDto.getCategoryId())
                        .orElseThrow(() -> new NoSuchElementException("Category with id " + productPostDto.getCategoryId() + " not found"))
        );

        // call productStatuService to determine the status of the product
        product.setStatus(productStatusService.determinProductStatus(productPostDto.getQuantity()));
        productRepository.save(product);
        return productPostDto;
    }

}
