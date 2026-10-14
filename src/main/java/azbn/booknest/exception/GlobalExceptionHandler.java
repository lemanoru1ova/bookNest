package azbn.booknest.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            BookNotFoundException.class,
            CategoryNotFoundException.class,
            AuthorNotFoundException.class,
            MemberNotFoundException.class,
            LoanNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFound(
            RuntimeException ex, HttpServletRequest request) {
        return build(HttpStatus.NOT_FOUND, "Not Found", ex.getMessage(), request, null);
    }

    @ExceptionHandler({
            DuplicateIsbnException.class,
            DuplicateEmailException.class,
            DuplicateNameException.class
    })
    public ResponseEntity<ErrorResponse> handleConflict(
            RuntimeException ex, HttpServletRequest request) {
        return build(HttpStatus.CONFLICT, "Conflict", ex.getMessage(), request, null);
    }

    @ExceptionHandler(BookNotAvailableException.class)
    public ResponseEntity<ErrorResponse> handleNotAvailable(
            BookNotAvailableException ex, HttpServletRequest request) {
        return build(HttpStatus.BAD_REQUEST, "Bad Request", ex.getMessage(), request, null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return build(HttpStatus.BAD_REQUEST, "Validation Failed",
                "Bir və ya bir neçə sahə yanlışdır", request, errors);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(
            Exception ex, HttpServletRequest request) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error",
                ex.getMessage(), request, null);
    }

    private ResponseEntity<ErrorResponse> build(HttpStatus status, String error,
                                                String message, HttpServletRequest request,
                                                Map<String, String> validationErrors) {
        ErrorResponse body = new ErrorResponse(
                status.value(), error, message, request.getRequestURI(), validationErrors);
        return ResponseEntity.status(status).body(body);
    }
}