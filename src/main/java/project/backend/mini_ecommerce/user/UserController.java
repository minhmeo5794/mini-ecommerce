package project.backend.mini_ecommerce.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.backend.mini_ecommerce.common.response.ApiResponse;
import project.backend.mini_ecommerce.user.dto.UserResponse;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserResponse>> getMe(HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok().body(
                ApiResponse.success(
                        userService.getMe(),
                        "Current user retrieved successfully",
                        HttpStatus.OK.value(),
                        httpServletRequest
                ));
    }
}
