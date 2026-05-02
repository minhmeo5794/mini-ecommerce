package project.backend.mini_ecommerce.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import project.backend.mini_ecommerce.product.dto.ProductResponse;

@RequiredArgsConstructor
@Component
public class ProductMapper {
    public ProductResponse mapToProductResponse(Product product) {
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
}
