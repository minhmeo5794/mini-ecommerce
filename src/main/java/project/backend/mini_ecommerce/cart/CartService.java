package project.backend.mini_ecommerce.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.backend.mini_ecommerce.cart.dto.CartResponse;
import project.backend.mini_ecommerce.cart_item.CartItemMapper;
import project.backend.mini_ecommerce.cart_item.CartItemRepository;
import project.backend.mini_ecommerce.cart_item.dto.CartItemResponse;
import project.backend.mini_ecommerce.common.exception.ResourceNotFoundException;
import project.backend.mini_ecommerce.helper.CurrentUserService;
import project.backend.mini_ecommerce.user.User;
import project.backend.mini_ecommerce.user.UserRepository;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;
    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;

    public CartResponse getMyCart(Pageable pageable) {
        Long currentUserId = currentUserService.getCurrentUserId();
        Cart cart = cartRepository.findByUserId(currentUserId).orElseGet(() -> {
            // Nếu chưa có Cart thì tạo Cart
            User user = userRepository.findById(currentUserId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + currentUserId));

            Cart newCart = Cart.builder()
                    .user(user)
                    .cartItems(new ArrayList<>())
                    .build();

            return cartRepository.save(newCart);
        });

        Page<CartItemResponse> cartItems = cartItemRepository.findByCartId(cart.getId(), pageable).map(cartItemMapper::toCartItemResponse);

        return cartMapper.toResponse(cart, cartItems);
    }
}
