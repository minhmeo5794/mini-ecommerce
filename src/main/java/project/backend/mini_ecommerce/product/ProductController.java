package project.backend.mini_ecommerce.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.backend.mini_ecommerce.product.dto.ProductResponse;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RequestMapping("/api/products")
@RestController
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<ProductResponse> getAllProducts(
//            @RequestParam(required = true) String category,
            @RequestParam(required = true) BigDecimal minPrice,
            @RequestParam(required = true) BigDecimal maxPrice,
            Pageable pageable
    ) {
        System.out.println("pageable: " + pageable.toString());
        System.out.println("getPageNumber: " + pageable.getPageNumber());
        System.out.println("getPageSize: " + pageable.getPageSize());
        System.out.println("getOffset: " + pageable.getOffset());


        return productService.getAllProducts(minPrice, maxPrice, pageable);
    }
}
