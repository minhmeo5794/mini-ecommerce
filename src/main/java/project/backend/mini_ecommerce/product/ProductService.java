package project.backend.mini_ecommerce.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import project.backend.mini_ecommerce.common.enums.ProductStatus;
import project.backend.mini_ecommerce.common.response.PageResponse;
import project.backend.mini_ecommerce.product.dto.ProductResponse;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public PageResponse<ProductResponse> getAllProducts(
            String q,
            String category,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            ProductStatus status,
            Pageable pageable) {
        Specification<Product> spec = Specification
                .where(ProductSpecification.hasQuery(q))
                .and(ProductSpecification.hasCategoryName(category))
                .and(ProductSpecification.priceGte(minPrice))
                .and(ProductSpecification.priceLte(maxPrice))
                .and(ProductSpecification.hasProductStatus(status));

        Page<ProductResponse> products = productRepository.findAll(spec, pageable).map(productMapper::mapToProductResponse);


        return PageResponse.from(products);
    }
}
