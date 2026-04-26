package project.backend.mini_ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.backend.mini_ecommerce.dto.response.ProductResponse;
import project.backend.mini_ecommerce.service.ProductService;

@RequiredArgsConstructor
@RequestMapping("/api/products")
@RestController
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        System.out.println("pageable: " + pageable);


        return productService.getAllProducts(pageable);
    }
}
