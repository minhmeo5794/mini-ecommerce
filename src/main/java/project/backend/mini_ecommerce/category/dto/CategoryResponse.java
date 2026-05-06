package project.backend.mini_ecommerce.category.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResponse {
    private final Long id;
    private final String name;
    private final String description;
}
