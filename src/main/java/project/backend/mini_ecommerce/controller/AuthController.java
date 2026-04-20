package project.backend.mini_ecommerce.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import project.backend.mini_ecommerce.dto.request.RegisterRequest;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @PostMapping("/register")
    public String register(@RequestBody @Valid RegisterRequest request) {
        throw new RuntimeException();
    }

}