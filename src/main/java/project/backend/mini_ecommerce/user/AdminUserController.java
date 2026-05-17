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
import project.backend.mini_ecommerce.common.response.ApiResponse;
import project.backend.mini_ecommerce.common.response.PageResponse;
import project.backend.mini_ecommerce.user.dto.CreateUserRequest;
import project.backend.mini_ecommerce.user.dto.UpdateUserStatusRequest;
import project.backend.mini_ecommerce.user.dto.UserResponse;

@RequiredArgsConstructor
@RequestMapping("/api/admin/users")
@RestController
public class AdminUserController {
    private final UserService userService;

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

    @PatchMapping("/{userId}/status")
    public ResponseEntity<ApiResponse<UserResponse>> updateUserStatus(@PathVariable("userId") Long id, @RequestBody @Valid UpdateUserStatusRequest request, HttpServletRequest httpServletRequest) {
        userService.updateUserStatus(id, request);

        return ResponseEntity.ok().body(
                ApiResponse.success(
                        null,
                        "User status changed successfully",
                        HttpStatus.OK.value(),
                        httpServletRequest
                ));
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long id, HttpServletRequest httpServletRequest) {
        userService.deleteUser(id);
    }
}
