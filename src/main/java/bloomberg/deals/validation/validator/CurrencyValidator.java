package bloomberg.deals.validation.validator;

import bloomberg.deals.validation.annotation.Currency;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Implements the validation logic for checking if a given string value is a valid
 * ISO 4217 currency code. This validator is used in conjunction with the {@link Currency}
 * annotation.
 * Example of a field that must be a valid, non-null currency code:
 * <pre>
 *     {@code @NotNull @Currency}
 *     private String currencyCode;
 * </pre>
 *
 * @see Currency
 */
public class CurrencyValidator implements ConstraintValidator<Currency, String> {

    /**
     * Validates whether the provided {@code String} value is a valid ISO 4217 currency code.
     *
     * @param value   the value to validate
     * @param context the context in which the constraint is evaluated
     * @return {@code true} if the value is a valid ISO 4217 currency code or {@code null},
     *         {@code false} otherwise
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        try {
            java.util.Currency.getInstance(value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}