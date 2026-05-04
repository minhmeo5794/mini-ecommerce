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

            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("name")),
                    "%" + q.toLowerCase() + "%"
            );
        };
    }

    public static Specification<Product> hasCategoryName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isBlank()) {
                return null;
            }

            return criteriaBuilder.equal(root.get("category").get("name"), name);
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

    public static Specification<Product> hasProductStatus(ProductStatus status) {
        return (root, query, criteriaBuilder) -> {
            if (status == null) {
                return null;
            }

            return criteriaBuilder.equal(root.get("status"), status);
        };
    }
}
