package com.example.ims.service;

import com.example.ims.constants.ProductStatus;

public interface ProductStatusService {
    ProductStatus determinProductStatus(Integer quantity);
}
