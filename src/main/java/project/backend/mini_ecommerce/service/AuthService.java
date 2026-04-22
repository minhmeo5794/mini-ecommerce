package project.backend.mini_ecommerce.service;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.mini_ecommerce.dto.request.RegisterRequest;
import project.backend.mini_ecommerce.dto.response.RegisterResponse;
import project.backend.mini_ecommerce.exception.custom.DuplicateResourceException;
import project.backend.mini_ecommerce.exception.custom.PasswordMismatchException;
import project.backend.mini_ecommerce.model.User;
import project.backend.mini_ecommerce.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse request(RegisterRequest request) {
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
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // Create a new user
        User user = User.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(encodedPassword)
                .build();

        // Save to database
        User savedUser = userRepository.save(user);

        return RegisterResponse.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .fullName(savedUser.getFullName())
                .createdAt(savedUser.getCreatedAt())
                .build();
    }
}
