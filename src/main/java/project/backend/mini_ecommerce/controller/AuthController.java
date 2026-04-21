package project.backend.mini_ecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.backend.mini_ecommerce.dto.request.RegisterRequest;
import project.backend.mini_ecommerce.service.AuthService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterRequest request) {
        authService.request(request);

        return "hello";
    }

}