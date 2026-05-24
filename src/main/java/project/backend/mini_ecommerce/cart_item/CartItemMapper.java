package project.backend.mini_ecommerce.cart_item;

import org.springframework.stereotype.Component;
import project.backend.mini_ecommerce.cart_item.dto.CartItemResponse;

import java.math.BigDecimal;

@Component
public class CartItemMapper {
    public CartItemResponse toCartItemResponse(CartItem cartItem) {
        BigDecimal unitPrice = cartItem.getProduct().getPrice();

        BigDecimal lineTotal = unitPrice.multiply(BigDecimal.valueOf(cartItem.getQuantity()));

        return CartItemResponse.builder()
                .id(cartItem.getId())
                .productId(cartItem.getProduct().getId())
                .productName(cartItem.getProduct().getName())
                .unitPrice(unitPrice)
                .quantity(cartItem.getQuantity())
                .lineTotal(lineTotal)
                .build();
    }
}
