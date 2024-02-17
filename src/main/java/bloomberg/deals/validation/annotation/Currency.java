package bloomberg.deals.validation.annotation;

import java.lang.annotation.*;

import bloomberg.deals.validation.validator.CurrencyValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * The {@code Currency} annotation is used to validate that a given string field
 * of an annotated element is a valid ISO 4217 currency code.
 * Example usage:
 * <pre>
 * public class Payment {
 *     {@code @Currency}
 *     private String currencyCode;
 * }
 * </pre>
 *
 * @see CurrencyValidator
 */
@Documented
@Constraint(validatedBy = CurrencyValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Currency {

    /**
     * The message to return when the currency code is invalid.
     *
     * @return the error message
     */
    String message() default "Must be a valid currency code";

    /**
     * Allows specifying validation groups with which the constraint declaration is associated.
     *
     * @return the groups with which the constraint is associated
     */
    Class<?>[] groups() default {};

    /**
     * Can be used by clients of the Bean Validation API to assign custom payload objects
     * to a constraint. This attribute is not used by the API itself.
     *
     * @return the payload with which the constraint is associated
     */
    Class<? extends Payload>[] payload() default {};
}