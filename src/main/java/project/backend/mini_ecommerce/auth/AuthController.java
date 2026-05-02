package project.backend.mini_ecommerce.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.backend.mini_ecommerce.auth.dto.LoginRequest;
import project.backend.mini_ecommerce.auth.dto.RegisterRequest;
import project.backend.mini_ecommerce.auth.dto.LoginResponse;
import project.backend.mini_ecommerce.auth.dto.RegisterResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody @Valid RegisterRequest request) {
        return authService.request(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }
}