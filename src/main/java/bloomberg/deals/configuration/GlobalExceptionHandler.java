package bloomberg.deals.configuration;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

/**
 * GlobalExceptionHandler provides a centralized exception handling mechanism across the entire application,
 * allowing for consistent responses to various exceptions that may occur during HTTP request processing.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles exceptions thrown due to validation failures in controller method parameters
     * annotated with {@link javax.validation.Valid} or {@link org.springframework.validation.annotation.Validated}.
     * It extracts field error details from the {@link MethodArgumentNotValidException} and formats them into
     * a map of field names to error messages.
     *
     * @param ex The exception instance containing validation error details.
     * @return A map where each key is a field name causing the validation error, and the value is the validation error message.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    /**
     * Provides a generic handler for all other exceptions that may occur in the application, which are not
     * explicitly handled by other exception handler methods.
     *
     * @param ex The exception instance caught by this handler.
     * @return A simple string representation of the exception, intended for logging purposes rather than being exposed directly to clients.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleExceptions(Exception ex) {
        return ex.toString();
    }
}
