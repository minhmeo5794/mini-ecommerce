package project.backend.mini_ecommerce.product;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.mini_ecommerce.common.enums.ProductStatus;
import project.backend.mini_ecommerce.common.response.ApiResponse;
import project.backend.mini_ecommerce.common.response.PageResponse;
import project.backend.mini_ecommerce.product.dto.ProductResponse;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RequestMapping("/api/products")
@RestController
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ProductResponse>>> getAllProducts(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) Long category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) ProductStatus status,
            Pageable pageable,
            HttpServletRequest httpServletRequest
    ) {

        return ResponseEntity.ok().body(
                ApiResponse.success(
                        productService.getAllProducts(q, category, minPrice, maxPrice, status, pageable),
                        "Products retrieved successfully",
                        HttpStatus.OK.value(),
                        httpServletRequest
                ));

    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductResponse>> getProduct(@PathVariable("productId") Long id, HttpServletRequest httpServletRequest) {

        return ResponseEntity.ok().body(
                ApiResponse.success(
                        productService.getProduct(id),
                        "Product retrieved successfully",
                        HttpStatus.OK.value(),
                        httpServletRequest
                ));
    }
}
