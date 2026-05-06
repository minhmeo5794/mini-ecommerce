package project.backend.mini_ecommerce.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import project.backend.mini_ecommerce.category.Category;
import project.backend.mini_ecommerce.category.CategoryRepository;
import project.backend.mini_ecommerce.common.enums.ProductStatus;
import project.backend.mini_ecommerce.common.exception.BadRequestException;
import project.backend.mini_ecommerce.common.exception.ResourceNotFoundException;
import project.backend.mini_ecommerce.common.exception.custom.BusinessException;
import project.backend.mini_ecommerce.common.response.PageResponse;
import project.backend.mini_ecommerce.product.dto.CreateProductRequest;
import project.backend.mini_ecommerce.product.dto.ProductResponse;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public PageResponse<ProductResponse> getAllProducts(
            String q,
            Long category,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            ProductStatus status,
            Pageable pageable) {

        if (minPrice != null && maxPrice != null && minPrice.compareTo(maxPrice) > 0) {
            throw new BadRequestException("minPrice must be less than or equal to maxPrice");
        }

        Specification<Product> spec = Specification
                .where(ProductSpecification.hasQuery(q))
                .and(ProductSpecification.hasCategoryId(category))
                .and(ProductSpecification.priceGte(minPrice))
                .and(ProductSpecification.priceLte(maxPrice))
                .and(ProductSpecification.hasStatus(status));

        Page<ProductResponse> products = productRepository.findAll(spec, pageable).map(productMapper::toResponse);


        return PageResponse.from(products);
    }

    public ProductResponse getProduct(Long id) {
        return productMapper.toResponse(productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id)));
    }

    public ProductResponse createProduct(CreateProductRequest request) {
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + request.getCategoryId()));
        ProductStatus status = resolveProductStatusProblem(request.getStockQuantity(), request.getStatus());

        Product product = productMapper.toEntity(request, status, category);
        Product savedProduct = productRepository.save(product);

        return productMapper.toResponse(savedProduct);
    }

    private ProductStatus resolveProductStatus(Integer stockQuantity, ProductStatus requestedStatus) {
        if (requestedStatus == null) {
            return stockQuantity == 0 ? ProductStatus.OUT_OF_STOCK : ProductStatus.ACTIVE;
        }

        return requestedStatus;
    }

    private void validateProductStatus(Integer stockQuantity, ProductStatus requestedStatus) {
        if (stockQuantity == 0 && requestedStatus != ProductStatus.OUT_OF_STOCK) {
            throw new BusinessException("If stock quantity is 0, product status must be OUT_OF_STOCK");
        }

        if (requestStockQuantity > 0 && requestedStatus == ProductStatus.OUT_OF_STOCK) {
            throw new BusinessException("If stock quantity is not 0, product status must be either INACTIVE or ACTIVE");
        }

        return requestedStatus;
    }
}
