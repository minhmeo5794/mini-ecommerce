package project.backend.mini_ecommerce.cart;

import org.springframework.stereotype.Component;
import project.backend.mini_ecommerce.cart.dto.CartResponse;
import project.backend.mini_ecommerce.cart_item.CartItem;
import project.backend.mini_ecommerce.cart_item.dto.CartItemResponse;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CartMapper {
    public CartResponse toResponse(Cart cart, List<CartItemResponse> cartItems) {
        BigDecimal totalAmount = cart.getCartItems()
                .stream()
                .map(item -> item.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Integer totalItems = cart.getCartItems()
                .stream()
                .mapToInt(CartItem::getQuantity)
                .sum();

        return CartResponse.builder()
                .id(cart.getId())
                .userId(cart.getUser().getId())
                .totalAmount(totalAmount)
                .totalItems(totalItems)
                .cartItems(cartItems)
                .build();
    }
}
