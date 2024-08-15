package com.example.ims.service;

import com.example.ims.dto.ProductGetDTO;
import com.example.ims.dto.ProductPostDTO;
import com.example.ims.dto.ProductPutDTO;
import com.example.ims.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductGetDTO> getAllProducts();
    ProductPostDTO createProduct(ProductPostDTO productPostDto);
    //Product updateProductById(Integer id, ProductPutDTO productPutDto);
    //Product deleteProductById(Integer id);
}
