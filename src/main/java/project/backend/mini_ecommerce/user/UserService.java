package project.backend.mini_ecommerce.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.backend.mini_ecommerce.common.response.PageResponse;
import project.backend.mini_ecommerce.user.dto.UserResponse;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public PageResponse<UserResponse> getAllUsers(Pageable pageable) {
        Page<UserResponse> users = userRepository.findAll(pageable).map(userMapper::toResponse);

        return PageResponse.from(users);
    }
}
