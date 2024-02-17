package bloomberg.deals.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "deals")
public class Deal {
    @Id
    @NotEmpty
    String id;
    @NotEmpty
    String fromCurrencyIsoCode;
    @NotEmpty
    String toCurrencyIsoCode;
    @NotNull
    LocalDateTime timeStamp;
    @NotNull
    @Positive
    double amountInFromCurrency;
}
