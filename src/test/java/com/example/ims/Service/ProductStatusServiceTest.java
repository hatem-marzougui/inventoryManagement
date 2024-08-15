package com.example.ims.Service;

import com.example.ims.constants.ProductStatus;
import com.example.ims.service.ProductStatusService;
import com.example.ims.service.ProductStatusServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class ProductStatusServiceTest {

    private ProductStatusService productStatusService;
    @BeforeEach
    public void setUp() {
        productStatusService = new ProductStatusServiceImpl();
    }

    @Test
    void testOutOfStock() {
        ProductStatus status = productStatusService.determinProductStatus(0);
        assertEquals(ProductStatus.OUT_OF_STOCK, status);

        status = productStatusService.determinProductStatus(-1);
        assertEquals(ProductStatus.OUT_OF_STOCK, status);
    }
    @Test
    void testLowStock() {
        ProductStatus status = productStatusService.determinProductStatus(1);
        assertEquals(ProductStatus.LOW, status);

        status = productStatusService.determinProductStatus(5);
        assertEquals(ProductStatus.LOW, status);

        status = productStatusService.determinProductStatus(9);
        assertEquals(ProductStatus.LOW, status);
    }

    @Test
    void testMediumStock() {
        ProductStatus status = productStatusService.determinProductStatus(10);
        assertEquals(ProductStatus.MEDIUM, status);

        status = productStatusService.determinProductStatus(15);
        assertEquals(ProductStatus.MEDIUM, status);

        status = productStatusService.determinProductStatus(19);
        assertEquals(ProductStatus.MEDIUM, status);
    }

    @Test
    void testHighStock() {
        ProductStatus status = productStatusService.determinProductStatus(20);
        assertEquals(ProductStatus.HIGH, status);

        status = productStatusService.determinProductStatus(100);
        assertEquals(ProductStatus.HIGH, status);
    }
}
