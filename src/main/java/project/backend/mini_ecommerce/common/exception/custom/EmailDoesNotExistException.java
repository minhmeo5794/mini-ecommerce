package project.backend.mini_ecommerce.common.exception.custom;

public class EmailDoesNotExistException extends RuntimeException {
    public EmailDoesNotExistException(String message) {
        super(message);
    }
}
