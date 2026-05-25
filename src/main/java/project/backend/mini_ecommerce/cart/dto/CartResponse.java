package project.backend.mini_ecommerce.cart.dto;

import lombok.Builder;
import lombok.Getter;
import project.backend.mini_ecommerce.cart_item.dto.CartItemResponse;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class CartResponse {
    private final Long id;
    private final Long userId;
    private final BigDecimal totalAmount;
    private final Integer totalItems;
    private final List<CartItemResponse> cartItems;
}
