package project.backend.mini_ecommerce.category;

import org.springframework.stereotype.Component;
import project.backend.mini_ecommerce.category.dto.CategoryResponse;

@Component
public class CategoryMapper {
    public CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
