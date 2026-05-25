package project.backend.mini_ecommerce.cart.dto;

import lombok.Builder;
import lombok.Getter;
import project.backend.mini_ecommerce.cart_item.dto.CartItemResponse;
import project.backend.mini_ecommerce.common.response.PageResponse;

import java.math.BigDecimal;

@Getter
@Builder
public class CartResponse {
    private final Long id;
    private final Long userId;
    private final BigDecimal totalAmount;
    private final Integer totalItems;
    private final PageResponse<CartItemResponse> cartItems;
}
