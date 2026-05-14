package project.backend.mini_ecommerce.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.mini_ecommerce.auth.dto.LoginRequest;
import project.backend.mini_ecommerce.auth.dto.RegisterRequest;
import project.backend.mini_ecommerce.auth.dto.LoginResponse;
import project.backend.mini_ecommerce.auth.dto.RegisterResponse;
import project.backend.mini_ecommerce.common.response.ApiResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterResponse>> register(@RequestBody @Valid RegisterRequest request, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok().body(
                ApiResponse.success(
                        authService.register(request),
                        "User registered successfully",
                        HttpStatus.OK.value(),
                        httpServletRequest
                ));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest request, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok().body(
                ApiResponse.success(
                        authService.login(request),
                        "User logged in successfully",
                        HttpStatus.OK.value(),
                        httpServletRequest
                ));
    }
}