package project.backend.mini_ecommerce.category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.backend.mini_ecommerce.category.dto.CategoryResponse;
import project.backend.mini_ecommerce.category.dto.CreateCategoryRequest;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/admin/categories")
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryId}")
    public CategoryResponse getCategory(@PathVariable("categoryId") Long id) {
        return categoryService.getCategory(id);
    }

    @PostMapping
    public CategoryResponse createCategory(@RequestBody @Valid CreateCategoryRequest request) {
        return categoryService.createCategory(request);
    }
}
