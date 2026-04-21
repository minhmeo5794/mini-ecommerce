package project.backend.mini_ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.backend.mini_ecommerce.dto.request.RegisterRequest;
import project.backend.mini_ecommerce.exception.custom.DuplicateResourceException;
import project.backend.mini_ecommerce.exception.custom.PasswordMismatchException;
import project.backend.mini_ecommerce.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;

    public void request(RegisterRequest request) {
        // Check existedEmail
        boolean isExists = userRepository.existsByEmail(request.getEmail());
        if (isExists) {
            throw new DuplicateResourceException("Email already exists");
        }

        // Check confirmPassword
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new PasswordMismatchException("Confirm password does not match");
        }

        // Hash password


        // return RegisterResponse
    }
}
