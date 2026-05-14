package project.backend.mini_ecommerce.user;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.backend.mini_ecommerce.common.response.ApiResponse;
import project.backend.mini_ecommerce.common.response.PageResponse;
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
}
