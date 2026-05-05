package project.backend.mini_ecommerce.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.backend.mini_ecommerce.category.Category;
import project.backend.mini_ecommerce.common.enums.ProductStatus;
import project.backend.mini_ecommerce.product.dto.CreateProductRequest;
import project.backend.mini_ecommerce.product.dto.ProductResponse;

@RequiredArgsConstructor
@Component
public class ProductMapper {
    public ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .productStatus(product.getStatus())
                .categoryInfo(ProductResponse.CategoryInfo.builder()
                        .id(product.getCategory().getId())
                        .name(product.getCategory().getName())
                        .description(product.getCategory().getDescription())
                        .build())
                .build();
    }

    public Product toEntity(CreateProductRequest request, ProductStatus status, Category category) {
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stockQuantity(request.getStockQuantity())
                .status(status)
                .category(Category.builder()
                        .id(category.getId())
                        .name(category.getName())
                        .description(category.getDescription())
                        .build())
                .build();
    }
}
