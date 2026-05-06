package project.backend.mini_ecommerce.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.mini_ecommerce.category.dto.CategoryResponse;

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
}
