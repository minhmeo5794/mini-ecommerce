package project.backend.mini_ecommerce.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import project.backend.mini_ecommerce.dto.response.ProductResponse;
import project.backend.mini_ecommerce.model.Product;

import java.util.ArrayList;
import java.util.List;

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
                .status(product.getStatus())
                .categoryInfo(ProductResponse.CategoryInfo.builder()
                        .id(product.getCategory().getId())
                        .name(product.getCategory().getName())
                        .description(product.getCategory().getDescription())
                        .build())
                .build();
    }
}
