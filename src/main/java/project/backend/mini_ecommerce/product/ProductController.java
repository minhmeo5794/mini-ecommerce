package project.backend.mini_ecommerce.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import project.backend.mini_ecommerce.common.enums.ProductStatus;
import project.backend.mini_ecommerce.common.response.PageResponse;
import project.backend.mini_ecommerce.product.dto.ProductResponse;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RequestMapping("/api/products")
@RestController
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public PageResponse<ProductResponse> getAllProducts(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) ProductStatus status,
            Pageable pageable
    ) {

        return productService.getAllProducts(
                q,
                category,
                minPrice,
                maxPrice,
                status,
                pageable
        );
    }

    @GetMapping("/{productId}")
    public ProductResponse getProduct(@PathVariable("productId") Long id) {
        return productService.getProduct(id);
    }
}
