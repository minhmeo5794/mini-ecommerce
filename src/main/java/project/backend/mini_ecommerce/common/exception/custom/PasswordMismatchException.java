package project.backend.mini_ecommerce.common.exception.custom;

public class PasswordMismatchException extends RuntimeException {
    public PasswordMismatchException(String message) {
        super(message);
    }
}
