package bloomberg.deals.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Represents an FX exchange transaction between two different currencies.
 */
@Data
@Entity
@Table(name = "deals")
public class Deal {
    /**
     * The deal's unique ID.
     * Note to reviewer: This ID is NOT auto generated, and that is intentional,
     * as it was what I inferred from the below constraint:
     * "System should not import same request twice."
     */
    @Id
    @NotEmpty
    String id;
    /**
     * The ISO code of the currency from which the amount is being exchanged.
     */
    @NotEmpty
    String fromCurrencyIsoCode;
    /**
     * The ISO code of the currency to exchanged to.
     */
    @NotEmpty
    String toCurrencyIsoCode;
    /**
     * The timestamp at which the exchange was made
     */
    @NotNull
    LocalDateTime timeStamp;
    /**
     * The amount being exchanged, in the fromCurrencyIsoCode currency.
     */
    @NotNull
    @Positive
    double amountInFromCurrency;
}
