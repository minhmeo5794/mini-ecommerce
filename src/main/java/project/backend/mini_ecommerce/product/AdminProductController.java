package project.backend.mini_ecommerce.product;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.mini_ecommerce.common.response.ApiResponse;
import project.backend.mini_ecommerce.product.dto.CreateProductRequest;
import project.backend.mini_ecommerce.product.dto.ProductResponse;
import project.backend.mini_ecommerce.product.dto.UpdateProductRequest;


@RequiredArgsConstructor
@RequestMapping("/api/admin/products")
@RestController
public class AdminProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(@RequestBody @Valid CreateProductRequest request, HttpServletRequest httpServletRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.success(
                        productService.createProduct(request),
                        "Product created successfully",
                        HttpStatus.CREATED.value(),
                        httpServletRequest
                ));
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProduct(@PathVariable("productId") Long id, @RequestBody @Valid UpdateProductRequest request, HttpServletRequest httpServletRequest) {

        return ResponseEntity.ok().body(
                ApiResponse.success(
                        productService.updateProduct(id, request),
                        "Product updated successfully",
                        HttpStatus.OK.value(),
                        httpServletRequest
                ));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductResponse>> deleteProduct(@PathVariable("productId") Long id, HttpServletRequest httpServletRequest) {
        productService.deleteProduct(id);

        return ResponseEntity.ok().body(
                ApiResponse.success(
                        null,
                        "Product deleted successfully",
                        HttpStatus.OK.value(),
                        httpServletRequest
                ));
    }
}
