package project.backend.mini_ecommerce.category;

import org.springframework.stereotype.Component;
import project.backend.mini_ecommerce.category.dto.CategoryResponse;
import project.backend.mini_ecommerce.category.dto.CreateCategoryRequest;

@Component
public class CategoryMapper {
    public CategoryResponse toResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    public Category toEntity(CreateCategoryRequest request) {
        return Category.builder()
                .name(request.getName().trim())
                .description(request.getDescription().trim())
                .build();
    }
}
