package project.backend.mini_ecommerce.category.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateCategoryRequest {
    @Size(max = 100, message = "Category name must not exceed 100 characters")
    private final String name;

    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private final String description;
}
