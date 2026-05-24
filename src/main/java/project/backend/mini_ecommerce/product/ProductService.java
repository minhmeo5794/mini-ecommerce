package project.backend.mini_ecommerce.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.mini_ecommerce.category.Category;
import project.backend.mini_ecommerce.category.CategoryRepository;
import project.backend.mini_ecommerce.common.enums.ProductStatus;
import project.backend.mini_ecommerce.common.exception.BadRequestException;
import project.backend.mini_ecommerce.common.exception.ResourceNotFoundException;
import project.backend.mini_ecommerce.common.exception.custom.BusinessException;
import project.backend.mini_ecommerce.common.response.PageResponse;
import project.backend.mini_ecommerce.product.dto.CreateProductRequest;
import project.backend.mini_ecommerce.product.dto.ProductResponse;
import project.backend.mini_ecommerce.product.dto.UpdateProductRequest;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public PageResponse<ProductResponse> getAllProducts(
            String q,
            Long categoryId,
            BigDecimal minPrice,
            BigDecimal maxPrice,
            ProductStatus status,
            Pageable pageable
    ) {
        if (minPrice != null && maxPrice != null && minPrice.compareTo(maxPrice) > 0) {
            throw new BadRequestException("minPrice must be less than or equal to maxPrice");
        }

        if (categoryId != null) {
            boolean hasCategory = categoryRepository.existsById(categoryId);
            if (!hasCategory) {
                throw new ResourceNotFoundException("Category not found with id: " + categoryId);
            }
        }

        Specification<Product> spec = Specification
                .where(ProductSpecification.hasQuery(q))
                .and(ProductSpecification.hasCategoryId(categoryId))
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
        ProductStatus status = resolveProductStatusProblemForCreate(request.getStockQuantity(), request.getStatus());

        Product product = productMapper.toEntity(request, status, category);
        Product savedProduct = productRepository.save(product);

        return productMapper.toResponse(savedProduct);
    }

    @Transactional
    public ProductResponse updateProduct(Long productId, UpdateProductRequest request) {
        // Kiểm tra xem product có tồn tại không
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product does not exist with id: " + productId));

        if (request.getName() != null) {
            product.setName(request.getName());
        }

        if (request.getDescription() != null) {
            product.setDescription(request.getDescription());
        }

        if (request.getPrice() != null) {
            product.setPrice(request.getPrice());
        }

        if (request.getStockQuantity() != null) {
            product.setStockQuantity(request.getStockQuantity());
        }

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category does not exist with id: " + request.getCategoryId()));

            product.setCategory(category);
        }

        ProductStatus finalProductStatus = resolveProductStatusProblemForUpdate(
                request.getStockQuantity(), product.getStockQuantity(), request.getStatus(), product.getStatus()
        );
        product.setStatus(finalProductStatus);

        Product savedProduct = productRepository.save(product);

        return productMapper.toResponse(savedProduct);
    }

    @Transactional
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        productRepository.delete(product);
    }

    private ProductStatus resolveProductStatusProblemForUpdate(
            Integer requestStockQuantity,
            Integer currentStockQuantity,
            ProductStatus requestProductStatus,
            ProductStatus currentProductStatus
    ) {
        Integer finalStockQuantity = requestStockQuantity != null ? requestStockQuantity : currentStockQuantity;

        if (finalStockQuantity == 0) {
            if (requestProductStatus != null && requestProductStatus != ProductStatus.OUT_OF_STOCK) {
                throw new BusinessException("If stock quantity is 0, product status must be OUT_OF_STOCK");
            }

            return ProductStatus.OUT_OF_STOCK;
        }

        if (requestProductStatus == ProductStatus.OUT_OF_STOCK) {
            throw new BusinessException("If stock quantity is not 0, product status must be either INACTIVE or ACTIVE");
        }

        if (requestProductStatus != null) {
            return requestProductStatus;
        }

        if (currentProductStatus == ProductStatus.OUT_OF_STOCK) {
            return ProductStatus.ACTIVE;
        }

        return currentProductStatus;
    }

    private ProductStatus resolveProductStatusProblemForCreate(Integer requestStockQuantity, ProductStatus requestedStatus) {
        if (requestedStatus == null) {
            return requestStockQuantity == 0 ? ProductStatus.OUT_OF_STOCK : ProductStatus.ACTIVE;
        }

        if (requestStockQuantity == 0 && requestedStatus != ProductStatus.OUT_OF_STOCK) {
            throw new BusinessException("If stock quantity is 0, product status must be OUT_OF_STOCK");
        }

        if (requestStockQuantity > 0 && requestedStatus == ProductStatus.OUT_OF_STOCK) {
            throw new BusinessException("If stock quantity is not 0, product status must be either INACTIVE or ACTIVE");
        }

        return requestedStatus;
    }
}
