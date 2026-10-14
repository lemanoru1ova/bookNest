package azbn.booknest.exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String email) {
        super("Member already exists with email: " + email);
    }
}