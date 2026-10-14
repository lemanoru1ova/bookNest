package azbn.booknest.exception;

public class AuthorNotFoundException extends RuntimeException {
    public AuthorNotFoundException(Long id) {
        super("Author not found with id: " + id);
    }
    public AuthorNotFoundException(String message) {
        super(message);
    }
}