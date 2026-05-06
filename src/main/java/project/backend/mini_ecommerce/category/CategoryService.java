package project.backend.mini_ecommerce.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.mini_ecommerce.category.dto.CategoryResponse;
import project.backend.mini_ecommerce.category.dto.CreateCategoryRequest;
import project.backend.mini_ecommerce.common.exception.ResourceNotFoundException;
import project.backend.mini_ecommerce.common.exception.custom.BusinessException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(categoryMapper::toResponse).toList();
    }

    public CategoryResponse getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + categoryId));

        return categoryMapper.toResponse(category);
    }

    public CategoryResponse createCategory(CreateCategoryRequest request) {
        boolean doesCategoryExist = categoryRepository.existsByNameIgnoreCase(request.getName().trim());

        if (doesCategoryExist) {
            throw new BusinessException("Category name already existed");
        }

        Category category = categoryMapper.toEntity(request);
        Category savedCategory = categoryRepository.save(category);

        return categoryMapper.toResponse(savedCategory);
    }
}
