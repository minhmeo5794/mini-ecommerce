package project.backend.mini_ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.backend.mini_ecommerce.dto.request.LoginRequest;
import project.backend.mini_ecommerce.dto.request.RegisterRequest;
import project.backend.mini_ecommerce.dto.response.LoginResponse;
import project.backend.mini_ecommerce.dto.response.RegisterResponse;
import project.backend.mini_ecommerce.service.AuthService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
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

    @GetMapping("/test")
    public String test() {
        return "hello test";
    }
}