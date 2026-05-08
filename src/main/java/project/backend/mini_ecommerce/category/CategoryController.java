package project.backend.mini_ecommerce.category;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.mini_ecommerce.category.dto.CategoryResponse;
import project.backend.mini_ecommerce.category.dto.CreateCategoryRequest;
import project.backend.mini_ecommerce.category.dto.UpdateCategoryRequest;
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
            @PageableDefault(page = 0, size = 5, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return ResponseEntity.ok().body(
                ApiResponse.success(
                        categoryService.getAllCategories(pageable),
                        "Categories retrieved successfully",
                        HttpStatus.OK.value(),
                        httpServletRequest
                ));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getCategory(@PathVariable("categoryId") Long id, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok().body(
                ApiResponse.success(
                        categoryService.getCategory(id),
                        "Category retrieved successfully",
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

    @PatchMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponse>> updateCategory(@PathVariable("categoryId") Long id, @RequestBody @Valid UpdateCategoryRequest request, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok().body(
                ApiResponse.success(
                        categoryService.updateCategory(id, request),
                        "Category updated successfully",
                        HttpStatus.OK.value(),
                        httpServletRequest
                ));
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryResponse>> deleteCategory(@PathVariable("categoryId") Long id, HttpServletRequest httpServletRequest) {
        categoryService.deleteCategory(id);

        return ResponseEntity.ok().body(
                ApiResponse.success(
                        null,
                        "Category deleted successfully",
                        HttpStatus.OK.value(),
                        httpServletRequest
                ));
    }
}
