package project.backend.mini_ecommerce.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateUserRequest {
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email can not be blank")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private final String email;

    @NotBlank(message = "Full name can not be blank")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    private final String fullName;

    @Size(max = 20, message = "Phone number must not exceed 20 characters")
    private final String phoneNumber;
}
