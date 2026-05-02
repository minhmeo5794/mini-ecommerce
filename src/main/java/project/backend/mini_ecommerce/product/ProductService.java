package project.backend.mini_ecommerce.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.backend.mini_ecommerce.common.response.PageResponse;
import project.backend.mini_ecommerce.product.dto.ProductResponse;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public PageResponse<ProductResponse> getAllProducts(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        Page<ProductResponse> products = productRepository.findAll(pageable).map(productMapper::mapToProductResponse);


//        Page<Product> products;
//        if (minPrice == null || maxPrice == null) {
//            products = productRepository.findAll(pageable);
//        } else {
//            products = productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
//        }

        return PageResponse.from(products);
    }
}
