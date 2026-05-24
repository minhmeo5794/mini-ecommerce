package project.backend.mini_ecommerce.cart;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.backend.mini_ecommerce.cart.dto.CartResponse;
import project.backend.mini_ecommerce.common.response.ApiResponse;

@RequiredArgsConstructor
@RequestMapping("/api/carts")
@RestController
public class CartController {
    private final CartService cartService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<CartResponse>> getMyCart(Pageable pageable, HttpServletRequest httpServletRequest) {

        return ResponseEntity.ok().body(ApiResponse.success(
                cartService.getMyCart(pageable),
                "Cart retrieved successfully",
                HttpStatus.OK.value(),
                httpServletRequest
        ));
    }
}
