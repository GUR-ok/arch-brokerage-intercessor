package ru.gur.archintercessor.interaction.productdictionary.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductInfo {

    private String productName;

    private Double rate;

    private Boolean active;
}
