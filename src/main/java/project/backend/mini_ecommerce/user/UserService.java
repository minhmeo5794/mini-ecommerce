package project.backend.mini_ecommerce.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.backend.mini_ecommerce.common.exception.ResourceNotFoundException;
import project.backend.mini_ecommerce.common.exception.custom.EmailAlreadyExistsException;
import project.backend.mini_ecommerce.common.response.PageResponse;
import project.backend.mini_ecommerce.user.dto.CreateUserRequest;
import project.backend.mini_ecommerce.user.dto.UserResponse;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public PageResponse<UserResponse> getAllUsers(Pageable pageable) {
        Page<UserResponse> users = userRepository.findAll(pageable).map(userMapper::toResponse);

        return PageResponse.from(users);
    }

    public UserResponse getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        return userMapper.toResponse(user);
    }

    public UserResponse createUser(CreateUserRequest request) {
        // Check email already exists
        boolean doesEmailExist = userRepository.existsByEmail(request.getEmail());

        if (doesEmailExist) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        // System auto generate default password & hash password
        String encodedPassword = passwordEncoder.encode("123456");

        // Create a new user
        User user = userMapper.toEntity(request, encodedPassword);

        // Save to database
        User savedUser = userRepository.save(user);

        return userMapper.toResponse(savedUser);
    }
}
