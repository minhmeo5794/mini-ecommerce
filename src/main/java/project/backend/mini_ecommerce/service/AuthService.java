package project.backend.mini_ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.backend.mini_ecommerce.dto.request.LoginRequest;
import project.backend.mini_ecommerce.dto.request.RegisterRequest;
import project.backend.mini_ecommerce.dto.response.LoginResponse;
import project.backend.mini_ecommerce.dto.response.RegisterResponse;
import project.backend.mini_ecommerce.exception.custom.EmailAlreadyExistsException;
import project.backend.mini_ecommerce.exception.custom.EmailDoesNotExistException;
import project.backend.mini_ecommerce.exception.custom.PasswordMismatchException;
import project.backend.mini_ecommerce.mapper.AuthMapper;
import project.backend.mini_ecommerce.model.User;
import project.backend.mini_ecommerce.repository.UserRepository;
import project.backend.mini_ecommerce.security.CustomUserDetails;
import project.backend.mini_ecommerce.security.JwtService;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public RegisterResponse request(RegisterRequest request) {
        // Check existedEmail
        boolean isExisted = userRepository.existsByEmail(request.getEmail());
        if (isExisted) {
            throw new EmailAlreadyExistsException("Email already exists");
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

        return AuthMapper.mapToRegisterResponse(savedUser);
    }

    public LoginResponse login(LoginRequest request) {
        // Check email and get user if it's exists
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new EmailDoesNotExistException("Invalid email or password"));

        // Compare password from user with the one in database
        boolean isMatched = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if (!isMatched) {
            throw new PasswordMismatchException("Invalid email or password");
        }

        // Generate JWT token
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("id", user.getId());
        extraClaims.put("role", user.getRole().name());

        String token = jwtService.generateToken(extraClaims, customUserDetails);
        long expiration = jwtService.getJwtExpiration();

        return AuthMapper.mapToLoginResponse(user, token, expiration);
    }
}
