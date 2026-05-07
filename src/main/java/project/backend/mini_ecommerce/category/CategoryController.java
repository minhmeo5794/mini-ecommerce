package project.backend.mini_ecommerce.category;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.mini_ecommerce.category.dto.CategoryResponse;
import project.backend.mini_ecommerce.category.dto.CreateCategoryRequest;
import project.backend.mini_ecommerce.common.response.ApiResponse;
import project.backend.mini_ecommerce.common.response.PageResponse;

@RequiredArgsConstructor
@RequestMapping("/api/admin/categories")
@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<CategoryResponse>>> getAllCategories(
            HttpServletRequest httpServletRequest,
            @PageableDefault(page = 0, size = 5) Pageable pageable
    ) {
        return ResponseEntity.ok().body(
                ApiResponse.success(
                        categoryService.getAllCategories(pageable),
                        "Get all categories successfully",
                        HttpStatus.OK.value(),
                        httpServletRequest
                ));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategory(@PathVariable("categoryId") Long id, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok().body(
                ApiResponse.success(
                        categoryService.getCategory(id),
                        "Get category successfully",
                        HttpStatus.OK.value(),
                        httpServletRequest
                ));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> createCategory(@RequestBody @Valid CreateCategoryRequest request, HttpServletRequest httpServletRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.success(
                        categoryService.createCategory(request),
                        "Category created successfully",
                        HttpStatus.CREATED.value(),
                        httpServletRequest
                ));
    }
}
