package project.backend.mini_ecommerce.cart;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.mini_ecommerce.cart.dto.AddItemToCartRequest;
import project.backend.mini_ecommerce.cart.dto.CartResponse;
import project.backend.mini_ecommerce.common.response.ApiResponse;

@RequiredArgsConstructor
@RequestMapping("/api/carts")
@RestController
public class CartController {
    private final CartService cartService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<CartResponse>> getMyCart(HttpServletRequest httpServletRequest) {

        return ResponseEntity.ok().body(ApiResponse.success(
                cartService.getMyCart(),
                "Cart retrieved successfully",
                HttpStatus.OK.value(),
                httpServletRequest
        ));
    }

    @PostMapping("/me/items")
    public ResponseEntity<ApiResponse<CartResponse>> addItemToCart(@RequestBody @Valid AddItemToCartRequest request, HttpServletRequest httpServletRequest) {

        return ResponseEntity.ok().body(ApiResponse.success(
                cartService.addItemToCart(request),
                "Cart retrieved successfully",
                HttpStatus.OK.value(),
                httpServletRequest
        ));
    }
}
