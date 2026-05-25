package project.backend.mini_ecommerce.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.mini_ecommerce.cart.dto.AddItemToCartRequest;
import project.backend.mini_ecommerce.cart.dto.CartResponse;
import project.backend.mini_ecommerce.cart_item.CartItemMapper;
import project.backend.mini_ecommerce.cart_item.CartItemRepository;
import project.backend.mini_ecommerce.cart_item.dto.CartItemResponse;
import project.backend.mini_ecommerce.common.exception.ResourceNotFoundException;
import project.backend.mini_ecommerce.helper.CurrentUserService;
import project.backend.mini_ecommerce.user.User;
import project.backend.mini_ecommerce.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;
    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;

    public CartResponse getMyCart() {
        Long currentUserId = currentUserService.getCurrentUserId();
        Cart cart = cartRepository.findByUserId(currentUserId).orElseGet(() -> createEmptyCart(currentUserId));

        List<CartItemResponse> cartItems = cartItemRepository.findByCartId(cart.getId()).stream().map(cartItemMapper::toCartItemResponse).toList();

        return cartMapper.toResponse(cart, cartItems);
    }

    public void addItemToCart(AddItemToCartRequest request) {

    }

    private Cart createEmptyCart(Long currentUserId) {
        User user = userRepository.findById(currentUserId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + currentUserId));

        Cart newCart = Cart.builder()
                .user(user)
                .cartItems(new ArrayList<>())
                .build();

        return cartRepository.save(newCart);
    }
}
