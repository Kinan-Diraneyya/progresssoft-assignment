package bloomberg.deals.configuration;

import bloomberg.deals.exception.RequestException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Handles exceptions thrown due to validation failures in controller method parameters
     * annotated with {@link jakarta.validation.Valid} or {@link org.springframework.validation.annotation.Validated}.
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
     * Handles exceptions thrown by business logic that must be transmitted to the user.
     *
     * @param ex The exception instance caught by this handler.
     * @return The business error to show to the user.
     */
    @ExceptionHandler(RequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleExceptions(RequestException ex) {
        return ex.getResponseMessage();
    }

    /**
     * Provides a generic handler for all other exceptions that may occur in the application, which are not
     * explicitly handled by other exception handler methods.
     *
     * @param ex The exception instance caught by this handler.
     * @return A generic 500 error.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleExceptions(Exception ex) {
        log.error("The request failed with the following exception: {}", ex.toString());
        return "Unknown error";
    }
}
