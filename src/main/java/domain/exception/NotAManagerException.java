package domain.exception;

public class NotAManagerException extends RuntimeException {
    public NotAManagerException(String message) {
        super(message);
    }
}
