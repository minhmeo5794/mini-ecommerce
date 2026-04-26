package project.backend.mini_ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.backend.mini_ecommerce.dto.response.ProductResponse;
import project.backend.mini_ecommerce.mapper.ProductMapper;
import project.backend.mini_ecommerce.model.Product;
import project.backend.mini_ecommerce.repository.ProductRepository;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Page<ProductResponse> getAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);

        return products.map(productMapper::mapToProductResponse);
    }
}
