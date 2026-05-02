package project.backend.mini_ecommerce.common.exception.custom;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
