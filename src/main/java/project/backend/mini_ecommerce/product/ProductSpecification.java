package project.backend.mini_ecommerce.product;

import org.springframework.data.jpa.domain.Specification;
import project.backend.mini_ecommerce.common.enums.ProductStatus;

import java.math.BigDecimal;

public class ProductSpecification {
    public static Specification<Product> hasQuery(String q) {
        return (root, query, criteriaBuilder) -> {
            if (q == null || q.isBlank()) {
                return null;
            }

            String keyword = q.trim().toLowerCase();

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")),
                    "%" + keyword + "%"
            );
        };
    }

    public static Specification<Product> hasCategoryId(Long categoryId) {
        return (root, query, criteriaBuilder) -> {
            if (categoryId == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("category").get("id"), categoryId);
        };
    }

    public static Specification<Product> priceGte(BigDecimal minPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null) {
                return null;
            }

            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
        };
    }

    public static Specification<Product> priceLte(BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (maxPrice == null) {
                return null;
            }

            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }

    public static Specification<Product> hasStatus(ProductStatus status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("status"), status);
        };
    }
}
