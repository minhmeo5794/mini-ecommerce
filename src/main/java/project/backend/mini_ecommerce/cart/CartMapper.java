package project.backend.mini_ecommerce.cart;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import project.backend.mini_ecommerce.cart.dto.CartResponse;
import project.backend.mini_ecommerce.cart_item.dto.CartItemResponse;
import project.backend.mini_ecommerce.common.response.PageResponse;

import java.math.BigDecimal;

@Component
public class CartMapper {
    public CartResponse toResponse(Cart cart, Page<CartItemResponse> cartItems) {
        BigDecimal totalAmount = cart.getCartItems()
                .stream()
                .map(item -> item.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return CartResponse.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .totalAmount(totalAmount)
                .cartItems(PageResponse.from(cartItems))
                .build();
    }
}
