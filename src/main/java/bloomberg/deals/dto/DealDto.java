package bloomberg.deals.dto;

import bloomberg.deals.validation.annotation.Currency;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * The data-transfer object for {@link bloomberg.deals.model.Deal}
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class DealDto {
    /**
     * The deal's unique ID.
     */
    @Id
    @NotEmpty
    String id;
    /**
     * The ISO code of the currency from which the amount is being exchanged.
     */
    @NotEmpty
    @Currency
    String fromCurrencyIsoCode;
    /**
     * The ISO code of the currency to exchanged to.
     */
    @NotEmpty
    @Currency
    String toCurrencyIsoCode;
    /**
     * The amount being exchanged, in the fromCurrencyIsoCode currency.
     */
    @NotNull
    @Positive
    double amountInFromCurrency;
}
