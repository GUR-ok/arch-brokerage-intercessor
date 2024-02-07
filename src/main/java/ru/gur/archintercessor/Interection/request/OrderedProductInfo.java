package ru.gur.archintercessor.Interection.request;

import lombok.Builder;
import lombok.Value;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.util.UUID;

@Value
@Builder
public class OrderedProductInfo {

    @NotNull(message = "Id can not be null")
    UUID id;

    @NotNull(message = "Quantity can not be null")
    @Positive(message = "Quantity can not be negative")
    Integer quantity;
}
