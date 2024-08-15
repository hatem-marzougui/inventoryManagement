package com.example.ims.service;

import com.example.ims.constants.ProductStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductStatusServiceImpl implements ProductStatusService {
    @Override
    public ProductStatus determinProductStatus(Integer quantity) {
        // 0 -> OUT_OF_STOCK
        // between 1 and 10 -> LOW
        // between 10 and 20 -> MEDIUM
        // between 20 +  -> HIGH
        if(quantity < 0){
            return ProductStatus.OUT_OF_STOCK;
        }
        if (quantity == 0) {
            return ProductStatus.OUT_OF_STOCK;
        } else if (quantity >= 1 && quantity < 10) {
            return ProductStatus.LOW;
        } else if (quantity >= 10 && quantity < 20) {
            return ProductStatus.MEDIUM;
        } else {
            return ProductStatus.HIGH;
        }

    }
}
