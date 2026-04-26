package project.backend.mini_ecommerce.dto.response;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import project.backend.mini_ecommerce.enums.Status;
import project.backend.mini_ecommerce.model.Category;

import java.math.BigDecimal;

@Builder
@Getter
public class ProductResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Integer stockQuantity;
    private final Status status;
    private final CategoryInfo categoryInfo;

    @RequiredArgsConstructor
    @Getter
    @Builder
    public static class CategoryInfo {
        private final Long id;
        private final String name;
        private final String description;
    }

}
