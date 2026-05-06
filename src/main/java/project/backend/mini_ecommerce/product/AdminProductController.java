package project.backend.mini_ecommerce.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.mini_ecommerce.product.dto.CreateProductRequest;
import project.backend.mini_ecommerce.product.dto.ProductResponse;
import project.backend.mini_ecommerce.product.dto.UpdateProductRequest;


@RequiredArgsConstructor
@RequestMapping("/api/admin/products")
@RestController
public class AdminProductController {
    private final ProductService productService;

    @PostMapping
    public ProductResponse createProduct(@RequestBody @Valid CreateProductRequest request) {
        return productService.createProduct(request);
    }

    @PatchMapping("/{productId}")
    public ProductResponse updateProduct(@PathVariable("productId") Long id, @RequestBody @Valid UpdateProductRequest request) {
        return productService.updateProduct(id, request);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
