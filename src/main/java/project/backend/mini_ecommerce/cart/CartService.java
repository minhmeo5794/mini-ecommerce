package project.backend.mini_ecommerce.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.mini_ecommerce.cart.dto.AddItemToCartRequest;
import project.backend.mini_ecommerce.cart.dto.CartResponse;
import project.backend.mini_ecommerce.cart_item.CartItem;
import project.backend.mini_ecommerce.cart_item.CartItemMapper;
import project.backend.mini_ecommerce.cart_item.CartItemRepository;
import project.backend.mini_ecommerce.cart_item.dto.CartItemResponse;
import project.backend.mini_ecommerce.common.exception.ResourceNotFoundException;
import project.backend.mini_ecommerce.helper.CurrentUserService;
import project.backend.mini_ecommerce.product.Product;
import project.backend.mini_ecommerce.product.ProductRepository;
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
    private final ProductRepository productRepository;
    private final CurrentUserService currentUserService;
    private final CartMapper cartMapper;
    private final CartItemMapper cartItemMapper;

    public CartResponse getMyCart() {
        Long currentUserId = currentUserService.getCurrentUserId();
        Cart cart = cartRepository.findByUserId(currentUserId).orElseGet(() -> createEmptyCart(currentUserId));

        List<CartItemResponse> cartItems = cartItemRepository.findByCartId(cart.getId()).stream().map(cartItemMapper::toCartItemResponse).toList();

        return cartMapper.toResponse(cart, cartItems);
    }

    @Transactional
    public CartResponse addItemToCart(AddItemToCartRequest request) {
        Long currentUserId = currentUserService.getCurrentUserId();
        Cart cart = cartRepository.findByUserId(currentUserId).orElseGet(() -> createEmptyCart(currentUserId));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + request.getProductId()));

        CartItem cartItem = cartItemRepository.findByCartIdAndProductId(cart.getId(), request.getProductId())
                .orElseGet(() -> CartItem.builder()
                        .cart(cart)
                        .product(product)
                        .quantity(0)
                        .build()
                );

        cartItem.setQuantity(request.getQuantity() + cartItem.getQuantity());

        cartItemRepository.save(cartItem);

        List<CartItemResponse> cartItems = cartItemRepository.findByCartId(cart.getId()).stream().map(cartItemMapper::toCartItemResponse).toList();

        return cartMapper.toResponse(cart, cartItems);
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
