package project.backend.mini_ecommerce.category;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.backend.mini_ecommerce.category.dto.CategoryResponse;

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
}
