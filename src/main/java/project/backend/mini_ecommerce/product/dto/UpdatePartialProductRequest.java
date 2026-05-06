package project.backend.mini_ecommerce.product.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import project.backend.mini_ecommerce.common.enums.ProductStatus;

import java.math.BigDecimal;

@Getter
@RequiredArgsConstructor
public class UpdatePartialProductRequest {
    @Size(max = 100, message = "Product name must not exceed 100 characters")
    private final String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private final String description;

    @DecimalMin(value = "0.01", message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Price must have at most 10 integer digits and 2 decimal places")
    private final BigDecimal price;

    @Min(value = 0, message = "Stock quantity must be greater than or equal to 0")
    private final Integer stockQuantity;

    private final ProductStatus status;

    private final Long categoryId;
}
