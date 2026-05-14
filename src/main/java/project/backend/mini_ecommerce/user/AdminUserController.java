package project.backend.mini_ecommerce.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.backend.mini_ecommerce.auth.AuthService;
import project.backend.mini_ecommerce.auth.dto.RegisterRequest;
import project.backend.mini_ecommerce.auth.dto.RegisterResponse;
import project.backend.mini_ecommerce.common.response.ApiResponse;
import project.backend.mini_ecommerce.common.response.PageResponse;
import project.backend.mini_ecommerce.user.dto.CreateUserRequest;
import project.backend.mini_ecommerce.user.dto.UserResponse;

@RequiredArgsConstructor
@RequestMapping("/api/admin/users")
@RestController
public class AdminUserController {
    private final UserService userService;
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<UserResponse>>> getAllUsers(
            @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok().body(
                ApiResponse.success(
                        userService.getAllUsers(pageable),
                        "Users retrieved successfully",
                        HttpStatus.OK.value(),
                        httpServletRequest
                ));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserResponse>> getUser(@PathVariable("userId") Long id, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok().body(
                ApiResponse.success(
                        userService.getUser(id),
                        "User retrieved successfully",
                        HttpStatus.OK.value(),
                        httpServletRequest
                ));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody @Valid CreateUserRequest request, HttpServletRequest httpServletRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.success(
                        userService.createUser(request),
                        "User created successfully",
                        HttpStatus.CREATED.value(),
                        httpServletRequest
                ));
    }
}
