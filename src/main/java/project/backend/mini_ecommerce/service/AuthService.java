package project.backend.mini_ecommerce.service;

import org.springframework.stereotype.Service;
import project.backend.mini_ecommerce.dto.request.RegisterRequest;
import project.backend.mini_ecommerce.exception.custom.PasswordMismatchException;

@Service
public class AuthService {
    public void request(RegisterRequest request) {
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new PasswordMismatchException("Confirm password does not match");
        }

    }
}
