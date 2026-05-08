package project.backend.mini_ecommerce.product.dto;

import lombok.Builder;
import lombok.Getter;
import project.backend.mini_ecommerce.common.enums.ProductStatus;

import java.math.BigDecimal;

@Builder
@Getter
public class ProductResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Integer stockQuantity;
    private final ProductStatus productStatus;
    private final CategoryInfo category;

    @Getter
    @Builder
    public static class CategoryInfo {
        private final Long id;
        private final String name;
        private final String description;
    }
}
