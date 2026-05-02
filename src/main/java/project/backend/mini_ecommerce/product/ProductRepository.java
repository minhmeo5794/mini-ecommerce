package project.backend.mini_ecommerce.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategory_Name(String name, Pageable pageable);
    Page<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
}
