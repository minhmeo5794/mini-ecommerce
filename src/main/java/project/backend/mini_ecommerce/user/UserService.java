package project.backend.mini_ecommerce.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.mini_ecommerce.common.enums.UserRole;
import project.backend.mini_ecommerce.common.enums.UserStatus;
import project.backend.mini_ecommerce.common.exception.ResourceNotFoundException;
import project.backend.mini_ecommerce.common.exception.custom.BusinessException;
import project.backend.mini_ecommerce.common.exception.custom.EmailAlreadyExistsException;
import project.backend.mini_ecommerce.common.response.PageResponse;
import project.backend.mini_ecommerce.helper.CurrentUserService;
import project.backend.mini_ecommerce.user.dto.CreateUserRequest;
import project.backend.mini_ecommerce.user.dto.UpdateUserStatusRequest;
import project.backend.mini_ecommerce.user.dto.UserResponse;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUserService currentUserService;

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

    @Transactional
    public void updateUserStatus(Long userId, UpdateUserStatusRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        UserStatus requestStatus = request.getStatus();

        if (user.getRole() == UserRole.ADMIN) {
            throw new BusinessException("You cannot lock another admin");
        }

        if (currentUserService.getCurrentUserId().equals(userId)) {
            throw new BusinessException("You cannot change your own status");
        }

        if (user.getStatus() == requestStatus) {
            return;
        }

        user.setStatus(request.getStatus());

        userRepository.save(user);
    }

    public void deleteUser(Long userId) {

    }

    public UserResponse getMe() {
        User user = currentUserService.getCurrentUser();

        return userMapper.toResponse(user);
    }
}
